package sudoku.computationLogic;

import sudoku.problemdomain.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

/**
 * Created by Nikita on 28.07.2021 at 23:16
 */
public class GameGenerator {
    public static int[][] getNewGameGrid() {
        return unsolveGame(getSolvedGame());
    }

    /**
     * 1. Generate a new 9x9 2D Array.
     * 2. For each value in the range 1..9, allocate that value 9 times based on the following constraints:
     * - A Random coordinate on the grid is selected. If it is empty, a Random value is allocated.
     * - The resulting allocation must not produce invalid rows, columns, or squares.
     * - If the allocation does produce an invalid game
     *
     */
    private static int[][] unsolveGame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());

        boolean solvable = false;
        int[][] solvableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        while (!solvable) {
            //Take values from solvedGame and write to new unsolved; i.e. reset to initial state
            SudokuUtilities.copySudokuArrayValues(solvedGame, solvableArray);

            int index = 0;

            while (index < 40) {
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if (solvableArray[xCoordinate][yCoordinate] != 0) {
                    solvableArray[xCoordinate][yCoordinate] = 0;
                    index++;
                }
            }

        int[][] toBeSolved = new int[GRID_BOUNDARY][GRID_BOUNDARY];
        SudokuUtilities.copySudokuArrayValues(solvableArray, toBeSolved);
        //check if result is solvable
        solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);

        //TODO delete after tests
        System.out.println(solvable);
    }
        return solvableArray;
    }

    public static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];
        for (int value = 1; value < GRID_BOUNDARY; value++) {
            int allocations = 0;
            int interrupt = 0;

            List<Coordinates> allocTracker = new ArrayList<>();
            int attempts = 0;

            while (allocations < GRID_BOUNDARY) {
                if (interrupt > 200) {
                    allocTracker.forEach(coord -> newGrid[coord.getX()][coord.getY()] = 0);

                    interrupt = 0;
                    allocations = 0;
                    allocTracker.clear();
                    attempts++;

                    if (attempts > 500) {
                        clearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if (newGrid[xCoordinate][yCoordinate] == 0) {
                    newGrid[xCoordinate][yCoordinate] = value;

                    if (GameLogic.sudokuIsInvalid(newGrid)) {
                        newGrid[xCoordinate][yCoordinate] = 0;
                        interrupt++;
                    } else {
                        allocTracker.add(new Coordinates(xCoordinate, yCoordinate));
                        allocations++;
                    }
                }
            }
        }
        return newGrid;
    }

    private static void clearArray(int[][] newGrid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                newGrid[xIndex][yIndex] = 0;
            }
        }
    }
}
