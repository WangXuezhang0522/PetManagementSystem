package com.ruoyi.pet.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mchange.lang.ShortUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pet.mapper.PetAdoptMapper;
import com.ruoyi.pet.domain.PetAdopt;
import com.ruoyi.pet.service.IPetAdoptService;

/**
 * 宠物领养Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class PetAdoptServiceImpl implements IPetAdoptService {
  @Autowired
  private PetAdoptMapper petAdoptMapper;

  /**
   * 查询宠物领养
   *
   * @param id 宠物领养主键
   * @return 宠物领养
   */
  @Override
  public PetAdopt selectPetAdoptById(Long id) {
    return petAdoptMapper.selectPetAdoptById(id);
  }

  /**
   * 查询宠物领养列表
   *
   * @param petAdopt 宠物领养
   * @return 宠物领养
   */
  @Override
  public List<PetAdopt> selectPetAdoptList(PetAdopt petAdopt) {
    return petAdoptMapper.selectPetAdoptList(petAdopt);
  }

  /**
   * 新增宠物领养
   *
   * @param petAdopt 宠物领养
   * @return 结果
   */
  @Override
  public int insertPetAdopt(PetAdopt petAdopt) {
    petAdopt.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    petAdopt.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    petAdopt.setCreateTime(DateUtils.getNowDate());
    return petAdoptMapper.insertPetAdopt(petAdopt);
  }

  /**
   * 修改宠物领养
   *
   * @param petAdopt 宠物领养
   * @return 结果
   */
  @Override
  public int updatePetAdopt(PetAdopt petAdopt) {
    petAdopt.setUpdateTime(DateUtils.getNowDate());
    return petAdoptMapper.updatePetAdopt(petAdopt);
  }

  @Override
  public int updatePetAdoptState(Long id, String state) {
    return petAdoptMapper.updatePetAdoptState(id, state);
  }

  /**
   * 批量删除宠物领养
   *
   * @param ids 需要删除的宠物领养主键
   * @return 结果
   */
  @Override
  public int deletePetAdoptByIds(Long[] ids) {
    return petAdoptMapper.deletePetAdoptByIds(ids);
  }

  /**
   * 删除宠物领养信息
   *
   * @param id 宠物领养主键
   * @return 结果
   */
  @Override
  public int deletePetAdoptById(Long id) {
    return petAdoptMapper.deletePetAdoptById(id);
  }

  @Override
  public Map getEchartsData(PetAdopt petAdopt) {
    String[] xData = {"未领养", "领养申请中", "领养支付中", "已领养"};
    petAdopt.setState("0");
    int value1 = petAdoptMapper.selectPetAdoptList(petAdopt).size();
    petAdopt.setState("1");
    int value2 = petAdoptMapper.selectPetAdoptList(petAdopt).size();
    petAdopt.setState("2");
    int value3 = petAdoptMapper.selectPetAdoptList(petAdopt).size();
    petAdopt.setState("3");
    int value4 = petAdoptMapper.selectPetAdoptList(petAdopt).size();
    int[] yData = {value1, value2, value3, value4};
    Map map = new HashMap();
    map.put("xData", xData);
    map.put("yData", yData);
    return map;
  }
}
