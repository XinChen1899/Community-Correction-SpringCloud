package com.tars.termination.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CrpHelper {
    public static String getXm(String dxbh) {
        String url = "http://localhost:9007/ic/crp/xm/" + dxbh;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.getForEntity(url,
                String.class);
        return res.getBody();
    }

    public static String getRandomDxbh() {
        String url = "http://localhost:9007/ic/crp/random";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.getForEntity(url,
                String.class);
        return res.getBody();
    }
}
