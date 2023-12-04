package views;

import AdventureModel.AdventureGame;
import AdventureModel.Interactions.Choice;
import AdventureModel.Interactions.ChoiceOption;
import AdventureModel.Interactions.Interaction;
import AdventureModel.Minigames.Battle.Battle;
import AdventureModel.Minigames.Minigame;
import AdventureModel.Movement.ForcedQueue;
import AdventureModel.Movement.Room;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.AccessibleRole;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Class AdventureGameView.
 *
 * This is the Class that will visualize your model.
 * You are asked to demo your visualization via a Zoom
 * recording. Place a link to your recording below.
 *
 * ZOOM LINK: <URL HERE>
 * PASSWORD: <PASSWORD HERE>
 */
public class AdventureGameView {

    AdventureGame model; //model of the game
    Stage stage; //stage on which all is rendered
    Button saveButton, loadButton, helpButton, pauseButton, invertButton; //buttons
    Boolean invertToggle = false;
    Boolean menuToggle = false;
    Boolean helpToggle = false; //is help on display?

    ArrayList<Button> moves = new ArrayList<>();
    public GridPane gridPane = new GridPane(); //to hold images and buttons
    Label roomDescLabel = new Label(); //to hold room description and/or instructions
    VBox objectsInRoom = new VBox(); //to hold room items
    VBox objectsInInventory = new VBox(); //to hold inventory items
    ImageView roomImageView; //to hold room image
    TextField inputTextField; //for user input

    private MediaPlayer mediaPlayer; //to play audio
    private boolean mediaPlaying; //to know if the audio is playing

    /**
     * Adventure Game View Constructor
     * __________________________
     * Initializes attributes
     */
    public AdventureGameView(AdventureGame model, Stage stage) {
        this.model = model;
        this.stage = stage;
        intiUI();
    }

    /**
     * Initialize the UI
     */
    public void intiUI() {

        // setting up the stage
        this.stage.setTitle("Rhythm Labyrinth"); //Replace <YOUR UTORID> with your UtorID

        //Inventory + Room items
        objectsInInventory.setSpacing(10);
        objectsInInventory.setAlignment(Pos.TOP_CENTER);
        objectsInRoom.setSpacing(10);
        objectsInRoom.setAlignment(Pos.TOP_CENTER);

        // GridPane, anyone?
        gridPane.setPadding(new Insets(20));
        gridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#000000"),
                new CornerRadii(0),
                new Insets(0)
        )));

        //Three columns, three rows for the GridPane
        ColumnConstraints column1 = new ColumnConstraints(150);
        ColumnConstraints column2 = new ColumnConstraints(650);
        ColumnConstraints column3 = new ColumnConstraints(150);
        column3.setHgrow( Priority.SOMETIMES ); //let some columns grow to take any extra space
        column1.setHgrow( Priority.SOMETIMES );

        // Row constraints
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints( 550 );
        RowConstraints row3 = new RowConstraints();
        row1.setVgrow( Priority.SOMETIMES );
        row3.setVgrow( Priority.SOMETIMES );

        gridPane.getColumnConstraints().addAll( column1 , column2 , column1 );
        gridPane.getRowConstraints().addAll( row1 , row2 , row1 );

        // Buttons
        saveButton = new Button("Save");
        saveButton.setId("Save");
        customizeButton(saveButton, 100, 50);
        makeButtonAccessible(saveButton, "Save Button", "This button saves the game.", "This button saves the game. Click it in order to save your current progress, so you can play more later.");
        addSaveEvent();

        loadButton = new Button("Load");
        loadButton.setId("Load");
        customizeButton(loadButton, 100, 50);
        makeButtonAccessible(loadButton, "Load Button", "This button loads a game from a file.", "This button loads the game from a file. Click it in order to load a game that you saved at a prior date.");
        addLoadEvent();

        helpButton = new Button("Instructions");
        helpButton.setId("Instructions");
        customizeButton(helpButton, 200, 50);
        makeButtonAccessible(helpButton, "Help Button", "This button gives game instructions.", "This button gives instructions on the game controls. Click it to learn how to play.");
        addInstructionEvent();


        HBox topButtons = new HBox();
        topButtons.getChildren().addAll(saveButton, loadButton);
        topButtons.setSpacing(10);
        topButtons.setAlignment(Pos.CENTER);

        pauseButton = new Button("MENU");
        customizeButton(pauseButton, 200, 50);
        pauseButton.setOnAction(e -> openMenu(topButtons));
