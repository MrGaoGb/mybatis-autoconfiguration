package com.mrgao.xbqx.test.jvm;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.net.URL;
import java.util.Arrays;

public class TestClassLoader {

    public static void main(String[] args) {


//        String classLoaderName = "com.mrgao.xbqx.entity.Product";
//
//        try {
//            Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(classLoaderName);
//            System.out.println(aClass);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }


        System.out.println(String.class.getClassLoader());
        System.out.println(DESKeyFactory.class.getClassLoader());
        System.out.println("---------------------------------------");
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("应用类加载器:"+ appClassLoader);
        System.out.println("扩展类加载器:"+ extClassLoader);
        System.out.println("引用类Bootstrap加载器:"+ bootstrapClassLoader);
        System.out.println("====================================");
        // bootstrapClassLoader加载以下文件
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        Arrays.stream(urLs).forEach(System.out::println);

        System.out.println("====================================");
        // extClassLoader加载以下文件
        String property = System.getProperty("java.ext.dirs");
        System.out.println(property);

        System.out.println("====================================");
        // appClassLoader加载以下文件
        System.out.println(System.getProperty("java.class.path"));
    }
}
