package gui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    /**
     * Start up the primary stage by calling this method
     * @param args
     */
    public static void main(String[] args)
    {
        launch(args);
    }


    /**
     * This is static method that controls input command from the
     * selected tab and pass it to the selected manager ( a.k.a. tab)
     *
     * @param user
     * @param tab
     * @param txtFld
     * @param textArea
     */
    private void inputAction(UserSelector user, Tab tab, TextField txtFld,  TextArea textArea)
    {
        // select the appropriate user
        user.changeUser(tab.getText());

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

    private ImageView getCorrespondingImage(ImageView imageView) {

        return imageView;
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
        final String userID;
        final ObservableList<Tab> tabs = tabPane.getTabs();
        int tabCount = tabs.size();

        // pop up dialog
        TextInputDialog dialog = new TextInputDialog("Your UserID");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Log In <AFRS>");
        dialog.setContentText("Please enter your UserID:");

        Optional<String> result = dialog.showAndWait();
        String default_userID = "Client " + tabs.size();
        /*  Checks if
         *  1. retrieved cancel button
         *  2. retrieved result equals to the placeholder text?
         *  3. retrieved result equals to the empty string?
         *  4. retrieved result equals to any other existing client?
         *  @TODO: This is such a terrible code ... :( I need to make this less coupled
         */
        if (result.isPresent()) {
            if (!result.get().equals("Your UserID") ) {
                if (!result.get().equals("")){
                    if (!userSelector.doesUserExist(result.get())) {
                        // pass all four counter conditions
                        userID = result.get();
                        this.userSelector.changeUser(result.get());
                    } else {
                        // call appropriate error code
                        String errorCode4 = "Your UserID has been detected as duplicate";
                        errorFoundDialog(errorCode4, default_userID).showAndWait();
                        userID = default_userID;
                        this.userSelector.changeUser(default_userID);
                    }
                } else {
                    // call appropriate error code
                    String errorCode3 = "You have left empty for your UserID!";
                    errorFoundDialog(errorCode3, default_userID).showAndWait();
                    userID = default_userID;
                    this.userSelector.changeUser(default_userID);
                }
            } else {
                // call appropriate error code
                String errorCode2 = "You may not have placeholder as your UserID!";
                errorFoundDialog(errorCode2, default_userID).showAndWait();
                userID = default_userID;
                this.userSelector.changeUser(default_userID);
            }
        } else {
            // call appropriate error code
            String errorCode1 = "You have pressed cancel button!";
            errorFoundDialog(errorCode1, default_userID).showAndWait();
            userID = default_userID;
            this.userSelector.changeUser(default_userID);
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

        // create input text field
        TextField textField = new TextField ();
        textField.setPrefSize(400, 30);
        textField.setOnAction(event -> inputAction(userSelector, tab, textField, textArea));
        textField.prefWidthProperty().bind(ibx.widthProperty());

        // create enter button
        Image enterImage = new Image(getClass().getResourceAsStream("images/key_enter.png"));
        ImageView enterImageView = new ImageView(enterImage);
        Button enterButton = new Button("", resizeImage(enterImageView));
        enterButton.setOnAction(event -> inputAction(userSelector, tab, textField, textArea));
        enterButton.setMinWidth(50);

        // create network/local toggle button
        Image internetImage = new Image(getClass().getResourceAsStream("images/internet.png"));
        Image localDriveImage = new Image(getClass().getResourceAsStream("images/floppy_disk_icon.png"));
        ImageView internetImageView = new ImageView(internetImage);
        ImageView localDriveImageView = new ImageView(localDriveImage);
        ToggleButton toggleButton = new ToggleButton("", resizeImage(localDriveImageView));
        toggleButton.addEventHandler(ActionEvent.ACTION, event -> {
            //@TODO: change image and report current state
        });

        toggleButton.setOnAction(event -> toggleButton.setGraphic(resizeImage(internetImageView)));
        toggleButton.setMinWidth(50);
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
     *
     * @param primaryStage
     * @throws Exception
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
