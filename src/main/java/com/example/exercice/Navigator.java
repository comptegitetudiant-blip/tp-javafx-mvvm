package com.example.exercice;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigator {
    private static Stage stage;
    public static void init(Stage primary){ stage = primary; }
    public static void goTo(Scene scene, String title){
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
