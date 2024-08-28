package com.ruoyi.pet.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pet.mapper.PetLogMapper;
import com.ruoyi.pet.domain.PetLog;
import com.ruoyi.pet.service.IPetLogService;

/**
 * 宠物日志Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class PetLogServiceImpl implements IPetLogService {
  @Autowired
  private PetLogMapper petLogMapper;

  /**
   * 查询宠物日志
   *
   * @param id 宠物日志主键
   * @return 宠物日志
   */
  @Override
  public PetLog selectPetLogById(Long id) {
    return petLogMapper.selectPetLogById(id);
  }

  /**
   * 查询宠物日志列表
   *
   * @param petLog 宠物日志
   * @return 宠物日志
   */
  @Override
  @DataScope(userAlias = "u")
  public List<PetLog> selectPetLogList(PetLog petLog) {
    return petLogMapper.selectPetLogList(petLog);
  }

  /**
   * 新增宠物日志
   *
   * @param petLog 宠物日志
   * @return 结果
   */
  @Override
  public int insertPetLog(PetLog petLog) {
    petLog.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    petLog.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    petLog.setCreateTime(DateUtils.getNowDate());
    return petLogMapper.insertPetLog(petLog);
  }

  /**
   * 修改宠物日志
   *
   * @param petLog 宠物日志
   * @return 结果
   */
  @Override
  public int updatePetLog(PetLog petLog) {
    petLog.setUpdateTime(DateUtils.getNowDate());
    return petLogMapper.updatePetLog(petLog);
  }

  /**
   * 批量删除宠物日志
   *
   * @param ids 需要删除的宠物日志主键
   * @return 结果
   */
  @Override
  public int deletePetLogByIds(Long[] ids) {
    return petLogMapper.deletePetLogByIds(ids);
  }

  /**
   * 删除宠物日志信息
   *
   * @param id 宠物日志主键
   * @return 结果
   */
  @Override
  public int deletePetLogById(Long id) {
    return petLogMapper.deletePetLogById(id);
  }
}
