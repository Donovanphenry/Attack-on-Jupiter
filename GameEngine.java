package AoJGame;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameEngine 
{
    private GamePane gamePane;
    private StartMenuPane startPane;
    private Scene scene;
    private Scene gameScene;
    private AnimationTimer gameLoop;
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    private final double GROUNDLEVEL = primaryScreenBounds.getHeight()*(.84);
    private Rectangle ground = new Rectangle();
    private ArrayList<String> input;
    private Rectangle background = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    private Rectangle backgroundCredits = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    private Rectangle backgroundHTP = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    private Rectangle healthBar = new Rectangle(180, 35);
    private RangedCharacter lisa;
    private NPC enemy;
    private Label deathLbl = new Label();
    private Label vicLbl = new Label();
    private boolean paused = false;
    private double gravity = 0;
    private double prevPos = 0;
    private int levelNum = 1;

     public GameEngine() 
     {
        startPane = new StartMenuPane();
        gameLoop = gameLoop();
        deathLbl.setOpacity(0);

        input = new ArrayList<>();

        scene = new Scene(startPane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

        btnListener();
     }
     
     public void initGame() 
     {
    	 levelNum = 1;
    	 Font creditFont = new Font("Comic Sans MS", primaryScreenBounds.getHeight() * .03472);
	       	
    	 //Initializng game pane
    	 gamePane = new GamePane();
    	 gamePane.getChildren().clear();
    	 
    	 //Initializing fighters
    	 lisa = new RangedCharacter(levelNum);
    	 enemy = new NPC(levelNum);
    	 lisa.setMissionLevel(levelNum);
    	 lisa.getHealthBar().setFill(Color.SPRINGGREEN);
    	 levelNum++;
    	   	 
    	 //Setting enemies
    	 lisa.setEnemy(enemy);
    	 enemy.setEnemy(lisa);
    	 
    	 //Health bars
    	 Label lisaHealthBar = new Label("Player One's Health:");
    	 //lisaHealthBar.setOpacity(.7);
    	 lisaHealthBar.setTextFill(Color.YELLOWGREEN);
    	 lisaHealthBar.setFont(creditFont);
    	 lisaHealthBar.setLayoutX(enemy.getHealthBar().getX() * .78);
    	 lisaHealthBar.setLayoutY(enemy.getHealthBar().getY() - enemy.getHealthBar().getHeight());
    	 
    	 Label enemyHealthBar = new Label("Poison Ivy's Health:");
    	 //enemyHealthBar.setOpacity(.7);
    	 enemyHealthBar.setTextFill(Color.SPRINGGREEN);
    	 enemyHealthBar.setFont(creditFont);
    	 enemyHealthBar.setLayoutX(lisa.getHealthBar().getX() * .98);
    	 enemyHealthBar.setLayoutY(lisa.getHealthBar().getY() - lisa.getHealthBar().getY());
    	 
         //Add character and ground to game
         gamePane.getChildren().addAll(background, lisa.getHealthBar(), enemy.getHealthBar(), lisaHealthBar,
        		 enemyHealthBar, lisa, enemy, ground);

         //Create game scene
         gameScene = new Scene(gamePane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

         //Initialize setting
         createSetting();
         
         //Initialize ground
         createGround();

         //Give character game instance
         lisa.setGame(this);
         enemy.setGame(this);

         //Set keybindings
         keyListener();

         //Start the game loop
         gameLoop.start();

         //Add game scene to Stage
         Main.getStage().setScene(gameScene);
         Main.getStage().setFullScreenExitHint("NetBeans is trash");
         Main.getStage().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
         Main.getStage().setFullScreen(true);
     }
     
     public void initGameTwo() 
     {
    	 Font creditFont = new Font("Comic Sans MS", primaryScreenBounds.getHeight() * .03472);
    	 
    	 //Initializing game pane
    	 gamePane = new GamePane();
    	 gamePane.getChildren().clear();
    	 
    	 //Initializing fighters
    	 lisa = new RangedCharacter(levelNum);
    	 enemy = new NPC(levelNum);
    	 lisa.setMissionLevel(2);
    	 lisa.getHealthBar().setFill(Color.CRIMSON);
    	 levelNum++;
    	 
    	 //Setting enemies
    	 lisa.setEnemy(enemy);
    	 enemy.setEnemy(lisa);
    	 
    	 //Health bars
    	 Label lisaHealthBar = new Label("Player One's Health:");
    	 //lisaHealthBar.setOpacity(.7);
    	 lisaHealthBar.setTextFill(Color.YELLOWGREEN);
    	 lisaHealthBar.setFont(creditFont);
    	 lisaHealthBar.setLayoutX(enemy.getHealthBar().getX() * .78);
    	 lisaHealthBar.setLayoutY(enemy.getHealthBar().getY() - enemy.getHealthBar().getHeight());
    	 
    	 Label enemyHealthBar = new Label("Gunslinger's Health:");
    	 //enemyHealthBar.setOpacity(.7);
    	 enemyHealthBar.setTextFill(Color.CRIMSON);
    	 enemyHealthBar.setFont(creditFont);
    	 enemyHealthBar.setLayoutX(lisa.getHealthBar().getX() * .97);
    	 enemyHealthBar.setLayoutY(lisa.getHealthBar().getY() - lisa.getHealthBar().getY());
    	 
    	 gamePane.getChildren().addAll(background, lisa.getHealthBar(), enemy.getHealthBar(), lisaHealthBar,
        		 enemyHealthBar, lisa, enemy, ground);

         //Create game scene
         gameScene = new Scene(gamePane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        
         //Initialize setting
         createSetting();
         
         //Initialize ground
         createGround();

         //Give character game instance
         lisa.setGame(this);
         enemy.setGame(this);

         //Set keybindings
         keyListener();

         //Start the game loop
         gameLoop.start();

         //Add game scene to Stage
         Main.getStage().setScene(gameScene);
         Main.getStage().setFullScreenExitHint("NetBeans is trash");
         Main.getStage().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
         Main.getStage().setFullScreen(true);
     }
     
     public void initGameThree() 
     {
    	 Font creditFont = new Font("Comic Sans MS", primaryScreenBounds.getHeight() * .03472);
    	 
    	 gamePane = new GamePane();
    	 gamePane.getChildren().clear();
    	 
    	 //Initializing fighters
    	 lisa = new RangedCharacter(levelNum);
    	 enemy = new NPC(levelNum);
    	 enemy.setBossLevel(3);
    	 lisa.setMissionLevel(3);
    	
    	 //Setting enemies
    	 lisa.setEnemy(enemy);
    	 enemy.setEnemy(lisa);
    	 
    	 //Health bars
    	 Label lisaHealthBar = new Label("Player One's Health:");
    	 //lisaHealthBar.setOpacity(.7);
    	 lisaHealthBar.setTextFill(Color.YELLOWGREEN);
    	 lisaHealthBar.setFont(creditFont);
    	 lisaHealthBar.setLayoutX(enemy.getHealthBar().getX() * .78);
    	 lisaHealthBar.setLayoutY(enemy.getHealthBar().getY() - enemy.getHealthBar().getHeight());
    	 
    	 Label enemyHealthBar = new Label("Metal META Man's Health:");
    	 //enemyHealthBar.setOpacity(.7);
    	 enemyHealthBar.setTextFill(Color.LIGHTGREY);
    	 enemyHealthBar.setFont(creditFont);
    	 enemyHealthBar.setLayoutX(lisa.getHealthBar().getX() * .98);
    	 enemyHealthBar.setLayoutY(lisa.getHealthBar().getY() - lisa.getHealthBar().getY());
    	 
         //Add character and ground to game
    	 gamePane.getChildren().addAll(background, lisa.getHealthBar(), enemy.getHealthBar(), lisaHealthBar,
        		 enemyHealthBar, lisa, enemy, ground);

         //Create game scene
         gameScene = new Scene(gamePane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

         //Initialize setting
         createSetting();
         
         //Initialize ground
         createGround();

         //Give character game instance
         lisa.setGame(this);
         enemy.setGame(this);
         
         //Set keybindings
         keyListener();
         
         //Start the game loop
         gameLoop.start();

         //Add game scene to Stage
         Main.getStage().setScene(gameScene);
         Main.getStage().setFullScreenExitHint("NetBeans is trash");
         Main.getStage().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
         Main.getStage().setFullScreen(true);
     }
     
     public void winningScreen()
     {
    	 //Main.getStage().setScene(scene);

    	 
    	 Stage finishedGame = new Stage();
    	 finishedGame.initStyle(StageStyle.UNDECORATED);
    	 finishedGame.setOpacity(1);
    	 Font lblFont = new Font("Comic Sans", primaryScreenBounds.getWidth() * .05);
         Font font = new Font("Times New Roman", primaryScreenBounds.getHeight() * .041);
         backgroundCredits.setWidth(primaryScreenBounds.getWidth());
         backgroundCredits.setHeight(primaryScreenBounds.getHeight());
         Image setting = new Image("file:Pictures/scene/jupiterScene.gif");
         ImagePattern settingP = new ImagePattern(setting);
         backgroundCredits.setFill(settingP);
         
         Pane pane = new Pane();
         
         //no button for confirming quit
         Button btnMenu = new Button("Exit to Main Menu");
         btnMenu.setStyle("-fx-border-color: darkorange; -fx-background-color: transparent");
         btnMenu.setFont(font);
         btnMenu.setOpacity(.7);
         btnMenu.setTextFill(Color.DARKORANGE);
         btnMenu.setLayoutX(primaryScreenBounds.getWidth() * .348);
         btnMenu.setLayoutY(primaryScreenBounds.getHeight() * .87);

         //yes button for confirming quit
         Button btnQuit = new Button("Quit");
         btnQuit.setStyle("-fx-border-color: darkorange; -fx-background-color: transparent");
         btnQuit.setFont(font);
         btnQuit.setOpacity(.7);
         btnQuit.setTextFill(Color.DARKORANGE);
         btnQuit.setLayoutX(primaryScreenBounds.getWidth() * .56);
         btnQuit.setLayoutY(primaryScreenBounds.getHeight() * .87);
         
         Label query = new Label("You saved Jupiter!");
         query.setFont(lblFont);
         query.setTextFill(Color.DARKORANGE);
         query.setOpacity(.7);
         query.setLayoutY(primaryScreenBounds.getHeight() * .75);
         query.setLayoutX(primaryScreenBounds.getWidth() / 3.3);

         pane.getChildren().addAll(backgroundCredits, btnQuit, btnMenu, query);
 		 Scene quitScene = new Scene(pane, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
 		 finishedGame.setScene(quitScene);
 		 finishedGame.setTitle("Quit Window");
 		 finishedGame.show();

          btnMenu.setOnMouseClicked((e) -> 
          {
        	 finishedGame.close();
             Main.getStage().setScene(scene);
          });
          btnQuit.setOnMouseClicked((e) -> {
             System.exit(0);
         });
     }
     
     public AnimationTimer gameLoop() 
     { 
    	 return gameLoop = new AnimationTimer() 
    	 {
    		 @Override
    		 public void handle(long now) 
    		 {
    			 if (deathLbl.getOpacity() > .01)
    				 deathLbl.setOpacity(deathLbl.getOpacity() - .01);
    			 
    			 else if (deathLbl.getOpacity() <= .01)
    			 {
    				 deathLbl.setOpacity(0);
    				 gamePane.getChildren().remove(deathLbl);
    			 }
    			 
    			 if (vicLbl.getOpacity() > .01)
    				 vicLbl.setOpacity(vicLbl.getOpacity() - .01);
    			 
    			 else if (vicLbl.getOpacity() <= .01)
    			 {
    				 vicLbl.setOpacity(0);
    				 gamePane.getChildren().remove(vicLbl);
    			 }
            	
    			 if (lisa.getSleepTime() != 0)
    				 lisa.setSleepTime(lisa.getSleepTime() - 1);
    			 if (enemy.getSleepTime() != 0)
    				 enemy.setSleepTime(enemy.getSleepTime() - 1);
    			 lisa.handleMovement(input);
    			 enemy.handleMovement(input);
    		 }
    	 };
     }	
     
     private void keyListener() 
     { 
    	 gameScene.setOnKeyTyped((event) ->
    	 {
    		 String code = event.getCode().toString();
    		
    		 if (lisa.isShooting() && input.contains("RIGHT"))
    			 input.remove(code);
        	
    		 if (lisa.isShooting() && input.contains("LEFT"))
    			 input.remove(code);
        	
    		 if (input.contains("ESCAPE"))
    			 Main.getStage().setFullScreen(false);
    		 
    	 });
    	
    	 gameScene.setOnKeyPressed((event) -> 
    	 {
    		 String code = event.getCode().toString();
        
    		 if(!input.contains(code))
    			 input.add(code);

    		 if(input.contains("SPACE")) 
    		 {
    			 lisa.jump();
    		 }
            
    		 if (input.contains("F12"))
    			 Main.getStage().setFullScreen(true);
            
    		 if(input.contains("P")) 
    		 {
    			 pauseGame();
    		 }
            
    		 lisa.setPrevXPos(lisa.getX());
    	 });

    	 gameScene.setOnKeyReleased((event) -> {
    		 String code = event.getCode().toString();

    		 input.remove(code);
    	 });
     }

     private void btnListener() {
         startPane.getPlayButton().setOnMouseClicked((event) -> 
         {
        		 initGame();
         });

         startPane.getHTPButton().setOnMouseClicked(((event) -> 
         {
        	 Stage htpWindow = new Stage();
        	 htpWindow.initStyle(StageStyle.UNDECORATED);
        	 htpWindow.setMaximized(true);
        	 backgroundHTP.setWidth(primaryScreenBounds.getWidth());
             backgroundHTP.setHeight(primaryScreenBounds.getHeight());
             Image setting = new Image("file:Pictures/menu/HowToPlay.png");
             ImagePattern settingP = new ImagePattern(setting);
             backgroundHTP.setFill(settingP);
                  
             Pane htpPane = new Pane();
             htpPane.getChildren().add(backgroundHTP);
             Scene htpScene = new Scene(htpPane, primaryScreenBounds.getWidth() * .9, primaryScreenBounds.getHeight() * .7);
             htpWindow.setScene(htpScene);
             Button btnBack = new Button("Go Back to Main Menu");
                      
             btnBack.setLayoutX(primaryScreenBounds.getWidth()*.39);
             btnBack.setStyle("-fx-border-color: black; -fx-background-color: transparent;");
             btnBack.setTextFill(Color.BLACK);
                      
             Font Htp = new Font("Comic Sans MS", .028 * primaryScreenBounds.getHeight());
             btnBack.setFont(Htp);
             htpPane.getChildren().add(btnBack);
             btnBack.setAlignment(Pos.CENTER);
                      
             // htpPane.setBackground(new Background(imageBG));
             htpWindow.show();
             
        	 //button to close how to play
        	 btnBack.setOnAction(new EventHandler<ActionEvent>() 
        	 {
               @Override
               public void handle(ActionEvent event) 
               {
                   htpWindow.close();
               }
               
        	 });
         }));
         
         startPane.getCreditsButton().setOnMouseClicked((event) -> 
         {
        	 backgroundCredits.setWidth(primaryScreenBounds.getWidth());
             backgroundCredits.setHeight(primaryScreenBounds.getHeight());
             Image setting = new Image("file:Pictures/menu/AoJLoadingScreen.gif");
             ImagePattern settingP = new ImagePattern(setting);
             backgroundCredits.setFill(settingP);
       	
             Pane creditPane = new Pane();
             
             Scene credits = new Scene(creditPane, scene.getWidth(), primaryScreenBounds.getHeight());
             
             //creditPane.setAlignment(Pos.BOTTOM_CENTER);
	       	 Font CreditFont = new Font("Comic Sans MS", primaryScreenBounds.getHeight() * .03472);
	       	 creditPane.getChildren().add(backgroundCredits);
	       	
	       	 //made 2 labels for names due to formatting issues
	       	 Label names1 = new Label("Shant"+"\n"+"Zak"+"\n"+"Ryoto"+"\n");
	       	 names1.setOpacity(.5);
	       	 names1.setTextFill(Color.CYAN);
	       	 names1.setFont(CreditFont);
	       	 names1.setLayoutX(primaryScreenBounds.getWidth() / 2);
	       	 names1.setLayoutY(primaryScreenBounds.getHeight() * 0.52);
	       	 creditPane.getChildren().add(names1);
	       	 
	       	 Label names = new Label("Alex"+"\n"+"Ayato"+"\n"+"Apurav"+"\n"+"Donovan");
	       	 names.setOpacity(.5);
	       	 names.setTextFill(Color.MEDIUMSLATEBLUE);
	       	 names.setFont(CreditFont);
	       	 names.setLayoutX(primaryScreenBounds.getWidth() / 2);
	       	 names.setLayoutY(primaryScreenBounds.getHeight() * 0.67);
	       	 creditPane.getChildren().add(names);
	       	
	       	 Main.getStage().setScene(credits);
	       	 Main.getStage().setMaximized(true);
	       	
	       	 //button to for going back to main menu
	       	 Button btnBack = new Button("Go Back to Main Menu");
	       	 btnBack.setAlignment(Pos.BOTTOM_CENTER);
	       	 btnBack.setOpacity(.5);
	       	 btnBack.setStyle("-fx-border-color: black; -fx-background-color: darkslateblue;");
	       	 btnBack.setTextFill(Color.MEDIUMSLATEBLUE);
	       	 btnBack.setFont(CreditFont);
	       	 btnBack.setLayoutX(primaryScreenBounds.getWidth() * .43);
	       	 btnBack.setLayoutY(primaryScreenBounds.getHeight() * .87);
	       	 creditPane.getChildren().add(btnBack);
	      	 btnBack.setAlignment(Pos.BOTTOM_RIGHT);
	          
	      	 //button action
	      	 btnBack.setOnAction(new EventHandler<ActionEvent>()
	      	 {
	      		 @Override
	      		 public void handle(ActionEvent event) 
	      		 {
	      			 Main.getStage().setScene(scene);
	      		 }
	      	 });
	      });
         
         startPane.getQuitButton().setOnMouseClicked((event -> 
         {
        	 Stage quitWindow = new Stage();
        	 quitWindow.initStyle(StageStyle.UNDECORATED);
        	 quitWindow.setOpacity(1);
             Font font = new Font("Times New Roman", 30);
             BackgroundImage imageBG = new BackgroundImage(new Image("file:Pictures/menu/AoJTerror.gif"),
                     BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                     BackgroundSize.DEFAULT);
             
             Pane pane = new Pane();
             pane.setBackground(new Background(imageBG));
             
             //no button for confirming quit
             Button btnNo = new Button("No");
             btnNo.setStyle("-fx-border-color: darkorange; -fx-background-color: transparent");
             btnNo.setFont(font);
             btnNo.setOpacity(.7);
             btnNo.setTextFill(Color.DARKORANGE);
             btnNo.setLayoutX(257);
             btnNo.setLayoutY(258);

             //yes button for confirming quit
             Button btnYes = new Button("Yes");
             btnYes.setStyle("-fx-border-color: darkorange; -fx-background-color: transparent");
             btnYes.setFont(font);
             btnYes.setOpacity(.7);
             btnYes.setTextFill(Color.DARKORANGE);
             btnYes.setLayoutX(163);
             btnYes.setLayoutY(258);
             
             Label query = new Label("Are you sure you want to quit?");
             query.setFont(font);
             query.setTextFill(Color.DARKORANGE);
             query.setOpacity(.7);
             query.setLayoutY(0);
             query.setLayoutX(70);

             pane.getChildren().addAll(btnYes, btnNo, query);
     		 Scene quitScene = new Scene(pane, 498, 315);
     		 quitWindow.setScene(quitScene);
     		 quitWindow.setTitle("Quit Window");
     		 quitWindow.show();

              btnNo.setOnMouseClicked((e) -> {
            	 quitWindow.close();
                 Main.getStage().setScene(scene);
             });
             btnYes.setOnMouseClicked((e) -> {
                 System.exit(0);
             });
         }));
    }

     public void pauseGame() {
        if(!paused) {
            gameLoop.stop();
            paused = true;
        }
        else {
            gameLoop.start();
            paused = false;
        }
    }
     
     private void createSetting()
     {	
    	 Image settingDay = new Image("file:Pictures/scene/back_day.gif");
    	 ImagePattern settingDayP = new ImagePattern(settingDay);
         
    	 Image settingRain = new Image("file:Pictures/scene/rain_back.gif");
    	 ImagePattern settingRainP = new ImagePattern(settingRain);
       
    	 Image settingNight = new Image("file:Pictures/scene/dark_ground_back.gif");
    	 ImagePattern settingNightP = new ImagePattern(settingNight);
        
    	 if (enemy.getBossLevel() == 1)
    		 background.setFill(settingDayP);
        
    	 if (enemy.getBossLevel() == 2)
    		 background.setFill(settingRainP);
        
    	 if (enemy.getBossLevel() == 3)
    		 background.setFill(settingNightP);
     }
    
     private void createGround() 
     {
    	 ground.setFill(Color.BLACK);
    	 ground.setStroke(Color.ORANGE);
    	 ground.setOpacity(0);
    	 ground.setWidth(scene.getWidth());
    	 ground.setX(0);
    	 ground.setHeight(1);
    	 ground.setY(GROUNDLEVEL);
     }

     public Scene getScene() 
     {
    	 return scene;
     }

     public GamePane getGamePane()
     {
    	 return this.gamePane;
     }
     
     public Rectangle getGround() 
     {
    	 return ground;
     }
    
     public Rectangle getSetting()
     {
    	 return this.background;
     }
     
     public void displayDeath()
     {
    	 Font deathFont = new Font("Comic Sans MS", primaryScreenBounds.getHeight() * .05);
    	 deathLbl = new Label("You died! Press 'P' to un-pause");
    	 deathLbl.setFont(deathFont);
    	 if (enemy.getBossLevel() == 1)
    	 	deathLbl.setTextFill(Color.SPRINGGREEN);
    	 if (enemy.getBossLevel() == 2)
    		 deathLbl.setTextFill(Color.CRIMSON);
    	 if (enemy.getBossLevel() == 3)
    		 deathLbl.setTextFill(Color.LIGHTGREY);
    	 deathLbl.setLayoutX(primaryScreenBounds.getWidth() / 3.6);
    	 deathLbl.setOpacity(1);
    	 gamePane.getChildren().add(deathLbl);
     }
     
     public void displayVictory()
     {
    	 Font vicFont = new Font("Comic Sans MS", primaryScreenBounds.getHeight() * .09);
    	 vicLbl = new Label("Victory! Prepare for fight " + enemy.getBossLevel() + "...");
    	 vicLbl.setFont(vicFont);
    	 if (enemy.getBossLevel() == 1)
    		 vicLbl.setTextFill(Color.SPRINGGREEN);
    	 if (enemy.getBossLevel() == 2)
    	 	vicLbl.setTextFill(Color.CRIMSON);
    	 if (enemy.getBossLevel() == 3)
    		 vicLbl.setTextFill(Color.LIGHTGREY);
    	 vicLbl.setLayoutX(primaryScreenBounds.getWidth() / 5.3);
    	 vicLbl.setLayoutY(primaryScreenBounds.getHeight() / 3);
    	 vicLbl.setOpacity(1);
    	 gamePane.getChildren().add(vicLbl);
     }
}
