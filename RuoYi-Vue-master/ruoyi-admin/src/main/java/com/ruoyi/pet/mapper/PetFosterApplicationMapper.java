package com.ruoyi.pet.mapper;

import java.util.List;
import com.ruoyi.pet.domain.PetFosterApplication;

/**
 * 宠物寄养申请Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-10
 */
public interface PetFosterApplicationMapper 
{
    /**
     * 查询宠物寄养申请
     * 
     * @param id 宠物寄养申请主键
     * @return 宠物寄养申请
     */
    public PetFosterApplication selectPetFosterApplicationById(Long id);

    /**
     * 查询宠物寄养申请列表
     * 
     * @param petFosterApplication 宠物寄养申请
     * @return 宠物寄养申请集合
     */
    public List<PetFosterApplication> selectPetFosterApplicationList(PetFosterApplication petFosterApplication);

    /**
     * 新增宠物寄养申请
     * 
     * @param petFosterApplication 宠物寄养申请
     * @return 结果
     */
    public int insertPetFosterApplication(PetFosterApplication petFosterApplication);

    /**
     * 修改宠物寄养申请
     * 
     * @param petFosterApplication 宠物寄养申请
     * @return 结果
     */
    public int updatePetFosterApplication(PetFosterApplication petFosterApplication);

    /**
     * 删除宠物寄养申请
     * 
     * @param id 宠物寄养申请主键
     * @return 结果
     */
    public int deletePetFosterApplicationById(Long id);

    /**
     * 批量删除宠物寄养申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetFosterApplicationByIds(Long[] ids);
}
