package scene.telephone;

import util.ConfigProperties;
import worker.SelfTalker;

import java.io.IOException;

/**
 * Created by william on 2018/6/25.
 */
public class Candy {

    public static void main(String[] argv) throws IOException {
        SelfTalker candy=new SelfTalker(ConfigProperties.getConfigByName("host"),"candy","candy");
        candy.listener("mikey2candy");
        candy.monitor("candy2mikey");
    }

}
