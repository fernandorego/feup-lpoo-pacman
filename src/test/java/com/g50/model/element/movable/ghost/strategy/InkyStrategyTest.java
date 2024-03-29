package com.g50.model.element.movable.ghost.strategy;

import com.g50.model.element.Position;
import com.g50.model.element.fixed.noncollectable.Target;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.ghost.BlinkyGhost;
import com.g50.model.element.movable.ghost.InkyGhost;
import com.g50.model.map.GameMap;
import com.g50.model.map.mapbuilder.FileGameMapBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class InkyStrategyTest {
    private Position p;
    private GameMap gameMap;
    private BlinkyGhost blinkyGhost;

    @BeforeEach
    public void initTest() throws IOException {
        int x = 10;
        int y = 15;
        p = new Position(x, y);
        blinkyGhost = new BlinkyGhost("blinky", new Position(18, 19), Orientation.LEFT, new Target(new Position(5, 5), "target"));
        gameMap = new FileGameMapBuilder("src/main/resources/maps/default.txt").getBuild();
    }

    @Test
    public void testNextOrientationCall(){
        InkyStrategy inkyStrategy = new InkyStrategy(blinkyGhost);
        InkyGhost inkyGhost = new InkyGhost("inky", new Position(21, 22), Orientation.DOWN, new Target(new Position(5, 5), "target"));;

        gameMap.getPacman().setPosition(new Position(17, 22));
        gameMap.getPacman().setOrientation(Orientation.LEFT);

        Assertions.assertEquals(inkyStrategy.inChase(gameMap, inkyGhost), Orientation.LEFT);

        gameMap.getPacman().setPosition(new Position(16, 19));
        gameMap.getPacman().setOrientation(Orientation.RIGHT);

        inkyGhost.setPosition(new Position(18, 22));
        inkyGhost.setOrientation(Orientation.UP);

        Assertions.assertEquals(inkyStrategy.inChase(gameMap, inkyGhost), Orientation.UP);
    }
}
