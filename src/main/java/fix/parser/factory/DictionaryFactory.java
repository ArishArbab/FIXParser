package fix.parser.factory;

import fix.parser.document.Tag;

import java.util.HashMap;

/**
 * Created by arisharbab on 4/9/15.
 */
public class DictionaryFactory {
    HashMap<Integer,Tag> dictionary = new HashMap<Integer,Tag>();


    public Tag getTag(int id){
        return dictionary.get(id);
    }
    public void addTag(Tag tag){
        dictionary.put(tag.getId(),tag);
    }

    public void deleteTag(Tag tag){
        dictionary.remove(tag.getId());
    }

    public void updateTag(int id,Tag newTag){
        dictionary.put(id,newTag);
    }

    public int getDictionarySize(){
        return dictionary.size();
    }

    public HashMap<Integer, Tag> getDictionary() {
        return dictionary;
    }

    public void setDictionary(HashMap<Integer, Tag> dictionary) {
        this.dictionary = dictionary;
    }


}
