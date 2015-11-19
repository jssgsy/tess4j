package com.miaxis;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

public class Tess4jSample {
	
	public static void main(String[] args) throws IOException {
		
		String projectRootPath = System.getProperty("user.dir");//获取当前程序根目录的绝对路径
		String imagePath = "eurotext.png";//这里可以使用绝对路径，如D://eurotext.png
		File imageFile = new File(imagePath);		
		Tesseract instance = new Tesseract();
		instance.setDatapath(projectRootPath);//指定TESSDATA_PREFIX所在目录，便于搜索字体库文件
		instance.setLanguage("chi_sim");//指定识别语言为中文，这里已经将中文字体库文件放在tessdata目录下
		
		BufferedImage biTemp = ImageIO.read(imageFile);//根据要识别的图片获得一个缓冲的bufferedimage供下面使用
		BufferedImage bi = ImageHelper.convertImageToGrayscale(biTemp);//要识别中文，对图片黑白处理很重要
		bi = ImageHelper.getScaledInstance(bi, bi.getWidth() *5, bi.getHeight() * 5);//将图片放大五倍
		try {
//			String result = instance.doOCR(imageFile);//可以直接使用，效果没有下面经过预处理的准确
			String result = instance.doOCR(bi);//进行预处理会延长识别时间
			System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * 提高识别率的两种可能：
	 * 1。Tesseract类的setTessVariable方法
	 * 2.Tesseract类的setConfigs方法
	 */
}


 
