package com.ruoyi.store.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.StoreInfoMapper;
import com.ruoyi.store.domain.StoreInfo;
import com.ruoyi.store.service.IStoreInfoService;

/**
 * 商店信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class StoreInfoServiceImpl implements IStoreInfoService {
  @Autowired
  private StoreInfoMapper storeInfoMapper;

  /**
   * 查询商店信息
   *
   * @param id 商店信息主键
   * @return 商店信息
   */
  @Override
  public StoreInfo selectStoreInfoById(Long id) {
    return storeInfoMapper.selectStoreInfoById(id);
  }

  /**
   * 查询商店信息列表
   *
   * @param storeInfo 商店信息
   * @return 商店信息
   */
  @Override
  @DataScope(userAlias = "u")
  public List<StoreInfo> selectStoreInfoList(StoreInfo storeInfo) {
    return storeInfoMapper.selectStoreInfoList(storeInfo);
  }

  /**
   * 新增商店信息
   *
   * @param storeInfo 商店信息
   * @return 结果
   */
  @Override
  public int insertStoreInfo(StoreInfo storeInfo) {
    storeInfo.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    storeInfo.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    storeInfo.setCreateTime(DateUtils.getNowDate());
    return storeInfoMapper.insertStoreInfo(storeInfo);
  }

  /**
   * 修改商店信息
   *
   * @param storeInfo 商店信息
   * @return 结果
   */
  @Override
  public int updateStoreInfo(StoreInfo storeInfo) {
    storeInfo.setUpdateTime(DateUtils.getNowDate());
    return storeInfoMapper.updateStoreInfo(storeInfo);
  }

  /**
   * 批量删除商店信息
   *
   * @param ids 需要删除的商店信息主键
   * @return 结果
   */
  @Override
  public int deleteStoreInfoByIds(Long[] ids) {
    return storeInfoMapper.deleteStoreInfoByIds(ids);
  }

  /**
   * 删除商店信息信息
   *
   * @param id 商店信息主键
   * @return 结果
   */
  @Override
  public int deleteStoreInfoById(Long id) {
    return storeInfoMapper.deleteStoreInfoById(id);
  }
}
