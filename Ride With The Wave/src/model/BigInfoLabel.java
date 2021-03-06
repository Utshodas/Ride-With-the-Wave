package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class BigInfoLabel extends Label {
	
	private final static String FONT_PATH = "src/model/resources/kenny_pixel.ttf";
	
	public BigInfoLabel( String text)
	{
		setPrefHeight(70);
		setPrefWidth(300);
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("yellow_button01.png").toExternalForm(),300,70,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10,10,10,10));
        setText(text);
        setLabelFont();
        
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont( new FileInputStream(new File(FONT_PATH)),15));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			setFont(Font.font("AvantGarde",15));
		}
	}

}
