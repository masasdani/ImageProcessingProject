package com.masasdani.impro.clustering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.masasdani.impro.model.Data;
import com.masasdani.impro.model.Picture;
import com.masasdani.impro.utils.Distance;

public class KMeans {

	private Picture picture;
	private BufferedImage bitmap;
	private int numberCluster;
	private ArrayList<Data> dataSet;
	private ArrayList<Color> centroids;
	private int maxIteration = 100;

	public KMeans(int numberCluster, Picture picture) {
		this.bitmap =picture.getImage();
		this.numberCluster = numberCluster;
		this.picture = picture;
	}
	
	public BufferedImage getClusteredBitmap(){
		clusters();
		int i=0;
		for(int y=0;y<bitmap.getHeight();y++){
			for(int x=0;x<bitmap.getWidth();x++){
				bitmap.setRGB(x, y, centroids.get(dataSet.get(i).getCluster()).getRGB());
				i++;
			}
		}
		return bitmap;
	}
	
	public Picture getClusteredPicture() {
		picture.setImage(getClusteredBitmap());
		return picture;
	}

	public int getNumberCluster() {
		return numberCluster;
	}

	public ArrayList<Data> getDataSet() {
		return dataSet;
	}

	public ArrayList<Color> getCentroids() {
		return centroids;
	}
	private void initialize(){
		centroids = new ArrayList<Color>();
		dataSet = new ArrayList<Data>();
		Random random = new Random();
        for(int i=0;i<numberCluster;i++){
        	int x = random.nextInt(bitmap.getWidth());
        	int y = random.nextInt(bitmap.getHeight());
        	centroids.add(new Color(bitmap.getRGB(x, y)));
        }
//        centroids.add(new Color(r, g, b)); // banyak sesuai k
        return;
    }
	
	public void clusters(){
		initialize();
		final double bigNumber = Math.pow(10, 10);
		double minimum = bigNumber;
		double distance = 0.0;
        int cluster = 0;
        boolean isStillMoving = true;
        Data newData = null;
        
        for(int y=0;y<bitmap.getHeight();y++){
			for(int x =0;x<bitmap.getWidth();x++){
				Color color = new Color(bitmap.getRGB(x, y));
	        	int red = color.getRed();
	        	int green = color.getGreen();
	        	int blue = color.getBlue();
				newData = new Data(red, green, blue, 0);
	            dataSet.add(newData);
	            minimum = bigNumber;
	            for(int i = 0; i < numberCluster; i++)
	            {
	                distance = Distance.euclidean(newData.getColor(), centroids.get(i));
	                if(distance < minimum){
	                    minimum = distance;
	                    cluster = i;
	                }
	            }
	            newData.setCluster(cluster);
	        }
        }
        int iteration = 0;
        
        // Now, keep looping until equilibrium occurs.
        while(isStillMoving && iteration < maxIteration)
        {
        	iteration ++;
        	
        	double[] sumRed = new double[numberCluster];
        	double[] sumGreen = new double[numberCluster];
        	double[] sumBlue = new double[numberCluster];
        	int[] countCentroid = new int[numberCluster];
        	for(int i=0; i< dataSet.size();i++){
        		sumRed[dataSet.get(i).getCluster()] += dataSet.get(i).getColor().getRed();
        		sumGreen[dataSet.get(i).getCluster()] += dataSet.get(i).getColor().getGreen();
        		sumBlue[dataSet.get(i).getCluster()] += dataSet.get(i).getColor().getBlue();
        		countCentroid[dataSet.get(i).getCluster()] ++;
        	}
        	
        	for(int i=0;i<numberCluster;i++){
        		double red = sumRed[i] / countCentroid[i];
        		double green = sumGreen[i] / countCentroid[i];
        		double blue = sumBlue[i] / countCentroid[i];
        		Color c = new Color((int)red, (int)green, (int)blue);
        		centroids.set(i, c);
        	}
        	
            // Assign all data to the new centroids
            isStillMoving = false;
            
            for(int i = 0; i < dataSet.size(); i++){
                Data tempData = dataSet.get(i);
                minimum = bigNumber;
                for(int j = 0; j < numberCluster; j++)
                {
                    distance = Distance.euclidean(tempData.getColor(), centroids.get(j));
                    if(distance < minimum){
                        minimum = distance;
                        cluster = j;
                    }
                }
                if(tempData.getCluster() != cluster){
                    tempData.setCluster(cluster);
                    isStillMoving = true;
                }

            }
        }
        System.out.println("iteration = "+iteration);
        return;
	}
	
	public int getThreshold(){
		clusters();
		int threshold = 0;
		double[] sumRed = new double[numberCluster];
    	double[] sumGreen = new double[numberCluster];
    	double[] sumBlue = new double[numberCluster];
    	int[] countCentroid = new int[numberCluster];
    	for(int i=0; i< dataSet.size();i++){
    		sumRed[dataSet.get(i).getCluster()] += dataSet.get(i).getColor().getRed();
    		sumGreen[dataSet.get(i).getCluster()] += dataSet.get(i).getColor().getGreen();
    		sumBlue[dataSet.get(i).getCluster()] += dataSet.get(i).getColor().getBlue();
    		countCentroid[dataSet.get(i).getCluster()] ++;
    	}
    	double sumgray=0;
    	for(int i=0;i<numberCluster;i++){
    		double red = sumRed[i] / countCentroid[i];
    		double green = sumGreen[i] / countCentroid[i];
    		double blue = sumBlue[i] / countCentroid[i];
    		double gray = (red+green+blue)/3; 
    		sumgray+=gray;
    	}
    	threshold = (int) sumgray / numberCluster;
		return threshold;
	}
	
	
}
