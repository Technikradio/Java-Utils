package org.technikradio.universal_tools;
/**
 * @author doralitze
 * @license LGPLv3
 * @copyright (c) technikradio 2014
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

public class ImageCreator {
	
	public static final String FORMAT_JPG = "jpg";
	public static final String FORMAT_PNG = "png";
	public static final String FORMAT_TIFF = "tiff";
	
	public static void saveImage(String filename, String format, BufferedImage img) throws IOException{
		ImageWriter writer = null;
		ImageOutputStream ios = null;
		ImageWriteParam param = null;
		IIOMetadata meta = null;
		try{extracted(meta).reset();}catch(Exception e){e.printStackTrace();}
		Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName(format);
		if( iterator.hasNext() ){
            writer = iterator.next();
        }
		ios = ImageIO.createImageOutputStream(filename + "." + format);
		writer.setOutput(ios);
		param = new JPEGImageWriteParam(java.util.Locale.getDefault());
	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
	    param.setCompressionQuality(1.00F);
	    meta = writer.getDefaultImageMetadata(new ImageTypeSpecifier(img), param);
	    writer.write(extracted(meta), new IIOImage( img, null, null ), param);
	}
	
	public static void saveImage(String filename, String format, BufferedImage img, float quality) throws IOException{
		ImageWriter writer = null;
		ImageOutputStream ios = null;
		ImageWriteParam param = null;
		IIOMetadata meta = null;
		//TODO set metadata
		try{extracted(meta).reset();}catch(Exception e){e.printStackTrace();}
		Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName(format);
		if( iterator.hasNext() ){
            writer = iterator.next();
        }
		ios = ImageIO.createImageOutputStream(filename + "." + format);
		writer.setOutput(ios);
		param = new JPEGImageWriteParam(java.util.Locale.getDefault());
	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
	    param.setCompressionQuality(quality);
	    meta = writer.getDefaultImageMetadata(new ImageTypeSpecifier(img), param);
	    writer.write(extracted(meta), new IIOImage(img, null, extracted(meta)), param);
	}

	private static IIOMetadata extracted(IIOMetadata meta) {
		return meta;
	}
	
	public static void saveImage(String filename, String format, BufferedImage img, float quality, List<? extends BufferedImage> thumbnails) throws IOException{
		ImageWriter writer = null;
		ImageOutputStream ios = null;
		ImageWriteParam param = null;
		IIOMetadata meta = null;
		//TODO set metadata
		try{extracted(meta).reset();}catch(Exception e){e.printStackTrace();}
		Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName(format);
		if( iterator.hasNext() ){
            writer = iterator.next();
        }
		ios = ImageIO.createImageOutputStream(filename + "." + format);
		writer.setOutput(ios);
		param = new JPEGImageWriteParam(java.util.Locale.getDefault());
	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
	    param.setCompressionQuality(quality);
	    meta = writer.getDefaultImageMetadata(new ImageTypeSpecifier(img), param);
	    writer.write(extracted(meta), new IIOImage(img, thumbnails, extracted(meta)), param);
	}
	
	public static void saveImage(String filename, String format, BufferedImage img, List<? extends BufferedImage> thumbnails) throws IOException{
		ImageWriter writer = null;
		ImageOutputStream ios = null;
		ImageWriteParam param = null;
		IIOMetadata meta = null;
		try{extracted(meta).reset();}catch(Exception e){;}
		Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName(format);
		if( iterator.hasNext() ){
            writer = iterator.next();
        }
		ios = ImageIO.createImageOutputStream(filename + "." + format);
		writer.setOutput(ios);
		param = new JPEGImageWriteParam(java.util.Locale.getDefault());
	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
	    param.setCompressionQuality(1.0F);
	    meta = writer.getDefaultImageMetadata(new ImageTypeSpecifier(img), param);
	    writer.write(extracted(meta), new IIOImage(img, thumbnails, extracted(meta)), param);
	}
	
	public static void saveImage(String filename, String format, BufferedImage img, List<? extends BufferedImage> thumbnails, IIOMetadata meta) throws IOException{
		ImageWriter writer = null;
		ImageOutputStream ios = null;
		ImageWriteParam param = null;
		Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName(format);
		if( iterator.hasNext() ){
            		writer = iterator.next();
        	}
		ios = ImageIO.createImageOutputStream(filename + "." + format);
		writer.setOutput(ios);
		param = new JPEGImageWriteParam(java.util.Locale.getDefault());
	    	param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
	    	param.setCompressionQuality(1.0F);
	    	writer.write(extracted(meta), new IIOImage(img, thumbnails, extracted(meta)), param);
	}
	
	public static BufferedImage generateBufferedImage(Graphics g, int height, int width){
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
	}
}
