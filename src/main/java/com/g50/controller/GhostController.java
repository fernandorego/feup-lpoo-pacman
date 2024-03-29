package com.g50.controller;

import com.g50.states.GameState;
import com.g50.states.GhostState;
import com.g50.gui.GUI;
import com.g50.model.element.movable.Orientation;
import com.g50.Application;
import com.g50.model.element.movable.ghost.Ghost;
import com.g50.model.map.GameMap;

import java.io.IOException;
import java.util.List;

public class GhostController extends Controller<Ghost> {
    private Orientation nextBufferedOrientation;

    public GhostController(Ghost ghost){
        super(ghost);
    }

    @Override
    public void update(Application application, int frame) {
        GameController gameController = (GameController) application.getController();
        updateGhostState(gameController);
        updateGhostSpeed(gameController);
        if (frame % getModel().getFramesPerPosition() == 0)
            updatePositions(gameController.getModel().getGameMap());
    }

    public void updateGhostSpeed(GameController gameController) {
        if(getModel().getState().equals(GhostState.FRIGHTENED))
            getModel().setFramesPerPosition(gameController.getModel().getLevel().getFrightenedGhostFramesPerMovement());
        else
            getModel().setDefaultFramesPerPosition();
    }

    public void updateGhostState(GameController gameController) {
        if (isDeadInSpawnPosition())
            getModel().setState(GhostState.IN_CAGE);
        else if (isReadyToLeaveCage())
            getModel().setState(GhostState.LEAVING_CAGE);
        else if (isRegularState() || isReadyToEnterRegularState(gameController))
            updateGhostState(gameController.getGameStateHandler().getState());
    }

    public void updatePositions(GameMap gameMap){
        Orientation newOrientation = getModel().getStrategy().getNextOrientation(gameMap, getModel(), getModel().getState());
        if (newOrientation == null) return;
        else getModel().setOrientation(newOrientation);
        moveToNewPosition(gameMap, gameMap.getAvailableOrientations(getModel().getPosition()));
    }

    private void moveToNewPosition(GameMap gameMap, List<Orientation> orientations){
        if (orientations.contains(nextBufferedOrientation)){
            getModel().move(nextBufferedOrientation, gameMap.getColumns(), gameMap.getLines());
            nextBufferedOrientation = null;
        } else if (orientations.contains(getModel().getOrientation()))
            getModel().move(getModel().getOrientation(), gameMap.getColumns(), gameMap.getLines());
    }

    private void updateGhostState(GameState gameState) {
        switch (gameState) {
            case GAME_CHASE: {
                if (getModel().getState() == GhostState.FRIGHTENED) reverseOrientation();
                getModel().setState(GhostState.CHASE);
                break;
            }
            case GAME_SCATTER: {
                if (getModel().getState() == GhostState.FRIGHTENED) reverseOrientation();
                getModel().setState(GhostState.SCATTER);
                break;
            }
            case GAME_FRIGHTENED: {
                if (getModel().getState() != GhostState.FRIGHTENED) reverseOrientation();
                getModel().setState(GhostState.FRIGHTENED);
                break;
            }
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

    public void reverseOrientation(){
        if (getModel().getState() != GhostState.LEAVING_CAGE &&
                getModel().getState() != GhostState.IN_CAGE && this.getModel().getState() != GhostState.DEAD)
            setNextBufferedOrientation(getModel().getOrientation().getOpposite());
    }

    private boolean isDeadInSpawnPosition(){
        return getModel().getState() == GhostState.DEAD && getModel().getPosition().equals(getModel().getSpawnPosition());
    }

    private boolean isReadyToLeaveCage() {
        return getModel().getState() == GhostState.IN_CAGE && getModel().getStrategy().getDotLimit() == 0;
    }

    private boolean isRegularState(){
        GhostState state = getModel().getState();
        return state != GhostState.IN_CAGE && state != GhostState.DEAD
                && state != GhostState.LEAVING_CAGE;
    }

    private boolean isReadyToEnterRegularState(GameController gameController) {
        return getModel().getState() == GhostState.LEAVING_CAGE && getModel().getPosition().
                equals(gameController.getModel().getGameMap().getGhostSpawnPosition());
    }
}
