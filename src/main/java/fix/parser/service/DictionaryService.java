package fix.parser.service;

import fix.parser.config.Config;
import fix.parser.document.Tag;
import fix.parser.document.Tags;
import fix.parser.factory.DictionaryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by arisharbab on 4/9/15.
 */
public class DictionaryService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    Config config;

    @Inject
    DictionaryFactory dictionaryFactory;

    @Inject
    SeleniumService seleniumService;

    public void init() {
        logger.info("Inititalizing Dictionary Factory..");
        if (config.isDictionaryPresent()) {
            logger.info("Dictionary found at {} ", config.getPath());
            if (!downloadFIXDictionary()) {
                logger.error("Cannot create Dictionary factory from {} ", config.getPath());
            } else {
                logger.info("Dictionary Factory initialised with {} tags", dictionaryFactory.getDictionarySize());
            }

        } else {
            seleniumService.initilizeDictionary();
            if (!uploadFIXDictionary()) {
                logger.error("Cannot upload dictionary.");
            } else {
                logger.info("Dictionary uploaded to file {}", config.getPath());
            }
        }
    }


    public boolean uploadFIXDictionary() {
        try {
            logger.info("Please wait uploading Dictionary...");
            Iterator it = dictionaryFactory.getDictionary().entrySet().iterator();
            Tags tags = new Tags();
            tags.setTags(new ArrayList<Tag>());
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                tags.getTags().add((Tag) pair.getValue());
                it.remove();
            }
            JAXBService.convertToXMLFile(tags, config.getFile());
            config.setIsDictionaryPresent(config.getFile().exists());
        } catch (Exception e) {
            return false;
        }
        return new File(config.getPath()).exists();
    }

    public boolean downloadFIXDictionary() {
        try {
            Tags tags = JAXBService.convertXMLFileToTags(config.getFile());
            for (Tag tag : tags.getTags()) {
                dictionaryFactory.addTag(tag);
            }
            return !dictionaryFactory.getDictionary().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }




}
