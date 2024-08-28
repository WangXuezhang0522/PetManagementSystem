package com.ruoyi.store.mapper;

import java.util.List;
import com.ruoyi.store.domain.StoreCommodity;

/**
 * 商品信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface StoreCommodityMapper 
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
     * 删除商品信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteStoreCommodityById(Long id);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreCommodityByIds(Long[] ids);
}
