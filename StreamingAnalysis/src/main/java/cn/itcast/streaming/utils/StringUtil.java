package cn.itcast.streaming.utils;

public class StringUtil {
    /**
     * 字符串倒序：有递归法（不推荐）、数组倒序拼接、冒泡对调、使用StringBuffer的reverse方法等。
     * 冒泡对调（推荐）
     *
     * @param orig
     * @return
     */
    public static String reverse(String orig) {
        char[] s = orig.toCharArray();
        int n = s.length - 1;
        int halflength = n / 2;
        for (int i = 0; i <= halflength; i++) {
            char tmp = s[i];
            s[i] = s[n-i];
            s[n-i] = tmp;
        }
        return new String(s);
    }
}
