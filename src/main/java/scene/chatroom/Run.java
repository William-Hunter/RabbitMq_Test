package chatroom;

import util.ConfigProperties;

/**
 * Created by william on 2018/9/5.
 */
public class Run {
    static public void main(String[] args){

        System.out.println(ConfigProperties.getConfigByName("host"));

    }
}
