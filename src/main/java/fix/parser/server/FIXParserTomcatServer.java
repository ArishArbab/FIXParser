package fix.parser.server;

import org.apache.catalina.startup.Tomcat;
import java.awt.Desktop;

import java.io.File;
import java.net.URI;

/**
 * Created by arisharbab on 13/9/15.
 */
public class FIXParserTomcatServer {

    public static void main(String[] args) throws Exception {
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        // The port that we should run on can be set into an environment
        // variable
        // Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "9000";
        }
        tomcat.setPort(Integer.valueOf(webPort));

        tomcat.addWebapp("/fix-parser", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        Desktop.getDesktop().browse(new URI("http://localhost:9000/fix-parser/home"));
        tomcat.getServer().await();


    }


}
