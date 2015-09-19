package fix.parser.service;

import fix.parser.document.Tag;
import fix.parser.factory.DictionaryFactory;
import fix.parser.rules.FIXValidations;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by arisharbab on 13/9/15.
 */
public class FIXParserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Inject
    DictionaryFactory dictionaryFactory;

    public String parseMsg(String msg,String specialChar){
        JSONObject responseDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String [] tagValuePairs = msg.split(specialChar);
        logger.info("Parsing FIX message - {}",msg);
        for(String tag:tagValuePairs){
            if(!FIXValidations.isValid(tag)) {
                logger.error("FIX Tag is not present in proper syntax:{}",tag);
                return "Invalid Tag definition";
            }
            String [] tagValuePair = tag.split("=");
            int tagId = Integer.parseInt(tagValuePair[0]);
            String tagValue = tagValuePair[1];
            jsonArray.add(getJSONObject(dictionaryFactory.getTag(tagId),tagId,tagValue));

        }
        responseDetailsJson.put("TAGS", jsonArray);
        logger.info("Parsed JSON FIX message - {}",responseDetailsJson.toJSONString());
        return responseDetailsJson.toJSONString();

    }

    public JSONObject getJSONObject(Tag tag,int tagID,String tagValue){
        JSONObject formDetailsJson = new JSONObject();
        if(tag!=null){
            formDetailsJson.put("FIX_TAG",tag.getId());
            formDetailsJson.put("TAG_NAME",tag.getFieldName());
            formDetailsJson.put("TAG_VALUE",tagValue);
            formDetailsJson.put("DESCRIPTION", tag.getDescription());
        }else{
            formDetailsJson.put("FIX_TAG",tagID);
            formDetailsJson.put("TAG_NAME","NA");
            formDetailsJson.put("TAG_VALUE",tagValue);
            formDetailsJson.put("DESCRIPTION","Tag not found. Please update dictionary");
            logger.error("FIX TAG-{} is not found in the dictionary",tagID);
        }
        return formDetailsJson;

    }



}
