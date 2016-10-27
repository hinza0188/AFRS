package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Yongki An
 * on 2016. 10. 25.
 */

public class Client extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Tab getTabs(final TabPane tabPane, final String title) {
        Tab tab = new Tab(title);

        return tab;
    }

    private HBox outputHBoxCenter() {
        HBox obx = new HBox();
        obx.setPadding(new Insets(15, 12, 15, 12));
        obx.setSpacing(10);

        TextArea textArea = new TextArea();
        textArea.setId("outputField");
        textArea.setPrefSize(460, 400);
        textArea.setEditable(false);      // read-only
        textArea.setMouseTransparent(true);
        textArea.setFocusTraversable(false);
        obx.getChildren().add(textArea);

        return obx;
    }

    private HBox inputHBoxBottom() {
        HBox ibx = new HBox();
        ibx.setPadding(new Insets(15, 12, 15, 12));
        ibx.setSpacing(10);
        //hb.setStyle("-fx-background-color: #336699;");

        /* input field */
        TextField txtFld = new TextField ();
        txtFld.setId("inputField");
        txtFld.setPrefSize(400, 20);
        txtFld.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                String txt = txtFld.getText(); // get the string in variable txt

                // run codes here for passing this input

                System.out.println(txt); // print the input in the console
                txtFld.clear();
            }
        });

        /* input button */
        Button enter_btn = new Button();
        enter_btn.setId("EnterButton");
        enter_btn.setText("Enter");
        enter_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String txt = txtFld.getText(); // get the string in variable txt

                // run codes here for passing this input

                System.out.println(txt); // print the input in the console
                txtFld.clear();
            }
        });
        enter_btn.setPrefSize(100, 20);

        ibx.getChildren().addAll(txtFld, enter_btn);

        return ibx;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AFRS"); // set title of entire window
        BorderPane root = new BorderPane(); // with border pane
        root.setPadding(new Insets(0, 10, 0, 10));

        root.setBottom(inputHBoxBottom()); // call input HBox at the bottom of the border pane

        root.setCenter(outputHBoxCenter()); // call output HBox at the center of the border pane

        primaryStage.setScene(new Scene(root, 500, 450));
        primaryStage.show();
    }
}
