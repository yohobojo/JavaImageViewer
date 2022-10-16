import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import java.io.*;

public class JIMachine extends Application {

    Canvas canvas = new Canvas(1280, 720);
    Image zoomImage;
    FileChooser fc = new FileChooser();
    File workFile = null;
    ImageView imageView = new ImageView();
    Group root = new Group();

    public void start(Stage stage) throws IOException {

        Button buttonOne = new Button("Open");
        buttonOne.setOnAction(e -> {
            try {
                open(stage);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        Button buttonTwo = new Button("Zoom In");
        buttonTwo.setOnAction(e -> zoomIn(stage));
        Button buttonThree = new Button("100%");
        buttonThree.setOnAction(e -> fullSize(stage));
        Button buttonFour = new Button("Zoom Out");
        buttonFour.setOnAction(e -> zoomOut(stage));
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> Platform.exit());

        HBox buttonBar = new HBox(20, buttonOne, buttonTwo, buttonThree, buttonFour, quitButton);
        buttonBar.setAlignment(Pos.CENTER);
        StackPane bottom = new StackPane(buttonBar);
        bottom.setStyle("-fx-background-color: gray; -fx-padding: 5px;"
                + " -fx-border-color:black; -fx-border-width: 2px 0 0 0");
        BorderPane border = new BorderPane(canvas);
        border.setBottom(bottom);
        border.setStyle("-fx-border-color: black; -fx-border-width:2px");

        root.getChildren().addAll(imageView, border);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("JavaFX Image Viewer Program");
        stage.show();

    }

    public void drawImage(int x) {
        GraphicsContext g = canvas.getGraphicsContext2D();

    }

    public void open(Stage stage) throws IOException {
        File workFile = fc.showOpenDialog(stage);
        if (workFile != null) {
            InputStream stream = new FileInputStream(workFile);
            Image image = new Image(stream);
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(720);
            imageView.setFitWidth(1280);
            stage.sizeToScene();
        } else {
            System.out.println("else stmt");
        }
    }

    public void zoomIn(Stage stage) {
        imageView.setFitHeight(imageView.getFitHeight() + (imageView.getFitHeight() * .25));
        imageView.setFitWidth(imageView.getFitWidth() + (imageView.getFitWidth() * .25));
    }

    public void zoomOut(Stage stage) {
        imageView.setFitHeight(imageView.getFitHeight() - (imageView.getFitHeight() * .25));
        imageView.setFitWidth(imageView.getFitWidth() - (imageView.getFitWidth() * .25));
    }

    public void fullSize(Stage stage) {
        imageView.setFitHeight(720);
        imageView.setFitWidth(1280);
    }

    public static void main(String[] args) {
        launch(args);
    }
}