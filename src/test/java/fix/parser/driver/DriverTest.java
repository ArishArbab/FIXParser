package fix.parser.driver;

import fix.parser.document.Tag;
import fix.parser.document.Tags;
import fix.parser.service.JAXBService;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arisharbab on 4/9/15.
 */
public class DriverTest {


    @Test
    public void testDriver() throws Exception{
        Tags tags = new Tags();
        tags.setTags(new ArrayList<Tag>());

        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://www.fixtradingcommunity.org/FIXimate/FIXimate3.0/en/FIX.5.0SP2/fields_sorted_by_tagnum.html");

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

            tags.getTags().add(tag);
        }
        JAXBService.convertToXMLFile(tags,new File("FIXTags.xml"));
        driver.quit();
    }

    @Test
    public void test_app(){
        new ClassPathXmlApplicationContext("classpath:META-INF/applicationContext.xml");
    }
}
