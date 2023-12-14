package com.ylx.yapi_interface.controller;



import com.ylx.yapiclientsdk.model.User;
import com.ylx.yapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称 API
 *
 * @author 杨乐昕
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name) {

        return "GET 你的名字是 " + name;
    }

    /**
     * URL传参
     *
     * @param name
     * @return
     */
    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是 " + name;
    }

    /**
     * json 格式传参
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        //String timestamp = request.getHeader("timestamp");
        String body = request.getHeader("body");
        String sign = request.getHeader("sign");
        // TODO: 查询数据库是否有配置key,进行校验
        if (!accessKey.equals("yyyy")) {
            throw new RuntimeException("无权限");
        }
        // TODO: 后台使用hashmap存储随机数，校验传递的随机数是否已存在，若存在则无权限
        if (Long.parseLong(nonce) > 10000) {
            throw new RuntimeException("无权限");
        }
        // TODO: 时间和当前时间相差不超过 5 分钟
//        if (timestamp){
//
//        }

        // TODO: 实际情况是从数据库中查出 secretKey ，通过从请求头拿到的accessKey 查询获取到 secretKey 作为签名的密钥
        String serverSign = SignUtils.getSign(body, "abcdefg");
        if (!sign.equals(serverSign)) {
            throw new RuntimeException("无权限");
        }
        return "POST 用户名字是" + user.getUsername();
    }
}
