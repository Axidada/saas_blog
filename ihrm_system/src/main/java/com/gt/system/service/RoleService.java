package com.gt.system.service;

import com.gt.common.service.BaseService;
import com.gt.common.utils.IdWorker;
import com.gt.common.utils.PermissionConstants;
import com.gt.domain.system.Permission;
import com.gt.domain.system.Role;
import com.gt.system.dao.PermissionDao;
import com.gt.system.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService extends BaseService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 添加角色
     */
    public void save(Role role) {
        String id = idWorker.nextId() + "";
        role.setId(id);
        roleDao.save(role);
    }

    /**
     * 更新角色
     */
    public void update(Role role) {
        Role target = roleDao.findById(role.getId()).get();
        target.setName(role.getName());
        target.setDescription(role.getDescription());
        target.setPermissions(role.getPermissions());
        roleDao.save(target);
    }

    //删除
    public void deleteById(String id) {
        roleDao.deleteById(id);
    }

    /**
     * 根据ID查询角色
     */
    public Role findById(String id) {
        return roleDao.findById(id).get();
    }

    /**
     * 查询所有角色:
     * 根据内部维护的公司id进行查询该公司的所有角色
     */
    public List<Role> findAll(String companyId) {
        return roleDao.findAll(getSpec(companyId));
    }

    /**
     * 获取分页角色集合
     *
     * @param companyId 公司id
     * @param page      第几页
     * @param size      每页大小
     * @return 角色集合
     */
    public Page<Role> findByPage(String companyId, int page, int size) {
        return roleDao.findAll(getSpec(companyId), PageRequest.of(page - 1, size));
    }

    /**
     * 分配权限
     */
    public void assignPerms(String roleId, List<String> permIds) {
        //1.获取分配的角色对象
        Role role = roleDao.findById(roleId).get();
        //2.构造角色的权限集合
        Set<Permission> perms = new HashSet<>();
        for (String permId : permIds) {
            Permission permission = permissionDao.findById(permId).get();
            //需要根据父id和类型查询API权限列表
            List<Permission> apiList = permissionDao.findByTypeAndPid(PermissionConstants.PY_API, permission.getId());
            perms.addAll(apiList);//自定赋予API权限
            perms.add(permission);//当前菜单或按钮的权限
        }
        //3.设置角色和权限的关系
        role.setPermissions(perms);
        //4.更新角色
        roleDao.save(role);
    }

}
