package com.ruoyi.pet.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pet.mapper.PetInfoMapper;
import com.ruoyi.pet.domain.PetInfo;
import com.ruoyi.pet.service.IPetInfoService;

/**
 * 宠物信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class PetInfoServiceImpl implements IPetInfoService {
  @Autowired
  private PetInfoMapper petInfoMapper;

  /**
   * 查询宠物信息
   *
   * @param id 宠物信息主键
   * @return 宠物信息
   */
  @Override
  public PetInfo selectPetInfoById(Long id) {
    return petInfoMapper.selectPetInfoById(id);
  }

  /**
   * 查询宠物信息列表
   *
   * @param petInfo 宠物信息
   * @return 宠物信息
   */
  @Override
  @DataScope(userAlias = "u")
  public List<PetInfo> selectPetInfoList(PetInfo petInfo) {
    return petInfoMapper.selectPetInfoList(petInfo);
  }

  /**
   * 新增宠物信息
   *
   * @param petInfo 宠物信息
   * @return 结果
   */
  @Override
  public int insertPetInfo(PetInfo petInfo) {
    if (StringUtils.isEmpty(petInfo.getCreateBy())) {
      petInfo.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
      petInfo.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    }
    petInfo.setCreateTime(DateUtils.getNowDate());
    return petInfoMapper.insertPetInfo(petInfo);
  }

  /**
   * 修改宠物信息
   *
   * @param petInfo 宠物信息
   * @return 结果
   */
  @Override
  public int updatePetInfo(PetInfo petInfo) {
    petInfo.setUpdateTime(DateUtils.getNowDate());
    return petInfoMapper.updatePetInfo(petInfo);
  }

  /**
   * 批量删除宠物信息
   *
   * @param ids 需要删除的宠物信息主键
   * @return 结果
   */
  @Override
  public int deletePetInfoByIds(Long[] ids) {
    return petInfoMapper.deletePetInfoByIds(ids);
  }

  /**
   * 删除宠物信息信息
   *
   * @param id 宠物信息主键
   * @return 结果
   */
  @Override
  public int deletePetInfoById(Long id) {
    return petInfoMapper.deletePetInfoById(id);
  }
}
