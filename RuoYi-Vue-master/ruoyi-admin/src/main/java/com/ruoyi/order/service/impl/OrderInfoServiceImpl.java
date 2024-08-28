package com.ruoyi.order.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.entity.SysUserShoppingCart;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.SysUserShoppingCartMapper;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.OrderInfoMapper;
import com.ruoyi.order.domain.OrderInfo;
import com.ruoyi.order.service.IOrderInfoService;

/**
 * 订单管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
  @Autowired
  private OrderInfoMapper orderInfoMapper;
  @Autowired
  private SysUserShoppingCartMapper sysUserShoppingCartMapper;
  @Autowired
  private SysUserRoleMapper sysUserRoleMapper;
  @Autowired
  private SysUserServiceImpl sysUserService;

  /**
   * 查询订单管理
   *
   * @param id 订单管理主键
   * @return 订单管理
   */
  @Override
  public OrderInfo selectOrderInfoById(Long id) {
    return orderInfoMapper.selectOrderInfoById(id);
  }

  /**
   * 查询订单管理列表
   *
   * @param orderInfo 订单管理
   * @return 订单管理
   */
  @Override
  @DataScope(userAlias = "u")
  public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo) {
    //获取当前登录人的角色
    SysUserRole sysUserRole = sysUserRoleMapper.selectRoleByUserId(SecurityUtils.getLoginUser().getUserId());
    Long roleId = sysUserRole.getRoleId();
    if (roleId.toString().equals("3")) {
      orderInfo.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
      return orderInfoMapper.selectOrderInfoListSJ(orderInfo);
    } else {
      return orderInfoMapper.selectOrderInfoList(orderInfo);
    }
  }

  /**
   * 新增订单管理
   *
   * @param orderInfo 订单管理
   * @return 结果
   */
  @Override
  public int insertOrderInfo(OrderInfo orderInfo) {
    orderInfo.setCreateBy(SecurityUtils.getLoginUser().getUserId().toString());
    orderInfo.setCreateByName(SecurityUtils.getLoginUser().getUser().getNickName());
    orderInfo.setCreateTime(DateUtils.getNowDate());
    return orderInfoMapper.insertOrderInfo(orderInfo);
  }

  /**
   * 修改订单管理
   *
   * @param orderInfo 订单管理
   * @return 结果
   */
  @Override
  public int updateOrderInfo(OrderInfo orderInfo) {
    orderInfo.setUpdateTime(DateUtils.getNowDate());
    return orderInfoMapper.updateOrderInfo(orderInfo);
  }

  /**
   * 批量删除订单管理
   *
   * @param ids 需要删除的订单管理主键
   * @return 结果
   */
  @Override
  public int deleteOrderInfoByIds(Long[] ids) {
    return orderInfoMapper.deleteOrderInfoByIds(ids);
  }

  /**
   * 删除订单管理信息
   *
   * @param id 订单管理主键
   * @return 结果
   */
  @Override
  public int deleteOrderInfoById(Long id) {
    return orderInfoMapper.deleteOrderInfoById(id);
  }

  @Override
  public int getGenerateOrder(Long sysUserAddressId, Long[] ids) {
    String orderNo = System.currentTimeMillis() + "";
    BigDecimal hj = new BigDecimal('0');
    for (Long id : ids) {
      OrderInfo orderInfo = new OrderInfo();
      orderInfo.setOrderNo(orderNo);
      orderInfo.setSysUserAddressId(sysUserAddressId);
      SysUserShoppingCart item = sysUserShoppingCartMapper.selectSysUserShoppingCartById(id);
      orderInfo.setFksjTime(new Date());
      orderInfo.setProductId(item.getProductId());
      orderInfo.setNumber(item.getNumber());
      orderInfo.setUnitPrice(item.getUnitPrice());
      BigDecimal price = new BigDecimal(item.getNumber()).multiply(item.getUnitPrice());
      hj = hj.add(price);
      orderInfo.setPrice(price);
      orderInfo.setState("1");
      orderInfo.setStoreInfoId(item.getStoreInfoId());
      insertOrderInfo(orderInfo);
    }
    //删除购物车
    sysUserShoppingCartMapper.deleteSysUserShoppingCartByIds(ids);
    //扣除账户余额
    return sysUserService.updateUserAccountBalance(SecurityUtils.getLoginUser().getUserId(), hj);
  }
}
