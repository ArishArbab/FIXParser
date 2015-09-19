package fix.parser.service;

import fix.parser.config.Config;
import fix.parser.document.Tag;
import fix.parser.factory.DictionaryFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by arisharbab on 11/9/15.
 */
public class SeleniumService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    DictionaryFactory dictionaryFactory;

    @Inject
    Config config;

    public void initilizeDictionary(){

        if(!config.isDictionaryPresent()){
            logger.info("Please wait downloading FIXDictionary...");
            WebDriver driver = new HtmlUnitDriver();
            driver.get(config.getFiximateURL());
            WebElement table = driver.findElement(By.tagName("table"));
            List<WebElement> trs = table.findElements(By.tagName("tr"));
            for(int i=1; i<trs.size(); i++) {
                WebElement tr = trs.get(i);
                List<WebElement> tds = tr.findElements(By.tagName("td"));
                Tag tag = new Tag();
                tag.setId(Integer.parseInt(tds.get(0).getText()));
                tag.setFieldName(tds.get(1).getText());
                tag.setXmlName(tds.get(2).getText());
                tag.setDataType(tds.get(3).getText());
                tag.setUnionType(tds.get(4).getText());
                tag.setDescription(tds.get(5).getText());
                tag.setAddedBy(tds.get(6).getText());
                tag.setDepr(tds.get(7).getText());
                tag.setEnumFormTags(tds.get(8).getText());
                dictionaryFactory.addTag(tag);
            }
            logger.info("DictionaryFactory initialised with {} tags",dictionaryFactory.getDictionarySize());
        }

    }
}
