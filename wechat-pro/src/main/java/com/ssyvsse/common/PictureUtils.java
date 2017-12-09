package com.ssyvsse.common;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/***
 * 
 * @author  上传图片时对图片进行压缩
 */
public class PictureUtils {
	
	
	private static Logger logger = Logger.getLogger(PictureUtils.class.getName());
	
	public static void main(String[] args) {
		solidityImage("c:2.jpg","c:4.jpg");
	}

	/**
	 * 压缩单个图片文件
	 * 
	 * @param oldpath
	 */
	public static void solidityImage(String oldpath, String newpath) {
		
		compressImage(oldpath, newpath);

		logger.info("PictureUtils solidityImage compress success");
	}
	
	/***
	 * 对文件批量进行压缩
	 * @param paths
	 */
	public static void batchSolidity(List<String> paths){
		for (String path : paths) {
			solidityImage(path, path);
		}
	}

	/**
	 * 图片文件读取
	 * 
	 * @param srcImgPath
	 * @return
	 */
	private static BufferedImage inputImage(String srcImgPath) {
		BufferedImage srcImage = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(srcImgPath);
			srcImage = javax.imageio.ImageIO.read(in);
		} catch (IOException e) {
			logger.error("PictureUtils 读取图片文件出错！" + e.getMessage());
			e.printStackTrace();
		}finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error("关闭流报错");
					e.printStackTrace();
				}
			}
		}
		return srcImage;
	}

	/**
	 * * 将图片按照指定的图片尺寸压缩
	 * 
	 * @param srcImgPath
	 *            :源图片路径
	 * @param outImgPath
	 *            :输出的压缩图片的路径
	 * @param newWidth
	 *            :压缩后的图片宽
	 * @param newHeigh
	 *            :压缩后的图片高
	 */
	public static void compressImage(String srcImgPath, String outImgPath) {
		BufferedImage src = inputImage(srcImgPath);
		disposeImage(src, outImgPath);
	}

	/**
	 * 指定长或者宽的最大值来压缩图片
	 * 
	 * @param srcImgPath
	 *            :源图片路径
	 * @param outImgPath
	 *            :输出的压缩图片的路径
	 * @param maxLength
	 *            :长或者宽的最大值
	 */
	public static void compressImage(String srcImgPath, String outImgPath, int maxLength) {
		// 得到图片
		BufferedImage src = inputImage(srcImgPath);
		if (null != src) {
			int oldWith = src.getWidth();
			// 得到源图宽
			int oldHeigh = src.getHeight();
			// 得到源图长
			int newWidth = 0;
			// 新图的宽
			int newHeigh = 0;
			// 新图的长
			// 根据图片尺寸压缩比得到新图的尺寸
			if (oldWith > oldHeigh) {
				// 图片要缩放的比例
				newWidth = maxLength;
				newHeigh = (int) Math.round(oldHeigh * ((float) maxLength / oldWith));
			} else {
				newWidth = (int) Math.round(oldWith * ((float) maxLength / oldHeigh));
				newHeigh = maxLength;
			}
			disposeImage(src, outImgPath);
		}
	}

	/**
	 * 处理图片
	 * 
	 * @param src
	 * @param outImgPath
	 * @param newWidth
	 * @param newHeigh
	 */
	private synchronized static void disposeImage(BufferedImage src, String outImgPath) {
		// 得到图片
		int oldWith = src.getWidth();
		// 得到源图宽
		int oldHeigh = src.getHeight();
		// 得到源图长
		BufferedImage newImg = null;
		// 判断输入图片的类型
		switch (src.getType()) {
		case 13:
			// png,gifnewImg = new BufferedImage(newWidth, newHeigh,
			// BufferedImage.TYPE_4BYTE_ABGR);
			break;
		default:
			newImg = new BufferedImage(oldWith, oldHeigh, BufferedImage.TYPE_BYTE_INDEXED);
			break;
		}
		Graphics2D g = newImg.createGraphics();
		// 从原图上取颜色绘制新图
		g.drawImage(src, 0, 0, oldWith, oldHeigh, null);
		g.dispose();
		// 根据图片尺寸压缩比得到新图的尺寸
		newImg.getGraphics().drawImage(src.getScaledInstance(oldWith, oldHeigh, Image.SCALE_AREA_AVERAGING), 0, 0, null);
		newImg.getGraphics().dispose();
		src.flush();
		// 调用方法输出图片文件
		outImage(outImgPath, newImg);
	}

	/**
	 * 将图片文件输出到指定的路径，并可设定压缩质量
	 * 
	 * @param outI	mgPath
	 * @param newImg
	 * @param per
	 */
	private static void outImage(String outImgPath, BufferedImage newImg) {
		// 判断输出的文件夹路径是否存在，不存在则创建
		File file = new File(outImgPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		} // 输出到文件流
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(outImgPath));
			ImageIO.write(newImg, outImgPath.substring(outImgPath.lastIndexOf(".") + 1), out);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			try {
				if(out != null) {
					out.close();
				}
				newImg.flush();
				newImg=null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 
	 * 	fold削除
	 * 
	 */
	public static void delFold(String filepath) throws IOException{
	File f = new File(filepath);     
	if(f.exists() && f.isDirectory()){
	    if(f.listFiles().length==0){
	        f.delete();
	    }
	}
	}
	/**
	 * 
	 * 	dummy file削除
	 * 
	 */
	public static void delFile(String filepath) throws IOException{
	File f = new File(filepath);     
	if(f.exists() ){
	        f.delete();
	}
	}

}
