/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testecropimagefunction;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private GridPane rootGrid;
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private ToggleButton btn_Cortar;
    @FXML
    private AnchorPane StackPaneImage;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageView2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleRealeasedCrop(MouseEvent event) {
    }

    @FXML
    private void handleDraggedCrop(MouseEvent event) {
    }

    @FXML
    private void handlePressedCrop(MouseEvent event) {
    }
    
}
