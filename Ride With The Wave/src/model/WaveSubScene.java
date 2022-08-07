package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class WaveSubScene extends SubScene {
	private boolean isHidden=true;
	public WaveSubScene() {
		super( new AnchorPane(),400,400);
		prefHeight(400);
		prefWidth(400);
		Image backgroundImage = new Image(getClass().getResource("yellow_panel.png").toExternalForm(), 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(background));
		setLayoutX(1124);
		setLayoutY(180);
	}
	public void moveSubScene(WaveSubScene subScene)
	{
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		if(isHidden==true) {
			transition.setToX(-676);
			isHidden=false;
		}
		else if(isHidden==false)
		{
			transition.setToX(0);
			isHidden=true;
		}
		transition.play();
	}
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}

}
