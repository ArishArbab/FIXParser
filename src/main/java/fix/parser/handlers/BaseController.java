package fix.parser.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by arisharbab on 13/9/15.
 */
@Controller
public class BaseController {


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }



}
