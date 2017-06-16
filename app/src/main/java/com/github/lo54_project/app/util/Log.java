package com.github.lo54_project.app.util;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Log
{

    private static Logger getLogger(Class clazz)
    {
        Logger logger = Logger.getLogger(clazz);
        return logger;
    }


    public static void error(Class clazz, String content)
    {
        getLogger(clazz).log(Level.ERROR,content);

    }

    public static void warning(Class clazz, String content)
    {
        getLogger(clazz).log(Level.WARN,content);
    }


    public static void info(Class clazz, String content)
    {
        getLogger(clazz).log(Level.INFO,content);
    }


}
