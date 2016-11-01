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

public class Client extends Application
{
    private UserSelector userSelector = new UserSelector();

    public static void main(String[] args)
    {
        launch(args);
    }

    private static void inputAction(UserSelector user, Tab tab, TextField txtFld,  TextArea textArea)
    {
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

    private Tab createAndSelectNewTab(final TabPane tabPane)
    {
        // get current tabs
        final ObservableList<Tab> tabs = tabPane.getTabs();
        int tabCount = tabs.size();

        // create tab
        Tab tab = new Tab("Client " + tabs.size());
        tab.closableProperty().bind(Bindings.size(tabs).greaterThan(2));
        tabs.add(tabCount - 1, tab);

        // add to tab pane
        tabPane.getSelectionModel().select(tab);

        // create output box
        HBox obx = new HBox();
        obx.setPadding(new Insets(15, 12, 15, 12));
        obx.setSpacing(10);

        // create text area
        TextArea textArea = new TextArea();
        textArea.setPrefSize(460, 380);
        textArea.setEditable(false);
        textArea.setText("Welcome to the AFRS System!\n");
        textArea.prefWidthProperty().bind(obx.widthProperty());
        obx.getChildren().add(textArea);

        // create input box
        HBox ibx = new HBox();
        ibx.setPadding(new Insets(15, 12, 15, 12));
        ibx.setSpacing(10);

        // create input text field
        TextField textField = new TextField ();
        textField.setPrefSize(400, 20);
        textField.setOnAction(event -> {
            inputAction(userSelector, tab, textField, textArea);
        });
        textField.prefWidthProperty().bind(ibx.widthProperty());

        // create enter button
        Button enterButton = new Button();
        enterButton.setText("Enter");
        enterButton.setOnAction(event -> {
            inputAction(userSelector, tab, textField, textArea);
        });
        enterButton.setMinWidth(100);
        ibx.getChildren().addAll(textField, enterButton);

        // cluster altogether into a border pane
        BorderPane page = new BorderPane();
        page.setPadding(new Insets(0, 10, 0, 10));

        // set page with content
        page.setBottom(ibx);
        page.setCenter(obx);

        // add the page into the tab
        tab.setContent(page);

        return tab;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // create main GUI components
        Group root = new Group();
        Scene scene = new Scene(root, 500, 450);
        TabPane tabPane = new TabPane();
        BorderPane tabBorder = new BorderPane();

        // create new tab tab
        final Tab newTab = new Tab("+");
        newTab.setClosable(false);
        tabPane.getTabs().add(newTab);

        // add functionality for creating new tab
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldSelectedTab, newSelectedTab) -> {
            if (newSelectedTab == newTab)
                createAndSelectNewTab(tabPane);
        });

        // create first tab
        createAndSelectNewTab(tabPane);

        // add tab properties
        tabBorder.prefHeightProperty().bind(scene.heightProperty());
        tabBorder.prefWidthProperty().bind(scene.widthProperty());
        tabBorder.setCenter(tabPane);
        root.getChildren().add(tabBorder);

        // show window
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(200);
        primaryStage.setTitle("AFRS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
