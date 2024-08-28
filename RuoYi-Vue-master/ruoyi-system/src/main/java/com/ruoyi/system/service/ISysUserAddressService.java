package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysUserAddress;

/**
 * 收货地址Service接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface ISysUserAddressService 
{
    /**
     * 查询收货地址
     * 
     * @param id 收货地址主键
     * @return 收货地址
     */
    public SysUserAddress selectSysUserAddressById(Long id);

    /**
     * 查询收货地址列表
     * 
     * @param sysUserAddress 收货地址
     * @return 收货地址集合
     */
    public List<SysUserAddress> selectSysUserAddressList(SysUserAddress sysUserAddress);

    /**
     * 新增收货地址
     * 
     * @param sysUserAddress 收货地址
     * @return 结果
     */
    public int insertSysUserAddress(SysUserAddress sysUserAddress);

    /**
     * 修改收货地址
     * 
     * @param sysUserAddress 收货地址
     * @return 结果
     */
    public int updateSysUserAddress(SysUserAddress sysUserAddress);

    /**
     * 批量删除收货地址
     * 
     * @param ids 需要删除的收货地址主键集合
     * @return 结果
     */
    public int deleteSysUserAddressByIds(Long[] ids);

    /**
     * 删除收货地址信息
     * 
     * @param id 收货地址主键
     * @return 结果
     */
    public int deleteSysUserAddressById(Long id);
}
