package com.g50.model.menu;

import java.util.Collections;

public class GameOverMenu extends Menu {
    public GameOverMenu() {
        super("GAME OVER", Collections.singletonList(ENTRIES.RETURN_ENTER));
    }
}
