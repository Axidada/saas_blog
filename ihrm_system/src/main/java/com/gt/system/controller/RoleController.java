package com.gt.system.controller;

import com.gt.common.controller.BaseController;
import com.gt.common.entity.PageResult;
import com.gt.common.entity.Result;
import com.gt.common.entity.ResultCode;
import com.gt.domain.system.Role;
import com.gt.domain.system.response.RoleResult;
import com.gt.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/system")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;


    //保存
    @PostMapping(value = "/role")
    public Result save(@RequestBody Role role) {
        role.setCompanyId(companyId);
        roleService.save(role);
        return new Result(ResultCode.SUCCESS);
    }

    //修改
    @PutMapping(value = "/role/{id}")
    public Result update(@PathVariable(value = "id") String id, @RequestBody Role role) {
        role.setId(id);
        roleService.update(role);
        return new Result(ResultCode.SUCCESS);
    }

    //删除
    @DeleteMapping(value = "/role/{id}")
    public Result deleteById(@PathVariable(value = "id") String id) {
        roleService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    //通过ID查询
    @GetMapping(value = "/role/{id}")
    public Result findById(@PathVariable(value = "id") String id) throws Exception {
        Role role = roleService.findById(id);
        RoleResult roleResult = new RoleResult(role);
        return new Result(ResultCode.SUCCESS, roleResult);
    }

    //查询全部
    @GetMapping(value = "/role/list")
    public Result findAll() {
        List<Role> roleList = roleService.findAll(companyId);
        return new Result(ResultCode.SUCCESS, roleList);
    }

    /**
     * 分页查询角色
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public Result findByPage(int page, int pagesize, Role role) throws Exception {
        Page<Role> searchPage = roleService.findByPage(companyId, page, pagesize);
        PageResult<Role> pr = new PageResult(searchPage.getTotalElements(), searchPage.getContent());
        return new Result(ResultCode.SUCCESS, pr);
    }

    /**
     * 分配权限
     */
    @RequestMapping(value = "/role/assignPrem", method = RequestMethod.PUT)
    public Result save(@RequestBody Map<String, Object> map) {
        //1.获取被分配的角色的id
        String roleId = (String) map.get("id");
        //2.获取到权限的id列表
        List<String> permIds = (List<String>) map.get("permIds");
        //3.调用service完成权限分配
        roleService.assignPerms(roleId, permIds);
        return new Result(ResultCode.SUCCESS);
    }

}
