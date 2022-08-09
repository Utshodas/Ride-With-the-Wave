package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Element;
import javafx.scene.image.ImageView;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
import model.HelpLabel;
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
	
	public WaveSubScene easyScoreScene;
	public WaveSubScene normalScoreScene;
	public WaveSubScene hardScoreScene;
	
	public WaveSubScene openedSubScene;
	
	public static int easyHighScore ;
	public static int normalHighScore;
	public static int hardHighScore;
	
	public static String easyHighName;
	public static String normalHighName;
	public static String hardHighName;
	
	BigInfoLabel score1;
	BigInfoLabel score2;
	BigInfoLabel score3;
	
	BigInfoLabel scoreName1;
	BigInfoLabel scoreName2;
	BigInfoLabel scoreName3;
	
	
	Image logo;
	
	public ViewManager()
	{
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		menuButtons = new ArrayList<>();
		//initializeScore();
		createPlayMenuButton();
		createScoresMenuButton();
		createLevelMenuButton();
		createHelpMenuButton();
		createExitMenuButton();
		createSubScenes();
		createScoreSubscene();
		createBackground();
		createLogo();
		
	}
	private void initializeScore()
	{
		try {
			try (BufferedReader easyReader = new BufferedReader(new FileReader("easyScore.txt"))) {
				easyHighScore=Integer.parseInt(easyReader.readLine());
				easyHighName=easyReader.readLine();
			}
		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			try (BufferedReader normalReader = new BufferedReader(new FileReader("normalScore.txt"))) {
				normalHighScore=Integer.parseInt(normalReader.readLine());
				normalHighName=normalReader.readLine();
			}
		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			try (BufferedReader hardReader = new BufferedReader(new FileReader("hardScore.txt"))) {
				hardHighScore=Integer.parseInt(hardReader.readLine());
				hardHighName=hardReader.readLine();
			}
		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void showSubScene(WaveSubScene subScene) {
		if(openedSubScene!=null)
		{
			openedSubScene.moveSubScene();
		}
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
	private void createScoreSubscene()
	{
		createEasySubScene();
		createNormalSubScene();
		createHardSubScene();
	}
	private void createHelpSubScene()
	{
		helpSubScene = new WaveSubScene();	
		mainPane.getChildren().add(helpSubScene);
		HelpLabel helpInfo = new HelpLabel("Press Up arrow and Down arrow to\nmove  the character."
        		+ "Going on reverse\nflow will end the Game and Neutral\nwave will decrease your life. At a time\n"
        		+ "there will be all three types of wave\ninfront of you");
		helpSubScene.getPane().getChildren().add(helpInfo);
	}
	private void createScoresSubScene()
	{
		scoresSubScene = new WaveSubScene();	
		mainPane.getChildren().add(scoresSubScene);
		WaveButton easyButton = new WaveButton("EASY");
		easyButton.setLayoutX(100);
		easyButton.setLayoutY(100);
		easyButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				easyScoreScene.moveSubScene();
				openedSubScene=easyScoreScene;
				//showSubScene(easyScoreScene);
				setEasyScore(easyHighName,easyHighScore);
			}	
        });
		WaveButton normalButton = new WaveButton("NORMAL");
		normalButton.setLayoutX(100);
		normalButton.setLayoutY(200);
		normalButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				normalScoreScene.moveSubScene();
				openedSubScene=normalScoreScene;
				//showSubScene(normalScoreScene);
				setNormalScore(normalHighName,normalHighScore);
			}	
        });
		WaveButton hardButton = new WaveButton("HARD");
		hardButton.setLayoutX(100);
		hardButton.setLayoutY(300);
		hardButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				hardScoreScene.moveSubScene();
				openedSubScene=hardScoreScene;
				//showSubScene(hardScoreScene);
				setHardScore(hardHighName,hardHighScore);
			}	
        });
		scoresSubScene.getPane().getChildren().add(easyButton);
		scoresSubScene.getPane().getChildren().add(normalButton);
		scoresSubScene.getPane().getChildren().add(hardButton);
		
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
				GameViewManager.difficulty(2, 2000);
			}	
        });
		WaveButton normalButton = new WaveButton("NORMAL");
		normalButton.setLayoutX(100);
		normalButton.setLayoutY(200);
		normalButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameViewManager.difficulty(4, 1000);
			}	
        });
		WaveButton hardButton = new WaveButton("HARD");
		hardButton.setLayoutX(100);
		hardButton.setLayoutY(300);
		hardButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameViewManager.difficulty(10, 500);
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
	private void createLogo()
	{
		Image logoImage = new Image(getClass().getResourceAsStream("logo4.png"));
		ImageView logo = new ImageView(logoImage);
		logo.setLayoutX(400);
		logo.setLayoutY(50);
		
		mainPane.getChildren().add(logo);
	}
	private void createEasySubScene()
	{
		easyScoreScene= new WaveSubScene();
		mainPane.getChildren().add(easyScoreScene);
		HugeInfoLabel easyLabel = new HugeInfoLabel("EASY HIGHEST SCORE");
		easyLabel.setLayoutX(70);
		easyLabel.setLayoutY(50);
		easyScoreScene.getPane().getChildren().add(easyLabel);
		WaveButton easyButton = new WaveButton("Close");
		easyButton.setLayoutX(100);
		easyButton.setLayoutY(250);
		scoreName1= new BigInfoLabel(easyHighName);
		scoreName1.setLayoutX(65);
		scoreName1.setLayoutY(150);
		easyScoreScene.getPane().getChildren().add(scoreName1);
		score1= new BigInfoLabel(String.valueOf(easyHighScore));
		score1.setLayoutX(215);
		score1.setLayoutY(150);
		easyScoreScene.getPane().getChildren().add(score1);
		
		easyScoreScene.getPane().getChildren().add(easyButton);
		easyButton.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				easyScoreScene.moveSubScene();
				//showSubScene(easyScoreScene);
			}
        });
	}
	private void createNormalSubScene()
	{
		normalScoreScene= new WaveSubScene();
		mainPane.getChildren().add(normalScoreScene);
		HugeInfoLabel normalLabel = new HugeInfoLabel("NORMAL HIGHEST SCORE");
		normalLabel.setLayoutX(70);
		normalLabel.setLayoutY(50);
		normalScoreScene.getPane().getChildren().add(normalLabel);
		WaveButton normalButton = new WaveButton("Close");
		normalButton.setLayoutX(100);
		normalButton.setLayoutY(250);
		scoreName2= new BigInfoLabel(easyHighName);
		scoreName2.setLayoutX(65);
		scoreName2.setLayoutY(150);
		normalScoreScene.getPane().getChildren().add(scoreName2);
		score2= new BigInfoLabel(String.valueOf(normalHighScore));
		score2.setLayoutX(215);
		score2.setLayoutY(150);
		normalScoreScene.getPane().getChildren().add(score2);
		normalScoreScene.getPane().getChildren().add(normalButton);
		normalButton.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				normalScoreScene.moveSubScene();
				//showSubScene(normalScoreScene);
			}
        });
		
	}
	private void createHardSubScene()
	{
		hardScoreScene= new WaveSubScene();
		mainPane.getChildren().add(hardScoreScene);
		HugeInfoLabel hardLabel = new HugeInfoLabel("HARD HIGHEST SCORE");
		hardLabel.setLayoutX(70);
		hardLabel.setLayoutY(50);
		scoreName3= new BigInfoLabel(easyHighName);
		scoreName3.setLayoutX(65);
		scoreName3.setLayoutY(150);
		hardScoreScene.getPane().getChildren().add(scoreName3);
		score3= new BigInfoLabel(String.valueOf(hardHighScore));
		score3.setLayoutX(215);
		score3.setLayoutY(150);
		hardScoreScene.getPane().getChildren().add(score3);
		hardScoreScene.getPane().getChildren().add(hardLabel);
		WaveButton hardButton = new WaveButton("Close");
		hardButton.setLayoutX(100);
		hardButton.setLayoutY(250);
		hardScoreScene.getPane().getChildren().add(hardButton);
		hardButton.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				hardScoreScene.moveSubScene();
				//scoresSubScene.moveSubScene();
			}
        });
		
	}
	public void setEasyScore(String name,int Score)
	{
		scoreName1.setText(name);
		score1.setText(String.valueOf(Score));
	}
	public void setNormalScore(String name,int Score)
	{
		scoreName2.setText(name);
		score2.setText(String.valueOf(Score));
		
	}
	public void setHardScore(String name,int Score)
	{
		scoreName3.setText(name);
		score3.setText(String.valueOf(Score));
	}
}
