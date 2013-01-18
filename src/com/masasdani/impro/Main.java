package com.masasdani.impro;

import com.masasdani.impro.clustering.KMeans;
import com.masasdani.impro.model.Picture;
import com.masasdani.impro.utils.Filters;

public class Main {

	public static final String BASE_FOLDER = "/Users/masasdani/Pictures/impro/";
	
	public static void main(String[] args){
		
		Picture picture = new Picture(BASE_FOLDER+"impro.jpg");
//		picture = Filters.spatial(picture);
//		picture.write(BASE_FOLDER+"processed"+System.currentTimeMillis()+".jpg", "jpg");
//		KMeans kMeans = new KMeans(6, picture);
//		kMeans.getClusteredPicture().write(BASE_FOLDER+"processed"+System.currentTimeMillis()+".jpg", "jpg");
//		int t = kMeans.getThreshold();
//		Filters.grayscaleTreshhold(picture, 128).write(BASE_FOLDER+"processed"+System.currentTimeMillis()+".jpg", "jpg");
		Filters.prewittEdgeGrayscale(picture).show();
	}
}
