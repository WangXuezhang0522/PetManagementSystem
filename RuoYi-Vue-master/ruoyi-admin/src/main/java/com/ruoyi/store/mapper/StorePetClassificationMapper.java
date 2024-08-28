package com.ruoyi.store.mapper;

import java.util.List;
import com.ruoyi.store.domain.StorePetClassification;

/**
 * 宠物分类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface StorePetClassificationMapper 
{
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
     * 删除宠物分类
     * 
     * @param id 宠物分类主键
     * @return 结果
     */
    public int deleteStorePetClassificationById(Long id);

    /**
     * 批量删除宠物分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStorePetClassificationByIds(Long[] ids);
}
