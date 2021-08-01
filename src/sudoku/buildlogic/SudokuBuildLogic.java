package sudoku.buildlogic;

import sudoku.computationLogic.GameLogic;
import sudoku.persistence.LocalStorageImpl;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userInterface.IUserInterfaceContract;
import sudoku.userInterface.logic.ControlLogic;

import java.io.IOException;

/**
 * Created by Nikita on 01.08.2021 at 12:04
 */
public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException{
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try{
            initialState = storage.getGameData();
        } catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage,userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}