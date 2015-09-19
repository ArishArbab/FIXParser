package fix.parser.service;

import fix.parser.config.Config;
import fix.parser.document.Tag;
import fix.parser.factory.DictionaryFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.HashMap;

/**
 * Created by arisharbab on 10/9/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class DictionaryServiceTest {

    @Mock
    DictionaryFactory dictionaryFactory;

    @Mock
    Config config;

    @InjectMocks
    DictionaryService dictionaryService;

    @Test
    public void uploadFIXDictionary_Test() throws Exception {
        Tag tag1 = new Tag();
        tag1.setId(1);
        tag1.setAddedBy("Arish");
        tag1.setDataType("INT");
        tag1.setDepr("depr");
        tag1.setDescription("Test");
        tag1.setEnumFormTags("Forms");
        tag1.setFieldName("Test1");
        tag1.setUnionType("Union");
        tag1.setXmlName("NAme");

        Tag tag2 = new Tag();
        tag2.setId(1);
        tag2.setAddedBy("Arish");
        tag2.setDataType("INT");
        tag2.setDepr("depr");
        tag2.setDescription("Test");
        tag2.setEnumFormTags("Forms");
        tag2.setFieldName("Test1");
        tag2.setUnionType("Union");
        tag2.setXmlName("NAme");

        HashMap<Integer, Tag> dictionary = new HashMap<Integer, Tag>();
        dictionary.put(1, tag1);
        dictionary.put(2, tag2);

        when(dictionaryFactory.getDictionary()).thenReturn(new HashMap<Integer, Tag>());
        when(config.getFile()).thenReturn(new File("tagsTest.xml"));
        when(config.getPath()).thenReturn("tagsTest.xml");

        Assert.assertEquals(true, dictionaryService.uploadFIXDictionary());

        when(dictionaryFactory.getDictionary()).thenReturn(dictionary);

        Assert.assertEquals(true, dictionaryService.uploadFIXDictionary());


    }

    @Test
    public void downloadFIXDictionary_Test() {
        final Tag tag1 = new Tag();
        tag1.setId(1);
        tag1.setAddedBy("Arish");
        tag1.setDataType("INT");
        tag1.setDepr("depr");
        tag1.setDescription("Test");
        tag1.setEnumFormTags("Forms");
        tag1.setFieldName("Test1");
        tag1.setUnionType("Union");
        tag1.setXmlName("NAme");

        final Tag tag2 = new Tag();
        tag2.setId(1);
        tag2.setAddedBy("Arish");
        tag2.setDataType("INT");
        tag2.setDepr("depr");
        tag2.setDescription("Test");
        tag2.setEnumFormTags("Forms");
        tag2.setFieldName("Test1");
        tag2.setUnionType("Union");
        tag2.setXmlName("NAme");

        final HashMap<Integer, Tag> dictionary = new HashMap<Integer, Tag>();

        when(config.getFile()).thenReturn(new File("tagsTest.xml"));

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                dictionary.put(tag1.getId(), tag1);
                return null;
            }
        }).doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                dictionary.put(tag2.getId(), tag2);
                return null;
            }
        }).when(dictionaryFactory).addTag((Tag) anyObject());

        when(dictionaryFactory.getDictionary()).thenReturn(dictionary);
        Assert.assertEquals(true, dictionaryService.downloadFIXDictionary());

        when(config.getFile()).thenReturn(new File("tagsTest1.xml"));
        Assert.assertEquals(false, dictionaryService.downloadFIXDictionary());

    }


}
