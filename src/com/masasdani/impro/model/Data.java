package com.masasdani.impro.model;

import java.awt.Color;

public class Data{

	private Color color;
	private int cluster;
	
	public Data(int red, int green, int blue, int cluster) {
		color = new Color(red, green, blue);
		this.cluster = 0;
	}
	
	public Data(Color color, int cluster) {
		this.color = color;
		this.cluster = 0;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getCluster() {
		return cluster;
	}

	public void setCluster(int cluster) {
		this.cluster = cluster;
	}
	
	
}
