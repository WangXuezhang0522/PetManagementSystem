package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserShoppingCartMapper;
import com.ruoyi.common.core.domain.entity.SysUserShoppingCart;
import com.ruoyi.system.service.ISysUserShoppingCartService;

/**
 * 购物车Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class SysUserShoppingCartServiceImpl implements ISysUserShoppingCartService {
  @Autowired
  private SysUserShoppingCartMapper sysUserShoppingCartMapper;

  /**
   * 查询购物车
   *
   * @param id 购物车主键
   * @return 购物车
   */
  @Override
  public SysUserShoppingCart selectSysUserShoppingCartById(Long id) {
    return sysUserShoppingCartMapper.selectSysUserShoppingCartById(id);
  }

  /**
   * 查询购物车列表
   *
   * @param sysUserShoppingCart 购物车
   * @return 购物车
   */
  @Override
  @DataScope(userAlias = "u")
  public List<SysUserShoppingCart> selectSysUserShoppingCartList(SysUserShoppingCart sysUserShoppingCart) {
    return sysUserShoppingCartMapper.selectSysUserShoppingCartList(sysUserShoppingCart);
  }

  /**
   * 新增购物车
   *
   * @param sysUserShoppingCart 购物车
   * @return 结果
   */
  @Override
  public int insertSysUserShoppingCart(SysUserShoppingCart sysUserShoppingCart) {
    sysUserShoppingCart.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    sysUserShoppingCart.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    sysUserShoppingCart.setCreateTime(DateUtils.getNowDate());
    return sysUserShoppingCartMapper.insertSysUserShoppingCart(sysUserShoppingCart);
  }

  /**
   * 修改购物车
   *
   * @param sysUserShoppingCart 购物车
   * @return 结果
   */
  @Override
  public int updateSysUserShoppingCart(SysUserShoppingCart sysUserShoppingCart) {
    sysUserShoppingCart.setUpdateTime(DateUtils.getNowDate());
    return sysUserShoppingCartMapper.updateSysUserShoppingCart(sysUserShoppingCart);
  }

  /**
   * 批量删除购物车
   *
   * @param ids 需要删除的购物车主键
   * @return 结果
   */
  @Override
  public int deleteSysUserShoppingCartByIds(Long[] ids) {
    return sysUserShoppingCartMapper.deleteSysUserShoppingCartByIds(ids);
  }

  /**
   * 删除购物车信息
   *
   * @param id 购物车主键
   * @return 结果
   */
  @Override
  public int deleteSysUserShoppingCartById(Long id) {
    return sysUserShoppingCartMapper.deleteSysUserShoppingCartById(id);
  }
}
