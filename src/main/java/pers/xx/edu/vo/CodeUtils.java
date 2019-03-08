package pers.xx.edu.vo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CodeUtils {

	
	/**
	 * @description 去掉白边的二维码
	 * @param request
	 * @param contents
	 * @return
	 */
	public static ByteArrayOutputStream createCode(HttpServletRequest request,String contents){
		
		String imgName = System.currentTimeMillis()+"";	//文件名字
		String suffix = "png";	//文件后缀
		String imgPath = request.getServletContext().getRealPath("/temp/"+imgName+"."+suffix);	//文件的真实路径
        int margin = 0;	//边距(0-4)
        ErrorCorrectionLevel level = ErrorCorrectionLevel.L;  //二维码容错率  
        int onColor = 0xFF000000;     //前景色  
        int offColor = 0xFFFFFFFF;    //背景色  
        
	    File filePath = new File(imgPath);  
	    if(!filePath.exists()){  
	        filePath.mkdirs();  
	    }  
	       
	    File imageFile = new File(imgPath,imgName);  
	    Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();    
	    // 指定纠错等级    
	    hints.put(EncodeHintType.ERROR_CORRECTION, level);    
	    // 指定编码格式    
	    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");   
	    hints.put(EncodeHintType.MARGIN, margin);   //设置白边  
	    ByteArrayOutputStream out = null;
	    try {    
	        MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);  
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,BarcodeFormat.QR_CODE, 150, 150, hints);    
	        MatrixToImageWriter.writeToPath(bitMatrix, suffix, imageFile.toPath(), config);   
	         
	        FileInputStream in = new FileInputStream(imageFile);
	        out = new ByteArrayOutputStream();
	        byte[] b = new byte[1024];
	        while ((in.read(b)) != -1) {
	        	out.write(b, 0, b.length);
	        }
	        out.close();
	        in.close();
	     } catch (Exception e) {    
	         e.printStackTrace();    
	     }
	    return out;
			
	}
}
