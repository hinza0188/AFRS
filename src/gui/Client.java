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

    private static void inputAction(UserSelector user, Tab tab, TextField txtFld,  TextArea textArea) {
        // set user as tab name
        user.changeUser(tab.getText());

        // get text input
        String txt = txtFld.getText();

        // get response
        String[] response = user.takeCommand(txt);
        for (String resp : response)
            textArea.appendText(resp + "\n");

        // clear input command area
        txtFld.clear();
    }

    private Tab createAndSelectNewTab(final TabPane tabPane, final Scene scene) {
        final ObservableList<Tab> tabs = tabPane.getTabs();

        Tab tab = new Tab("Client " + tabs.size());
        tab.closableProperty().bind(Bindings.size(tabs).greaterThan(2));
        int tab_index = tabs.size();
        tabs.add(tab_index, tab);
        tabPane.getSelectionModel().select(tab);

        /* create output box */
        HBox obx = new HBox();
        obx.setPadding(new Insets(15, 12, 15, 12));
        obx.setSpacing(10);
        TextArea textArea = new TextArea();
        textArea.setId("outputField_" + tab_index);
        textArea.setPrefSize(460, 380);
        textArea.setEditable(false);  // read-only
        textArea.setText("Welcome to the AFRS System!\n");
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
            inputAction(userSelector, tab, txtFld, textArea);
        });

        // enter button
        Button enter_btn = new Button();
        enter_btn.setId("EnterButton_" + tab_index);
        enter_btn.setText("Enter");
        enter_btn.setOnAction(event -> {
            inputAction(userSelector, tab, txtFld, textArea);
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
            if (newSelectedTab == newTab)
                createAndSelectNewTab(tabPane, scene);
        });

        createAndSelectNewTab(tabPane, scene);

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
