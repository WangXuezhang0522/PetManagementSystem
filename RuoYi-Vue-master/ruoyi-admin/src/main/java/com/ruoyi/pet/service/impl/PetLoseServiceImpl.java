package com.ruoyi.pet.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pet.mapper.PetLoseMapper;
import com.ruoyi.pet.domain.PetLose;
import com.ruoyi.pet.service.IPetLoseService;

/**
 * 宠物丢失Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class PetLoseServiceImpl implements IPetLoseService {
  @Autowired
  private PetLoseMapper petLoseMapper;

  /**
   * 查询宠物丢失
   *
   * @param id 宠物丢失主键
   * @return 宠物丢失
   */
  @Override
  public PetLose selectPetLoseById(Long id) {
    return petLoseMapper.selectPetLoseById(id);
  }

  /**
   * 查询宠物丢失列表
   *
   * @param petLose 宠物丢失
   * @return 宠物丢失
   */
  @Override
  @DataScope(userAlias = "u")
  public List<PetLose> selectPetLoseList(PetLose petLose) {
    return petLoseMapper.selectPetLoseList(petLose);
  }

  /**
   * 新增宠物丢失
   *
   * @param petLose 宠物丢失
   * @return 结果
   */
  @Override
  public int insertPetLose(PetLose petLose) {
    petLose.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    petLose.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    petLose.setCreateTime(DateUtils.getNowDate());
    return petLoseMapper.insertPetLose(petLose);
  }

  /**
   * 修改宠物丢失
   *
   * @param petLose 宠物丢失
   * @return 结果
   */
  @Override
  public int updatePetLose(PetLose petLose) {
    petLose.setUpdateTime(DateUtils.getNowDate());
    return petLoseMapper.updatePetLose(petLose);
  }

  /**
   * 批量删除宠物丢失
   *
   * @param ids 需要删除的宠物丢失主键
   * @return 结果
   */
  @Override
  public int deletePetLoseByIds(Long[] ids) {
    return petLoseMapper.deletePetLoseByIds(ids);
  }

  /**
   * 删除宠物丢失信息
   *
   * @param id 宠物丢失主键
   * @return 结果
   */
  @Override
  public int deletePetLoseById(Long id) {
    return petLoseMapper.deletePetLoseById(id);
  }
}
