package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysUserShoppingCart;

/**
 * 购物车Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public interface SysUserShoppingCartMapper 
{
    /**
     * 查询购物车
     * 
     * @param id 购物车主键
     * @return 购物车
     */
    public SysUserShoppingCart selectSysUserShoppingCartById(Long id);

    /**
     * 查询购物车列表
     * 
     * @param sysUserShoppingCart 购物车
     * @return 购物车集合
     */
    public List<SysUserShoppingCart> selectSysUserShoppingCartList(SysUserShoppingCart sysUserShoppingCart);

    /**
     * 新增购物车
     * 
     * @param sysUserShoppingCart 购物车
     * @return 结果
     */
    public int insertSysUserShoppingCart(SysUserShoppingCart sysUserShoppingCart);

    /**
     * 修改购物车
     * 
     * @param sysUserShoppingCart 购物车
     * @return 结果
     */
    public int updateSysUserShoppingCart(SysUserShoppingCart sysUserShoppingCart);

    /**
     * 删除购物车
     * 
     * @param id 购物车主键
     * @return 结果
     */
    public int deleteSysUserShoppingCartById(Long id);

    /**
     * 批量删除购物车
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserShoppingCartByIds(Long[] ids);
}
