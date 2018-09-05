package telephone;

import java.io.IOException;

/**
 * Created by william on 2018/6/25.
 */
public class Guest {
    public static void main(String[] argv) throws IOException {
        SelfTalker mikey=new SelfTalker("192.168.3.254","william","lone");
        mikey.listener("bigroom");
        mikey.monitor("bigroom");
    }

}
