package com.ruoyi.pet.service;

import java.util.List;
import com.ruoyi.pet.domain.PetForum;

/**
 * 宠物论坛Service接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IPetForumService 
{
    /**
     * 查询宠物论坛
     * 
     * @param id 宠物论坛主键
     * @return 宠物论坛
     */
    public PetForum selectPetForumById(Long id);

    /**
     * 查询宠物论坛列表
     * 
     * @param petForum 宠物论坛
     * @return 宠物论坛集合
     */
    public List<PetForum> selectPetForumList(PetForum petForum);

    /**
     * 新增宠物论坛
     * 
     * @param petForum 宠物论坛
     * @return 结果
     */
    public int insertPetForum(PetForum petForum);

    /**
     * 修改宠物论坛
     * 
     * @param petForum 宠物论坛
     * @return 结果
     */
    public int updatePetForum(PetForum petForum);

    /**
     * 批量删除宠物论坛
     * 
     * @param ids 需要删除的宠物论坛主键集合
     * @return 结果
     */
    public int deletePetForumByIds(Long[] ids);

    /**
     * 删除宠物论坛信息
     * 
     * @param id 宠物论坛主键
     * @return 结果
     */
    public int deletePetForumById(Long id);
}
