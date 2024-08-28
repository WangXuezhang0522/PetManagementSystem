package com.ruoyi.pet.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.pet.domain.PetFosterInstitution;

/**
 * 宠物寄养机构Service接口
 *
 * @author ruoyi
 * @date 2024-03-10
 */
public interface IPetFosterInstitutionService {
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
   * 批量删除宠物寄养机构
   *
   * @param ids 需要删除的宠物寄养机构主键集合
   * @return 结果
   */
  public int deletePetFosterInstitutionByIds(Long[] ids);

  /**
   * 删除宠物寄养机构信息
   *
   * @param id 宠物寄养机构主键
   * @return 结果
   */
  public int deletePetFosterInstitutionById(Long id);

  public Map getEchartsData(PetFosterInstitution petFosterInstitution);
}
