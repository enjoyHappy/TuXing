package organization.yhwapp.com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
    /**
     * 判断是否是英文字母
     */
    public static boolean isEnglish(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(str).matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 电子邮件
     */
    public static boolean checkEmail(String email) {
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.find();
    }

    /**
     * 验证密码 6-20位数字或字母
     */
    public static boolean checkPwd(String pwd) {
        String check = "^[a-zA-Z0-9]{6,20}$";
        return matcher(check, pwd);
    }

    /**
     * 验证中文昵称且不包含空格的字符
     */
    public static boolean checkName(String name) {
        String check = "[\u4e00-\u9fa5]";
        Pattern p = Pattern.compile(check);
        Matcher m = p.matcher(name);
        int count = 0;
        while (m.find()) {
            count++;
        }
        return name.length() == count ? true : false;
    }

    /**
     * 手机号验证
     */
    public static boolean isMobile(String str) {
        String check = "^[1][3,4,5,8][0-9]{9}$";// 验证手机号
        return matcher(check, str);
    }

    /**
     * 电话号码验证
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    private static boolean matcher(String reg, String string) {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }

    // 截取数字
    public static int getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return Integer.valueOf(matcher.group(0));
        }
        return 0;
    }

    // 限制输入字数
    public static boolean setNumbers(String content, int index) {
        if (content.length() <= index) {
            return true;
        }
        return false;
    }

}
