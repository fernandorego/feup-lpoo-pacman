package g50.model.element.fixed.nonCollectable;

import g50.model.Position;
import g50.model.element.fixed.FixedElement;

public class SpawnArea extends NonCollectable{

    public SpawnArea(Position position) {
        super(position);
    }

    @Override
    public FixedElement generate(Position position) {
        return new SpawnArea(position);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
