package com.ruoyi.pet.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pet.mapper.PetFosterApplicationMapper;
import com.ruoyi.pet.domain.PetFosterApplication;
import com.ruoyi.pet.service.IPetFosterApplicationService;

/**
 * 宠物寄养申请Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-10
 */
@Service
public class PetFosterApplicationServiceImpl implements IPetFosterApplicationService {
  @Autowired
  private PetFosterApplicationMapper petFosterApplicationMapper;

  /**
   * 查询宠物寄养申请
   *
   * @param id 宠物寄养申请主键
   * @return 宠物寄养申请
   */
  @Override
  public PetFosterApplication selectPetFosterApplicationById(Long id) {
    return petFosterApplicationMapper.selectPetFosterApplicationById(id);
  }

  /**
   * 查询宠物寄养申请列表
   *
   * @param petFosterApplication 宠物寄养申请
   * @return 宠物寄养申请
   */
  @Override
  @DataScope(userAlias = "u")
  public List<PetFosterApplication> selectPetFosterApplicationList(PetFosterApplication petFosterApplication) {
    return petFosterApplicationMapper.selectPetFosterApplicationList(petFosterApplication);
  }

  /**
   * 新增宠物寄养申请
   *
   * @param petFosterApplication 宠物寄养申请
   * @return 结果
   */
  @Override
  public int insertPetFosterApplication(PetFosterApplication petFosterApplication) {
    petFosterApplication.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    petFosterApplication.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    petFosterApplication.setCreateTime(DateUtils.getNowDate());
    return petFosterApplicationMapper.insertPetFosterApplication(petFosterApplication);
  }

  /**
   * 修改宠物寄养申请
   *
   * @param petFosterApplication 宠物寄养申请
   * @return 结果
   */
  @Override
  public int updatePetFosterApplication(PetFosterApplication petFosterApplication) {
    petFosterApplication.setUpdateTime(DateUtils.getNowDate());
    return petFosterApplicationMapper.updatePetFosterApplication(petFosterApplication);
  }

  @Override
  public int updateExamine(PetFosterApplication petFosterApplication) {
    return petFosterApplicationMapper.updatePetFosterApplication(petFosterApplication);
  }

  /**
   * 批量删除宠物寄养申请
   *
   * @param ids 需要删除的宠物寄养申请主键
   * @return 结果
   */
  @Override
  public int deletePetFosterApplicationByIds(Long[] ids) {
    return petFosterApplicationMapper.deletePetFosterApplicationByIds(ids);
  }

  /**
   * 删除宠物寄养申请信息
   *
   * @param id 宠物寄养申请主键
   * @return 结果
   */
  @Override
  public int deletePetFosterApplicationById(Long id) {
    return petFosterApplicationMapper.deletePetFosterApplicationById(id);
  }
}
