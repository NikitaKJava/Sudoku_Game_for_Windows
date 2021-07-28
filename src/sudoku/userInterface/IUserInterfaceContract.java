package sudoku.userInterface;

import sudoku.problemdomain.SudokuGame;

/**
 * Created by Nikita on 25.07.2021 at 23:29
 */
public interface IUserInterfaceContract {
    interface EventListener{
        void onSudokuInput(int x, int y , int input);
        void onDialogClick();
    }

    interface View{
        void setListener(IUserInterfaceContract.EventListener listener);
        void updateSquare(int x, int y, int input);
        void updateBoard(SudokuGame game);
        void showDialog(String Message);
        void showError(String message);
    }
}
