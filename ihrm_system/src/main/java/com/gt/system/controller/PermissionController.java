package com.gt.system.controller;

import com.gt.common.entity.Result;
import com.gt.common.entity.ResultCode;
import com.gt.domain.system.Permission;
import com.gt.system.service.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "权限")
@CrossOrigin
@RestController
@RequestMapping(value = "/system")

public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 保存
     */
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public Result save(@RequestBody Map<String, Object> map) throws Exception {
        permissionService.save(map);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 修改Department
     */
    @PutMapping(value = "/permission/{id}")
    public Result update(@PathVariable(value = "id") String id, @RequestBody Map<String, Object> map) throws Exception {
        map.put("id", id);
        permissionService.update(map);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据id删除
     */
    @DeleteMapping(value = "/permission/{id}")
    public Result delete(@PathVariable(value = "id") String id) throws Exception {
        permissionService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据ID查询department
     */
    @GetMapping(value = "/permission/{id}")
    public Result findById(@PathVariable(value = "id") String id) throws Exception {
        Map map = permissionService.findById(id);
        return new Result(ResultCode.SUCCESS, map);
    }

    /**
     * 查询全部
     */
    @GetMapping(value = "/permission")
    public Result findAll(@RequestParam Map map) {
        List<Permission> list = permissionService.findAll(map);
        return new Result(ResultCode.SUCCESS, list);
    }
}
