package g50.controller.ghost;

import g50.controller.Controller;
import g50.controller.GameController;
import g50.controller.states.GameState;
import g50.controller.states.GhostState;
import g50.gui.GUI;
import g50.model.element.Position;
import g50.model.element.movable.Orientation;
import g50.Application;
import g50.model.element.movable.ghost.Ghost;
import g50.model.element.movable.ghost.strategy.GhostStrategy;
import g50.model.map.GameMap;

import java.io.IOException;
import java.util.List;

public class GhostController extends Controller<Ghost> {
    private Orientation nextBufferedOrientation;
    private int speed = 15;

    public GhostController(Ghost ghost){
        super(ghost);
    }

    @Override
    public void update(Application application, int frame) {

        if(getModel().getState() == GhostState.DEAD && getModel().getPosition().equals(getModel().getSpawnPosition()))
            getModel().setState(GhostState.INCAGE);
        else if (getModel().getState() == GhostState.INCAGE && getModel().getStrategy().getDotLimit() == 0)
            getModel().setState(GhostState.LEAVINGCAGE);
        else if(getModel().getState() != GhostState.INCAGE && getModel().getState() != GhostState.DEAD
                && getModel().getState() != GhostState.LEAVINGCAGE ||
                (getModel().getState() == GhostState.LEAVINGCAGE && getModel().getPosition().
                        equals(((GameController)(application.getController())).getModel().getGameMap().getGhostStartPos())))
            updateGhostState(((GameController)(application.getController())).getGameStateHandler().getState());

        if (frame % speed != 0) return;
        Orientation newOrientation = getModel().getStrategy().getNextOrientation(application.getGame().getGameMap(), getModel(), getModel().getState());
        if (newOrientation == null) return;
        else getModel().setOrientation(newOrientation);
        moveToNewPosition(((GameController)(application.getController())).getModel().getGameMap(),
                ((GameController)(application.getController())).getModel().getGameMap().getAvailableOrientations(getModel().getPosition()),
                getModel().getPosition());
    }

    private void moveToNewPosition(GameMap gameMap, List<Orientation> orientations, Position currentPos){
        if (orientations.contains(nextBufferedOrientation)){
            getModel().move(nextBufferedOrientation, gameMap.getColumns(), gameMap.getLines());
            nextBufferedOrientation = null;
        } else if (orientations.contains(getModel().getOrientation()))
            getModel().move(getModel().getOrientation(), gameMap.getColumns(), gameMap.getLines());
    }

    private void updateGhostState(GameState gameState) {
        switch (gameState) {
            case GameChase: {
                getModel().setState(GhostState.CHASE);
                break;
            }
            case GameScatter: {
                getModel().setState(GhostState.SCATTER);
                break;
            }
            case GameFrightned:
                getModel().setState(GhostState.FRIGHTENED);
                break;
        }
    }

    public void decrementStrategyDotLimit() {
        if (getModel().getStrategy().getDotLimit() > 0) this.getModel().getStrategy().decrementDotLimit();
    }

    public void setNextBufferedOrientation(Orientation orientation){
        this.nextBufferedOrientation = orientation;
    }

    public void consumeGhost() {
        getModel().setState(GhostState.DEAD);
        this.getModel().getStrategy().resetDotLimit();
    }

    @Override
    public void addPendingKBDAction(GUI.KBD_ACTION action) throws IOException {}
}
