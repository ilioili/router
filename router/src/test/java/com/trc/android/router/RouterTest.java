package com.trc.android.router;

import android.content.Context;

import com.trc.android.router.Router;

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
    }
}
