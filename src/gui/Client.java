package gui;

import information.AirportManager;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import server.UserSelector;

import java.util.Optional;

/**
 * Client class made out of JavaFX for constructing
 * Graphical User Interface for AFRS.
 */
public class Client extends Application
{
    private UserSelector userSelector = new UserSelector();
    private int lastTabIndex = 0;

    /**
     * Start up the primary stage by calling this method
     * @param args: arguments that could taken with startup of javaFX launching
     */
    public static void main(String[] args)
    {
        launch(args);
    }


    /**
     * This is static method that controls input command from the
     * selected tab and pass it to the selected manager ( a.k.a. tab)
     *
     * @param user: the userSelector object that contains manager with Hash Map to transfer command to corresponding user
     * @param tab: specific tab within list of tabPane for output generation in corresponding textArea
     * @param txtFld: specific textField(input) within the tab
     * @param textArea: specific textArea(output) within the tab
     */
    private void inputAction(UserSelector user, Tab tab, TextField txtFld, TextArea textArea, boolean offline)
    {
        // select the appropriate user
        user.changeUser(tab.getText());
        AirportManager.getManager().setOffline(offline);

        // get text input
        String txt = txtFld.getText();

        // parse command and get response
        switch (txt) {
            case "undo;":
                textArea.appendText(userSelector.getCurrentManager().undo() + "\n");
                break;
            case "redo;":
                textArea.appendText(userSelector.getCurrentManager().redo() + "\n");
                break;
            default:
                // get response
                String[] response = user.takeCommand(txt);
                for (String resp : response)
                    textArea.appendText(resp + "\n");
                break;
        }

        // clear input command area
        txtFld.clear();
    }

    /**
     * create a new alert dialog that says user did not follow
     * what the developer intended to do
     * @param errorCode : String, main error message that goes to the header of the dialog
     * @param new_userID: String, default userID that will be produced instead of user intended string
     * @return Alert dialog
     */
    private Alert errorFoundDialog(String errorCode, String new_userID) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Encountered!");
        alert.setHeaderText(errorCode);
        alert.setContentText("Instead, your userID will be set to -> " + new_userID);
        return alert;
    }

    /**
     * Instead of directly resizing image data, take the
     * image into ImageView and shrink the image for its desired size
     * This method minimize the pixel loss for GUI.
     * @param imageView: ImageView object that wraps image object with directory
     * @return imageView: modified
     */
    private ImageView resizeImage(ImageView imageView) {
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    /**
     * Takes current state of user (whether user is in localDrive or networkConnection)
     * and set the toggle button with corresponding image for that
     * @param toggleButton: takes clicked status of toggleButton to differentiate current user status
     */
    private void getCorrespondingImage(ToggleButton toggleButton) {
        // change online/offline based on button
        if (toggleButton.isSelected())
            toggleButton.setGraphic(resizeImage(new ImageView(new Image(getClass().getResourceAsStream("images/internet.png")))));
        else
            toggleButton.setGraphic(resizeImage(new ImageView(new Image(getClass().getResourceAsStream("images/floppy_disk_icon.png")))));
    }

    /**
     * This method calls TabPane object to create
     * a brand new tab with specific id of input and output field
     * with corresponding buttons.
     *
     * @param tabPane: allocated tabPane from the primaryStage -> root scene.
     * @return Tab: added new tab with all I/O action contents in the provided tabPane
     */
    private Tab createAndSelectNewTab(final TabPane tabPane)
    {
        // get current tabs
        String userID;
        final ObservableList<Tab> tabs = tabPane.getTabs();
        int tabCount = tabs.size();

        // pop up dialog
        String defaultUserID = "Client " + tabs.size();
        TextInputDialog dialog = new TextInputDialog(defaultUserID);
        dialog.setTitle("New Client");
        dialog.setHeaderText("Enter user ID");
        dialog.setContentText("User ID:");

        Optional<String> result = dialog.showAndWait();

        // check if they hit cancel
        if (result.isPresent())
        {
            userID = result.get();
            if (!userID.isEmpty())
            {
                if (!userSelector.doesUserExist(userID))
                {
                    // pass all four counter conditions
                    this.userSelector.changeUser(userID);
                }
                else
                {
                    // call appropriate error code
                    String errorCode4 = "Your user ID has been detected as duplicate...";
                    userID = defaultUserID;
                    errorFoundDialog(errorCode4, defaultUserID).showAndWait();
                    this.userSelector.changeUser(defaultUserID);
                }
            }
            else
            {
                // call appropriate error code
                String errorCode3 = "You have left your user ID empty!";
                errorFoundDialog(errorCode3, defaultUserID).showAndWait();
                userID = defaultUserID;
                this.userSelector.changeUser(defaultUserID);
            }
        }
        else
        {
            // if they hit cancel, don't change the user
            tabPane.getSelectionModel().select(lastTabIndex);
            return null;
        }

        // create tab
        Tab tab = new Tab(userID);
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

        // create network/local toggle button
        ToggleButton toggleButton = new ToggleButton("", resizeImage(new ImageView(new Image(getClass().getResourceAsStream("images/floppy_disk_icon.png")))));
        toggleButton.addEventHandler(ActionEvent.ACTION, event -> {
            // set offline <-> online status
            // TODO: ASK Prof. Bobby whether or not undo command should also operate on online <-> offline switch
            AirportManager.getManager().setOffline(!toggleButton.isSelected());
            getCorrespondingImage(toggleButton);
        });
        toggleButton.setMinWidth(50);

        // create input text field
        TextField textField = new TextField ();
        textField.setPrefSize(400, 30);
        textField.setOnAction(event -> inputAction(userSelector, tab, textField, textArea, !toggleButton.isSelected()));
        textField.prefWidthProperty().bind(ibx.widthProperty());

        // create enter button
        Image enterImage = new Image(getClass().getResourceAsStream("images/key_enter.png"));
        ImageView enterImageView = new ImageView(enterImage);
        Button enterButton = new Button("", resizeImage(enterImageView));
        enterButton.setOnAction(event -> inputAction(userSelector, tab, textField, textArea, !toggleButton.isSelected()));
        enterButton.setMinWidth(50);

        // add all Input interface to the ibx<HBox>
        ibx.getChildren().addAll(textField, enterButton, toggleButton);

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


    /**
     * start up the JavaFX system with primary stage
     * @param primaryStage: stage that has root scene with every pane filled with content included
     * @throws Exception: if thrown error: keep going without letting user know about it
     */

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // create main GUI components
        Group root = new Group();
        Scene scene = new Scene(root, 500, 450);
        TabPane tabPane = new TabPane();
        BorderPane tabBorder = new BorderPane();

        // create new tab tab

        final Tab newTab = new Tab("");
        newTab.setGraphic(resizeImage(new ImageView(new Image(getClass().getResourceAsStream("images/symbol_add.png")))));
        newTab.setClosable(false);
        tabPane.getTabs().add(newTab);

        // add functionality for creating new tab
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldSelectedTab, newSelectedTab) -> {
            // set last tab index
            lastTabIndex = tabPane.getTabs().indexOf(oldSelectedTab);

            // check if we are creating a new tab
            if (newSelectedTab == newTab)
                createAndSelectNewTab(tabPane);
        });

        // create first tab
        while (tabPane.getTabs().size() < 2)
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
