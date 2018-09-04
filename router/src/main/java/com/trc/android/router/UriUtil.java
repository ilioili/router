package com.trc.android.router;


import android.support.annotation.Nullable;

/**
 * 注意：忽略URL中的#号处理
 * <br>示例：url=http://baidu.com/search/#/1233?name=hunter
 * <br>则：getParam(url,name)=hunter
 * <br>示例：url=http://baidu.com/search?name=hunter#/1233
 * <br>则：getParam(url,name)=hunter
 */
public class UriUtil {
    /**
     * @return 如果没有对应但key或包含"key"
     */
    @Nullable
    public static String getParam(String url, String key) {
        int scanStart = url.indexOf('?');
        int keyLength = key.length();
        while (true) {
            int keyStart = url.indexOf(key, scanStart);
            if (keyStart == -1) {
                return "";
            } else {
                char keyPrefixChar = url.charAt(keyStart - 1);
                char keySuffixChar = url.charAt(keyStart + keyLength);
                if (keySuffixChar == '=' && (keyPrefixChar == '&' || keyPrefixChar == '?')) {
                    int valueStart = keyStart + keyLength + 1;
                    int valueEnd = url.indexOf('&', valueStart);
                    if (valueEnd == -1) valueEnd = url.indexOf('#', valueStart);
                    if (valueEnd == -1) valueEnd = url.length();
                    return url.substring(valueStart, valueEnd);
                } else {
                    scanStart = keyStart + 1;
                }
            }
            if (scanStart + key.length() > url.length()) {
                return "";
            }
        }
    }

    @Nullable
    /**
     * www.baidu.com
     * @return getScheme(www.baidu.com)=null
     */
    public static String getScheme(String url) {
        int endIndex = url.indexOf("://");
        if (endIndex == -1) {
            endIndex = url.indexOf(':');
            if (endIndex == -1)
                endIndex = 0;
        }
        return url.substring(0, endIndex);

    }

    /**
     * 包含端口：https://www.baidu.com:8888/search?key=English 返回 www.baidu.com:8888
     *
     * @param url
     * @return host+:+port
     */
    public static String getAuthority(String url) {
        int hostStart = url.indexOf("://") + 3;
        if (hostStart == 2) {// -1 + 3 = 2
            hostStart = 0;
        }
        int hostEnd = url.indexOf('/', hostStart);
        if (hostEnd == -1) hostEnd = url.indexOf('?', hostStart);
        if (hostEnd == -1) {
            return url.substring(hostStart);
        } else {
            if (hostEnd == hostStart) {
                return "";
            } else {
                return url.substring(hostStart, hostEnd);
            }
        }
    }


    public static String appendParam(String url, String key, Object value) {
        if (null == value) return url;
        else if ("".equals(value.toString())) return url;
        else {
            StringBuilder stringBuilder = new StringBuilder(url);
            char appendChar = url.contains("?") ? '&' : '?';
            return stringBuilder.append(appendChar).append(key).append('=').append(value).toString();
        }
    }
}
