package com.ruoyi.pet.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.pet.domain.PetAdopt;
import com.ruoyi.pet.domain.PetInfo;
import com.ruoyi.pet.service.IPetAdoptService;
import com.ruoyi.pet.service.IPetInfoService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pet.mapper.PetAdoptApplicationMapper;
import com.ruoyi.pet.domain.PetAdoptApplication;
import com.ruoyi.pet.service.IPetAdoptApplicationService;

/**
 * 宠物领养申请Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class PetAdoptApplicationServiceImpl implements IPetAdoptApplicationService {
  @Autowired
  private PetAdoptApplicationMapper petAdoptApplicationMapper;
  @Autowired
  private IPetAdoptService petAdoptService;
  @Autowired
  private IPetInfoService petInfoService;
  @Autowired
  private ISysUserService sysUserService;

  /**
   * 查询宠物领养申请
   *
   * @param id 宠物领养申请主键
   * @return 宠物领养申请
   */
  @Override
  public PetAdoptApplication selectPetAdoptApplicationById(Long id) {
    return petAdoptApplicationMapper.selectPetAdoptApplicationById(id);
  }

  /**
   * 查询宠物领养申请列表
   *
   * @param petAdoptApplication 宠物领养申请
   * @return 宠物领养申请
   */
  @Override
  @DataScope(userAlias = "u")
  public List<PetAdoptApplication> selectPetAdoptApplicationList(PetAdoptApplication petAdoptApplication) {
    return petAdoptApplicationMapper.selectPetAdoptApplicationList(petAdoptApplication);
  }

  /**
   * 新增宠物领养申请
   *
   * @param petAdoptApplication 宠物领养申请
   * @return 结果
   */
  @Override
  public int insertPetAdoptApplication(PetAdoptApplication petAdoptApplication) {
    petAdoptApplication.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    petAdoptApplication.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    petAdoptApplication.setCreateTime(DateUtils.getNowDate());
    if (petAdoptApplicationMapper.insertPetAdoptApplication(petAdoptApplication) > 0) {
      //将领养的宠物状态进行更改
      return petAdoptService.updatePetAdoptState(petAdoptApplication.getAdoptId(), "1");
    }
    return 0;
  }

  /**
   * 修改宠物领养申请
   *
   * @param petAdoptApplication 宠物领养申请
   * @return 结果
   */
  @Override
  public int updatePetAdoptApplication(PetAdoptApplication petAdoptApplication) {
    petAdoptApplication.setUpdateTime(DateUtils.getNowDate());
    return petAdoptApplicationMapper.updatePetAdoptApplication(petAdoptApplication);
  }

  @Override
  public int updateExamine(PetAdoptApplication petAdoptApplication) {
    if (petAdoptApplicationMapper.updatePetAdoptApplication(petAdoptApplication) > 0) {
      if (petAdoptApplication.getState().equals("1")) {
        //审核成功
        return petAdoptService.updatePetAdoptState(petAdoptApplication.getAdoptId(), "2");
      } else if (petAdoptApplication.getState().equals("2")) {
        //审核失败
        return petAdoptService.updatePetAdoptState(petAdoptApplication.getAdoptId(), "0");
      }
    }
    return 0;
  }

  @Override
  public int payment(Long id) {
    //获取领养宠物申请信息
    PetAdoptApplication petAdoptApplication = petAdoptApplicationMapper.selectPetAdoptApplicationById(id);
    //新增宠物信息
    PetAdopt petAdopt = petAdoptService.selectPetAdoptById(petAdoptApplication.getAdoptId());
    PetInfo petInfo = new PetInfo();
    petInfo.setName(petAdopt.getName());
    petInfo.setSex(petAdopt.getSex());
    petInfo.setAge(petAdopt.getAge());
    petInfo.setBreed(petAdopt.getCategory());
    petInfo.setHealthCondition(petAdopt.getVaccinum());
    petInfo.setCreateBy(petAdoptApplication.getCreateBy());
    petInfo.setCreateByName(petAdoptApplication.getCreateByName());
    petInfoService.insertPetInfo(petInfo);
    //扣除账户余额
    sysUserService.updateUserAccountBalance(new Long(petAdoptApplication.getCreateBy()), petAdoptApplication.getAdoptionExpenses());
    //支付成功
    petAdoptApplication.setPaymentStatus("1");
    return petAdoptApplicationMapper.updatePetAdoptApplication(petAdoptApplication);
  }

  /**
   * 批量删除宠物领养申请
   *
   * @param ids 需要删除的宠物领养申请主键
   * @return 结果
   */
  @Override
  public int deletePetAdoptApplicationByIds(Long[] ids) {
    return petAdoptApplicationMapper.deletePetAdoptApplicationByIds(ids);
  }

  /**
   * 删除宠物领养申请信息
   *
   * @param id 宠物领养申请主键
   * @return 结果
   */
  @Override
  public int deletePetAdoptApplicationById(Long id) {
    return petAdoptApplicationMapper.deletePetAdoptApplicationById(id);
  }
}
