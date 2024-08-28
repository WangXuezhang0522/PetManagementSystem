package com.ruoyi.pet.mapper;

import java.util.List;
import com.ruoyi.pet.domain.PetLog;

/**
 * 宠物日志Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface PetLogMapper 
{
    /**
     * 查询宠物日志
     * 
     * @param id 宠物日志主键
     * @return 宠物日志
     */
    public PetLog selectPetLogById(Long id);

    /**
     * 查询宠物日志列表
     * 
     * @param petLog 宠物日志
     * @return 宠物日志集合
     */
    public List<PetLog> selectPetLogList(PetLog petLog);

    /**
     * 新增宠物日志
     * 
     * @param petLog 宠物日志
     * @return 结果
     */
    public int insertPetLog(PetLog petLog);

    /**
     * 修改宠物日志
     * 
     * @param petLog 宠物日志
     * @return 结果
     */
    public int updatePetLog(PetLog petLog);

    /**
     * 删除宠物日志
     * 
     * @param id 宠物日志主键
     * @return 结果
     */
    public int deletePetLogById(Long id);

    /**
     * 批量删除宠物日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetLogByIds(Long[] ids);
}
