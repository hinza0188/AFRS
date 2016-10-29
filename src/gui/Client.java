package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import server.UserSelector;

/**
 * Created by Yongki An
 * on 2016. 10. 25.
 */

public class Client extends Application {
    private UserSelector userSelector = new UserSelector();

    public static void main(String[] args) {
        launch(args);
    }

    private Tab getTabs(final TabPane tabPane, final String title) {
        // TODO: call this.userSelector.changeUser("[TAB_INDEX HERE]"); when tab changed
        Tab tab = new Tab(title);

        return tab;
    }

//    private HBox outputHBoxCenter() {
//        HBox obx = new HBox();
//        obx.setPadding(new Insets(15, 12, 15, 12));
//        obx.setSpacing(10);
//
//        TextArea textArea = new TextArea();
//        textArea.setId("outputField");
//        textArea.setPrefSize(460, 380);
//        textArea.setEditable(false);      // read-only
//        textArea.setMouseTransparent(true);
//        textArea.setFocusTraversable(false);
//        obx.getChildren().add(textArea);
//
//        return obx;
//    }

//    private HBox inputHBoxBottom() {
//        HBox ibx = new HBox();
//        ibx.setPadding(new Insets(15, 12, 15, 12));
//        ibx.setSpacing(10);
//        //hb.setStyle("-fx-background-color: #336699;");
//
//        /* input field */
//        TextField txtFld = new TextField ();
//        txtFld.setId("inputField");
//        txtFld.setPrefSize(400, 20);
//        txtFld.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event){
//                // get the command string
//                String txt = txtFld.getText();
//
//                String[] response = userSelector.takeCommand(txt);
//                // TODO: write response to text area
//                System.out.println(txt);
//                // clear command area
//                txtFld.clear();
//            }
//        });
//
//        /* input button */
//        Button enter_btn = new Button();
//        enter_btn.setId("EnterButton");
//        enter_btn.setText("Enter");
//        enter_btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // get the command string
//                String txt = txtFld.getText();
//
//                String[] response = userSelector.takeCommand(txt);
//                // TODO: write response to text area
//
//                // clear command area
//                txtFld.clear();
//            }
//        });
//        enter_btn.setPrefSize(100, 20);
//
//        ibx.getChildren().addAll(txtFld, enter_btn);
//
//        return ibx;
//    }

//    private BorderPane borderContents() {
//        BorderPane page = new BorderPane();
//        page.setPadding(new Insets(0, 10, 0, 10));
//
//        // call input HBox at the bottom of the border pane
//        page.setBottom(inputHBoxBottom());
//        // call output HBox at the center of the border pane
//        page.setCenter(outputHBoxCenter());
//        return page;
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // set current user
        Group root = new Group();
        Scene scene = new Scene(root, 500, 450);
        TabPane tabPane = new TabPane();
        BorderPane edge = new BorderPane();

        // temporary hard coded tabs with
        for (int i = 1; i < 6; i++) {
            Tab tab = new Tab(); // instanciates a new tab
            tab.setText("Client_" + i); // name the tab

            /* create output box */
            HBox obx = new HBox();
            obx.setPadding(new Insets(15, 12, 15, 12));
            obx.setSpacing(10);
            TextArea textArea = new TextArea();
            textArea.setId("outputField_" + i);
            textArea.setPrefSize(460, 380);
            textArea.setEditable(false);      // read-only
            textArea.setMouseTransparent(true);
            textArea.setFocusTraversable(false);
            obx.getChildren().add(textArea);

            /* create input box */
            HBox ibx = new HBox();
            ibx.setPadding(new Insets(15, 12, 15, 12));
            ibx.setSpacing(10);
                // input text field
            TextField txtFld = new TextField ();
            txtFld.setId("inputField_" + i);
            txtFld.setPrefSize(400, 20);
            txtFld.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // get the command string
                    String txt = txtFld.getText();

                    String[] response = userSelector.takeCommand(txt);
                    // TODO: write response to text area
                    TextArea a = (TextArea)scene.lookup("#outputField_1");
                    System.out.println(txt);
                    a.setText(txt);

                    // clear command area
                    txtFld.clear();

                }
            }); // add event handler
                // enter button
            Button enter_btn = new Button();
            enter_btn.setId("EnterButton_" + i);
            enter_btn.setText("Enter");
            enter_btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // get the command string
                    String txt = txtFld.getText();

                    String[] response = userSelector.takeCommand(txt);
                    // TODO: write response to text area

                    // clear command area
                    txtFld.clear();
                }
            });
            enter_btn.setPrefSize(100, 20);
            ibx.getChildren().addAll(txtFld, enter_btn);

            /* cluster altogether into a border pane*/
            BorderPane page = new BorderPane();
            page.setPadding(new Insets(0, 10, 0, 10));

                // call input HBox at the bottom of the border pane
            page.setBottom(ibx);
                // call output HBox at the center of the border pane
            page.setCenter(obx);

            /* add the page into the tab */
            tab.setContent(page); // call contents constructor method
            tabPane.getTabs().add(tab); // attach the tab to the tabPane
        }
        edge.prefHeightProperty().bind(scene.heightProperty());
        edge.prefWidthProperty().bind(scene.widthProperty());
        edge.setCenter(tabPane);
        root.getChildren().add(edge);

        this.userSelector.changeUser("user1");
        // set title of entire window
        primaryStage.setTitle("AFRS");


        // show window
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
