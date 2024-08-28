package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserAddressMapper;
import com.ruoyi.common.core.domain.entity.SysUserAddress;
import com.ruoyi.system.service.ISysUserAddressService;

/**
 * 收货地址Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class SysUserAddressServiceImpl implements ISysUserAddressService {
  @Autowired
  private SysUserAddressMapper sysUserAddressMapper;

  /**
   * 查询收货地址
   *
   * @param id 收货地址主键
   * @return 收货地址
   */
  @Override
  public SysUserAddress selectSysUserAddressById(Long id) {
    return sysUserAddressMapper.selectSysUserAddressById(id);
  }

  /**
   * 查询收货地址列表
   *
   * @param sysUserAddress 收货地址
   * @return 收货地址
   */
  @Override
  @DataScope(userAlias = "u")
  public List<SysUserAddress> selectSysUserAddressList(SysUserAddress sysUserAddress) {
    return sysUserAddressMapper.selectSysUserAddressList(sysUserAddress);
  }

  /**
   * 新增收货地址
   *
   * @param sysUserAddress 收货地址
   * @return 结果
   */
  @Override
  public int insertSysUserAddress(SysUserAddress sysUserAddress) {
    sysUserAddress.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    sysUserAddress.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    sysUserAddress.setCreateTime(DateUtils.getNowDate());
    return sysUserAddressMapper.insertSysUserAddress(sysUserAddress);
  }

  /**
   * 修改收货地址
   *
   * @param sysUserAddress 收货地址
   * @return 结果
   */
  @Override
  public int updateSysUserAddress(SysUserAddress sysUserAddress) {
    sysUserAddress.setUpdateTime(DateUtils.getNowDate());
    return sysUserAddressMapper.updateSysUserAddress(sysUserAddress);
  }

  /**
   * 批量删除收货地址
   *
   * @param ids 需要删除的收货地址主键
   * @return 结果
   */
  @Override
  public int deleteSysUserAddressByIds(Long[] ids) {
    return sysUserAddressMapper.deleteSysUserAddressByIds(ids);
  }

  /**
   * 删除收货地址信息
   *
   * @param id 收货地址主键
   * @return 结果
   */
  @Override
  public int deleteSysUserAddressById(Long id) {
    return sysUserAddressMapper.deleteSysUserAddressById(id);
  }
}
