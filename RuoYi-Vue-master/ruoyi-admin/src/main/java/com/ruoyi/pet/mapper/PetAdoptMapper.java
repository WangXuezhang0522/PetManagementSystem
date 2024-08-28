package com.ruoyi.pet.mapper;

import java.util.List;

import com.ruoyi.pet.domain.PetAdopt;
import org.apache.ibatis.annotations.Param;

/**
 * 宠物领养Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public interface PetAdoptMapper {
  /**
   * 查询宠物领养
   *
   * @param id 宠物领养主键
   * @return 宠物领养
   */
  public PetAdopt selectPetAdoptById(Long id);

  /**
   * 查询宠物领养列表
   *
   * @param petAdopt 宠物领养
   * @return 宠物领养集合
   */
  public List<PetAdopt> selectPetAdoptList(PetAdopt petAdopt);

  /**
   * 新增宠物领养
   *
   * @param petAdopt 宠物领养
   * @return 结果
   */
  public int insertPetAdopt(PetAdopt petAdopt);

  /**
   * 修改宠物领养
   *
   * @param petAdopt 宠物领养
   * @return 结果
   */
  public int updatePetAdopt(PetAdopt petAdopt);

  /**
   * 修改宠物领养的状态
   */
  public int updatePetAdoptState(@Param("id") Long id, @Param("state") String state);

  /**
   * 删除宠物领养
   *
   * @param id 宠物领养主键
   * @return 结果
   */
  public int deletePetAdoptById(Long id);

  /**
   * 批量删除宠物领养
   *
   * @param ids 需要删除的数据主键集合
   * @return 结果
   */
  public int deletePetAdoptByIds(Long[] ids);
}
