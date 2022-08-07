package view;

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
import model.InfoLabel;
import model.WaveButton;

public class DeathViewManager {
	
	private Stage deathStage;
	private AnchorPane deathPane;
	private Scene deathScene;
	
	private Stage gameStage;
	private Stage menuStage;
	
	public static final int DEATH_HEIGHT = 300;
	public static final int DEATH_WIDTH = 900;
	
	public DeathViewManager() {
		deathPane = new AnchorPane();
		deathScene = new Scene(deathPane,DEATH_WIDTH,DEATH_HEIGHT);
		deathStage = new Stage();
	    deathStage.setResizable(false);
		deathStage.setScene(deathScene);
		
	}
	
	public void deathScene(Stage gameStage, int point,Stage  menuStage, String text){
		this.gameStage = gameStage;
		gameStage.hide();
		deathStage.show();
		this.menuStage=menuStage;
		BackgroundImage backgroundImage1 = new BackgroundImage( new Image( getClass().getResource("red_button.png").toExternalForm()), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        Background background1 = new Background(backgroundImage1);
        deathPane.setBackground(background1);
		BigInfoLabel finalPoint = new  BigInfoLabel("Your Point is "+ point);
		finalPoint.setLayoutX(250);
		finalPoint.setLayoutY(100);
		deathPane.getChildren().add(finalPoint);
		
		WaveButton okButton = new WaveButton("OK");
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("red_button.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background background = new Background(backgroundImage);
        okButton.setBackground(background);
        okButton.setLayoutX(315);
        okButton.setLayoutY(200);
        okButton.setText(text);
        deathPane.getChildren().add(okButton);
        
        okButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deathStage.close();
				menuStage.show();
			}
        	
        });
	}

}
