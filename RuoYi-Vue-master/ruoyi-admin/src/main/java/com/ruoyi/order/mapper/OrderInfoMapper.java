package com.ruoyi.order.mapper;

import java.util.List;

import com.ruoyi.order.domain.OrderInfo;

/**
 * 订单管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public interface OrderInfoMapper {
  /**
   * 查询订单管理
   *
   * @param id 订单管理主键
   * @return 订单管理
   */
  public OrderInfo selectOrderInfoById(Long id);

  /**
   * 查询订单管理列表
   *
   * @param orderInfo 订单管理
   * @return 订单管理集合
   */
  public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo);
  public List<OrderInfo> selectOrderInfoListSJ(OrderInfo orderInfo);

  /**
   * 新增订单管理
   *
   * @param orderInfo 订单管理
   * @return 结果
   */
  public int insertOrderInfo(OrderInfo orderInfo);

  /**
   * 修改订单管理
   *
   * @param orderInfo 订单管理
   * @return 结果
   */
  public int updateOrderInfo(OrderInfo orderInfo);

  /**
   * 删除订单管理
   *
   * @param id 订单管理主键
   * @return 结果
   */
  public int deleteOrderInfoById(Long id);

  /**
   * 批量删除订单管理
   *
   * @param ids 需要删除的数据主键集合
   * @return 结果
   */
  public int deleteOrderInfoByIds(Long[] ids);
}
