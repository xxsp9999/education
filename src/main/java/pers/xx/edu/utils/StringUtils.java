package pers.xx.edu.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 白贵才
 * @description 字符串工具类
 * @create 2017-3-23上午10:58:31
 */
@Component
public class StringUtils {

    private static Integer idNumber = 1;


    /**
     * @param msg
     * @return
     * @description MD5算法加密，Base64算法编码
     */
    public static String StringToMd5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 明文
            byte[] input = msg.getBytes();
            // 密文
            byte[] output = md.digest(input);
            // Base64编码
            String s = Base64.encodeBase64String(output);
            return s;
        } catch (Exception ex) {
            throw new RuntimeException("加密失败");
        }
    }

    /**
     * @param newPassword
     * @param oldPassword
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @description 判断数据库密码和用户输入密码是否一致
     */
    public static boolean checkPassword(String newPassword, String oldPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (StringToMd5(newPassword).equals(oldPassword)) {
            return true;
        } else
            return false;
    }

    /**
     * @param str
     * @return
     * @description 判断是否为空
     */
    public static boolean isNotEmpty(Object str) {
        boolean flag = true;
        if (str != null && !str.equals("")) {
            if (str.toString().length() > 0) {
                flag = true;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * @param param
     * @return
     * @description 判断输入的是电话还是非电话
     */
    public static boolean isPhoneNum(String param) {
        if (param.length() > 11) {
            return false;
        }
        char[] chars = param.toCharArray();
        int length = 0;
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                break;
            }
            length++;
        }
        if (length != chars.length) {
            return false;
        }
        return true;
    }


    /**
     * @param str
     * @return
     * @desctiption 是不是数字
     */
    public static boolean isNumeric(String str) {
        if ("".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 字符串转double类型
     *
     * @param str
     * @return
     */
    public static double idDouble(String str) {
        double d = 0;
        if (!"".equals(str)) {
            d = Double.parseDouble(str);
        }
        return d;
    }

    /**
     * @param str
     * @param delIndex
     * @return
     * @description 删除字符串（可变成数组）指定索引的位置的元素
     */
    public static String delByIndex(String str, String delIndex) {
        String[] strs = str.split(",");
        String returnStr = "";
        for (int i = 0; i < strs.length; i++) {
            if (delIndex == null) {
                return str;
            } else {
                if (!delIndex.contains(String.valueOf(i))) {
                    returnStr += strs[i] + ",";
                }
            }
        }
        if (returnStr.length() > 0) {
            returnStr = returnStr.substring(0, returnStr.length() - 1);
        }

        return returnStr;
    }

    /**
     * @return
     * @description 生成一个12位的UUID
     * @author 白贵才
     * @createDate 2017年10月19日15:24:52
     */
    public static String get12UUID() {
        String uuid = "";
        uuid = UUID.randomUUID().toString().substring(13).replace("-", "").toUpperCase();
        return uuid;
    }

    /**
     * @return
     * @description 生成一个12位的物品的UUID（由年月+其余UUID组成）
     * @author 白贵才
     * @createDate 2017年11月30日17:32:35
     */
    public static String getGoodsUUID() {
        String rfid = "";
        rfid = UUID.randomUUID().toString().replace("-", "").substring(0, 20).toUpperCase();
        System.out.println(rfid);
        String today = new SimpleDateFormat("yyyyMM").format(new Date());
        rfid = today + rfid.substring(6, 20);
        return rfid;
    }

    /**
     * @return
     * @description 生成一个12位的RFID
     * @author 白贵才
     * @createDate 2017年10月19日15:24:52
     */
    public static String get12RFID() {
        String rfid = "";
        rfid = UUID.randomUUID().toString().replace("-", "").substring(0, 24).toUpperCase();
        System.out.println(rfid);
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        rfid = today + rfid.substring(8, 24);
        return rfid;
    }

    /**
     * @return
     * @description 返回一个批次号（20130101）
     * @author 白贵才
     * @createDate 2017年10月19日15:56:40
     */
    public static String getBatch() {
        String batch = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return batch;
    }

    /**
     * @return
     * @description 格式化数字
     * @author 白贵才
     * @createDate 2017年11月3日14:21:12
     */
    public static String formatNum(float dou) {
        DecimalFormat d = new DecimalFormat("0.00");
        if (dou == 0.0f) {
            return "0";
        } else {
            return d.format(dou);
        }
    }

    /**
     * @param num    格式化成几位，不足加0
     * @param number 数字
     * @return
     */
    public static String formatNumWithZero(int num, int number) {
        return String.format("%0" + num + "d", number);
    }


    /**
     * @return
     * @description 生成主表编号
     */
    public static String createOrderNumber() {
        Date date = new Date();
        return new SimpleDateFormat("yyMMdd").format(date) + ((Long) (date.getTime() / 1000)).toString().substring(4, 10);
    }

    /**
     * @description 处理以逗号拼接的字符串id.
     * @param ids
     * @return
     */
    public static Integer[] getListInteger(String ids) {
        String[] id = ids.split(",");
        Integer[] list = new Integer[id.length];
        for (int i = 0; i < id.length; i++) {
            list[i] = Integer.parseInt(id[i]);
        }
        return list;
    }

    public static String getBillNumber() {
        //议案编号规则
        StringBuffer sb = new StringBuffer();
        String dateNumber = new SimpleDateFormat(("yyyyMMdd")).format(new Date());
        sb.append(dateNumber);
        sb.append("CK");//暂时写死，由公司信息提供数据

        if (idNumber < 10) {
            sb.append("000" + idNumber);
        } else if (idNumber < 100) {
            sb.append("00" + idNumber);
        } else if (idNumber < 1000) {
            sb.append("0" + idNumber);
        } else {
            sb.append(idNumber);
        }
        idNumber++;
        return sb.toString();
    }

    @Scheduled(cron = "0 0 00 * * ?")
    public void autoaddDay() {
        idNumber = 1;
    }

    public static String getTimeDifference(Date nowTime, Date lastTime) {

        long diff = nowTime.getTime() - lastTime.getTime();//这样得到的差值是微秒级别
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        if(days == 0l && hours == 0L){
            return minutes + "分";
        }
        if(days == 0l){
            return hours + "时" + minutes + "分";
        }else {
            return days + "天" + hours + "时" + minutes + "分";
        }

    }
}
