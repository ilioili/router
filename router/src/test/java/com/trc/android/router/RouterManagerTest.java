package com.trc.android.router;

import org.junit.Test;

public class RouterManagerTest {

    @Test
    public void match() {
        assert 5 == RouterManager.match("abcdefg", new String[]{"abc", "abcd", "abcde"});
        assert 3 == RouterManager.match("abc", new String[]{"ab", "abcde", "abc"});
        assert 3 == RouterManager.match("abc", new String[]{"ab", "abc"});
        assert 3 == RouterManager.match("abc", new String[]{"abc"});
        assert 2 == RouterManager.match("abc", new String[]{"aaa", "ab"});
        assert 0 == RouterManager.match("abc", new String[]{});
        assert 0 == RouterManager.match("abc", new String[]{"aaa"});
    }
}