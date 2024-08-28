package com.ruoyi.pet.mapper;

import java.util.List;
import com.ruoyi.pet.domain.PetAdoptApplication;

/**
 * 宠物领养申请Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface PetAdoptApplicationMapper 
{
    /**
     * 查询宠物领养申请
     * 
     * @param id 宠物领养申请主键
     * @return 宠物领养申请
     */
    public PetAdoptApplication selectPetAdoptApplicationById(Long id);

    /**
     * 查询宠物领养申请列表
     * 
     * @param petAdoptApplication 宠物领养申请
     * @return 宠物领养申请集合
     */
    public List<PetAdoptApplication> selectPetAdoptApplicationList(PetAdoptApplication petAdoptApplication);

    /**
     * 新增宠物领养申请
     * 
     * @param petAdoptApplication 宠物领养申请
     * @return 结果
     */
    public int insertPetAdoptApplication(PetAdoptApplication petAdoptApplication);

    /**
     * 修改宠物领养申请
     * 
     * @param petAdoptApplication 宠物领养申请
     * @return 结果
     */
    public int updatePetAdoptApplication(PetAdoptApplication petAdoptApplication);

    /**
     * 删除宠物领养申请
     * 
     * @param id 宠物领养申请主键
     * @return 结果
     */
    public int deletePetAdoptApplicationById(Long id);

    /**
     * 批量删除宠物领养申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetAdoptApplicationByIds(Long[] ids);
}
