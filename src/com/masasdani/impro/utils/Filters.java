package com.masasdani.impro.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.masasdani.impro.model.Picture;

public class Filters {

	public static Picture grayscale(Picture picture){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = picture.get(x, y);
				int gray = (c.getRed()+c.getGreen()+c.getBlue()) / 3;
				picture.set(x, y, new Color(gray, gray, gray));
			}
		}
		return p;
	}
	
	public static Picture grayscaleTreshhold(Picture picture, int threshold){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = picture.get(x, y);
				int gray = (c.getRed()+c.getGreen()+c.getBlue()) / 3;
				if(gray < threshold)
					picture.set(x, y, Color.BLACK);
				else 
					picture.set(x, y, Color.WHITE);
			}
		}
		return p;
	}
	
	public static Picture grayscaleTreshhold(Picture picture, int threshold, Color minColor, Color maxColor){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = picture.get(x, y);
				int gray = (c.getRed()+c.getGreen()+c.getBlue()) / 3;
				if(gray < threshold)
					picture.set(x, y, minColor);
				else 
					picture.set(x, y, maxColor);
			}
		}
		return p;
	}
	
	public static Picture graphicTreshhold(Picture picture, Color color, int threshold){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = picture.get(x, y);
				double distance = Distance.euclidean(color, c);
				if(distance < threshold)
					picture.set(x, y, Color.BLACK);
				else 
					picture.set(x, y, Color.WHITE);
			}
		}
		return p;
	}
	
	public static Picture graphicTreshhold(Picture picture, Color color, int threshold, Color minColor, Color maxColor){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = picture.get(x, y);
				double distance = Distance.euclidean(color, c);
				if(distance < threshold)
					picture.set(x, y, minColor);
				else 
					picture.set(x, y, maxColor);
			}
		}
		return p;
	}
	
	public static Picture negative(Picture picture){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = picture.get(x, y);
				int red = 255 - c.getRed();
				int green = 255-c.getGreen();
				int blue = 255-c.getBlue();
				image.setRGB(x, y, new Color(red, green, blue).getRGB());
			}
		}
		return p;
	}
	
	public static Picture brightness(Picture picture, int brightness){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = picture.get(x, y);
				int red = brightness + c.getRed();
				int green = brightness + c.getGreen();
				int blue = brightness + c.getBlue();
				image.setRGB(x, y, new Color(red, green, blue).getRGB());
			}
		}
		return p;
	}
	
	public static Picture contrast(Picture picture, int contrast){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = picture.get(x, y);
				int red = (2*(c.getRed()-contrast))+contrast;
				int green = (2*(c.getGreen()-contrast))+contrast;
				int blue = (2*(c.getBlue()-contrast))+contrast;
				if(red>255) red=255;
				if(green>255) green=255;
				if(blue>255) blue=255;
				if(red<0) red=0;
				if(green<0) green=0;
				if(blue<0) blue=0;
				image.setRGB(x, y, new Color(red, green, blue).getRGB());
			}
		}
		return p;
	}
	
	public static Picture logaritmic(Picture picture, int log){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = picture.get(x, y);
				double red = log * Math.log(c.getRed());
				double green = log * Math.log(c.getGreen());
				double blue = log * Math.log(c.getBlue());
				if(red>255) red=255;
				if(green>255) green=255;
				if(blue>255) blue=255;
				if(red<0) red=0;
				if(green<0) green=0;
				if(blue<0) blue=0;
				image.setRGB(x, y, new Color((int)red, (int)green, (int)blue).getRGB());
			}
		}
		return p;
	}
	
	public static Picture logaritmic(Picture picture){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight();y++){
			for(int x=0;x<image.getWidth();x++){
				Color c = picture.get(x, y);
				double red = 1 * Math.log(c.getRed());
				double green = 2 * Math.log(c.getGreen());
				double blue = 5 * Math.log(c.getBlue());
				if(red>255) red=255;
				if(green>255) green=255;
				if(blue>255) blue=255;
				if(red<0) red=0;
				if(green<0) green=0;
				if(blue<0) blue=0;
				image.setRGB(x, y, new Color((int)red, (int)green, (int)blue).getRGB());
			}
		}
		return p;
	}
	
	public static Picture robertEdgeGrayscale(Picture picture){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=0;y<image.getHeight()-1;y++){
			for(int x=0;x<image.getWidth()-1;x++){
				Color c00 = picture.get(x, y);
				Color c01 = picture.get(x, y+1);
				Color c10 = picture.get(x+1, y);
				Color c11 = picture.get(x+1, y+1);
				int gray00 = (c00.getRed()+c00.getGreen()+c00.getBlue()) / 3;
				int gray01 = (c01.getRed()+c01.getGreen()+c01.getBlue()) / 3;
				int gray10 = (c10.getRed()+c10.getGreen()+c10.getBlue()) / 3;
				int gray11 = (c11.getRed()+c11.getGreen()+c11.getBlue()) / 3;
				int gx = gray10 - gray01;
				int gy = - gray00 + gray11;
				int gf = Math.abs(gx)+Math.abs(gy);
				if(gf > 255) gf=255;
				if(gf<0) gf=0;
				image.setRGB(x, y, new Color(gf, gf, gf).getRGB());
			}
		}
		return p;
	}
	

	public static Picture sobelEdgeGrayscale(Picture picture){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=1;y<image.getHeight()-1;y++){
			for(int x=1;x<image.getWidth()-1;x++){
				Color c00 = picture.get(x-1, y-1);
				Color c01 = picture.get(x-1, y);
				Color c02 = picture.get(x-1, y+1);
				Color c10 = picture.get(x, y-1);
				Color c12 = picture.get(x, y+1);
				Color c20 = picture.get(x+1, y-1);
				Color c21 = picture.get(x+1, y);
				Color c22 = picture.get(x+1, y+1);
				int gray00 = (c00.getRed()+c00.getGreen()+c00.getBlue()) / 3;
				int gray01 = (c01.getRed()+c01.getGreen()+c01.getBlue()) / 3;
				int gray02 = (c02.getRed()+c02.getGreen()+c02.getBlue()) / 3;
				int gray10 = (c10.getRed()+c10.getGreen()+c10.getBlue()) / 3;
				int gray12 = (c12.getRed()+c12.getGreen()+c12.getBlue()) / 3;
				int gray20 = (c20.getRed()+c20.getGreen()+c20.getBlue()) / 3;
				int gray21 = (c21.getRed()+c21.getGreen()+c21.getBlue()) / 3;
				int gray22 = (c22.getRed()+c22.getGreen()+c22.getBlue()) / 3;
				int gx = - gray00 - 2 *(gray10) - gray20 + gray02 + 2*(gray12) + gray22;
				int gy = - gray00 - 2 *(gray01) - gray02 + gray20 + 2*(gray21) + gray22;
				int gf = Math.abs(gx)+Math.abs(gy);
				if(gf > 255) gf=255;
				if(gf<0) gf=0;
				image.setRGB(x, y, new Color(gf, gf, gf).getRGB());
			}
		}
		return p;
	}
	
	public static Picture prewittEdgeGrayscale(Picture picture){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=1;y<image.getHeight()-1;y++){
			for(int x=1;x<image.getWidth()-1;x++){
				Color c00 = picture.get(x-1, y-1);
				Color c01 = picture.get(x-1, y);
				Color c02 = picture.get(x-1, y+1);
				Color c10 = picture.get(x, y-1);
				Color c12 = picture.get(x, y+1);
				Color c20 = picture.get(x+1, y-1);
				Color c21 = picture.get(x+1, y);
				Color c22 = picture.get(x+1, y+1);
				int gray00 = (c00.getRed()+c00.getGreen()+c00.getBlue()) / 3;
				int gray01 = (c01.getRed()+c01.getGreen()+c01.getBlue()) / 3;
				int gray02 = (c02.getRed()+c02.getGreen()+c02.getBlue()) / 3;
				int gray10 = (c10.getRed()+c10.getGreen()+c10.getBlue()) / 3;
				int gray12 = (c12.getRed()+c12.getGreen()+c12.getBlue()) / 3;
				int gray20 = (c20.getRed()+c20.getGreen()+c20.getBlue()) / 3;
				int gray21 = (c21.getRed()+c21.getGreen()+c21.getBlue()) / 3;
				int gray22 = (c22.getRed()+c22.getGreen()+c22.getBlue()) / 3;
				int gx = - gray00 - gray10 - gray20 + gray02 + gray12 + gray22;
				int gy = - gray00 - gray01 - gray02 + gray20 + gray21 + gray22;
				int gf = Math.abs(gx)+Math.abs(gy);
				if(gf > 255) gf=255;
				if(gf<0) gf=0;
				image.setRGB(x, y, new Color(gf, gf, gf).getRGB());
			}
		}
		return p;
	}
	
	public static Picture spatial(Picture picture, double[][] kernel){
		Picture p = picture;
		BufferedImage image = p.getImage();
		int kernelmid = kernel.length/2;
		for(int y=kernelmid;y<image.getHeight()-kernelmid;y++){
			for(int x=1;x<image.getWidth()-1;x++){
				double red = 0;
				double green = 0;
				double blue = 0;
				for(int i=0;i<kernel.length;i++){
					for(int j=0; j<kernel[i].length;j++){
						Color c = new Color(image.getRGB((x-kernelmid)+j, (y-kernelmid)+j));
						red += c.getRed()*kernel[i][j];
						green += c.getGreen()*kernel[i][j];
						blue += c.getBlue()*kernel[i][j];	
					}
				}
//				Color color = new Color(image.getRGB(x, y));
//				red += color.getRed();
//				green += color.getGreen();
//				blue += color.getBlue();
				if(red>255) red=255;
				if(green>255) green=255;
				if(blue>255) blue=255;
				if(red<0) red=0;
				if(green<0) green=0;
				if(blue<0) blue=0;
				image.setRGB(x, y, new Color((int)red, (int)green, (int)blue).getRGB());
			}
		}
		return p;
	}
	
	public static Picture spatial(Picture picture, int kerneldimention){
		Picture p = picture;
		BufferedImage image = p.getImage();
		int kernelmid = kerneldimention/2;
		int kernelpow = kerneldimention*kerneldimention;
		for(int y=kernelmid;y<image.getHeight()-kernelmid;y++){
			for(int x=kernelmid;x<image.getWidth()-kernelmid;x++){
				double red = 0;
				double green = 0;
				double blue = 0;
				for(int i=0;i<kerneldimention;i++){
					for(int j=0; j<kerneldimention;j++){
						Color c = new Color(image.getRGB((x-kernelmid)+j, (y-kernelmid)+j));
						red += c.getRed();
						green += c.getGreen();
						blue += c.getBlue();	
					}
				}
				red /= kernelpow;
				green /= kernelpow;
				blue /= kernelpow;
//				Color color = new Color(image.getRGB(x, y));
//				red += color.getRed();
//				green += color.getGreen();
//				blue += color.getBlue();
				if(red>255) red=255;
				if(green>255) green=255;
				if(blue>255) blue=255;
				if(red<0) red=0;
				if(green<0) green=0;
				if(blue<0) blue=0;
				image.setRGB(x, y, new Color((int)red, (int)green, (int)blue).getRGB());
			}
		}
		return p;
	}
	public static Picture spatial(Picture picture){
		Picture p = picture;
		BufferedImage image = p.getImage();
		for(int y=1;y<image.getHeight()-1;y++){
			for(int x=1;x<image.getWidth()-1;x++){
				double red = 0;
				double green = 0;
				double blue = 0;
				for(int i=0;i<3;i++){
					for(int j=0; j<3;j++){
						Color c = new Color(image.getRGB((x-1)+j, (y-1)+j));
						red += c.getRed();
						green += c.getGreen();
						blue += c.getBlue();	
					}
				}
				red /= 9;
				green /= 9;
				blue /= 9;
				if(red>255) red=255;
				if(green>255) green=255;
				if(blue>255) blue=255;
				if(red<0) red=0;
				if(green<0) green=0;
				if(blue<0) blue=0;
				image.setRGB(x, y, new Color((int)red, (int)green, (int)blue).getRGB());
			}
		}
		return p;
	}
}
