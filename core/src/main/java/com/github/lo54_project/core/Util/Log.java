package com.github.lo54jeeminiprojet.miniprojet.Util;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * Created by qsm on 05/05/17.
 */
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
