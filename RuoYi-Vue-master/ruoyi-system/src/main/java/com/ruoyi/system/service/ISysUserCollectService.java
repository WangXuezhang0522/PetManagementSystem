package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysUserCollect;

/**
 * 收藏管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface ISysUserCollectService 
{
    /**
     * 查询收藏管理
     * 
     * @param id 收藏管理主键
     * @return 收藏管理
     */
    public SysUserCollect selectSysUserCollectById(Long id);

    /**
     * 查询收藏管理列表
     * 
     * @param sysUserCollect 收藏管理
     * @return 收藏管理集合
     */
    public List<SysUserCollect> selectSysUserCollectList(SysUserCollect sysUserCollect);

    /**
     * 新增收藏管理
     * 
     * @param sysUserCollect 收藏管理
     * @return 结果
     */
    public int insertSysUserCollect(SysUserCollect sysUserCollect);

    /**
     * 修改收藏管理
     * 
     * @param sysUserCollect 收藏管理
     * @return 结果
     */
    public int updateSysUserCollect(SysUserCollect sysUserCollect);

    /**
     * 批量删除收藏管理
     * 
     * @param ids 需要删除的收藏管理主键集合
     * @return 结果
     */
    public int deleteSysUserCollectByIds(Long[] ids);

    /**
     * 删除收藏管理信息
     * 
     * @param id 收藏管理主键
     * @return 结果
     */
    public int deleteSysUserCollectById(Long id);
}
