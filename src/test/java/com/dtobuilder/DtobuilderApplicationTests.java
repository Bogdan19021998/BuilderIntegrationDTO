package com.dtobuilder;

import com.dtobuilder.sender.Request;
import com.dtobuilder.sender.RequestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DtobuilderApplicationTests {


    @Test
    public void testKlaviyo() throws Exception {

        String resultAllLists = new Request()
                .baseURL("https://a.klaviyo.com/api/v2/lists")
                .GET()
                .authorizationEmpty()
                .finishHeader()
                .paramsContentTypeApplicationJson()
                .lastParam("api_key", "pk_c573b131433429f49daef8ea6380147e97")
                .skipBody()
                .send();

        System.out.println();

        //list_ID JA7vcH //  https://a.klaviyo.com/api/v2/list/{LIST_ID}

        String resultInfoAboutList = new Request()
                .baseURL("https://a.klaviyo.com/api/v2/list/JA7vcH")
                .GET()
                .authorizationEmpty()
                .finishHeader()
                .paramsContentTypeApplicationJson()
                .lastParam("api_key", "pk_c573b131433429f49daef8ea6380147e97")
                .skipBody()
                .send();

        System.out.println();

        Map<String, String> map = new HashMap<>();
        map.put("list_name", "test_list222");

        String resultPost = new Request()
                .baseURL("https://a.klaviyo.com/api/v2/lists")
                .POST()
                .authorizationEmpty()
                .finishHeader()
                .paramsContentTypeApplicationJson()
                .lastParam("api_key", "pk_c573b131433429f49daef8ea6380147e97")
                .body(map)
                .send();

        System.out.println();
    }

    @Test
    public void testDotDigital() throws Exception {

        // DotDigital
        String accountInfo = new Request()
                .baseURL("https://r1-api.dotmailer.com/v2/account-info")
                .GET()
                .authorizationOnHeaderBasicEncoded("apiuser-8d024d09fe44@apiconnector.com", "apiuser-8d024d09fe44")
                .finishHeader()
                .finishParams()
                .skipBody()
                .send();

        System.out.println();
    }
}
