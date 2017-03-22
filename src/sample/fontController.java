package sample;

import java.awt.*;
import java.util.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.javafx.css.converters.StringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static sample.Controller.*;

public class fontController {
        public static List<String> fonts=Font.getFamilies();
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private ComboBox<?> selectorFont;

        @FXML
        private CheckBox boldBox;

        @FXML
        private CheckBox italicBox;

        @FXML
        private CheckBox underlineBox;

        @FXML
        private Label previewArea;

        @FXML
        private Button apply;

        @FXML
        void initialize() {
            assert selectorFont != null : "fx:id=\"selectorFont\" was not injected: check your FXML file 'FontSelect.fxml'.";
            assert boldBox != null : "fx:id=\"boldBox\" was not injected: check your FXML file 'FontSelect.fxml'.";
            assert italicBox != null : "fx:id=\"italicBox\" was not injected: check your FXML file 'FontSelect.fxml'.";
            assert underlineBox != null : "fx:id=\"underlineBox\" was not injected: check your FXML file 'FontSelect.fxml'.";
            assert previewArea != null : "fx:id=\"previewArea\" was not injected: check your FXML file 'FontSelect.fxml'.";
            assert apply != null : "fx:id=\"apply\" was not injected: check your FXML file 'FontSelect.fxml'.";
            apply.setOnAction((t)->{


                String selectedFont=((ComboBox)selectorFont.getValue()).toString();
                StringTokenizer st=new StringTokenizer(selectedFont,"'");
                System.out.println( selectorFont.getValue().toString());



                Controller.fontWindowOpen=false;
                fontStage.hide();

            });





            ArrayList<Label> FontList=new ArrayList<>();

            for(int x=0;x<fonts.size();x++){
                Label fLabel=new Label(fonts.get(x));
                fLabel.setOnMouseEntered((t)->{
                    final String name=fLabel.getText();
                    previewArea.setFont(Font.font(name));
                });
                FontList.add(fLabel); //stopped here, changes preview text dynamically
            }

            ObservableList oFontList=FXCollections.observableArrayList(FontList);
            selectorFont.setItems(oFontList);


    }

}
