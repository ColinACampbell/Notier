package com.VocetSoft.Notier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage mStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/MainStage.fxml"));
        Scene mScene = new Scene(root,800,422);
        mStage.setScene(mScene);
        mStage.setTitle("Notier");
        mStage.show();
        mStage.centerOnScreen();
    }



    protected void aboutStage()
    {



    }
}
