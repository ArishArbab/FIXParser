package fix.parser.repository;

import fix.parser.document.Tag;
import fix.parser.factory.DictionaryFactory;
import fix.parser.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by arisharbab on 12/9/15.
 */
public class DictionaryRepository {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    DictionaryFactory dictionaryFactory;

    @Inject
    DictionaryService dictionaryService;

    public void update(int id,Tag newTag){
        logger.info("Updating tag{}-->{}",id,newTag);
        dictionaryFactory.updateTag(id,newTag);
    }

    public void remove(Tag tag){
        logger.info("Removing tag{}",tag.getId());
        dictionaryFactory.deleteTag(tag);
    }

    public void add(Tag tag){
        logger.info("Adding tag{}",tag.getId());
        dictionaryFactory.addTag(tag);
    }

    public void save(){
        dictionaryService.uploadFIXDictionary();
        logger.info("Saving tags to dictionary file..");
    }
}
