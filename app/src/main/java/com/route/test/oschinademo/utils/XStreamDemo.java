package com.route.test.oschinademo.utils;

import com.thoughtworks.xstream.XStream;

/**
 * Created by 田晓龙 on 2017/4/12.
 */

public class XStreamDemo<T> {
    private XStream xStream;

    private XStreamDemo() {
        xStream= new XStream();
    }

    private static XStreamDemo xStreamDemo;

    public static synchronized XStreamDemo getInstance() {
        if (xStreamDemo == null) {
            xStreamDemo = new XStreamDemo();
        }
        return xStreamDemo;
    }

    public XStreamDemo alies(String type, Class classes) {
        xStream.alias(type, classes);
        return xStreamDemo;
    }

    public T build(String str, Class classes) {
        T t = null;
        try {
            t = (T) classes.newInstance();
            t = (T) xStream.fromXML(str);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
