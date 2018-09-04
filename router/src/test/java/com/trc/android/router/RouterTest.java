package com.trc.android.router;

import android.content.Context;

import org.junit.Test;

public class RouterTest {
    Context context;

    @Test
    public void testUriParse() {
        //测试设置了param 和 uri之后拼接的param是否
        assert Router.from(context)
                .setParam("key", "value")
                .setUri("http://www.baidu.com")
                .toUriStr()
                .equals("http://www.baidu.com?key=value");

        assert Router.from(context)
                .setUri("http://www.baidu.com")
                .setParam("key", "value")
                .toUriStr()
                .equals("http://www.baidu.com?key=value");

        assert Router.from(context)
                .setUri("http://www.baidu.com?")
                .setParam("key", "value")
                .toUriStr()
                .equals("http://www.baidu.com?key=value");

        assert Router.from(context)
                .setParam("key", "value")
                .setUri("http://www.baidu.com?")
                .toUriStr()
                .equals("http://www.baidu.com?key=value");

        assert Router.from(context)
                .setUri("http://www.baidu.com?key=value")
                .getParam("key")
                .equals("value");


        assert Router.from(context)
                .setParam("key", "value")
                .setUri("http://www.baidu.com?name=hunter")
                .toUriStr()
                .equals("http://www.baidu.com?name=hunter&key=value");

        assert Router.from(context)
                .setParam("key", "value")
                .setUri("http://www.baidu.com?name=hunter&")
                .toUriStr()
                .equals("http://www.baidu.com?name=hunter&key=value");

        assert Router.from(context)
                .setParam("key", "value")
                .setUri("http://www.baidu.com/path/#/fragment")
                .toUriStr()
                .equals("http://www.baidu.com/path/#/fragment?key=value");

        assert Router.from(context)
                .setUri("http://www.baidu.com/path/#/fragment?key=value")
                .getParam("key")
                .equals("value");

        assert Router.from(context)
                .setParam("name", "hunter")
                .setUri("http://www.baidu.com")
                .setParam("key", "value")
                .toUriStr()
                .equals("http://www.baidu.com?name=hunter&key=value");

        assert Router.from(context)
                .setParam("name", "hunter")
                .setUri("http://www.baidu.com/#")
                .setParam("key", "value")
                .toUriStr()
                .equals("http://www.baidu.com/#?name=hunter&key=value");

        assert Router.from(context)
                .setParam("name", "hunter")
                .setParam("key", "value")
                .setUri("http://www.baidu.com/")
                .toUriStr()
                .equals("http://www.baidu.com/?name=hunter&key=value");

        assert Router.from(context)
                .setParam("name", "hunter")
                .setParam("key", "value")
                .setUri("http://www.baidu.com/?")
                .toUriStr()
                .equals("http://www.baidu.com/?name=hunter&key=value");

        assert Router.from(context)
                .setParam("name", "hunter")
                .setParam("key", "value")
                .setUri("http://www.baidu.com/?a=b")
                .toUriStr()
                .equals("http://www.baidu.com/?a=b&name=hunter&key=value");

        assert Router.from(context)
                .setParam("name", "hunter")
                .setParam("key", "value")
                .setUri("http://www.baidu.com/?a=b&")
                .toUriStr()
                .equals("http://www.baidu.com/?a=b&name=hunter&key=value");

        assert Router.from(context)
                .setParam("name", "hunter")
                .setUri("http://www.baidu.com/?a=b&")
                .setParam("key", "value")
                .toUriStr()
                .equals("http://www.baidu.com/?a=b&name=hunter&key=value");

        assert Router.from(context)
                .setUri("http://www.baidu.com/?a=b&")
                .setParam("name", "hunter")
                .setParam("key", "value")
                .toUriStr()
                .equals("http://www.baidu.com/?a=b&name=hunter&key=value");

        assert Router.from(context)
                    .setUri("http://www.baidu.com")
                    .setParam("name",null)
                    .toUriStr()
                    .equals("http://www.baidu.com");

        assert Router.from(context)
                .setUri("http://www.baidu.com")
                .setParam("name","")
                .setParam("sex", 1)
                .toUriStr()
                .equals("http://www.baidu.com?name=&sex=1");

        assert Router.from(context)
                .setUri("www.baidu.com")
                .setParam("name","")
                .setParam("sex", 1)
                .toUriStr()
                .equals("www.baidu.com?name=&sex=1");

        Router router0 = Router.from(context).setUri("http://www.baidu.com?name=&sex=1");
        assert router0.getParam("name").equals("");
        assert router0.getParam("sex").equals("1");

        Router router00 = Router.from(context).setUri("a?name=&sex=1");
        assert router00.getParam("name").equals("");
        assert router00.getParam("sex").equals("1");

        assert Router.from(context)
                .setUri("http://www.baidu.com")
                .setParam("name", "韩拓")
                .setParam("sex", 1)
                .setParam("link", "https://github.com/ilioili")
                .toUriStr().equalsIgnoreCase("http://www.baidu.com?name=%e9%9f%a9%e6%8b%93&sex=1&link=https%3a%2f%2fgithub.com%2filioili");

        Router router1 = Router.from(context).setUri("http://www.baidu.com?name=%e9%9f%a9%e6%8b%93&link=https%3a%2f%2fgithub.com%2filioili");
        assert router1.getParam("name").equals("韩拓");
        assert router1.getParam("link").equals("https://github.com/ilioili");

        //严谨的情况下，不应该出现此种情况(参数未经URL编码),但是不编码不应影响解码
        Router router2 = Router.from(context).setUri("http://www.baidu.com?name=韩拓&link=https://github.com/ilioili");
        assert router2.getParam("name").equals("韩拓");
        assert router1.getParam("link").equals("https://github.com/ilioili");

    }
}
