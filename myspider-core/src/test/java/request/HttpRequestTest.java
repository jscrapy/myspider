package request;

import com.oxf1.spider.request.HttpRequestMethod;
import com.oxf1.spider.request.Request;
import com.oxf1.spider.request.impl.HttpRequest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * Created by cxu on 2015/5/21.
 */
public class HttpRequestTest {
    @DataProvider(name = "queue_request_provider")
    public Object[][] rangeData() {

        return new Object[][] {
                {"{\"url\":\"http://oxf1.com\",\"http_method\":\"HTTP_POST\",\"post_parms\":{\"site\":\"oxf1\", \"person\":\"cxu\"}}", "http://oxf1.com", HttpRequestMethod.HTTP_POST },
                {"{\"url\":\"http://oxf1.com/test.html\",\"http_method\":\"HTTP_GET\"}", "http://oxf1.com/test.html", HttpRequestMethod.HTTP_GET },
                {"{}", null, null },
        };
    }

    @Test(dataProvider = "queue_request_provider")
    public void testBuild(String jsonStr, String url , HttpRequestMethod method) throws IOException {
        Request req = Request.build(jsonStr, HttpRequest.class);
        assert req!=null : "jackson parse result can not empty!";

        assertEquals(req.getUrl(), url);
        assertEquals(req.getHttpMethod(), method);
        if(req.getParameters()!=null) {
            Map<String, String> map = req.getParameters();
            assertEquals("oxf1", map.get("site"));
            assertEquals("cxu", map.get("person"));
        }
    }




}
