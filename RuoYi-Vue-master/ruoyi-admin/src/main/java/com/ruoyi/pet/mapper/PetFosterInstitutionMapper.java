package com.ruoyi.pet.mapper;

import java.util.List;

import com.ruoyi.pet.domain.PetFosterInstitution;

/**
 * 宠物寄养机构Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-10
 */
public interface PetFosterInstitutionMapper {
  /**
   * 查询宠物寄养机构
   *
   * @param id 宠物寄养机构主键
   * @return 宠物寄养机构
   */
  public PetFosterInstitution selectPetFosterInstitutionById(Long id);

  /**
   * 查询宠物寄养机构列表
   *
   * @param petFosterInstitution 宠物寄养机构
   * @return 宠物寄养机构集合
   */
  public List<PetFosterInstitution> selectPetFosterInstitutionList(PetFosterInstitution petFosterInstitution);

  /**
   * 新增宠物寄养机构
   *
   * @param petFosterInstitution 宠物寄养机构
   * @return 结果
   */
  public int insertPetFosterInstitution(PetFosterInstitution petFosterInstitution);

  /**
   * 修改宠物寄养机构
   *
   * @param petFosterInstitution 宠物寄养机构
   * @return 结果
   */
  public int updatePetFosterInstitution(PetFosterInstitution petFosterInstitution);

  /**
   * 删除宠物寄养机构
   *
   * @param id 宠物寄养机构主键
   * @return 结果
   */
  public int deletePetFosterInstitutionById(Long id);

  /**
   * 批量删除宠物寄养机构
   *
   * @param ids 需要删除的数据主键集合
   * @return 结果
   */
  public int deletePetFosterInstitutionByIds(Long[] ids);

  public List<PetFosterInstitution> getEchartsData(PetFosterInstitution petFosterInstitution);
}
