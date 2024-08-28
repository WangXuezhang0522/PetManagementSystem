package com.ruoyi.pet.service;

import java.util.List;
import com.ruoyi.pet.domain.PetLose;

/**
 * 宠物丢失Service接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface IPetLoseService 
{
    /**
     * 查询宠物丢失
     * 
     * @param id 宠物丢失主键
     * @return 宠物丢失
     */
    public PetLose selectPetLoseById(Long id);

    /**
     * 查询宠物丢失列表
     * 
     * @param petLose 宠物丢失
     * @return 宠物丢失集合
     */
    public List<PetLose> selectPetLoseList(PetLose petLose);

    /**
     * 新增宠物丢失
     * 
     * @param petLose 宠物丢失
     * @return 结果
     */
    public int insertPetLose(PetLose petLose);

    /**
     * 修改宠物丢失
     * 
     * @param petLose 宠物丢失
     * @return 结果
     */
    public int updatePetLose(PetLose petLose);

    /**
     * 批量删除宠物丢失
     * 
     * @param ids 需要删除的宠物丢失主键集合
     * @return 结果
     */
    public int deletePetLoseByIds(Long[] ids);

    /**
     * 删除宠物丢失信息
     * 
     * @param id 宠物丢失主键
     * @return 结果
     */
    public int deletePetLoseById(Long id);
}
