package com.VocetSoft.Notier.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStageController implements Initializable {

    private File file = null;
    private String contents;
    @FXML private Label label_fileUrl;
    @FXML private TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void openFile(ActionEvent e)
    {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        File file = fileChooser.showOpenDialog(label_fileUrl.getScene().getWindow());

        // house keeping
        if (file.exists() && file.canRead() && file.isFile() && !file.equals(null))
        {
            label_fileUrl.setText(file.getAbsolutePath());
            try {
                char[] b = new char[2048];
                FileReader fr = new FileReader(file);
                fr.read(b);
                contents = new String(b);
            } catch (java.io.IOException e1) {
                e1.printStackTrace();
            }

            textArea.setText(contents);
            this.file = file;
        }

    }

    @FXML
    public void saveFile(ActionEvent e)
    {
        overWriteFile(this.file);
    }


    // Review this code
    @FXML
    public void saveAs(ActionEvent e)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)","*.txt");
        fileChooser.getExtensionFilters().add(exFilter);
        file = fileChooser.showSaveDialog(label_fileUrl.getScene().getWindow());
        FileWriter fr;
        try
        {
            contents = textArea.getText();
            fr = new FileWriter(file);
            fr.write(contents);
            fr.flush();
            fr.close();

        } catch (IOException err)
        {
            err.printStackTrace();
        }

        // Update the ui
        label_fileUrl.setText(file.getAbsolutePath());
    }

    @FXML
    public void createNewFile(ActionEvent e)
    {
        textArea.clear();
        label_fileUrl.setText("No File Open");
        file = null;
    }

    @FXML
    public void updateGUI(ActionEvent e)
    {
        //if (label_fileUrl.getText().equals("No File Open"))
            label_fileUrl.setText("File not saved");
    }

    private Parent root;
    @FXML
    public void aboutStage(ActionEvent e)
    {
        Stage stage = new Stage();

        try
        {
            root = FXMLLoader.load(getClass().getResource("FXML/AboutStage.fxml"));
        } catch (Exception err)
        {
            err.printStackTrace();
        }

        Scene scene = new Scene(root,800,422);
        stage.setScene(scene);
        stage.show();
    }



    // this method is only called in method saveFile
    private void overWriteFile(File file)
    {
        String newContents = textArea.getText();
        FileWriter fr;
        try
        {
            fr = new FileWriter(file);
            fr.write(newContents);
            fr.flush();
            fr.close();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }


}
