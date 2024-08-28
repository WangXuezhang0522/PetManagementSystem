package com.ruoyi.pet.service.impl;

import com.ruoyi.common.core.domain.entity.SysUserShoppingCart;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.pet.service.IPetStoreService;
import com.ruoyi.store.domain.StoreCommodity;
import com.ruoyi.store.mapper.StoreCommodityMapper;
import com.ruoyi.system.service.ISysUserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class PetStoreServiceImpl implements IPetStoreService {
  @Autowired
  private StoreCommodityMapper storeCommodityMapper;
  @Autowired
  private ISysUserShoppingCartService sysUserShoppingCartService;

  /**
   * 查询商品信息
   *
   * @param id 商品信息主键
   * @return 商品信息
   */
  @Override
  public StoreCommodity selectStoreCommodityById(Long id) {
    return storeCommodityMapper.selectStoreCommodityById(id);
  }

  /**
   * 查询宠物商店列表
   */
  @Override
  public List<StoreCommodity> selectStoreCommodityList(StoreCommodity storeCommodity) {
    return storeCommodityMapper.selectStoreCommodityList(storeCommodity);
  }

  /**
   * 新增购物车
   *
   * @param sysUserShoppingCart 购物车
   * @return 结果
   */
  @Override
  public int insertSysUserShoppingCart(SysUserShoppingCart sysUserShoppingCart) {
    return sysUserShoppingCartService.insertSysUserShoppingCart(sysUserShoppingCart);
  }

}
