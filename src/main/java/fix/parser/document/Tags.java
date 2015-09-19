package fix.parser.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by arisharbab on 10/9/15.
 */
@XmlRootElement(name = "tags")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tags {

    @XmlElement(name = "tag")
    private List<Tag> tags = null;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
