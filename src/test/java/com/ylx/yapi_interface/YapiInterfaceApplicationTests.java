package com.ylx.yapi_interface;

import com.ylx.yapiclientsdk.client.YAPIClient;
import com.ylx.yapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class YapiInterfaceApplicationTests {

    @Resource
    private YAPIClient yapiClient;

    @Test
    void contextLoads() {
        String result = yapiClient.getNameByGet("yyyy");
        User user = new User();
        user.setUsername("yyyy");
        String usernameByPost = yapiClient.getUsernameByPost(user);

        System.out.println(result);
        System.out.println(usernameByPost);
    }

}
