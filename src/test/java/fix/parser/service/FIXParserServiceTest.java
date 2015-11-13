package fix.parser.service;

import fix.parser.document.Tag;
import fix.parser.factory.DictionaryFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;


/**
 * Created by arisharbab on 13/9/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class FIXParserServiceTest {

    @Mock
    DictionaryFactory dictionaryFactory;

    @InjectMocks
    FIXParserService fixParserService;

    @Test
    public void testParseMsg() throws Exception {
        Tag tag1 = new Tag();
        tag1.setId(8);
        tag1.setDescription("Message sequence number of first message in range to be resent");
        tag1.setFieldName("BeginString");

        Tag tag2 = new Tag();
        tag2.setId(9);
        tag2.setDescription("Message length, in bytes, forward to the CheckSum field. ALWAYS SECOND FIELD IN MESSAGE. (Always unencrypted)");
        tag2.setFieldName("BodyLength");

        Tag tag3 = new Tag();
        tag3.setId(35);
        tag3.setDescription("Defines message type ALWAYS THIRD FIELD IN MESSAGE. (Always unencrypted)\n" +
                "Note: A \"U\" as the first character in the MsgType field (i.e. U, U2, etc) indicates that the message format is privately defined between the sender and receiver.\n" +
                "*** Note the use of lower case letters ***");
        tag3.setFieldName("MsgType");


        String msg="8=FIX.4.2\u00019=261\u000135=D\u000145=E";
        String test = "8=FIX.4.2/u00019=175/u000135=D";


        when(dictionaryFactory.getTag(8)).thenReturn(tag1);
        when(dictionaryFactory.getTag(9)).thenReturn(tag2);
        when(dictionaryFactory.getTag(35)).thenReturn(tag3);
        when(dictionaryFactory.getTag(45)).thenReturn(null);
        String jsonResponse = fixParserService.parseMsg(msg,"\u0001");
        Assert.assertEquals(4, jsonResponse.split("}").length - 1);

        msg="8=FIX.4.2";
        jsonResponse = fixParserService.parseMsg(msg,"\u0001");
        Assert.assertEquals(1, jsonResponse.split("}").length - 1);

        msg="8";
        jsonResponse = fixParserService.parseMsg(msg,"\u0001");
        Assert.assertEquals("Invalid Tag definition", jsonResponse);

        msg="=FIX";
        jsonResponse = fixParserService.parseMsg(msg,"\u0001");
        Assert.assertEquals("Invalid Tag definition", jsonResponse);

        msg="=";
        jsonResponse = fixParserService.parseMsg(msg,"\u0001");
        Assert.assertEquals("Invalid Tag definition", jsonResponse);



    }
}