package sudoku.problemdomain;

import java.io.IOException;

/**
 * Created by Nikita on 25.07.2021 at 23:06
 */
public interface IStorage {
    void updateGameDate(SudokuGame game) throws IOException;
    SudokuGame getGameData() throws IOException;
}
