package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Yongki An
 * on 2016. 10. 25.
 */

public class client extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public HBox inputHboxBottom() {
        HBox hb = new HBox();
        hb.setPadding(new Insets(15, 12, 15, 12));
        hb.setSpacing(10);
        //hb.setStyle("-fx-background-color: #336699;");

        /* input field */
        TextField textField = new TextField ();
        textField.setId("inputField");
        textField.setPrefSize(400, 20);

        /* input button */
        Button enter_btn = new Button();
        enter_btn.setId("EnterButton");
        enter_btn.setText("Enter");
        enter_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String txt_input = "hi";
                System.out.println(txt_input);
            }
        });
        enter_btn.setPrefSize(100, 20);

        hb.getChildren().addAll(textField, enter_btn);

        return hb;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AFRS"); // set title of entire window

        BorderPane root = new BorderPane();

        root.setPadding(new Insets(0, 10, 0, 10));
        HBox iField = inputHboxBottom();
        root.setBottom(iField);

        primaryStage.setScene(new Scene(root, 500, 450));
        primaryStage.show();
    }
}
