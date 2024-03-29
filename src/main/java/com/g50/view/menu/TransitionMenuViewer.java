package com.g50.view.menu;

import com.g50.gui.GUI;
import com.g50.model.menu.TransitionMenu;
import com.g50.view.ViewProperty;
import com.g50.view.builders.DefaultPacManViewerBuilder;

import java.io.IOException;

public class TransitionMenuViewer extends MenuViewer {
    DefaultPacManViewerBuilder pacmanBuilder;
    ViewProperty ghostViewer;

    public TransitionMenuViewer(TransitionMenu model) {
        super(model);
        this.pacmanBuilder = new DefaultPacManViewerBuilder();
        this.ghostViewer = new ViewProperty("#FF0000",(char)(200));
    }

    @Override
    public void draw(GUI gui) throws IOException {
        if (((TransitionMenu) getModel()).getGhost().getPosition().getX() < 0)
            this.ghostViewer = new ViewProperty("#0000FF",(char)(200));
        gui.clear();
        this.pacmanBuilder.getViewer(((TransitionMenu)getModel()).getPacMan()).draw(gui);
        gui.drawText(String.valueOf(ghostViewer.getCharacter()), ((TransitionMenu) getModel()).getGhost().getPosition(), ghostViewer.getColor());
        gui.refresh();
    }
}
