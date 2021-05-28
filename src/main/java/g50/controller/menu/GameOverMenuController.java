package g50.controller.menu;

import g50.Application;
import g50.controller.states.GameState;
import g50.controller.states.app_states.AppState;
import g50.gui.GUI;
import g50.model.menu.GameOverMenu;
import g50.view.menu.GameOverViewer;
import g50.view.menu.MenuViewer;

public class GameOverMenuController extends MenuController {

    public GameOverMenuController(GUI gui, GameOverMenu menu) {
        super(gui,new GameOverViewer(menu),menu);
    }

    @Override
    public void handleKBDAction(Application application, GUI.KBD_ACTION action) {
        if (action == GUI.KBD_ACTION.SELECT) {
            application.setState(AppState.MAIN_MENU);
        }
    }
}
