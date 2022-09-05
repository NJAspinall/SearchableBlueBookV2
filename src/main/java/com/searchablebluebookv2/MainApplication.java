package com.searchablebluebookv2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


/***
 * This main class loads the GUI using JavaFX and the FXML Loader.
 *
 * @author Nathan Aspinall
 * @version 0.9
 */
public class MainApplication extends Application {


    /***
     * Opens the GUI for the user
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 540);
        stage.setTitle("Searchable Blue Book");
        stage.setScene(scene);
        stage.show();

        Controller controller = new Controller();
        controller.setContextMenu();
    }

    public static void main(String[] args) {
        launch();
    }
}