package game;

import connection.BattleshipServer;
import connection.ConnectionHandler;
import gui.InitialConnectionWindow;

public class GameController {

    private GameState gameState = GameState.CONNECTION_PHASE;

    public static final int BOARD_DIMENSION = 10;

    private final Square[][] localClientBoard = new Square[BOARD_DIMENSION][BOARD_DIMENSION];
    private final Square[][] remoteClientBoard = new Square[BOARD_DIMENSION][BOARD_DIMENSION];

    private final BattleshipServer SERVER;

    public GameController(BattleshipServer server) {
        this.SERVER = server;
    }

    public void setGameState(GameState newState) {
        this.gameState = newState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void twoConnectedPlayers() {
        System.out.println("TVÅ TILLKOPPLADE!");
        gameState = GameState.SETUP_PHASE;

        SERVER.broadcastMessage("setup");

    }

    public boolean validateMove(String msg) {

        //Denna låg tidigare direkt i klienttråden, som anropade broadcast direkt den fick in ngt meddelande

//        if (gameState == GameState.SETUP_PHASE) {
//            String [] tokens =  msg.split(" ");
//        } else {
//            SERVER.broadcastMessage(msg);
//        }
        SERVER.broadcastMessage(msg);

        return false;
    }

}
