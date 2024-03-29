package com.g50.model.element.movable.ghost.strategy;

import com.g50.model.element.Position;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.ghost.BlinkyGhost;
import com.g50.model.element.movable.ghost.Ghost;
import com.g50.model.map.GameMap;

public class InkyStrategy extends GhostStrategy {
    private static final int defaultDotLimit = 30;
    private BlinkyGhost blinkyGhost;

    public InkyStrategy(BlinkyGhost blinkyGhost) {
        super(defaultDotLimit);
        this.blinkyGhost = blinkyGhost;
    }

    @Override
    protected Orientation inChase(GameMap map, Ghost ghost) {
        if (blinkyGhost == null){
            return super.inChase(map, ghost);
        }

        Position targetPos = map.getPacman().getPosition();
        Orientation pacmanOrientation = map.getPacman().getOrientation();

        for(int i = 0; i < 2; ++i) targetPos = targetPos.getAdjacent(pacmanOrientation);

        targetPos = new Position(2 * (targetPos.getX() - blinkyGhost.getPosition().getX()),
                2 * (targetPos.getY() - blinkyGhost.getPosition().getY()));

        targetPos = new Position(blinkyGhost.getPosition().getX() + targetPos.getX(), blinkyGhost.getPosition().getY() + targetPos.getY());
        return getBestOrientation(map, ghost, targetPos);
    }

    public void setBlinkyGhost(BlinkyGhost blinkyGhost) {
        this.blinkyGhost = blinkyGhost;
    }
}
