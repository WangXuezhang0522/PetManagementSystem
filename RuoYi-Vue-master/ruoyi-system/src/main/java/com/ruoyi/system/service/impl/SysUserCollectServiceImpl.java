package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserCollectMapper;
import com.ruoyi.common.core.domain.entity.SysUserCollect;
import com.ruoyi.system.service.ISysUserCollectService;

/**
 * 收藏管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class SysUserCollectServiceImpl implements ISysUserCollectService {
  @Autowired
  private SysUserCollectMapper sysUserCollectMapper;

  /**
   * 查询收藏管理
   *
   * @param id 收藏管理主键
   * @return 收藏管理
   */
  @Override
  public SysUserCollect selectSysUserCollectById(Long id) {
    return sysUserCollectMapper.selectSysUserCollectById(id);
  }

  /**
   * 查询收藏管理列表
   *
   * @param sysUserCollect 收藏管理
   * @return 收藏管理
   */
  @Override
  @DataScope(userAlias = "u")
  public List<SysUserCollect> selectSysUserCollectList(SysUserCollect sysUserCollect) {
    return sysUserCollectMapper.selectSysUserCollectList(sysUserCollect);
  }

  /**
   * 新增收藏管理
   *
   * @param sysUserCollect 收藏管理
   * @return 结果
   */
  @Override
  public int insertSysUserCollect(SysUserCollect sysUserCollect) {
    sysUserCollect.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    sysUserCollect.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    sysUserCollect.setCreateTime(DateUtils.getNowDate());
    return sysUserCollectMapper.insertSysUserCollect(sysUserCollect);
  }

  /**
   * 修改收藏管理
   *
   * @param sysUserCollect 收藏管理
   * @return 结果
   */
  @Override
  public int updateSysUserCollect(SysUserCollect sysUserCollect) {
    sysUserCollect.setUpdateTime(DateUtils.getNowDate());
    return sysUserCollectMapper.updateSysUserCollect(sysUserCollect);
  }

  /**
   * 批量删除收藏管理
   *
   * @param ids 需要删除的收藏管理主键
   * @return 结果
   */
  @Override
  public int deleteSysUserCollectByIds(Long[] ids) {
    return sysUserCollectMapper.deleteSysUserCollectByIds(ids);
  }

  /**
   * 删除收藏管理信息
   *
   * @param id 收藏管理主键
   * @return 结果
   */
  @Override
  public int deleteSysUserCollectById(Long id) {
    return sysUserCollectMapper.deleteSysUserCollectById(id);
  }
}
