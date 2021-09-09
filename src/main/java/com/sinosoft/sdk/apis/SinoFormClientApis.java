package com.sinosoft.sdk.apis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yanyuxin
 * 2021/8/27 11:04
 */
@RequestMapping("/sinoform/client/apis")
public interface SinoFormClientApis {

    @RequestMapping("/post")
    public ResponseEntity post(@RequestBody JSONObject event) throws ClassNotFoundException;
}
