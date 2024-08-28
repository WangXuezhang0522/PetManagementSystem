package com.ruoyi.store.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.StorePetClassificationMapper;
import com.ruoyi.store.domain.StorePetClassification;
import com.ruoyi.store.service.IStorePetClassificationService;

/**
 * 宠物分类Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class StorePetClassificationServiceImpl implements IStorePetClassificationService {
  @Autowired
  private StorePetClassificationMapper storePetClassificationMapper;

  /**
   * 查询宠物分类
   *
   * @param id 宠物分类主键
   * @return 宠物分类
   */
  @Override
  public StorePetClassification selectStorePetClassificationById(Long id) {
    return storePetClassificationMapper.selectStorePetClassificationById(id);
  }

  /**
   * 查询宠物分类列表
   *
   * @param storePetClassification 宠物分类
   * @return 宠物分类
   */
  @Override
  @DataScope(userAlias = "u")
  public List<StorePetClassification> selectStorePetClassificationList(StorePetClassification storePetClassification) {
    return storePetClassificationMapper.selectStorePetClassificationList(storePetClassification);
  }

  @Override
  public List<StorePetClassification> selectStorePetClassificationListAll(StorePetClassification storePetClassification) {
    return storePetClassificationMapper.selectStorePetClassificationList(storePetClassification);
  }

  /**
   * 新增宠物分类
   *
   * @param storePetClassification 宠物分类
   * @return 结果
   */
  @Override
  public int insertStorePetClassification(StorePetClassification storePetClassification) {
    storePetClassification.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    storePetClassification.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    storePetClassification.setCreateTime(DateUtils.getNowDate());
    return storePetClassificationMapper.insertStorePetClassification(storePetClassification);
  }

  /**
   * 修改宠物分类
   *
   * @param storePetClassification 宠物分类
   * @return 结果
   */
  @Override
  public int updateStorePetClassification(StorePetClassification storePetClassification) {
    storePetClassification.setUpdateTime(DateUtils.getNowDate());
    return storePetClassificationMapper.updateStorePetClassification(storePetClassification);
  }

  /**
   * 批量删除宠物分类
   *
   * @param ids 需要删除的宠物分类主键
   * @return 结果
   */
  @Override
  public int deleteStorePetClassificationByIds(Long[] ids) {
    return storePetClassificationMapper.deleteStorePetClassificationByIds(ids);
  }

  /**
   * 删除宠物分类信息
   *
   * @param id 宠物分类主键
   * @return 结果
   */
  @Override
  public int deleteStorePetClassificationById(Long id) {
    return storePetClassificationMapper.deleteStorePetClassificationById(id);
  }
}
