package pers.xx.edu.utils;


/**
 * 
 * @description 进制转换 
 * @author 白贵才
 * @create 2017-11-10下午3:20:15
 *
 */
public class HexStringUtils {

	
	/**
     * 将 byte 数组转化为十六进制字符串
     *
     * @param bytes byte[] 数组
     * @param begin 起始位置
     * @param end 结束位置
     * @return byte 数组的十六进制字符串表示
     */
	public static String bytesToHex(byte[] bytes, int begin, int end) {
        StringBuilder hexBuilder = new StringBuilder(2 * (end - begin));
        for (int i = begin; i < end; i++) {
            hexBuilder.append(Character.forDigit((bytes[i] & 0xF0) >> 4, 16)); // 转化高四位
            hexBuilder.append(Character.forDigit((bytes[i] & 0x0F), 16)); // 转化低四位
            hexBuilder.append(' '); // 加一个空格将每个字节分隔开
        }
        return hexBuilder.toString().toUpperCase();
    }
    
	
    
    /**
     * @description 把数据格式化成标准的十六进制格式
     * @param str
     * @return
     */
    public static String formatStr(String str){
    	//去掉空格
    	str = str.replace(" ", "");
    	if(str == null || str.length() == 0 || str.length()%2 != 0){
    		return null;
    	}
    	StringBuffer formatStr = new StringBuffer();
    	for (int i = 0;i < str.length();) {
    		//formatStr.append((byte) Integer.parseInt((str.substring(i, i+1)+str.substring(i+1, i+2)), 16)+" ");
    		formatStr.append((str.substring(i, i+1)+str.substring(i+1, i+2))+" ");
			i = i+2;
		}
    	
    	return formatStr.substring(0, formatStr.length()-1);
    }
    
    
    /**
     * @description check检验数据是否正确
     * @param formatStr
     * @return
     */
    public static String checkStr(String formatStr){
    	byte sum = 0x00;

 	    String[] formatStrs = formatStr.split(" ");
        for (int i = 0; i < formatStrs.length;i++ ){
             sum += (byte) Integer.parseInt(formatStrs[i],16);
        }
        StringBuffer checkResult = new StringBuffer();
        checkResult.append(Character.forDigit((((~sum) + 1) & 0xF0) >> 4, 16)).append(Character.forDigit((((~sum) + 1) & 0x0F), 16));
        
        return  checkResult.toString().toUpperCase();
    }
    
    
    /**
     * 将 byte转化为十六进制
     * @return
     */
    public static String byteToHex(byte bt){
    	 String result = Integer.toHexString(bt & 0xFF).toUpperCase();
    	 if (result.length() == 1) {  
             result = '0' + result;  
         }  
    	
    	return result;
    }
    
    
    
    public static void main(String[] args) {
    	byte[] a = new byte[10];
        a[0]= -127;
        System.out.println(a[0]);
        int c = a[0] & 0xff;
        int d = a[0] & 0x0f;
        int e = a[0] & 0xf0;
        int f = a[0] >>> 4;
        int g = a[0];
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
        System.out.println(g);
        
        a[1] = 1;
        
        System.out.println(a[1] > 1);
        System.out.println(a[1] > 2);
        System.out.println(a[1] << 1);
        System.out.println(a[1] << 2);
        
        System.out.println(Integer.toBinaryString(222));
	}
}
