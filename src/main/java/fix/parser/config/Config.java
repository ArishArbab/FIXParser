package fix.parser.config;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by arisharbab on 10/9/15.
 */
public class Config {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    boolean isDictionaryPresent;
    File file;
    String path;
    String fiximateURL;

    public Config(String path,String fiximateURL) throws Exception{
        setPath(path);
        setFiximateURL(fiximateURL);
        ClassPathResource resource = new ClassPathResource(path);
        setFile(resource.getFile());
        if(getFile().exists() && !getFile().isDirectory()) {
            setIsDictionaryPresent(true);
        }else{
            setIsDictionaryPresent(false);
        }
        logger.info("Dictionary Path {} and  Dictionary URL {}", path, fiximateURL);
        logger.info("Dictionary present {}", isDictionaryPresent());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isDictionaryPresent() {
        return isDictionaryPresent;
    }

    public void setIsDictionaryPresent(boolean isDictionaryPresent) {
        this.isDictionaryPresent = isDictionaryPresent;
    }

    public String getFiximateURL() {
        return fiximateURL;
    }

    public void setFiximateURL(String fiximateURL) {
        this.fiximateURL = fiximateURL;
    }
}
