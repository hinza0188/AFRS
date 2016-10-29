package gui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
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

    private Tab createAndSelectNewTab(final TabPane tabPane, final Scene scene) {
        Tab tab = new Tab("New Client");
        final ObservableList<Tab> tabs = tabPane.getTabs();
        tab.closableProperty().bind(Bindings.size(tabs).greaterThan(2));
        int tab_index = tabs.size()-1;
        tabs.add(tab_index, tab);
        tabPane.getSelectionModel().select(tab);

        this.userSelector.currentManager = null; // make sure user is not created yet

        /* create output box */
        HBox obx = new HBox();
        obx.setPadding(new Insets(15, 12, 15, 12));
        obx.setSpacing(10);
        TextArea textArea = new TextArea();
        textArea.setId("outputField_" + tab_index);
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
        txtFld.setId("inputField_" + tab_index);
        txtFld.setPrefSize(400, 20);
        txtFld.setOnAction(event -> {
            String txt = txtFld.getText();
            if (userSelector.currentManager == null){
                // if user is not allocated with id, create one
                userSelector.changeUser(txt);
                tab.setText(txt); // change the tab display with user id
                textArea.appendText("\nLog in success! your user_ID: ");
            } else { //otherwise, proceed taking input text as command
                //TODO: Do something with response
                String[] response = userSelector.takeCommand(txt);
            }
            // Get Output Field
            TextArea outputField = (TextArea)scene.lookup("#outputField_" + tab_index);
            // Append the input Command
            outputField.appendText(txt + "\n");

            // clear input command area
            txtFld.clear();

        }); // add event handler
        // enter button
        Button enter_btn = new Button();
        enter_btn.setId("EnterButton_" + tab_index);
        enter_btn.setText("Enter");
        enter_btn.setOnAction(event -> {
            //TODO: I gotta figure out how to call above action code
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

        return tab;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // set current user
        Group root = new Group();
        Scene scene = new Scene(root, 500, 450);
        TabPane tabPane = new TabPane();
        BorderPane tabBorder = new BorderPane();

        final Tab newTab = new Tab("+");
        newTab.setClosable(false);
        tabPane.getTabs().add(newTab);

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldSelectedTab, newSelectedTab) -> {
            if (newSelectedTab == newTab) {
                createAndSelectNewTab(tabPane, scene);
            }
        });

        Tab init = createAndSelectNewTab(tabPane, scene);
        tabPane.getTabs().add(init);

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
