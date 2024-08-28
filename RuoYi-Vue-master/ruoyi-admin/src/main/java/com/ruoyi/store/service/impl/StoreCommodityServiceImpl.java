package com.ruoyi.store.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.StoreCommodityMapper;
import com.ruoyi.store.domain.StoreCommodity;
import com.ruoyi.store.service.IStoreCommodityService;

/**
 * 商品信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class StoreCommodityServiceImpl implements IStoreCommodityService {
  @Autowired
  private StoreCommodityMapper storeCommodityMapper;

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
   * 查询商品信息列表
   *
   * @param storeCommodity 商品信息
   * @return 商品信息
   */
  @Override
  @DataScope(userAlias = "u")
  public List<StoreCommodity> selectStoreCommodityList(StoreCommodity storeCommodity) {
    return storeCommodityMapper.selectStoreCommodityList(storeCommodity);
  }

  /**
   * 新增商品信息
   *
   * @param storeCommodity 商品信息
   * @return 结果
   */
  @Override
  public int insertStoreCommodity(StoreCommodity storeCommodity) {
    storeCommodity.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    storeCommodity.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    storeCommodity.setCreateTime(DateUtils.getNowDate());
    return storeCommodityMapper.insertStoreCommodity(storeCommodity);
  }

  /**
   * 修改商品信息
   *
   * @param storeCommodity 商品信息
   * @return 结果
   */
  @Override
  public int updateStoreCommodity(StoreCommodity storeCommodity) {
    storeCommodity.setUpdateTime(DateUtils.getNowDate());
    return storeCommodityMapper.updateStoreCommodity(storeCommodity);
  }

  /**
   * 批量删除商品信息
   *
   * @param ids 需要删除的商品信息主键
   * @return 结果
   */
  @Override
  public int deleteStoreCommodityByIds(Long[] ids) {
    return storeCommodityMapper.deleteStoreCommodityByIds(ids);
  }

  /**
   * 删除商品信息信息
   *
   * @param id 商品信息主键
   * @return 结果
   */
  @Override
  public int deleteStoreCommodityById(Long id) {
    return storeCommodityMapper.deleteStoreCommodityById(id);
  }
}
