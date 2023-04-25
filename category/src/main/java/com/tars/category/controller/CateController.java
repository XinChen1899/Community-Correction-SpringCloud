package com.tars.category.controller;

import com.tars.category.api.ResponseResult;
import com.tars.category.entity.CategoryInfo;
import com.tars.category.service.CateService;
import com.tars.category.service.remote.RemoteCrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cate/info")
@CrossOrigin(origins = "*")
public class CateController {
    @Autowired
    private CateService service;
    @Autowired
    private RemoteCrpService crpService;

    @GetMapping("/all")
    public ResponseResult<List<CategoryInfo>> getAll() {
        List<CategoryInfo> list = service.list().stream()
                .peek(item -> item.setXm(
                        crpService.getName(
                                item.getDxbh())))
                .toList();
        return ResponseResult.success(list);
    }

    public String getGLLB(String dxbh) {
        return service.query().eq("dxbh", dxbh)
                .one().getGllb();
    }

    @GetMapping("/{id}")
    public ResponseResult<CategoryInfo> getCategoryBtId(@PathVariable String id) {
        try {
            CategoryInfo info = service.query().eq("dxbh", id).one();
            info.setXm(crpService.getName(id));
            return ResponseResult.success(info);
        } catch (Exception e) {
            return ResponseResult.fail(null, e.getMessage());
        }

    }


    @PostMapping("/save")
    public ResponseResult<Boolean> saveCategory(@RequestBody CategoryInfo info) {
        try {
            return ResponseResult.success(service.save(info));
        } catch (Exception e) {
            return ResponseResult.fail(false, "保存失败!");
        }
    }

    @PostMapping("/update")
    public ResponseResult<Boolean> updateCateInfo(@RequestBody CategoryInfo info) {
        try {
            return ResponseResult.success(
                    service.update().eq("dxbh", info.getDxbh())
                            .update(info));
        } catch (Exception e) {
            return ResponseResult.fail(false, "更新失败!");
        }
    }
}
