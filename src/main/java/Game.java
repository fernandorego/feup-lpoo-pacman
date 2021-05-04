import g50.model.map.Map;

import java.io.File;
import java.io.IOException;

public class Game {

    public void init(){
        Map map = new Map(new File("src/main/resources/maps/default.txt").getAbsolutePath());
        try{
            map.build();
            System.out.println(map.getMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
