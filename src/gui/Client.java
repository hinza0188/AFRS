package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        // set current user
        Group root = new Group();
        Scene scene = new Scene(root, 500, 450);
        TabPane tabPane = new TabPane();
        BorderPane tabBorder = new BorderPane();

        Tab tab = new Tab();  // instanciates a new tab
        tab.setText("New Client");  // name the tab

        this.userSelector.currentManager = null; // make sure user is not created yet

        /* create output box */
        HBox obx = new HBox();
        obx.setPadding(new Insets(15, 12, 15, 12));
        obx.setSpacing(10);
        TextArea textArea = new TextArea();
        textArea.setId("outputField_0");
        textArea.setPrefSize(460, 380);
        textArea.setEditable(false);  // read-only
        textArea.setMouseTransparent(true);
        textArea.setFocusTraversable(false);
        textArea.setText("Welcome to the AFRS System!\nPlease provide your username");
        obx.getChildren().add(textArea);

        /* create input box */
        HBox ibx = new HBox();
        ibx.setPadding(new Insets(15, 12, 15, 12));
        ibx.setSpacing(10);
            // input text field
        TextField txtFld = new TextField ();
        txtFld.setId("inputField_0");
        txtFld.setPrefSize(400, 20);
        txtFld.setOnAction(event -> {
            String txt = txtFld.getText();
            //TODO: Do something with response
            if (userSelector.currentManager == null){
                // if user is not allocated with id, create one
                userSelector.changeUser(txt);
                tab.setText(txt); // change the tab display with user id
                textArea.appendText("\nLog in success! your user_ID: ");
            } else { //otherwise, proceed taking input text as command
                String[] response = userSelector.takeCommand(txt);
            }
            // Get Output Field
            TextArea outputField = (TextArea)scene.lookup("#outputField_0");
            // Append the input Command
            outputField.appendText(txt + "\n");

            // clear input command area
            txtFld.clear();

        }); // add event handler
            // enter button
        Button enter_btn = new Button();
        enter_btn.setId("EnterButton_0");
        enter_btn.setText("Enter");
        enter_btn.setOnAction(event -> {
            // get the command string
            String txt = txtFld.getText();
            String[] response = userSelector.takeCommand(txt);
            TextArea outputField = (TextArea)scene.lookup("#outputField_0");
            outputField.appendText(txt);
            // clear input command area
            txtFld.clear();
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

        tabBorder.prefHeightProperty().bind(scene.heightProperty());
        tabBorder.prefWidthProperty().bind(scene.widthProperty());
        tabBorder.setCenter(tabPane);
        root.getChildren().add(tabBorder);

        // show window
        primaryStage.setTitle("AFRS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
