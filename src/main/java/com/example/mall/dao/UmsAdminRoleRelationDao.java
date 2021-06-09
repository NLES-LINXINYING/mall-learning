package com.example.mall.dao;

import com.example.mall.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linxinying
 * @version 1.0.0
 * @className UmsAdminRoleRelationDao.java
 * @description 后台用户与角色管理自定义Dao
 * @createTime 2021-06-02 12:47:00
 */
@Repository
public interface UmsAdminRoleRelationDao {
    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
