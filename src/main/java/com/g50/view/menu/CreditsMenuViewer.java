package com.g50.view.menu;

import com.g50.gui.GUI;
import com.g50.model.element.Position;
import com.g50.model.menu.CreditsMenu;

import java.io.IOException;

public class CreditsMenuViewer extends MenuViewer {

    public CreditsMenuViewer(CreditsMenu model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        gui.drawText(getModel().getTitle(), new Position(10, 5), "#FFFF00");

        for (int i = 0; i < ((CreditsMenu)getModel()).getLinesNumber(); i++) {
            if (!((CreditsMenu)getModel()).getText(i).equals(""))
                gui.drawText(((CreditsMenu)getModel()).getText(i), new Position(0, 10 + i));
        }

        gui.drawBlinkText(map.get(getModel().getEntry(0)), new Position(3, 30), "#FFFF00");

        gui.refresh();
    }
}