//        inputTextField = new TextField();
//        inputTextField.setFont(new Font("Arial", 16));
//        inputTextField.setFocusTraversable(true);
//
//        inputTextField.setAccessibleRole(AccessibleRole.TEXT_AREA);
//        inputTextField.setAccessibleRoleDescription("Text Entry Box");
//        inputTextField.setAccessibleText("Enter commands in this box.");
//        inputTextField.setAccessibleHelp("This is the area in which you can enter commands you would like to play.  Enter a command and hit return to continue.");
//        addTextHandlingEvent(); //attach an event to this input field

        //labels for inventory and room items
//        Label objLabel =  new Label("Objects in Room");
//        objLabel.setAlignment(Pos.CENTER);
//        objLabel.setStyle("-fx-text-fill: white;");
//        objLabel.setFont(new Font("Arial", 16));
//        addClickEvent();
//
//        Label invLabel =  new Label("Your Inventory");
//        invLabel.setAlignment(Pos.CENTER);
//        invLabel.setStyle("-fx-text-fill: white;");
//        invLabel.setFont(new Font("Arial", 16));

        //add all the widgets to the GridPane
//        gridPane.add( objLabel, 0, 0, 1, 1 );  // Add label
        gridPane.add( pauseButton, 0, 0, 1, 1 );  // Add buttons
        gridPane.add(helpButton, 2,0);
//        gridPane.add( invLabel, 2, 0, 1, 1 );  // Add label

//        Label commandLabel = new Label("What would you like to do?");
//        commandLabel.setStyle("-fx-text-fill: white;");
//        commandLabel.setFont(new Font("Arial", 16));

        updateScene(""); //method displays an image and whatever text is supplied
        queueCycle();

        // Movement buttons, subject to change
        Button northButton = new Button("North");
        Button southButton = new Button("South");
        Button westButton = new Button("West");
        Button eastButton = new Button("East");
        Collections.addAll(moves, northButton, southButton, westButton, eastButton);
        for (Button direction : moves) {
            styleMovementButtons(direction);
            direction.setOnAction(e -> {
                submitEvent(direction.getText());
            });
            direction.setDisable(true);
        }
        VBox northDirection = new VBox(northButton);
        northDirection.setAlignment(Pos.BOTTOM_CENTER);
//        northDirection.
        VBox southDirection = new VBox(southButton);
        southDirection.setAlignment(Pos.TOP_CENTER);
        VBox westDirection = new VBox(westButton);
        westDirection.setAlignment(Pos.CENTER);
        VBox eastDirection = new VBox(eastButton);
        eastDirection.setAlignment(Pos.CENTER);
        gridPane.add(northDirection, 1, 0);
        gridPane.add(southDirection, 1, 2);
        gridPane.add(westDirection, 0, 1);
        gridPane.add(eastDirection, 2, 1);

        // adding the text area and submit button to a VBox
//        VBox textEntry = new VBox();
//        textEntry.setStyle("-fx-background-color: #000000;");
//        textEntry.setPadding(new Insets(20, 20, 20, 20));
//        textEntry.getChildren().addAll(commandLabel, inputTextField);
//        textEntry.setSpacing(10);
//        textEntry.setAlignment(Pos.CENTER);
//        gridPane.add( textEntry, 0, 2, 3, 1 );

        // Render everything
        var scene = new Scene( gridPane ,  1000, 800);
        scene.setFill(Color.BLACK);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
