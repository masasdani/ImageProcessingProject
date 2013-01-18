package com.masasdani.impro.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	/**
	 * this is the custom panel for showing image
	 */
	private static final long serialVersionUID = 1L;
	Image image;
	
	public ImagePanel(BufferedImage bufferedImage) {
		image = bufferedImage;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D gd=(Graphics2D) g.create();
        gd.drawImage(image, 0,0, getWidth(), getHeight(), null);
        gd.dispose();
	}
}
