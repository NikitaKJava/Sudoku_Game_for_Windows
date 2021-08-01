package sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sudoku.buildlogic.SudokuBuildLogic;
import sudoku.userInterface.IUserInterfaceContract;
import sudoku.userInterface.UserInterfaceImpl;

import java.io.IOException;

/**
 * Created by Nikita on 25.07.2021 at 22:50
 */
public class SudokuApplication extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        IUserInterfaceContract.View uiImpl = new UserInterfaceImpl(primaryStage);

        try {
            SudokuBuildLogic.build(uiImpl);
        } catch (IOException e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
