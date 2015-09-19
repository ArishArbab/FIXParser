package fix.parser.handlers;

/**
 * Created by arisharbab on 13/9/15.
 */
import fix.parser.service.FIXParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FIXParserService fixParserService;

    @RequestMapping(value = "/parse/{msg:.+}", method = RequestMethod.POST)
    public String getIndex(@PathVariable("msg") String msg) {
        logger.info("RAW FIX MESSAGE - {}",msg);
        String parsedmsg = fixParserService.parseMsg(msg,"<SOW>");
        return parsedmsg;
    }
}
