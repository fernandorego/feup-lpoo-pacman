package g50.controller.menu;

import g50.Application;
import g50.controller.states.GameState;
import g50.controller.states.app_states.AppState;
import g50.gui.GUI;
import g50.model.menu.CreditsMenu;
import g50.view.menu.CreditsMenuViewer;
import g50.view.menu.MenuViewer;

public class CreditsMenuController extends MenuController {

    public CreditsMenuController(GUI gui, CreditsMenu menu) {
        super(gui, new CreditsMenuViewer(menu), menu);
    }

    @Override
    public void notify(GameState state) {

    }

    @Override
    public void handleKBDAction(Application application, GUI.KBD_ACTION action) {
        if (action == GUI.KBD_ACTION.SELECT) {
            application.setState(AppState.MAIN_MENU);
        }
    }
}
