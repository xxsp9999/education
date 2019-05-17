package pers.xx.edu.utils;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

public class Java2Word {
	public static String readWord(String path) {
		String buffer = "";
		try {
			if (path.endsWith(".doc")) {
				InputStream is = new FileInputStream(new File(path));
				WordExtractor ex = new WordExtractor(is);
				buffer = ex.getText();
				ex.close();
			} else if (path.endsWith("docx")) {
				OPCPackage opcPackage = POIXMLDocument.openPackage(path);
				POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
				buffer = extractor.getText();
				extractor.close();
			} else {
				System.out.println("此文件不是word文件！");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer;
	}

	public static String getChinese(String url) {
		Pattern pattern = Pattern.compile("[^\u4E00-\u9FA5]");
		Matcher matcher = pattern.matcher(url);
        String result = matcher.replaceAll("");
		return result;
	}

	public static void main(String[] args) throws IOException {
		String content = readWord("D:\\毕业设计\\毕业设计完成进度（重要）\\相关数据\\大学常见课程.doc");
		String[] strs = content.split("\\n");
		for (String url : strs) {
			String str = getChinese(url);
			if (str!=null && str.length() > 0) {
				str = str.substring(str.length() - 1, str.length());
				Integer index = url.indexOf(str);
				System.out.print(url.substring(0, index + 1) + "--");
				System.out.print(url.substring(index + 1));
				System.out.println();
			}
		}
	}

}
