package com.g50.view.menu;

import com.g50.model.menu.Menu;
import com.g50.view.Viewer;

import java.util.HashMap;
import java.util.Map;

public abstract class MenuViewer extends Viewer<Menu> {

    public MenuViewer(Menu model) {
        super(model);
    }

    protected static final Map<Menu.ENTRIES, String> map = new HashMap<Menu.ENTRIES, String>() {{
        put(Menu.ENTRIES.START, "START");
        put(Menu.ENTRIES.CONTROLS, "CONTROLS");
        put(Menu.ENTRIES.HIGH_SCORE, "HIGH-SCORE");
        put(Menu.ENTRIES.CREDITS, "CREDITS");
        put(Menu.ENTRIES.EXIT, "EXIT");
        put(Menu.ENTRIES.RESUME, "RESUME");
        put(Menu.ENTRIES.RETURN_ENTER, "PRESS ENTER TO RETURN");
    }};
}
