package com.ruoyi.pet.service;

import com.ruoyi.common.core.domain.entity.SysUserShoppingCart;
import com.ruoyi.store.domain.StoreCommodity;

import java.util.List;

/**
 * 商品信息Service接口
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IPetStoreService {
  /**
   * 查询商品信息
   *
   * @param id 商品信息主键
   * @return 商品信息
   */
  public StoreCommodity selectStoreCommodityById(Long id);

  /**
   * 查询宠物商店列表
   */
  public List<StoreCommodity> selectStoreCommodityList(StoreCommodity storeCommodity);

  /**
   * 新增购物车
   *
   * @param sysUserShoppingCart 购物车
   * @return 结果
   */
  public int insertSysUserShoppingCart(SysUserShoppingCart sysUserShoppingCart);

}
