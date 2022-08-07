package view;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.InfoLabel;
import model.WaveSubScene;

public class GameViewManager {
	
	public static int speed=4;
	private Stage gameStage;
	private AnchorPane gamePane;
	private Scene gameScene;
	
	private Stage menuStage;
	
	public static final int GAME_HEIGHT = 603;
	public static final int GAME_WIDTH = 1072;
	
	
	private ImageView forwardWave1;
	private ImageView reverseWave1;
	private ImageView neutralWave1;
	private ImageView forwardWave2;
	private ImageView reverseWave2;
	private ImageView neutralWave2;
	private ImageView forwardWave3;
	private ImageView reverseWave3;
	private ImageView neutralWave3;
	
	ImageView boy;
	
	private boolean isDownKeyPressed;
	private boolean isUpKeyPressed;
	private boolean isRightKeyPressed;
	private boolean isLeftKeyPressed;
	private int angle; //will add some rotating animation later
	private AnimationTimer gameTimer;
	
	List<ImageView> forwave1;
	List<ImageView> forwave2;
	List<ImageView> forwave3;

	
	private InfoLabel pointsLabel;
	private int points=0;
	private int neutralPixel=0;
	
	public GameViewManager() {
		initializeStage();
		forwave1 = new ArrayList<>();
		forwave2 =new ArrayList<>();
		forwave3 =new ArrayList<>();
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
				if(event.getCode()==KeyCode.RIGHT)
				{
					isRightKeyPressed=true;
					
				}else if (event.getCode()==KeyCode.LEFT)
				{
					isLeftKeyPressed=true;
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
		addBackgounds();
		createGameElements();
		createBoy();
		gameLoop();
		gameStage.show();
	}
	private void createGameElements() {
		pointsLabel = new InfoLabel("POINTS : " + points);
		pointsLabel.setLayoutX(600);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
	}
	
	private void createBoy() {
		boy = new ImageView(new Image(getClass().getResourceAsStream("surfer_boy2.png")));
		boy.setLayoutX(10);
		boy.setLayoutY(0);
		gamePane.getChildren().add(boy);
		}
	
	private void gameLoop()
	{
		gameTimer= new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				moveBackground();
				deathLogic();
				moveBoy();
			}
		};
		gameTimer.start();
	}
	
  private void deathLogic() {
	
	if((boy.getLayoutY()==reverseWave1.getLayoutY()||boy.getLayoutY()==reverseWave2.getLayoutY()||boy.getLayoutY()==reverseWave3.getLayoutY()) &&
			( reverseWave1.getLayoutX()<201 || reverseWave2.getLayoutX()<201 || reverseWave3.getLayoutX()<201)
			&& ( reverseWave1.getLayoutX()>0 || reverseWave2.getLayoutX()>0 || reverseWave3.getLayoutX()>0)) {
		gameTimer.stop();
		DeathViewManager  deathManager = new DeathViewManager();
		deathManager.deathScene(gameStage,points,menuStage,"reverse");
	}
  }
		
	
	
	private void moveBoy() {
		if(isDownKeyPressed && !isUpKeyPressed)
		{
			if(boy.getLayoutY()<402) {
				boy.setLayoutY(boy.getLayoutY()+201);
				//neutralPixel=0;
			}
			isDownKeyPressed=false;
			
		}
		if(!isDownKeyPressed && isUpKeyPressed)
		{
			if(boy.getLayoutY()>0) {
				boy.setLayoutY(boy.getLayoutY()-201);
				//neutralPixel=0;
			}
			isUpKeyPressed=false;
		}
		if(isRightKeyPressed && !isLeftKeyPressed)
		{
			if(boy.getLayoutX()<201) {
				boy.setLayoutX(boy.getLayoutX()+20);
				//neutralPixel=0;
			}
			isRightKeyPressed=false;
			
		}
		if(!isRightKeyPressed && isLeftKeyPressed)
		{
			if(boy.getLayoutX()>0){
				boy.setLayoutX(boy.getLayoutX()-20);
				neutralPixel=0;
			}
			isLeftKeyPressed=false;
		}
		
	}
	
	private void addBackgounds() {
		forwardWave1 = new ImageView(new Image(getClass().getResourceAsStream("forward_wave1.png")));
		reverseWave1 = new ImageView(new Image(getClass().getResourceAsStream("reverse_wave1.png")));
		neutralWave1 = new ImageView(new Image(getClass().getResourceAsStream("neutral_wave1.png")));
		forwardWave2= new ImageView(new Image(getClass().getResourceAsStream("forward_wave1.png")));
		reverseWave2= new ImageView(new Image(getClass().getResourceAsStream("reverse_wave1.png")));
		neutralWave2= new ImageView(new Image(getClass().getResourceAsStream("neutral_wave1.png")));
		forwardWave3= new ImageView(new Image(getClass().getResourceAsStream("forward_wave1.png")));
		reverseWave3= new ImageView(new Image(getClass().getResourceAsStream("reverse_wave1.png")));
		neutralWave3= new ImageView(new Image(getClass().getResourceAsStream("neutral_wave1.png")));
		gamePane.getChildren().add(forwardWave1);
		gamePane.getChildren().add(neutralWave1);
		gamePane.getChildren().add(reverseWave1);
		gamePane.getChildren().add(forwardWave2);
		gamePane.getChildren().add(neutralWave2);
		gamePane.getChildren().add(reverseWave2);
		gamePane.getChildren().add(forwardWave3);
		gamePane.getChildren().add(reverseWave3);
		gamePane.getChildren().add(neutralWave3);
		forwave1.add(forwardWave1);
		forwave1.add(neutralWave1);
		forwave1.add(reverseWave1);
		forwave2.add(forwardWave2);
		forwave2.add(neutralWave2);
		forwave2.add(reverseWave2);
		forwave3.add(neutralWave3);
		forwave3.add(reverseWave3);
		forwave3.add(forwardWave3);
		Collections.shuffle(forwave3);
		
		setBackgroundPosition();
		
	}
	private void setBackgroundPosition() {
		forwave1.get(0).setLayoutX(0);
		forwave1.get(0).setLayoutY(0);
		forwave1.get(1).setLayoutX(0);
		forwave1.get(1).setLayoutY(201);
		forwave1.get(2).setLayoutX(0);
		forwave1.get(2).setLayoutY(402);
		forwave2.get(0).setLayoutX(536);
		forwave2.get(0).setLayoutY(0);
		forwave2.get(1).setLayoutX(536);
		forwave2.get(1).setLayoutY(201);
		forwave2.get(2).setLayoutX(536);
		forwave2.get(2).setLayoutY(402);
		forwave3.get(0).setLayoutX(1072);
		forwave3.get(0).setLayoutY(0);
		forwave3.get(1).setLayoutX(1072);
		forwave3.get(1).setLayoutY(201);
		forwave3.get(2).setLayoutX(1072);
		forwave3.get(2).setLayoutY(402);
		
	}
	
	private void setPositionLater(List<ImageView> waves) {
		Collections.shuffle(waves);
		waves.get(0).setLayoutX(1068);
		waves.get(0).setLayoutY(0);
		waves.get(1).setLayoutX(1068);
		waves.get(1).setLayoutY(201);
		waves.get(2).setLayoutX(1068);
		waves.get(2).setLayoutY(402);
		
	}

		
	private void moveBackground() {

		forwave1.get(0).setLayoutX(forwave1.get(0).getLayoutX()-speed);
		forwave1.get(1).setLayoutX(forwave1.get(1).getLayoutX()-speed);
		forwave1.get(2).setLayoutX(forwave1.get(2).getLayoutX()-speed);
		forwave2.get(0).setLayoutX(forwave2.get(0).getLayoutX()-speed);
		forwave2.get(1).setLayoutX(forwave2.get(1).getLayoutX()-speed);
		forwave2.get(2).setLayoutX(forwave2.get(2).getLayoutX()-speed);
		forwave3.get(0).setLayoutX(forwave3.get(0).getLayoutX()-speed);
		forwave3.get(1).setLayoutX(forwave3.get(1).getLayoutX()-speed);
		forwave3.get(2).setLayoutX(forwave3.get(2).getLayoutX()-speed);
		points = points + 4;
		pointsLabel.setText("POINTS : "+ points);
		
		if(forwave1.get(0).getLayoutX()<-536) {
			setPositionLater(forwave1);	
		}
		if(forwave2.get(0).getLayoutX()<-536) {
			setPositionLater(forwave2);	
		}
		if(forwave3.get(0).getLayoutX()<-536) {
			setPositionLater(forwave3);	
		}			
	}
	

}
