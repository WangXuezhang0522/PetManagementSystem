package com.ruoyi.pet.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pet.mapper.PetFosterInstitutionMapper;
import com.ruoyi.pet.domain.PetFosterInstitution;
import com.ruoyi.pet.service.IPetFosterInstitutionService;

/**
 * 宠物寄养机构Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-10
 */
@Service
public class PetFosterInstitutionServiceImpl implements IPetFosterInstitutionService {
  @Autowired
  private PetFosterInstitutionMapper petFosterInstitutionMapper;

  /**
   * 查询宠物寄养机构
   *
   * @param id 宠物寄养机构主键
   * @return 宠物寄养机构
   */
  @Override
  public PetFosterInstitution selectPetFosterInstitutionById(Long id) {
    return petFosterInstitutionMapper.selectPetFosterInstitutionById(id);
  }

  /**
   * 查询宠物寄养机构列表
   *
   * @param petFosterInstitution 宠物寄养机构
   * @return 宠物寄养机构
   */
  @Override
  public List<PetFosterInstitution> selectPetFosterInstitutionList(PetFosterInstitution petFosterInstitution) {
    return petFosterInstitutionMapper.selectPetFosterInstitutionList(petFosterInstitution);
  }

  /**
   * 新增宠物寄养机构
   *
   * @param petFosterInstitution 宠物寄养机构
   * @return 结果
   */
  @Override
  public int insertPetFosterInstitution(PetFosterInstitution petFosterInstitution) {
    petFosterInstitution.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    petFosterInstitution.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    petFosterInstitution.setCreateTime(DateUtils.getNowDate());
    return petFosterInstitutionMapper.insertPetFosterInstitution(petFosterInstitution);
  }

  /**
   * 修改宠物寄养机构
   *
   * @param petFosterInstitution 宠物寄养机构
   * @return 结果
   */
  @Override
  public int updatePetFosterInstitution(PetFosterInstitution petFosterInstitution) {
    petFosterInstitution.setUpdateTime(DateUtils.getNowDate());
    return petFosterInstitutionMapper.updatePetFosterInstitution(petFosterInstitution);
  }

  /**
   * 批量删除宠物寄养机构
   *
   * @param ids 需要删除的宠物寄养机构主键
   * @return 结果
   */
  @Override
  public int deletePetFosterInstitutionByIds(Long[] ids) {
    return petFosterInstitutionMapper.deletePetFosterInstitutionByIds(ids);
  }

  /**
   * 删除宠物寄养机构信息
   *
   * @param id 宠物寄养机构主键
   * @return 结果
   */
  @Override
  public int deletePetFosterInstitutionById(Long id) {
    return petFosterInstitutionMapper.deletePetFosterInstitutionById(id);
  }

  @Override
  public Map getEchartsData(PetFosterInstitution petFosterInstitution) {
    List<PetFosterInstitution> list = petFosterInstitutionMapper.getEchartsData(petFosterInstitution);
    List xData = new ArrayList();
    List yData = new ArrayList();
    for (PetFosterInstitution item : list) {
      xData.add(item.getName());
      yData.add(item.getCount());
    }
    Map map = new HashMap();
    map.put("xData", xData);
    map.put("yData", yData);
    return map;
  }
}
