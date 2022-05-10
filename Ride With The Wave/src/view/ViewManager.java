package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import model.WaveButton;

public class ViewManager {

	
	public static final int HEIGHT = 600;
	public static final int WIDTH = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTON_START_X = 300;
	private final static int MENU_BUTTON_START_Y =100;
	
	List <WaveButton> menuButtons;
	
	public ViewManager()
	{
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		menuButtons = new ArrayList<>();
		createMenuButton("PLAY!");
		createMenuButton("Scores");
		createMenuButton("About");
		createMenuButton("Exit");
		
		createBackground();
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
	private void  createMenuButton(String text) {
		
		WaveButton button = new WaveButton(text);
		addMenuButton(button);
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("red_button.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background background = new Background(backgroundImage);
        button.setBackground(background);
	}
}
