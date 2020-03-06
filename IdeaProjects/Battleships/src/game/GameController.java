package game;

import connection.ConnectionHandler;
import gui.InitialConnectionWindow;

public class GameController {

    private GameState gameState = GameState.SETUP_PHASE;

    public static final int BOARD_DIMENSION = 10;

    private final Square [][] localClientBoard = new Square[BOARD_DIMENSION][BOARD_DIMENSION];
    private final Square [][] remoteClientBoard = new Square[BOARD_DIMENSION][BOARD_DIMENSION];

    public void setGameState(GameState newState) {
        this.gameState = newState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void twoConnectedPlayers(){

    }

    public boolean validateMove(String s){
        return false;
    }

}
