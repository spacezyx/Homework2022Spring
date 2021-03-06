package com.example.dotdot.utils.apputils;

import com.example.dotdot.utils.timeutils.TimeUtil;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


public class APPUtil {
    private final static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * @Description: <p>
     * 短8位UUID思想其实借鉴微博短域名的生成方式，但是其重复概率过高，而且每次生成4个，需要随即选取一个。
     * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，
     * 所以将UUID分成8组，每4个为一组，然后通过模62操作，结果作为索引取出字符，
     * 这样重复率大大降低。
     * 经测试，在生成一千万个数据也没有出现重复，完全满足大部分需求。
     * </p>
     */
    public static String getAPIKey() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    /**
     *  算法： sha1(appid+uuid) 生成AppSecret
     */
    public static String getAPISecret(String appId) {
        try {
            StringBuffer sb = new StringBuffer();
            String uuid = UUID.randomUUID().toString();
            sb.append(appId).append(uuid);

            String str = sb.toString();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        }  catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return appId;
    }

    /**
     *  两次md5加密
     */
    public static String checkAuth(String APISecret,String timestamp,String question,String sign) {
        if(!TimeUtil.cmpTimeForUrl(timestamp))
//        if(timestamp.equals(""))
            return "Timeout";
        else {
            System.out.println("apisecret : "+APISecret);
            String tmp = APISecret+question+timestamp;
            String m1d5 = DigestUtils.md5DigestAsHex(tmp.getBytes());
            String m2d5 = DigestUtils.md5DigestAsHex(m1d5.getBytes());
            String md5 = DigestUtils.md5DigestAsHex(m2d5.getBytes());
            System.out.println("md5 : "+md5);
            System.out.println("sign : "+sign);
            if(md5.equals(sign))
                return "Success";
            else
                return "Wrong sign";
        }
    }
}
