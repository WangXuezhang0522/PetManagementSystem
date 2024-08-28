package com.ruoyi.store.service;

import java.util.List;
import com.ruoyi.store.domain.StoreInfo;

/**
 * 商店信息Service接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IStoreInfoService 
{
    /**
     * 查询商店信息
     * 
     * @param id 商店信息主键
     * @return 商店信息
     */
    public StoreInfo selectStoreInfoById(Long id);

    /**
     * 查询商店信息列表
     * 
     * @param storeInfo 商店信息
     * @return 商店信息集合
     */
    public List<StoreInfo> selectStoreInfoList(StoreInfo storeInfo);

    /**
     * 新增商店信息
     * 
     * @param storeInfo 商店信息
     * @return 结果
     */
    public int insertStoreInfo(StoreInfo storeInfo);

    /**
     * 修改商店信息
     * 
     * @param storeInfo 商店信息
     * @return 结果
     */
    public int updateStoreInfo(StoreInfo storeInfo);

    /**
     * 批量删除商店信息
     * 
     * @param ids 需要删除的商店信息主键集合
     * @return 结果
     */
    public int deleteStoreInfoByIds(Long[] ids);

    /**
     * 删除商店信息信息
     * 
     * @param id 商店信息主键
     * @return 结果
     */
    public int deleteStoreInfoById(Long id);
}
