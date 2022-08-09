package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import model.BigInfoLabel;
import model.HugeInfoLabel;
import model.InfoLabel;
import model.WaveButton;

public class DeathViewManager {
	
	private Stage deathStage;
	private AnchorPane deathPane;
	private Scene deathScene;
	
	private Stage gameStage;
	private Stage menuStage;
	
	private String tempname;
	private int tempScore;
	
	
	public static final int DEATH_HEIGHT = 300;
	public static final int DEATH_WIDTH = 900;
	
	String name;
	
	public DeathViewManager() {
		deathPane = new AnchorPane();
		deathScene = new Scene(deathPane,DEATH_WIDTH,DEATH_HEIGHT);
		deathStage = new Stage();
	    deathStage.setResizable(false);
		deathStage.setScene(deathScene);
		
	}
	
	public void deathScene(Stage gameStage, int point,Stage  menuStage, String text,int speed){
		this.gameStage = gameStage;
		gameStage.hide();
		deathStage.show();
		this.menuStage=menuStage;
		BackgroundImage backgroundImage1 = new BackgroundImage( new Image( getClass().getResource("grey_panel.jpg").toExternalForm()), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        Background background1 = new Background(backgroundImage1);
        deathPane.setBackground(background1);
		HugeInfoLabel finalPoint = new  HugeInfoLabel("Your Point is "+ point);
		finalPoint.setLayoutX(300);
		finalPoint.setLayoutY(20);
		deathPane.getChildren().add(finalPoint);
		
		InfoLabel reasonLabel = new  InfoLabel(text);
		reasonLabel.setLayoutX(360);
		reasonLabel.setLayoutY(100);
		deathPane.getChildren().add(reasonLabel);
		
		WaveButton okButton = new WaveButton("Menu");
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("red_button.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background background = new Background(backgroundImage);
        okButton.setBackground(background);
        okButton.setLayoutX(200);
        okButton.setLayoutY(200);
        deathPane.getChildren().add(okButton);
        
        okButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deathStage.hide();
				menuStage.show();
			}
        	
        });
        WaveButton exitButton = new WaveButton("Exit");
		BackgroundImage backgroundImage2 = new BackgroundImage( new Image( getClass().getResource("red_button.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background background2 = new Background(backgroundImage2);
        exitButton.setBackground(background2);
        exitButton.setLayoutX(450);
        exitButton.setLayoutY(200);
        deathPane.getChildren().add(exitButton);
        
        exitButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deathStage.hide();
			}
        	
        });
        TextField textField = new TextField();
		textField.setPromptText("Enter Your Name :");
		textField.setFocusTraversable(false);
		textField.setLayoutX(350);
		textField.setLayoutY(150);
		deathPane.getChildren().add(textField);
		textField.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				name=textField.getText();
				if(speed==4)
				{
					/*try {
						try (BufferedReader easyReader = new BufferedReader(new FileReader("easyScore.txt"))) {
							tempScore=Integer.parseInt(easyReader.readLine());
						}
					} catch (IOException | NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	*/	
					if(point>tempScore) {
					ViewManager.normalHighScore=point;
					ViewManager.normalHighName=name;

					/*try {
						BufferedWriter easyWriter = new BufferedWriter(new FileWriter("easyScores.txt"));
						easyWriter.write(point);
						easyWriter.write("\n"+name);
						easyWriter.close();
					}catch(IOException e) {
						e.printStackTrace();
					}*/
					}
				}
				if(speed==2)
				{
					/*try {
						try (BufferedReader normalReader = new BufferedReader(new FileReader("easyScore.txt"))) {
							tempScore=Integer.parseInt(normalReader.readLine());
						}
					} catch (IOException | NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/	
					ViewManager.easyHighScore=point;
					ViewManager.easyHighName=name;

					/*try {
						BufferedWriter normalWriter = new BufferedWriter(new FileWriter("normalScores.txt"));
						normalWriter.write(point);
						normalWriter.write("\n"+name);
						normalWriter.close();
					}catch(IOException e) {
						e.printStackTrace();
					}*/
				}
				if(speed==10)
				{
					/*try {
						try (BufferedReader hardReader = new BufferedReader(new FileReader("easyScore.txt"))) {
							tempScore=Integer.parseInt(hardReader.readLine());
						}
					} catch (IOException | NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	*/
					ViewManager.hardHighScore=point;
					ViewManager.hardHighName=name;

					/*try {
						BufferedWriter hardWriter = new BufferedWriter(new FileWriter("hardScores.txt"));
						hardWriter.write(point);
						hardWriter.write("\n"+name);
						hardWriter.close();
					}catch(IOException e) {
						e.printStackTrace();
					}*/
				}
				textField.setText("Added Score");
			}
        	
        });
	}

}
