package com.masasdani.impro.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;

import com.masasdani.impro.view.ImagePanel;

public class Picture {

	private BufferedImage image;
	
	public Picture(Image image){
		this.image = (BufferedImage) image;
	}
	
	public Picture(String filename) {
		File file = new File(filename);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			image = null;
		} 
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Color get(int x, int y){
		return new Color(image.getRGB(x, y));
	}
	
	public void set(int x, int y, Color color){
		image.setRGB(x, y, color.getRGB());
	}
	
	public int[] histogram(){
		int[] hist = new int[256];
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = get(x, y);
				hist[c.getRed()] ++;
				hist[c.getBlue()] ++;
				hist[c.getGreen()] ++;
			}
		}
		return hist;
	}
	
	public int getWidth(){
		return image.getWidth();
	}
	
	public int getHeight(){
		return image.getHeight();
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = get(x, y);
				builder.append(c.getRed()+","+c.getGreen()+","+c.getBlue()+" ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public void write(String filename, String format){
		try {
			ImageIO.write(image, format, new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void show(){
		JDialog dialog = new JDialog();
		dialog.setMinimumSize(new Dimension(getWidth(), getHeight()));
//		Dimension dimension = new Dimension(getWidth(), getHeight());
//		dialog.setPreferredSize(dimension);
		dialog.getContentPane().add(new ImagePanel(image),BorderLayout.CENTER);
		dialog.setVisible(true);
	}
	
	public void print(){
		JDialog dialog = new JDialog();
		dialog.setMinimumSize(new Dimension(800, 600));
		TextArea area = new TextArea(toString());
		dialog.getContentPane().add(area, BorderLayout.CENTER);
		dialog.setVisible(true);
	}
}
