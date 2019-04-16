package pers.xx.edu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @description 文件上传工具类
 * @author XieXing
 * @create 2018-3-30上午9:33:20
 *
 */
public class FileUtils {


	/**使用范围   一般的照片上传
	 * @description 多文件上传
	 * @param files
	 * @param realPath
	 * @return 文件存储地址
	 */
	public static List<String> uploadFile(MultipartFile[] files,HttpServletRequest request,String catalog){
		String realPath = request.getServletContext().getRealPath(catalog);
		//String addressList = "";
		List<String> images=new ArrayList<String>();
		long startTime = System.currentTimeMillis();
		//判断file数组不能为空并且长度大于0
        if(files!=null && files.length>0){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                String fileName = file.getOriginalFilename();
                //保存文件
                if (!file.isEmpty() && fileName != "") {
                    try {

                    	String filePrefix = fileName.substring(0,fileName.indexOf("."));//前缀
                    	String filePostfix = fileName.substring(fileName.indexOf("."));//后缀
                    	String str = "";
                    	switch(filePrefix.length()){
                    		case 1:
                    		case 2:
                    		case 3:
                    			str = filePrefix;
                    			break;
                    		default:
                    			str = filePrefix.substring(filePrefix.length()-3,filePrefix.length());
                    			break;
                    	}
                		String newFileName = startTime + str + i + filePostfix;

                        // 文件保存路径
                        String filePath = realPath +"\\"+newFileName;
                        // 转存文件
                        file.transferTo(new File(filePath));
                        //存储文件保存地址
                        //addressList += catalog+newFileName+",";
                        //保存上传文件的路径,返回数据库。
                        String imagePath="/files/"+newFileName;
                        images.add(imagePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            /*if(addressList.length()>0){
            	addressList = addressList.substring(0, addressList.length()-1);
            }*/
        }
		//System.out.println("文件上传时间为："+String.valueOf(endTime-startTime)+"ms"+"\n目录:"+addressList);
        return images;
	}


	/**适用范围：多线程多文件上传
	 * @description 多文件上传
	 * @param files
	 * @param realPath
	 * @return 文件存储地址
	 */
	public static String uploadFiles(MultipartFile[] files,HttpServletRequest request,String catalog){
		String realPath = request.getServletContext().getRealPath(catalog);
		String addressList = "";
		long startTime = System.currentTimeMillis();
		//判断file数组不能为空并且长度大于0
        if(files!=null&&files.length>0){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                String fileName = file.getOriginalFilename();
                //保存文件
                if (!file.isEmpty() && fileName != "") {
                    try {

                    	String filePrefix = fileName.substring(0,fileName.indexOf("."));//前缀
                    	String filePostfix = fileName.substring(fileName.indexOf("."));//后缀
                    	String str = "";
                    	switch(filePrefix.length()){
                    		case 1:
                    		case 2:
                    		case 3:
                    			str = filePrefix;
                    			break;
                    		default:
                    			str = filePrefix.substring(filePrefix.length()-3,filePrefix.length());
                    			break;
                    	}
                		String newFileName = startTime + str + i + filePostfix;

                        // 文件保存路径
                        String filePath = realPath +"\\"+newFileName;
                        // 转存文件
                        file.transferTo(new File(filePath));
                        //存储文件保存地址
                        addressList += catalog+newFileName+",";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if(addressList.length()>0){
            	addressList = addressList.substring(0, addressList.length()-1);
            }
        }
		long  endTime=System.currentTimeMillis();
        System.out.println("文件上传时间为："+String.valueOf(endTime-startTime)+"ms"+"\n目录:"+addressList);
        return addressList;
	}

	/**
	 * @author xiexing
	 * @description Transfers bytes into this channel's file from the given readable byte channel.
	 *  @param  src
     *         The source channel
     *
     * @param  position
     *         The position within the file at which the transfer is to begin;
     *         must be non-negative
     *
     * @param  count
     *         The maximum number of bytes to be transferred; must be
     *         non-negative
 	 * @return the path of saved file
	 */
	@SuppressWarnings("resource")
	public static String fileCopy_channel(HttpServletRequest request,String sourceFile,String targetFile) {
		String curProjectPath = request.getServletContext().getRealPath("/");//项目路径
		String sourcePath = curProjectPath+"files/"+sourceFile ;//项目下需要复制的文件路径
		String savePath = getSavePath(curProjectPath)+targetFile;//项目复制的目标路径
		FileChannel input = null;
		FileChannel output = null;
		try {
			input = new FileInputStream(sourcePath).getChannel();
			output = new FileOutputStream(savePath).getChannel();
			output.transferFrom(input, 0, input.size());
			return savePath;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 获取保存文件的路径
	 * @author xiexing
	 * @param curProjectPath
	 * @return
	 */
	public static String getSavePath(String curProjectPath) {
		String saveDirectoryPath="";
		String path[] = curProjectPath.split("\\\\");// 把路径按\分割
		for (int i = 0; i < path.length; i++) {// 在webapp之外创建文件夹
			if (path[i].equals("webapps")) {
				break;
			}
			saveDirectoryPath = saveDirectoryPath + path[i] + "\\";
		}
		saveDirectoryPath = saveDirectoryPath + "upload" + "\\"; // 创建保存路径
		File file = new File(saveDirectoryPath);
		//判断文件夹是否存在，不存在则创建文件夹
		judeDirExists(file);
		return saveDirectoryPath;
	}
	
	// 判断文件夹是否存在
    public static void judeDirExists(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                //System.out.println("dir exists");
            } else {
                //System.out.println("the same name file exists, can not create dir");
            }
        } else {
            //System.out.println("dir not exists, create it ...");
            file.mkdir();
        }

    }
}
