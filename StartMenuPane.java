package AoJGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;


public class StartMenuPane extends Group 
{
    private GameEngine game;
    private Button btnPlay;
    private Button btnQuit;
    private Button btnCredits;
    private Button btnHTP;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    private Rectangle background = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

    public StartMenuPane() 
    {
        Font font = new Font("Times New Roman", 30);
        
        //Creating background image
        background.setWidth(primaryScreenBounds.getWidth());
        background.setHeight(primaryScreenBounds.getHeight());
        Image setting = new Image("file:Pictures/menu/AttackOnJavaJupiterMenu.jpg");
        ImagePattern settingP = new ImagePattern(setting);
        background.setFill(settingP);

        //Creating buttons
        //Play Button with its properties
        btnPlay = new Button("Play");
        btnPlay.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
        btnPlay.setTextFill(Color.DEEPPINK);
        btnPlay.setFont(font);
        btnPlay.setLayoutX(primaryScreenBounds.getWidth() / 2.25);
        btnPlay.setLayoutY(primaryScreenBounds.getHeight() * .3);
        
        //How to Play button with its properties
        btnHTP = new Button("How to play");
        btnHTP.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
        btnHTP.setTextFill(Color.DEEPPINK);
        btnHTP.setFont(font);
        btnHTP.setLayoutX(primaryScreenBounds.getWidth() / 2.25);
        btnHTP.setLayoutY(primaryScreenBounds.getHeight() * .4);

        //Credits Button with its properties
        btnCredits = new Button("Credits");
        btnCredits.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
        btnCredits.setTextFill(Color.DEEPPINK);
        btnCredits.setFont(font);
        btnCredits.setLayoutX(primaryScreenBounds.getWidth() / 2.25);
        btnCredits.setLayoutY(primaryScreenBounds.getHeight() * .5);

        //Quit Button with its properties
        btnQuit = new Button("Quit");
        btnQuit.setStyle("-fx-border-color: deeppink; -fx-background-color: pink;");
        btnQuit.setTextFill(Color.DEEPPINK);
        btnQuit.setFont(font);
        btnQuit.setLayoutX(primaryScreenBounds.getWidth() / 2.25);
        btnQuit.setLayoutY(primaryScreenBounds.getHeight() * .6);

        //Adding nodes to the pane
        this.getChildren().addAll(background, btnPlay, btnHTP, btnCredits, btnQuit);
    }

    public Button getPlayButton() 
    {
        return btnPlay;
    }
    
    public Button getCreditsButton()
    {
    	return btnCredits;
    }
    
    public Button getHTPButton()
    {
    	return btnHTP;
    }

    public Button getQuitButton() 
    {
        return btnQuit;
    }

    public void setGameEngine(GameEngine game) 
    {
        this.game = game;
    }

}
