package com.ruoyi.store.service;

import java.util.List;
import com.ruoyi.store.domain.StoreProductClassification;

/**
 * 商品分类Service接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IStoreProductClassificationService 
{
    /**
     * 查询商品分类
     * 
     * @param id 商品分类主键
     * @return 商品分类
     */
    public StoreProductClassification selectStoreProductClassificationById(Long id);

    /**
     * 查询商品分类列表
     * 
     * @param storeProductClassification 商品分类
     * @return 商品分类集合
     */
    public List<StoreProductClassification> selectStoreProductClassificationList(StoreProductClassification storeProductClassification);
    public List<StoreProductClassification> selectStoreProductClassificationListAll(StoreProductClassification storeProductClassification);

    /**
     * 新增商品分类
     * 
     * @param storeProductClassification 商品分类
     * @return 结果
     */
    public int insertStoreProductClassification(StoreProductClassification storeProductClassification);

    /**
     * 修改商品分类
     * 
     * @param storeProductClassification 商品分类
     * @return 结果
     */
    public int updateStoreProductClassification(StoreProductClassification storeProductClassification);

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的商品分类主键集合
     * @return 结果
     */
    public int deleteStoreProductClassificationByIds(Long[] ids);

    /**
     * 删除商品分类信息
     * 
     * @param id 商品分类主键
     * @return 结果
     */
    public int deleteStoreProductClassificationById(Long id);
}