//        updateScene("", "move"); //method displays an image and whatever text is supplied

    }
    private void openMenu(HBox topButtons) {
        if (!menuToggle) {
            menuToggle = true;
            roomImageView.setImage(null);
            roomImageView.setFitWidth(0);
            roomImageView.setFitHeight(0);

            Label textToDisplay = new Label("Save or Load a game. You can also invert the colours for accessibility purposes.");
            formatText(textToDisplay.getText()); //format the text to display
            roomDescLabel.setPrefWidth(500);
            roomDescLabel.setPrefHeight(500);
            roomDescLabel.setTextOverrun(OverrunStyle.CLIP);
            roomDescLabel.setWrapText(true);
            VBox roomPane = new VBox(roomImageView, roomDescLabel, topButtons);
            roomPane.setPadding(new Insets(10));
            roomPane.setAlignment(Pos.TOP_CENTER);
            roomPane.setStyle("-fx-background-color: #000000;");

            gridPane.add(roomPane, 1, 1);
            stage.sizeToScene();
        } else {
            updateScene("");
            submitEvent("LOOK");
            menuToggle = false;
        }
    }

    /**
     * CSS styling for the directions
     * @param direction is the button to style
     */
    private void styleMovementButtons(Button direction) {
        direction.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0);" +
                        "-fx-border-color: rgba(74, 74, 74, 0.075);" +
                        "-fx-border-width: 0px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-text-fill: #ffffff;" +
                        "-fx-padding: 2em;" +
                        "-fx-font-weight: bold;"
        );
        // Add hover effect
        direction.setOnMouseEntered(e -> direction.setStyle(
                "-fx-text-fill: #ff7583;" +
                        "-fx-background-color: rgba(255, 255, 255, 0);"+
                        "-fx-border-width: 0px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-padding: 2em;" +
                        "-fx-font-weight: bold;"
        ));

        direction.setOnMouseExited(e -> direction.setStyle(
                "-fx-text-fill: #ffffff;" +
                        "-fx-background-color: rgba(255, 255, 255, 0);"+
                        "-fx-border-width: 0px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-padding: 2em;" +
                        "-fx-font-weight: bold;"
        ));
    }


    /**
     * makeButtonAccessible
     * __________________________
     * For information about ARIA standards, see
     * https://www.w3.org/WAI/standards-guidelines/aria/
     *
     * @param inputButton the button to add screenreader hooks to
     * @param name ARIA name
     * @param shortString ARIA accessible text
     * @param longString ARIA accessible help text
     */
    public static void makeButtonAccessible(Button inputButton, String name, String shortString, String longString) {
        inputButton.setAccessibleRole(AccessibleRole.BUTTON);
        inputButton.setAccessibleRoleDescription(name);
        inputButton.setAccessibleText(shortString);
        inputButton.setAccessibleHelp(longString);
        inputButton.setFocusTraversable(true);
    }

    /**
     * customizeButton
     * __________________________
     *
     * @param inputButton the button to make stylish :)
     * @param w width
     * @param h height
     */
    private void customizeButton(Button inputButton, int w, int h) {
        inputButton.setPrefSize(w, h);
        inputButton.setFont(new Font("Arial", 16));
        inputButton.setStyle("-fx-background-color: #a81132; -fx-text-fill: white;");
    }

    /**
     * addTextHandlingEvent
     * __________________________
     * Add an event handler to the myTextField attribute 
     *
     * Your event handler should respond when users 
     * hits the ENTER or TAB KEY. If the user hits 
     * the ENTER Key, strip white space from the
     * input to myTextField and pass the stripped 
     * string to submitEvent for processing.
     *
     * If the user hits the TAB key, move the focus 
     * of the scene onto any other node in the scene 
     * graph by invoking requestFocus method.
     */
    private void addTextHandlingEvent() {
        inputTextField.setOnKeyPressed(e ->{
            if (e.getCode() == KeyCode.ENTER){
                String inputText = this.inputTextField.getText();
                submitEvent(inputText);
                inputTextField.setText("");
            }
        });
    }

    /**
     * addEnterEvent
     * __________________________
     * Add an event handler to the roomDescLabel attribute
     *
     * Your event handler should respond when users
     * click the left mouse KEY. If the user clicks
     * the left mouse key, queueCycle.
     */
    private void addClickEvent() {
        roomDescLabel.setOnMouseClicked((click) -> queueCycle());
        roomDescLabel.requestFocus();
    }


    /**
     * submitEvent
     * __________________________
     *
     * @param text the command that needs to be processed
     */
    private void submitEvent(String text) {

        text = text.strip(); //get rid of white space
        stopArticulation(); //if speaking, stop

        if (text.equalsIgnoreCase("LOOK") || text.equalsIgnoreCase("L")) {
            String roomDesc = this.model.getPlayer().getCurrentRoom().getRoomDescription() + "\n\nAvailable moves:" +
                    this.model.getPlayer().getCurrentRoom().getCommands();
            roomDescLabel.setText(roomDesc);
            articulateRoomDescription(); //all we want, if we are looking, is to repeat description.
            return;
        } else if (text.equalsIgnoreCase("HELP") || text.equalsIgnoreCase("H")) {
            showInstructions();
            return;
        } else if (text.equalsIgnoreCase("COMMANDS") || text.equalsIgnoreCase("C")) {
            return;
        }

        //try to move!
        String output = this.model.interpretAction(text); //process the command!

        if (output == null || (!output.equals("GAME OVER") && !output.equals("FORCED") && !output.equals("HELP"))) {
            updateScene(output);
            queueCycle();
        } else if (output.equals("GAME OVER")) {
            updateScene("");
            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        } else if (output.equals("FORCED")) {
            String roomDesc = this.model.getPlayer().getCurrentRoom().getRoomDescription();
            updateScene(roomDesc + "\n\nObjects in this room:\n");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {
                        submitEvent("FORCED");
                    }
                    );
            pause.play();
        }
    }




    /**
     * updateScene
     * __________________________
     *
     * Show the current room, and print some text below it.
     * If the input parameter is not null, it will be displayed
     * below the image.
     * Otherwise, the current room description will be displayed
     * below the image.
     * 
     * @param textToDisplay the text to display below the image.
     */
    public void updateScene(String textToDisplay) {

        getRoomImage(); //get the image of the current room
        formatText(textToDisplay); //format the text to display
        roomDescLabel.setPrefWidth(500);
        roomDescLabel.setPrefHeight(500);
        roomDescLabel.setTextOverrun(OverrunStyle.CLIP);
        roomDescLabel.setWrapText(true);
        VBox roomPane = new VBox(roomImageView,roomDescLabel);
        roomPane.setPadding(new Insets(10));
        roomPane.setAlignment(Pos.TOP_CENTER);
        roomPane.setStyle("-fx-background-color: #000000;");

        gridPane.add(roomPane, 1, 1);
        stage.sizeToScene();

        //finally, articulate the description
        if (textToDisplay == null || textToDisplay.isBlank()) articulateRoomDescription();
    }

    public void updateScene(String textToDisplay, String key) { // TODO: Implement MOVE

        if (key.equals("instructions")) {
            roomImageView.setImage(null);
            roomImageView.setFitWidth(0);
            roomImageView.setFitHeight(0);

            formatText(textToDisplay); //format the text to display
            roomDescLabel.setPrefWidth(500);
            roomDescLabel.setPrefHeight(500);
            roomDescLabel.setTextOverrun(OverrunStyle.CLIP);
            roomDescLabel.setWrapText(true);
            VBox roomPane = new VBox(roomImageView, roomDescLabel);
            roomPane.setPadding(new Insets(10));
            roomPane.setAlignment(Pos.TOP_CENTER);
            roomPane.setStyle("-fx-background-color: #000000;");

            gridPane.add(roomPane, 1, 1);
            stage.sizeToScene();

            //finally, articulate the description
            if (textToDisplay == null || textToDisplay.isBlank()) articulateRoomDescription();
        }
        if (key.equals("puzzle")) {
            updateScene(textToDisplay);
            GridPane puzzleDisplay = new GridPane();
            puzzleDisplay.setPadding(new Insets(20));
            puzzleDisplay.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#000000"),
                    new CornerRadii(0),
                    new Insets(0)
            )));

            //Three columns, three rows for the GridPane
            ColumnConstraints column1 = new ColumnConstraints(150);
            ColumnConstraints column2 = new ColumnConstraints(650);
            ColumnConstraints column3 = new ColumnConstraints(150);
