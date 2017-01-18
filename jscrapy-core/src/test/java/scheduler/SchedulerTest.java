package scheduler;

import org.jscrapy.core.exception.MySpiderException;
import org.jscrapy.core.exception.MySpiderFetalException;
import org.jscrapy.core.request.HttpRequestMethod;
import org.jscrapy.core.request.Request;
import org.jscrapy.core.request.impl.HttpRequest;
import org.jscrapy.core.scheduler.Scheduler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by cxu on 2015/12/9.
 */
public class SchedulerTest {

    @DataProvider(name = "dp")
    public Object[][] dataProvider() throws MySpiderFetalException, FileNotFoundException {
        return new Scheduler[][]{

        };
    }

    @Test(dataProvider = "dp")
    public void test(Scheduler scheduler) throws MySpiderException {

        List<Request> request = new ArrayList<Request>();
        for (int i = 0; i < 100; i++) {
            request.add(new HttpRequest("http://x.com", HttpRequestMethod.DELETE, null));
        }
        scheduler.push(request);

        request = scheduler.poll(100);
        assertNotNull(request);
        assertEquals(100, request.size());
    }

}
