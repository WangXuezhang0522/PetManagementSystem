package com.ruoyi.store.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.StoreProductClassificationMapper;
import com.ruoyi.store.domain.StoreProductClassification;
import com.ruoyi.store.service.IStoreProductClassificationService;

/**
 * 商品分类Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class StoreProductClassificationServiceImpl implements IStoreProductClassificationService {
  @Autowired
  private StoreProductClassificationMapper storeProductClassificationMapper;

  /**
   * 查询商品分类
   *
   * @param id 商品分类主键
   * @return 商品分类
   */
  @Override
  public StoreProductClassification selectStoreProductClassificationById(Long id) {
    return storeProductClassificationMapper.selectStoreProductClassificationById(id);
  }

  /**
   * 查询商品分类列表
   *
   * @param storeProductClassification 商品分类
   * @return 商品分类
   */
  @Override
  @DataScope(userAlias = "u")
  public List<StoreProductClassification> selectStoreProductClassificationList(StoreProductClassification storeProductClassification) {
    return storeProductClassificationMapper.selectStoreProductClassificationList(storeProductClassification);
  }

  @Override
  public List<StoreProductClassification> selectStoreProductClassificationListAll(StoreProductClassification storeProductClassification) {
    return storeProductClassificationMapper.selectStoreProductClassificationList(storeProductClassification);
  }

  /**
   * 新增商品分类
   *
   * @param storeProductClassification 商品分类
   * @return 结果
   */
  @Override
  public int insertStoreProductClassification(StoreProductClassification storeProductClassification) {
    storeProductClassification.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    storeProductClassification.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    storeProductClassification.setCreateTime(DateUtils.getNowDate());
    return storeProductClassificationMapper.insertStoreProductClassification(storeProductClassification);
  }

  /**
   * 修改商品分类
   *
   * @param storeProductClassification 商品分类
   * @return 结果
   */
  @Override
  public int updateStoreProductClassification(StoreProductClassification storeProductClassification) {
    storeProductClassification.setUpdateTime(DateUtils.getNowDate());
    return storeProductClassificationMapper.updateStoreProductClassification(storeProductClassification);
  }

  /**
   * 批量删除商品分类
   *
   * @param ids 需要删除的商品分类主键
   * @return 结果
   */
  @Override
  public int deleteStoreProductClassificationByIds(Long[] ids) {
    return storeProductClassificationMapper.deleteStoreProductClassificationByIds(ids);
  }

  /**
   * 删除商品分类信息
   *
   * @param id 商品分类主键
   * @return 结果
   */
  @Override
  public int deleteStoreProductClassificationById(Long id) {
    return storeProductClassificationMapper.deleteStoreProductClassificationById(id);
  }
}
