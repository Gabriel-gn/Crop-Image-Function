/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cropimagefunction;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Gabriel Nogueira
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane StackPaneImage;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageView2;

    double xFinalRect;
    double yFinalRect;
    Rectangle rectCrop;
    double xInicialRect;
    double yInicialRect;
    Image image1;
    Image image2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        image1 = new Image(getClass().getResourceAsStream("Lena.png"));
        imageView.setImage(image1);
        imageView.setPreserveRatio(true);
        imageView2.setPreserveRatio(true);
    }

    @FXML
    private void handleDraggedCrop(javafx.scene.input.MouseEvent event) {
        xFinalRect = event.getX();
        yFinalRect = event.getY();

        if (xFinalRect >= StackPaneImage.getWidth()) {
            xFinalRect = StackPaneImage.getWidth();
        } else if (xFinalRect <= 0) {

            xFinalRect = 0;
        }

        if (yFinalRect >= StackPaneImage.getHeight()) {
            yFinalRect = StackPaneImage.getHeight();
        } else if (yFinalRect <= 0) {
            yFinalRect = 0;
        }

        rectCrop.setX(xInicialRect);
        rectCrop.setY(yInicialRect);
        rectCrop.setWidth(xFinalRect - xInicialRect);
        rectCrop.setHeight(yFinalRect - yInicialRect);

        if (rectCrop.getWidth() < 0) {
            rectCrop.setWidth(-rectCrop.getWidth());
            rectCrop.setX(rectCrop.getX() - rectCrop.getWidth());
        }

        if (rectCrop.getHeight() < 0) {
            rectCrop.setHeight(-rectCrop.getHeight());
            rectCrop.setY(rectCrop.getY() - rectCrop.getHeight());
        }

    }

    @FXML
    private void handlePressedCrop(javafx.scene.input.MouseEvent event) {
        //if (btn_Cortar.isSelected()) {

        xInicialRect = event.getX();
        yInicialRect = event.getY();

        double width = StackPaneImage.getWidth();
        double height = StackPaneImage.getHeight();

        StackPaneImage.setPrefSize(width, height);
        StackPaneImage.setMinSize(width, height);
        StackPaneImage.setMaxSize(width, height);

        if (rectCrop != null) {
            StackPaneImage.getChildren().remove(rectCrop);
        }

        rectCrop = new Rectangle();
        //rectCrop.getStyleClass().add("editToolsCropSelection");
        StackPaneImage.getChildren().add(rectCrop);
        rectCrop.setOpacity(0.35);

        //}
    }

    @FXML
    private void handleRealeasedCrop(javafx.scene.input.MouseEvent event) {

        //if (btn_Cortar.isSelected()) {
        StackPaneImage.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        StackPaneImage.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        StackPaneImage.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        //}
        //System.out.println("rectCrop largura: " + rectCrop.getWidth());
        //System.out.println("rectCrop altura: " + rectCrop.getHeight());

        //DAQUI PRA BAIXO PEGA A IMAGEM E FAZ O CROP DA PARTE SELECIONADA
        Bounds bounds = rectCrop.getBoundsInParent();
        //System.out.println(bounds);

        double rectMinX = bounds.getMinX();
        double rectMinY = bounds.getMinY();
        double escala;

        double escalaView = imageView.getFitWidth() / (double) imageView.getFitHeight();
        double escalaImg = image1.getWidth() / (double) image1.getHeight();
        escala = escalaView > escalaImg ? image1.getHeight() / imageView.getFitHeight() : image1.getWidth() / imageView.getFitWidth();

        //System.out.println("escala: " + escala);

        //COMEÇO DA LEITURA DOS PIXELS
        PixelReader reader = image1.getPixelReader();

        int rectWidth = (int) rectCrop.getWidth();
        int rectHeight = (int) rectCrop.getHeight();

        WritableImage dest = new WritableImage((int) (rectWidth * escala), (int) (rectHeight * escala));
        PixelWriter writer = dest.getPixelWriter();

        for (int x = 0; x < (int) (rectWidth * escala); x++) {
            for (int y = 0; y < (int) (rectHeight * escala); y++) {
                int argb = reader.getArgb(x + (int) (rectMinX * escala), y + (int) (rectMinY * escala));
                writer.setArgb(x, y, argb);
            }
        }

        //System.out.println(writer.getPixelFormat().getType()); //BYTE_BGRA_PRE
        imageView2.setImage(dest);

    }

}
