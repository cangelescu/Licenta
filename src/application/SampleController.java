package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SampleController implements Initializable {

	@FXML Button browseButton;
	@FXML Button saveCoordinatesButton;
	@FXML ImageView imageView;
	@FXML TextField chooseImageText;
	@FXML TextField coordX1;
	@FXML TextField coordY1;
	@FXML TextField coordX2;
	@FXML TextField coordY2;
	@FXML ListView<String> listView = new ListView<String>();
	@FXML GraphicsContext gc;
	@FXML Canvas test;

	private double minRectX = 0;
	private double minRectY = 0;
	private double maxRectX = 0;
	private double maxRectY = 0;
	private boolean isLeftMouseButtonPressed = false;;
	private double leftMouseButtonX = -1;
	private double leftMouseButtonY = -1;

	@FXML 
	public void onMouseClicked(MouseEvent e){
		//if (e.getButton().equals(MouseButton.SECONDARY))
			//gc.clearRect(minRectX, minRectY, maxRectX - minRectX, maxRectY - minRectY);
	}
	
	@FXML
	public void browseButtonAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			Image image = new Image(file.toURI().toString());
			chooseImageText.setText(file.getAbsolutePath().toString());
			imageView.setImage(image);
			// http://java-buddy.blogspot.ro/2014/12/javafx-filechooser-open-and-save-image.html
			// http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
		}
	}

	@FXML
	public void textFieldAction(KeyEvent keyEvent) {
		if (keyEvent.getCode().equals(KeyCode.ENTER)) {
			System.out.println("test");
			File file = new File(chooseImageText.getText());
			Image image = new Image(file.toURI().toString());
			imageView.setImage(image);
		}
	}

	@FXML
	public void saveCoordinatesButtonAction(ActionEvent e) {
		listView.getItems().add("[" + coordX1.getText() + ", " + coordY1.getText() + "]"
								+ "  [" + coordX2.getText() + ", " + coordY2.getText() + "]");
		listView.scrollTo(listView.getItems().size() - 1);
		listView.layout();
		gc.clearRect(minRectX, minRectY, maxRectX - minRectX, maxRectY - minRectY);
		// de resetat listView-ul cand incarc o noua poza
		// http://docs.oracle.com/javafx/2/ui_controls/list-view.htm
	}

	@FXML
	public void onMousePressed(MouseEvent e) {
//		System.out.println("Mouse pressed");
//		if (e.getButton().equals(MouseButton.PRIMARY)) {
//			System.out.println("Primary mouse button pressed");
//			isLeftMouseButtonPressed = true;
//			leftMouseButtonX = e.getX();
//			leftMouseButtonY = e.getY();
//			coordX1.setText(String.valueOf(e.getX()));
//			coordY1.setText(String.valueOf(e.getY()));
//		}
	}

	@FXML
	public void onMouseReleased(MouseEvent e) {
//		System.out.println("Mouse released");
//		if (e.getButton().equals(MouseButton.PRIMARY)) {
//			System.out.println("primary mouse button released");
//			isLeftMouseButtonPressed = false;
//			leftMouseButtonX = -1;
//			leftMouseButtonY = -1;
//			coordX2.setText(String.valueOf(e.getX()));
//			coordY2.setText(String.valueOf(e.getY()));
//		}
	}

	@FXML
	public void onMouseMoved(MouseEvent e) {
//		if (isLeftMouseButtonPressed) {
//			gc = test.getGraphicsContext2D();
//			gc.clearRect(minRectX, minRectY, maxRectX - minRectX, maxRectY - minRectY);
//			minRectX = Math.min(leftMouseButtonX, e.getX());
//			minRectY = Math.min(leftMouseButtonY, e.getY());
//			maxRectX = Math.max(leftMouseButtonX, e.getX());
//			maxRectY = Math.max(leftMouseButtonY, e.getY());
//			gc.fillRect(minRectX, minRectY, maxRectX - minRectX, maxRectY - minRectY);
//		}
	}
	
	public void onMouseAction(MouseEvent ms){
		System.out.println("am ajuns aici");
		Group imageLayer = new Group(); 
		imageLayer.getChildren().add( imageView);
		RubberBandSelection rubberBandSelection = new RubberBandSelection(imageLayer);
		
        ContextMenu contextMenu = new ContextMenu();

        MenuItem cropMenuItem = new MenuItem("Crop");
        cropMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                // get bounds for image crop
                Bounds selectionBounds = rubberBandSelection.getBounds();

                // show bounds info
                System.out.println( "Selected area: " + selectionBounds);

                // crop the image
                crop( selectionBounds);

            }
        });
        contextMenu.getItems().add( cropMenuItem);

        // set context menu on image layer
        imageLayer.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isSecondaryButtonDown()) {
                    contextMenu.show(imageLayer, event.getScreenX(), event.getScreenY());
                }
            }
        });
	}
	
    private void crop( Bounds bounds) {

        int width = (int) bounds.getWidth();
        int height = (int) bounds.getHeight();

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        parameters.setViewport(new Rectangle2D( bounds.getMinX(), bounds.getMinY(), width, height));

        WritableImage wi = new WritableImage( width, height);
        imageView.snapshot(parameters, wi);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listView.setCellFactory(TextFieldListCell.forListView());
		
	}

}
