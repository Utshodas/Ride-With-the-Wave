package view;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameViewManager {
	
	private Stage gameStage;
	private AnchorPane gamePane;
	private Scene gameScene;
	
	private Stage menuStage;
	
	public static final int GAME_HEIGHT = 603;
	public static final int GAME_WIDTH = 1071;
	
	ImageView boy;
	
	private boolean isDownKeyPressed;
	private boolean isUpKeyPressed;
	private int angle; //will add some rotating animation later
	private AnimationTimer gameTimer;

	public GameViewManager() {
		initializeStage();
		createKeyListners();
		
		
	}

	private void createKeyListners() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.UP)
				{
					isUpKeyPressed=true;
					
				}else if (event.getCode()==KeyCode.DOWN)
				{
					isDownKeyPressed=true;
				}
				
				
			}
			
		});
	}

	private void initializeStage() {
		// TODO Auto-generated method stub
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane,GAME_WIDTH,GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setResizable(false);
		gameStage.setScene(gameScene);
		
	}
	
	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBoy();
		gameLoop();
		gameStage.show();
	}
	
	private void createBoy() {
		boy = new ImageView(new Image(getClass().getResourceAsStream("sufer_boy_small.png")));
		boy.setLayoutX(10);
		boy.setLayoutY(50);
		gamePane.getChildren().add(boy);
		}
	
	private void gameLoop()
	{
		gameTimer= new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				moveBoy();
			}
		};
		gameTimer.start();
	}
	
	private void moveBoy() {
		if(isDownKeyPressed && !isUpKeyPressed)
		{
			if(boy.getLayoutY()<450) {
				boy.setLayoutY(boy.getLayoutY()+200);
			}
			isDownKeyPressed=false;
			
		}
		if(!isDownKeyPressed && isUpKeyPressed)
		{
			if(boy.getLayoutY()>50) {
				boy.setLayoutY(boy.getLayoutY()-200);
			}
			isUpKeyPressed=false;
		}
		
	}

}
