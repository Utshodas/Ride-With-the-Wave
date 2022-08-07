package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.BigInfoLabel;
import model.DifficultyWaveSubScene;
import model.HugeInfoLabel;
import model.InfoLabel;
import model.WaveButton;
import model.WaveSubScene;

public class ViewManager {

	
	public static final int HEIGHT = 603;
	public static final int WIDTH = 1071;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTON_START_X = 100;
	private final static int MENU_BUTTON_START_Y =100;
	
	List <WaveButton> menuButtons;
	private WaveSubScene helpSubScene;
	//private WaveSubScene playSubScene;
	private WaveSubScene scoresSubScene;
	private WaveSubScene levelSubScene;
	private WaveSubScene sceneToHide;
	
	public ViewManager()
	{
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		menuButtons = new ArrayList<>();
		createPlayMenuButton();
		createScoresMenuButton();
		createLevelMenuButton();
		createHelpMenuButton();
		createExitMenuButton();
		createSubScenes();
		createBackground();
		
	}
	
	private void showSubScene(WaveSubScene subScene) {
		if (sceneToHide != null) {
			sceneToHide.moveSubScene();
		}	
			subScene.moveSubScene();
			sceneToHide = subScene;
	}
	private void createSubScenes()
	{
		createHelpSubScene();
		createScoresSubScene();
		createLevelSubScene();
		//createplaySubScene();
		
	}
	private void createHelpSubScene()
	{
		helpSubScene = new WaveSubScene();	
		mainPane.getChildren().add(helpSubScene);
		BigInfoLabel helpInfo = new BigInfoLabel("Press Up arrow and Down arrow to\nmove  the character."
        		+ "Going on reverse\nflow will end the Game and Neutral\nwave will decrease your life. At a time\n"
        		+ "there will be all three types of wave\ninfront of you");
		helpSubScene.getPane().getChildren().add(helpInfo);
	}
	private void createScoresSubScene()
	{
		scoresSubScene = new WaveSubScene();	
		mainPane.getChildren().add(scoresSubScene);
	}
	/*private void createplaySubScene()
	{
		playSubScene = new WaveSubScene();	
		mainPane.getChildren().add(playSubScene);
	}*/
	private void createLevelSubScene()
	{
		levelSubScene = new WaveSubScene();	
		mainPane.getChildren().add(levelSubScene);
		WaveButton easyButton = new WaveButton("EASY");
		easyButton.setLayoutX(100);
		easyButton.setLayoutY(100);
		easyButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameViewManager.speed=2;
				GameViewManager.neutralPixel=500;
			}	
        });
		WaveButton normalButton = new WaveButton("NORMAL");
		normalButton.setLayoutX(100);
		normalButton.setLayoutY(200);
		normalButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameViewManager.speed=4;
				GameViewManager.neutralPixel=300;
			}	
        });
		WaveButton hardButton = new WaveButton("HARD");
		hardButton.setLayoutX(100);
		hardButton.setLayoutY(300);
		hardButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameViewManager.speed=10;
				GameViewManager.neutralPixel=100;
			}	
        });
		levelSubScene.getPane().getChildren().add(easyButton);
		levelSubScene.getPane().getChildren().add(normalButton);
		levelSubScene.getPane().getChildren().add(hardButton);
		
		
	}
	private void createBackground() {
		Image backgroundImage = new Image(getClass().getResource("blue_panel.jpg").toExternalForm(), 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

	public Stage getMainStage()
	{
		return mainStage;
	}
	
	private void  addMenuButton(WaveButton button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y+menuButtons.size()*100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
		
		
	}
	private void  createPlayMenuButton() {
		
		WaveButton playButton = new WaveButton("PLAY!");
		addMenuButton(playButton);
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("red_button.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background background = new Background(backgroundImage);
        playButton.setBackground(background);
        
        playButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameViewManager gameManager = new GameViewManager();
				gameManager.createNewGame(mainStage);
			}	
        });
	}
	private void  createScoresMenuButton() {
		
		WaveButton scoresButton = new WaveButton("Scores");
		addMenuButton(scoresButton);
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("red_button.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background background = new Background(backgroundImage);
        scoresButton.setBackground(background);
        
        
        
        scoresButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoresSubScene);
			}
        	
        });
	}
	private void  createLevelMenuButton() {
		
		WaveButton levelButton = new WaveButton("Difficulty");
		addMenuButton(levelButton);
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("red_button.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background background = new Background(backgroundImage);
        levelButton.setBackground(background);
        
        
        
        levelButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(levelSubScene);
			}
        	
        });
	}
	private void  createHelpMenuButton() {
		
		WaveButton helpButton = new WaveButton("Help");
		addMenuButton(helpButton);
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("red_button.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background background = new Background(backgroundImage);
        helpButton.setBackground(background);
        
        helpButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubScene);
			}
        	
        });
        
        
	}
	private void  createExitMenuButton() {
		
		WaveButton exitButton = new WaveButton("Exit");
		addMenuButton(exitButton);
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("red_button.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background background = new Background(backgroundImage);
        exitButton.setBackground(background);
        
        exitButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
        	
        });
	}
}
