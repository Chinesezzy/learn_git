package com.week01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Class hello = new TestClassLoader().findClass("Hello");
            Object helloInstance = hello.newInstance();
            Method method = helloInstance.getClass().getMethod("hello");
            method.invoke(helloInstance);
        } catch (ClassNotFoundException | InstantiationException | NoSuchMethodException |
                IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = readFile("src/Hello.xlass");
        return defineClass(name, bytes, 0, bytes.length);
    }


    /**
     * 读取文件字节码，并处理 x=255-x
     * */
    private byte[] readFile(String path) {
        File file = new File(path);
        Long filelength = file.length();
        byte[] bytes = new byte[filelength.intValue()];
        try {
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(bytes);
            for (int i = 0; i < bytes.length; i++) bytes[i] = (byte) (255 - bytes[i]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
