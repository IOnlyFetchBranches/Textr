package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.font.FontManager;
import sun.misc.OSEnvironment;

import javax.swing.*;

import java.io.*;
import java.util.StringTokenizer;

@SuppressWarnings("ALL")
public class Controller {
    private boolean isSaved=true;
    public static boolean fontWindowOpen=false;


    public static Stage fontStage;




    @FXML
    private Menu fileMenu;
    @FXML
    private Menu editMenu;
    @FXML
    private Menu helpMenu;
    @FXML
    public static TextArea document;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem save;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem wrap;

    @FXML
    private MenuItem fontSelect;

    @FXML
    private MenuItem about;

    @FXML
    public void initialize(){
        assert fileMenu != null : "fx:id=\"fileMenu\" was not injected: check your FXML file 'main.fxml'.";
        assert open != null : "fx:id=\"open\" was not injected: check your FXML file 'main.fxml'.";
        assert save != null : "fx:id=\"save\" was not injected: check your FXML file 'main.fxml'.";
        assert exit != null : "fx:id=\"exit\" was not injected: check your FXML file 'main.fxml'.";
        assert editMenu != null : "fx:id=\"editMenu\" was not injected: check your FXML file 'main.fxml'.";
        assert fontSelect != null : "fx:id=\"fontSelect\" was not injected: check your FXML file 'main.fxml'.";
        assert wrap != null : "fx:id=\"wrap\" was not injected: check your FXML file 'main.fxml'.";
        assert helpMenu != null : "fx:id=\"helpMenu\" was not injected: check your FXML file 'main.fxml'.";
        assert document != null : "fx:id=\"document\" was not injected: check your FXML file 'main.fxml'.";









        try {
            document.setOnKeyTyped((t) -> {
                println("Text Changed");
                isSaved = false;
            });
        }catch(Exception e){
            println(e.getMessage());
        }
        save.setOnAction((t)->{
            save();
        });






        fontSelect.setOnAction((t)->{
            if(!fontWindowOpen) {
                try {
                    Pane root = new FXMLLoader().load(getClass().getResource("FontSelect.fxml"));
                    fontStage = new Stage();
                    Scene fontScene = new Scene(root);
                    fontWindowOpen = true;
                    fontStage.setScene(fontScene);
                    fontStage.show();
                } catch (IOException ioe) {
                    println("Error" +" "+ioe.getCause().getCause());
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Please Close Existing Font Window!","Duplicate Found",JOptionPane.ERROR_MESSAGE);

            }
        });


        exit.setOnAction((t)->{
            if(isSaved){
                System.exit(0);
            }
            else{
                int choice=JOptionPane.showConfirmDialog(null,"File has been changed without Saving, Would you like to save?","Unsaved Changes!",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                switch(choice){
                    case JOptionPane.YES_OPTION:
                        save();
                        System.exit(0);
                        break;
                    case JOptionPane.NO_OPTION:
                        System.exit(0);
                }
            }
                  });
    }

    private void save(){
        try {
            if (isSaved) {
                throw new Exception("File is not modified!");
            }
            try {
                File temp = File.createTempFile("temp", ".root");

                FileChooser chooser = new FileChooser();
                chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("All Files", ".txt"));
                File path = chooser.showSaveDialog(null);

                String[] lexedPath = path.getAbsolutePath().split("\\\\"); //incase of need the file name
                String fileName = lexedPath[lexedPath.length - 1];

                // get the content in bytes
                byte[] byteData = document.getText().getBytes();

                FileOutputStream fileOut = new FileOutputStream(path);

                fileOut.write(byteData);
                fileOut.flush();
                fileOut.close();

                //Open the file?
                //Desktop.getDesktop().open(path);

            isSaved=true;
            } catch (IOException ioe) {
                System.err.print("IO EXCEPTION!");
            }
        }catch(Exception e){
            System.err.print(e.getLocalizedMessage());
        }
    }
    private void println(String text){
        System.out.println(text);
    }
}
