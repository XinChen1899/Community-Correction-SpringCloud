package com.tars.daily.controller.check;

import com.tars.daily.api.ResponseResult;
import com.tars.daily.entity.check.CheckDetail;
import com.tars.daily.service.check.CheckDetailService;
import com.tars.daily.service.remote.RemoteCrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/daily/check/detail")
@CrossOrigin(origins = "*")
public class CheckDetailController {
    @Autowired
    private CheckDetailService service;
    @Autowired
    private RemoteCrpService crpService;

    @GetMapping("/{dxbh}")
    public ResponseResult<List<CheckDetail>> getCheckDetailByDxbh(@PathVariable("dxbh") String dxbh) {
        List<CheckDetail> list = service.query().eq("dxbh", dxbh).list();
        return ResponseResult.success(list);
    }

    // 更新首次报到
    void firstCheck(String dxbh) {
        crpService.firstCheck(dxbh);
    }

    @PostMapping
    public ResponseResult<Boolean> saveCheck(@RequestBody CheckDetail detail) {
        try {
            firstCheck(detail.getDxbh());
            service.save(detail);
            return ResponseResult.success(true);
        } catch (Exception e) {
            return ResponseResult.fail(false, e.getMessage());
        }
    }
}
