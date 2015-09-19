package fix.parser.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by arisharbab on 4/9/15.
 */
@XmlRootElement(name = "tag")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tag {
    int id;
    String fieldName;
    String xmlName;
    String dataType;
    String unionType;
    String description;
    String addedBy;
    String depr;
    String enumFormTags;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUnionType() {
        return unionType;
    }

    public void setUnionType(String unionType) {
        this.unionType = unionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getDepr() {
        return depr;
    }

    public void setDepr(String depr) {
        this.depr = depr;
    }

    public String getEnumFormTags() {
        return enumFormTags;
    }

    public void setEnumFormTags(String enumFormTags) {
        this.enumFormTags = enumFormTags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
