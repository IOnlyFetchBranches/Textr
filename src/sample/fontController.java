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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.*;

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
        public ComboBox<?> sizeBox;

        @SuppressWarnings("unchecked")
        @FXML
        void initialize() {
            assert selectorFont != null : "fx:id=\"selectorFont\" was not injected: check your FXML file 'FontSelect.fxml'.";
            assert boldBox != null : "fx:id=\"boldBox\" was not injected: check your FXML file 'FontSelect.fxml'.";
            assert italicBox != null : "fx:id=\"italicBox\" was not injected: check your FXML file 'FontSelect.fxml'.";
            assert underlineBox != null : "fx:id=\"underlineBox\" was not injected: check your FXML file 'FontSelect.fxml'.";
            assert previewArea != null : "fx:id=\"previewArea\" was not injected: check your FXML file 'FontSelect.fxml'.";
            assert apply != null : "fx:id=\"apply\" was not injected: check your FXML file 'FontSelect.fxml'.";




            ArrayList<Label> FontList = new ArrayList<>();



            //populate the sizes;
            final String[] standardSizes={"8","9","10","11",
            "12","14","16","18","20","22","24","26","28","36","48","72"};
            ObservableList sizesList=FXCollections.observableArrayList(new ArrayList<Label>(){});
            for(int x=0;x<standardSizes.length;x++){
                Label sizeLabel=new Label(standardSizes[x]);
                sizeLabel.setStyle("-fx-text-fill: black");
                sizesList.add(sizeLabel);


            }
            sizeBox.setItems(sizesList);





            //populate the fonts
            for (int x = 0; x < fonts.size(); x++) {
                Label fLabel = new Label(fonts.get(x));
                fLabel.setStyle("-fx-text-fill: black");
                fLabel.setOnMouseEntered((t) -> {
                    final String name = fLabel.getText();
                    previewArea.setFont(Font.font(name));
                });
                FontList.add(fLabel); //stopped here, changes preview text dynamically
            }


            ObservableList oFontList=FXCollections.observableArrayList(FontList);
            selectorFont.setItems(oFontList);
            apply.setOnAction((t)-> {
                if(sizeBox.getValue()==null || selectorFont.getValue()==null){
                    JOptionPane.showMessageDialog(null,"One of the Fields is left Empty!","Please Review",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    boolean isValid = true;
                    final int defaultSize = 12;
                    int size = defaultSize;
                    try {
                        String selectedSize = sizeBox.getValue().toString();
                        StringTokenizer st = new StringTokenizer(selectedSize, "'");
                        st.nextToken();
                        size = Integer.parseInt(st.nextToken());


                    } catch (Exception e) {
                        size = defaultSize;
                        isValid = false;
                        System.err.println(e.getLocalizedMessage());
                    }
                    if (isValid) {
                        String selectedFont = selectorFont.getValue().toString();
                        StringTokenizer st = new StringTokenizer(selectedFont, "'");
                        st.nextToken();
                        String font = st.nextToken();
                        document.setFont(Font.font(font, size));
                        Controller.fontWindowOpen = false;
                        fontStage.hide();
                    }
                }
            });



        }

}
