package pl.polsl.michal.slusarczyk.sapper;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.polsl.michal.slusarczyk.controller.StartController;

public class App extends Application {
    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void init() {}

    @Override
    public void start(final Stage stage) throws Exception {
        final Scene scene = new Scene((new StartController(stage)).getStartView());
        stage.setScene(scene);
        stage.show();
    }
}