package com.g50.controller.menu;

import com.g50.Application;
import com.g50.controller.Controller;
import com.g50.gui.GUI;
import com.g50.gui.GUIObserver;
import com.g50.model.menu.Menu;
import com.g50.view.menu.MenuViewer;

import java.io.IOException;

public abstract class MenuController extends Controller<Menu> implements GUIObserver {
    private final GUI gui;
    private final MenuViewer menuViewer;
    private GUI.KBD_ACTION lastAction;

    public MenuController(GUI gui, MenuViewer menuViewer, Menu menu) {
        super(menu);
        this.gui = gui;
        this.menuViewer = menuViewer;
        this.lastAction = GUI.KBD_ACTION.NONE;
    }

    public void addPendingKBDAction(GUI.KBD_ACTION action){
        lastAction = action;
    }

    @Override
    public void update(Application application, int frame) throws IOException {
        if (lastAction != GUI.KBD_ACTION.NONE){
            handleKBDAction(application, lastAction);
            lastAction = GUI.KBD_ACTION.NONE;
        }
        try {
            menuViewer.draw(gui);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void handleKBDAction(Application application, GUI.KBD_ACTION action);

    public MenuViewer getMenuViewer() {
        return menuViewer;
    }

    public GUI getGui() {
        return gui;
    }
}
