package com.masasdani.impro.utils;

import java.awt.Color;

public class Distance {

	public static double euclidean(Color color1, Color color2){
		return Math.sqrt(Math.pow((color1.getRed()-color2.getRed()), 2)
				+Math.pow(color1.getGreen()-color2.getGreen(), 2)
				+Math.pow(color1.getBlue()-color2.getBlue(), 2));
	}
	
	public static double euclidean(double [] x1, double [] x2) {
        if (x1.length != x2.length) {
            throw new IllegalArgumentException(
                    "Vectors should be of equal length: x1 length = "
                            + x1.length + " x2 length = " + x2.length);
        }
        double val = 0;
        double temp;
        for (int i = 0; i < x1.length; i++) {
            temp = x1[i] - x2[i];
            temp *= temp;
            val += temp;
        }

        return Math.sqrt(val);
    }

	public static double euclideanSquared(Color color1, Color color2){
		return Math.pow((color1.getRed()-color2.getRed()), 2)
				+Math.pow(color1.getGreen()-color2.getGreen(), 2)
				+Math.pow(color1.getBlue()-color2.getBlue(), 2);
	}
	
	
	public static double euclideanSquared(double[] x1, double[] x2) {
        if (x1.length != x2.length) {
            throw new IllegalArgumentException(
                    "Vectors should be of equal length: x1 length = "
                            + x1.length + " x2 length = " + x2.length);
        }

        double val = 0;
        double temp;
        for (int i = 0; i < x1.length; i++) {
            temp = x1[i] - x2[i];
            temp *= temp;
            val += temp;
        }

        return val;
    }
	
	public static double manhattan(Color color1, Color color2){
		return Math.abs((color1.getRed()-color2.getRed()))
				+Math.abs(color1.getGreen()-color2.getGreen())
				+Math.abs(color1.getBlue()-color2.getBlue());
	}
	
	public static double manhattan(double[] x1, double[] x2) {
        if (x1.length != x2.length) {
            throw new IllegalArgumentException(
                    "Vectors should be of equal length: x1 length = "
                            + x1.length + " x2 length = " + x2.length);
        }
        double distance = 0;
        for (int i = 0; i < x1.length; i++) {
            distance += Math.abs(x1[i] - x2[i]);
        }
        return distance;
    }
}
