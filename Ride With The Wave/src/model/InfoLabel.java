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

public class InfoLabel extends Label {
	
	private final static String FONT_PATH = "src/model/resources/kenny_pixel.ttf";
	
	public InfoLabel( String text)
	{
		setPrefHeight(20);
		setPrefWidth(130);
		BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("info_label.png").toExternalForm(),130,20,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER);
        //setPadding(new Insets(2,2,2,2));
        setText(text);
        setLabelFont();
        
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont( new FileInputStream(new File(FONT_PATH)),15));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//setFont(Font.font("AvantGarde",15));
		}
	}

}
