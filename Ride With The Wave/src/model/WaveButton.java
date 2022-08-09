package model;



import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class WaveButton extends Button {
	
	
	private final String FONT_PATH = "src/model/resources/kenney_pixel.ttf";
	
	public WaveButton( String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(50);
		initializeMouseListners();
		
	}	
	private void setButtonPressedStyle()
	{
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
	}
	private void setButtonReleasedStyle()
	{
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4);
	}
	private void setButtonFont()
	{
		try {
			setFont(Font.loadFont( new FileInputStream(FONT_PATH), 30));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			setFont(Font.font("AvantGarde",23));
		}
	}
	private void initializeMouseListners()
	{
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if( event.getButton().equals(MouseButton.PRIMARY))
					setButtonPressedStyle();	
			}	
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if( event.getButton().equals(MouseButton.PRIMARY))
					setButtonReleasedStyle();	
			}	
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
			setEffect( new DropShadow());
			}
		});
		setOnMouseExited( new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
				
			}
			
		});
	}
	
	

}
