package com.g50.model.element.fixed;
import com.g50.model.element.Position;
import com.g50.model.element.fixed.collectable.Collectable;
import com.g50.model.element.fixed.collectable.PacDot;
import com.g50.model.element.fixed.collectable.PowerPellet;
import com.g50.model.element.fixed.collectable.fruit.Apple;
import com.g50.model.element.fixed.collectable.fruit.Cherry;
import com.g50.model.element.fixed.collectable.fruit.Manjaro;
import com.g50.model.element.fixed.collectable.fruit.Orange;
import com.g50.model.element.fixed.noncollectable.Door;
import com.g50.model.element.fixed.noncollectable.EmptySpace;
import com.g50.model.element.fixed.noncollectable.SpawnArea;
import com.g50.model.element.fixed.noncollectable.Target;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class FixedElementTest {
    private Position pos1, pos2;
    List<FixedElement> list;
    @BeforeEach
    public void initTest() {
        int x = 10;
        int y = 15;
        pos1 = new Position(x, y);
        pos2 = new Position(x, y+1);
        list = Arrays.asList(new Door(pos1),
                new EmptySpace(pos1),
                new PacDot(pos1),
                new Cherry(pos1),
                new Manjaro(pos1),
                new Orange(pos1),
                new Apple(pos1),
                new PowerPellet(pos1),
                new Target(pos1, ""),
                new SpawnArea(pos1));
    }

    @Test
    public void verifyGenerate(){
        for(FixedElement elem: list) {
            FixedElement newElement = elem.generate(new Position(pos2));
            Assertions.assertEquals(newElement.getClass(), elem.getClass());
            Assertions.assertEquals(newElement.getPosition(), pos2);
            Assertions.assertNotEquals(newElement, elem);
            Assertions.assertEquals(newElement.isCollectable(), elem.isCollectable());
            Assertions.assertEquals(newElement.isWalkable(), elem.isWalkable());
        }
    }

    @Test
    public void verifyCollectability(){
        for(FixedElement elem: list){
            if(elem instanceof Collectable) Assertions.assertEquals(elem.isCollectable(), true);
            else  Assertions.assertEquals(elem.isCollectable(), false);
        }
    }
}