//            column3.setHgrow( Priority.SOMETIMES ); //let some columns grow to take any extra space
//            column1.setHgrow( Priority.SOMETIMES );

            // Row constraints
            RowConstraints row1 = new RowConstraints();
            RowConstraints row2 = new RowConstraints( 650 );
            RowConstraints row3 = new RowConstraints();
//            row1.setVgrow( Priority.SOMETIMES );
//            row3.setVgrow( Priority.SOMETIMES );

            puzzleDisplay.getColumnConstraints().addAll( column1 , column2 , column3 );
            puzzleDisplay.getRowConstraints().addAll( row1 , row2 , row3 );
            Label titleText = new Label("COMPLETE THIS PUZZLE TO MOVE ON");
            titleText.setStyle("-fx-text-fill: white;"+"-fx-font-weight: bold;");
            titleText.setFont(new Font("Arial", 24));
            HBox title = new HBox(titleText);
            title.setAlignment(Pos.BOTTOM_CENTER);
            puzzleDisplay.add(title,1,0);
            Label instructionText = new Label("Match the sequence shown to you to pass");
            instructionText.setStyle("-fx-text-fill: white;"+"-fx-font-weight: bold;");
            titleText.setFont(new Font("Arial", 20));
            instructionText.setWrapText(true);
            puzzleDisplay.add(instructionText,2,1);

