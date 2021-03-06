package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import model.BigInfoLabel;
import model.HugeInfoLabel;
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
	private WaveSubScene playSubScene;
	private WaveSubScene scoresSubScene;
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
		createHelpMenuButton();
		createExitMenuButton();
		createSubScenes();
		
		createBackground();
		
	}
	
	private void showSubScene( WaveSubScene subScene)
	{
		if (sceneToHide != null) {
			sceneToHide.moveSubScene();
		}
		
		else
			{ subScene.moveSubScene();
		      sceneToHide = subScene;
			}
	}
	
	private void createSubScenes()
	{
		helpSubScene = new WaveSubScene();	
		mainPane.getChildren().add(helpSubScene);
		HugeInfoLabel helpInfo = new HugeInfoLabel("Press Up arrow and Down arrow to move the character. "
        		+ "Going on reverse flow will end the Game and Neutral wave will decrease your life. At a time"
        		+ "there will be all three types of wave infront of you");
		helpSubScene.getPane().getChildren().add(helpInfo);
		
		scoresSubScene = new WaveSubScene();	
		mainPane.getChildren().add(scoresSubScene);
		
		playSubScene = new WaveSubScene();	
		mainPane.getChildren().add(playSubScene);
		
		
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
