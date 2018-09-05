package telephone;

import java.io.IOException;

/**
 * Created by william on 2018/6/25.
 */
public class Candy {

    public static void main(String[] argv) throws IOException {
        SelfTalker candy=new SelfTalker("192.168.3.254","candy","candy");
        candy.listener("bigroom");
        candy.monitor("bigroom");
    }

}
