package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import exception.ErrorGUI;


/*
 * This class is used to Load Image from the resources files by inheriting the methods 
 * from ImageIcon Class
 */
public class LoadImage extends ImageIcon {
	
	public LoadImage(String f) {
		this(f, 26, 26);
	}
	
	public LoadImage(String f, int height, int width) {
		BufferedImage bImage;
		try {
			bImage = ImageIO.read(getClass().getResource(f));
			Image imgScaled = bImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			setImage(imgScaled);
		} catch (IOException | IllegalArgumentException e) {
			if (e instanceof IllegalArgumentException) {
				new ErrorGUI("File not Found", e);
			} else if (e instanceof IOException) {
				new ErrorGUI("Error while Reading File", e);
			}
		}
	}
}
