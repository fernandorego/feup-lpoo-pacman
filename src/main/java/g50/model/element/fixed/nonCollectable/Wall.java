package g50.model.element.fixed.nonCollectable;

import g50.model.element.fixed.FixedElement;

public class Wall extends NonCollectable {
     public Wall(int x, int y) {
         super(x,y);
     }

    @Override
    public FixedElement generate(int x, int y) {
        return new Wall(x,y);
    }
}
