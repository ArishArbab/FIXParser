package fix.parser.service;

import fix.parser.document.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by arisharbab on 10/9/15.
 */
public class JAXBService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static void convertToXMLFile(Tags tags,File file)throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(Tags.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(tags, file);

    }

    public static Tags convertXMLFileToTags(File file)throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(Tags.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Tags tags = (Tags) jaxbUnmarshaller.unmarshal( file);
        return tags;
    }
}
