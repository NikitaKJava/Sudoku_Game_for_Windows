package sudoku.problemdomain;
import sudoku.computationLogic.SudokuUtilities;
import sudoku.constants.GameState;

import java.io.Serializable;

/**
 * Created by Nikita on 25.07.2021 at 22:53
 */
public class SudokuGame implements Serializable {
    private final GameState gameState;
    private final int[][] gridState;

    public static final int GRID_BOUNDARY = 9;

    public SudokuGame(GameState gameState, int[][] gridState){
        this.gameState = gameState;
        this.gridState = gridState;
    }

    public GameState getGameState(){
        return gameState;
    }

    public int[][] getCopyOfGridState() {
        return SudokuUtilities.copyToNewArray(gridState);
    }
}
