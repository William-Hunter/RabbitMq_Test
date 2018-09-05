package telephone;

import java.io.IOException;

/**
 * Created by william on 2018/6/25.
 */
public class Mikey {
    public static void main(String[] argv) throws IOException {
        SelfTalker mikey=new SelfTalker("192.168.3.254","mikey","mikey");
        mikey.listener("bigroom");
        mikey.monitor("bigroom");
    }


}