//            var scene = new Scene( puzzleDisplay ,  1000, 800);
//            this.stage.setScene(scene);
            this.stage.getScene().setRoot(puzzleDisplay);
            this.stage.show();
//            gridPane.add(burh,0,0);
//            System.out.println("Puzzle");
        }
        else if (key.equals("battle")) {
            updateScene(textToDisplay);
            GridPane battleDisplay = new GridPane();
            battleDisplay.setPadding(new Insets(20));
            battleDisplay.setBackground(new Background(new BackgroundFill(
                    Color.valueOf("#000000"),
                    new CornerRadii(0),
                    new Insets(0)
            )));

            //Three columns, three rows for the GridPane
            ColumnConstraints column1 = new ColumnConstraints(150);
            ColumnConstraints column2 = new ColumnConstraints(650);
            ColumnConstraints column3 = new ColumnConstraints(150);
            column3.setHgrow( Priority.SOMETIMES ); //let some columns grow to take any extra space
            column1.setHgrow( Priority.SOMETIMES );

            // Row constraints
            RowConstraints row1 = new RowConstraints();
            RowConstraints row2 = new RowConstraints( 650 );
            RowConstraints row3 = new RowConstraints();
            row1.setVgrow( Priority.SOMETIMES );
            row3.setVgrow( Priority.SOMETIMES );

            battleDisplay.getColumnConstraints().addAll( column1 , column2 , column1 );
            battleDisplay.getRowConstraints().addAll( row1 , row2 , row1 );
            Label titleText = new Label("YOU ARE IN A BATTLE NOW");
            titleText.setStyle("-fx-text-fill: white;"+"-fx-font-weight: bold;");
            titleText.setFont(new Font("Arial", 24));
            HBox title = new HBox(titleText);
            title.setAlignment(Pos.BOTTOM_CENTER);
            battleDisplay.add(title,1,0);
            battleDisplay.setAlignment(Pos.CENTER);

//            var scene = new Scene( battleDisplay , 1000, 800);
//            this.stage.setScene(scene);
            this.stage.getScene().setRoot(battleDisplay);
            this.stage.show();
        }
    }

    public void playGame(Minigame minigame) {
        if (minigame.minigameType.equals("battle")) {
            GridPane curPane = (GridPane) this.stage.getScene().getRoot();
//            ProgressBar healthBar = new ProgressBar();

            curPane.add(minigame.createGamePane(this), 1, 1);
        }
        else if (minigame.minigameType.equals("puzzle")) {
            GridPane curPane = (GridPane) this.stage.getScene().getRoot();
//            ProgressBar healthBar = new ProgressBar();
//            curPane.setAlignment(Pos.CENTER);
            curPane.add(minigame.createGamePane(this), 1, 1);
        }
    }

    public void finishGame() {
//        this.stage.getScene().
//        var scene = new Scene( gridPane , 1000, 800);
        this.stage.getScene().setRoot(gridPane);
        this.stage.show();
    }

    public void updateScene(String textToDisplay, Choice choice) { // TODO: Implement MOVE

//        if (key.equals("instructions")) {
//            roomImageView.setImage(null);
//            roomImageView.setFitWidth(0);
//            roomImageView.setFitHeight(0);
//
//            formatText(textToDisplay); //format the text to display
//            roomDescLabel.setPrefWidth(500);
//            roomDescLabel.setPrefHeight(500);
//            roomDescLabel.setTextOverrun(OverrunStyle.CLIP);
//            roomDescLabel.setWrapText(true);
//            VBox roomPane = new VBox(roomImageView, roomDescLabel);
//            roomPane.setPadding(new Insets(10));
//            roomPane.setAlignment(Pos.TOP_CENTER);
//            roomPane.setStyle("-fx-background-color: #000000;");
//
//            gridPane.add(roomPane, 1, 1);
//            stage.sizeToScene();
//
//            //finally, articulate the description
//            if (textToDisplay == null || textToDisplay.isBlank()) articulateRoomDescription();
//        }
        getRoomImage(); //get the image of the current room
        formatText(textToDisplay); //format the text to display
        roomDescLabel.setPrefWidth(500);
        roomDescLabel.setPrefHeight(500);
        roomDescLabel.setTextOverrun(OverrunStyle.CLIP);
        roomDescLabel.setWrapText(true);
        VBox roomPane = new VBox(roomImageView,roomDescLabel);
        roomPane.setPadding(new Insets(10));
        roomPane.setAlignment(Pos.TOP_CENTER);
        roomPane.setStyle("-fx-background-color: #000000;");



        //finally, articulate the description
//        if (textToDisplay == null || textToDisplay.isBlank()) articulateRoomDescription();

        HBox clickableOptions = new HBox();
        for (ChoiceOption option : choice.getOptions()){
            // TODO: Populate optionsView with option buttons
//            s += "\n\tOption: " + option.getOptionText();
            Button button = new Button(option.getOptionText());
            button.setOnAction(e -> {
                option.execute(this);
            });
            clickableOptions.getChildren().add(button);
        }
        clickableOptions.setAlignment(Pos.BOTTOM_CENTER);
        roomPane.getChildren().add(clickableOptions);

        gridPane.add(roomPane, 1, 1);
        stage.sizeToScene();
    }

    private void queueCycle(){
        Room room = this.model.getPlayer().getCurrentRoom();
        ForcedQueue q = room.getQueue();
        if (!q.is_empty()) {
            Interaction i = q.dequeue();
            i.execute(this);
        } else {
            q.refresh();
            for (Button direction : moves) {
                direction.setDisable(false);
            }
        }
    }

    /**
     * formatText
     * __________________________
     *
     * Format text for display.
     * 
     * @param textToDisplay the text to be formatted for display.
     */
    private void formatText(String textToDisplay) {
        if (textToDisplay == null || textToDisplay.isBlank()) {
            ;
        } else roomDescLabel.setText(textToDisplay);
        roomDescLabel.setStyle("-fx-text-fill: white;");
        roomDescLabel.setFont(new Font("Arial", 16));
        roomDescLabel.setAlignment(Pos.CENTER);
    }

    /**
     * getRoomImage
     * __________________________
     *
     * Get the image for the current room and place 
     * it in the roomImageView 
     */
    private void getRoomImage() {

        int roomNumber = this.model.getPlayer().getCurrentRoom().getRoomNumber();
        String roomImage = this.model.getDirectoryName() + "/room-images/" + roomNumber + ".png";

        Image roomImageFile = new Image(roomImage);
        roomImageView = new ImageView(roomImageFile);
        roomImageView.setPreserveRatio(true);
        roomImageView.setFitWidth(400);
        roomImageView.setFitHeight(400);

        //set accessible text
        roomImageView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        roomImageView.setAccessibleText(this.model.getPlayer().getCurrentRoom().getRoomDescription());
        roomImageView.setFocusTraversable(true);
    }

    /*
     * Show the game instructions.
     *
     * If helpToggle is FALSE:
     * -- display the help text in the CENTRE of the gridPane (i.e. within cell 1,1)
     * -- use whatever GUI elements to get the job done!
     * -- set the helpToggle to TRUE
     * -- REMOVE whatever nodes are within the cell beforehand!
     *
     * If helpToggle is TRUE:
     * -- redraw the room image in the CENTRE of the gridPane (i.e. within cell 1,1)
     * -- set the helpToggle to FALSE
     * -- Again, REMOVE whatever nodes are within the cell beforehand!
     */
    public void showInstructions() {

        if (!helpToggle) {
            updateScene(this.model.getInstructions(), "instructions");
            helpToggle = true;
        } else {
            updateScene("");
            submitEvent("LOOK");
            helpToggle = false;
        }
    }

    /**
     * This method handles the event related to the
     * help button.
     */
    public void addInstructionEvent() {
        helpButton.setOnAction(e -> {
            stopArticulation(); //if speaking, stop
            showInstructions();
        });
    }

    /**
     * This method handles the event related to the
     * save button.
     */
    public void addSaveEvent() {
        saveButton.setOnAction(e -> {
            gridPane.requestFocus();
            SaveView saveView = new SaveView(this);
        });
    }

    /**
     * This method handles the event related to the
     * load button.
     */
    public void addLoadEvent() {
        loadButton.setOnAction(e -> {
            gridPane.requestFocus();
            LoadView loadView = new LoadView(this);
        });
    }


    /**
     * This method articulates Room Descriptions
     */
    public void articulateRoomDescription() {
        String musicFile;
        String adventureName = this.model.getDirectoryName();
        String roomName = this.model.getPlayer().getCurrentRoom().getRoomName();

        if (!this.model.getPlayer().getCurrentRoom().getVisited()) musicFile = "./" + adventureName + "/sounds/" + roomName.toLowerCase() + "-long.mp3" ;
        else musicFile = "./" + adventureName + "/sounds/" + roomName.toLowerCase() + "-short.mp3" ;
        musicFile = musicFile.replace(" ","-");

        Media sound = new Media(new File(musicFile).toURI().toString());

        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlaying = true;

    }

    /**
     * This method stops articulations 
     * (useful when transitioning to a new room or loading a new game)
     */
    public void stopArticulation() {
        if (mediaPlaying) {
            mediaPlayer.stop(); //shush!
            mediaPlaying = false;
        }
    }

    public AdventureGame getModel() {return this.model;}
    public GridPane getCurrentPane() {return (GridPane) this.stage.getScene().getRoot();}
}
