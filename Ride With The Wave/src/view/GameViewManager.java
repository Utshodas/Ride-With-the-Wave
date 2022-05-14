package view;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameViewManager {
	
	private Stage gameStage;
	private AnchorPane gamePane;
	private Scene gameScene;
	
	private Stage menuStage;
	
	public static final int GAME_HEIGHT = 603;
	public static final int GAME_WIDTH = 1071;

	public GameViewManager() {
		initializeStage();
		createKeyListners();
		
		
	}

	private void createKeyListners() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.LEFT)
				{
					
				}else if (event.getCode()==KeyCode.RIGHT)
				{
					
				}
				
				
			}
		});
		gameScene.setOnKeyReleased( new EventHandler <KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.LEFT)
				{
					
				}else if (event.getCode()==KeyCode.RIGHT)
				{
					
				}
		
				
			}
			
			
		});
	}

	private void initializeStage() {
		// TODO Auto-generated method stub
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane,GAME_WIDTH,GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
	}
	
	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		gameStage.show();
	}

}
