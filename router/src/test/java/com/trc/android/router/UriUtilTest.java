package com.trc.android.router;

import com.trc.android.router.UriUtil;

import org.junit.Test;

public class UriUtilTest {

    @Test
    public void testParams() {
        assert UriUtil.getParam("http://www.baidu.com?key1=value", "key").equals("");
        assert UriUtil.getParam("http://www.baidu.com?key1key=value", "key").equals("");
        assert UriUtil.getParam("http://www.baidu.com?key=value", "key").equals("value");
        assert UriUtil.getParam("http://www.baidu.com?key=", "key").equals("");
        assert UriUtil.getParam("http://www.baidu.com?key=value&", "key").equals("value");
        assert UriUtil.getParam("http://www.baidu.com?key=value&name=hunter", "name").equals("hunter");
        assert UriUtil.getParam("http://www.baidu.com?aaaa=xx", "aa").equals("");
        assert UriUtil.getParam("http://www.baidu.com?aaaa=xx&aa=111", "aa").equals("111");
        assert UriUtil.getParam("http://www.baidu.com?key=name&key=hunter", "key").equals("name");
        assert UriUtil.getParam("http://www.baidu.com?key=name#fuck", "key").equals("name");
        assert UriUtil.getParam("http://www.baidu.com?key=name&fuck#kkk", "fuck").equals("");
        assert UriUtil.getParam("http://www.baidu.com?key=name&fuck=#kkk", "fuck").equals("");
        assert UriUtil.getParam("http://www.baidu.com#fragment?key=name&fuck=122", "fuck").equals("122");
    }

    @Test
    public void testScheme() {
        assert UriUtil.getScheme("http://www.baidu.com?key1=value").equals("http");
        assert UriUtil.getScheme("trc://www.baidu.com?key1=value").equals("trc");
        assert UriUtil.getScheme("aaa").equals("");
        assert UriUtil.getScheme("tel:+15669989021").equals("tel");
    }

    @Test
    public void testHost() {
        assert UriUtil.getAuthority("http://www.baidu.com?key1=value").equals("www.baidu.com");
        assert UriUtil.getAuthority("trc://www.baidu.com/search?key1=value").equals("www.baidu.com");
        assert UriUtil.getAuthority("www.baidu.com?key1=value").equals("www.baidu.com");
        assert UriUtil.getAuthority("www.baidu.com:80?key1=value").equals("www.baidu.com:80");
        assert UriUtil.getAuthority("http://").equals("");
    }

}
