package hu.petrik.konyvtarasztali_probavizsga;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("konyv.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Könyvtár");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        if (args.length != 0  && args[0].equals("--stat")){
            Statisztika st = new Statisztika();
            System.exit(0);
        }else{
            launch();
        }


    }
}