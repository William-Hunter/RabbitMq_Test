package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by william on 2018/9/5.
 */
public class ConfigProperties {
    static private Properties prop = new Properties();//属性集合对象

    static{
        FileInputStream fis = null;//属性文件流
        try {
            fis = new FileInputStream("F:\\workspace\\rabbit\\src\\main\\resources\\config.properties");
            prop.load(fis);//将属性文件流装载到Properties对象中
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public String getConfigByName(String key){
        return prop.getProperty(key);
    }




}
