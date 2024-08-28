package com.ruoyi.store.service;

import java.util.List;
import com.ruoyi.store.domain.StoreCommodity;

/**
 * 商品信息Service接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IStoreCommodityService 
{
    /**
     * 查询商品信息
     * 
     * @param id 商品信息主键
     * @return 商品信息
     */
    public StoreCommodity selectStoreCommodityById(Long id);

    /**
     * 查询商品信息列表
     * 
     * @param storeCommodity 商品信息
     * @return 商品信息集合
     */
    public List<StoreCommodity> selectStoreCommodityList(StoreCommodity storeCommodity);

    /**
     * 新增商品信息
     * 
     * @param storeCommodity 商品信息
     * @return 结果
     */
    public int insertStoreCommodity(StoreCommodity storeCommodity);

    /**
     * 修改商品信息
     * 
     * @param storeCommodity 商品信息
     * @return 结果
     */
    public int updateStoreCommodity(StoreCommodity storeCommodity);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的商品信息主键集合
     * @return 结果
     */
    public int deleteStoreCommodityByIds(Long[] ids);

    /**
     * 删除商品信息信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteStoreCommodityById(Long id);
}
