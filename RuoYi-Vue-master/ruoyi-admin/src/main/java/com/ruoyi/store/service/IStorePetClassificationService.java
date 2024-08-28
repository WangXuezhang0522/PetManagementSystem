package com.ruoyi.store.service;

import java.util.List;

import com.ruoyi.store.domain.StorePetClassification;

/**
 * 宠物分类Service接口
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IStorePetClassificationService {
  /**
   * 查询宠物分类
   *
   * @param id 宠物分类主键
   * @return 宠物分类
   */
  public StorePetClassification selectStorePetClassificationById(Long id);

  /**
   * 查询宠物分类列表
   *
   * @param storePetClassification 宠物分类
   * @return 宠物分类集合
   */
  public List<StorePetClassification> selectStorePetClassificationList(StorePetClassification storePetClassification);

  public List<StorePetClassification> selectStorePetClassificationListAll(StorePetClassification storePetClassification);

  /**
   * 新增宠物分类
   *
   * @param storePetClassification 宠物分类
   * @return 结果
   */
  public int insertStorePetClassification(StorePetClassification storePetClassification);

  /**
   * 修改宠物分类
   *
   * @param storePetClassification 宠物分类
   * @return 结果
   */
  public int updateStorePetClassification(StorePetClassification storePetClassification);

  /**
   * 批量删除宠物分类
   *
   * @param ids 需要删除的宠物分类主键集合
   * @return 结果
   */
  public int deleteStorePetClassificationByIds(Long[] ids);

  /**
   * 删除宠物分类信息
   *
   * @param id 宠物分类主键
   * @return 结果
   */
  public int deleteStorePetClassificationById(Long id);
}
