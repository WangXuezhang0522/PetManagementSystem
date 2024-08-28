package com.ruoyi.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.SysUserAddress;
import com.ruoyi.common.core.domain.entity.SysUserShoppingCart;
import com.ruoyi.store.domain.StoreCommodity;
import com.ruoyi.store.domain.StoreInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单管理对象 order_info
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public class OrderInfo extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 编号
   */
  private Long id;

  /**
   * 商家id
   */
  @Excel(name = "商家id")
  private Long storeInfoId;

  /**
   * 商家数据
   */
  private StoreInfo storeInfo;

  /**
   * 订单编号
   */
  @Excel(name = "订单编号")
  private String orderNo;

  /**
   * 收货地址id
   */
  @Excel(name = "收货地址id")
  private Long sysUserAddressId;

  /**
   * 收货地址数据
   */
  private SysUserAddress sysUserAddress;

  /**
   * 订单价格（无用删除的字段）
   */
  @Excel(name = "订单价格")
  private BigDecimal orderPrice;

  /**
   * 付款时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
  private Date fksjTime;

  /**
   * 商品id
   */
  @Excel(name = "商品id")
  private Long productId;

  /**
   * 商品数据
   */
  private StoreCommodity storeCommodity;

  /**
   * 数量
   */
  @Excel(name = "数量")
  private Long number;

  /**
   * 单价
   */
  @Excel(name = "单价")
  private BigDecimal unitPrice;

  /**
   * 价格
   */
  @Excel(name = "价格")
  private BigDecimal price;

  /**
   * 状态(1.待发货,2.已发货,3.已签收)
   */
  @Excel(name = "状态(1.待发货,2.已发货,3.已签收)")
  private String state;

  /**
   * 创建者姓名
   */
  @Excel(name = "创建者姓名")
  private String createByName;

  public StoreInfo getStoreInfo() {
    return storeInfo;
  }

  public void setStoreInfo(StoreInfo storeInfo) {
    this.storeInfo = storeInfo;
  }

  public SysUserAddress getSysUserAddress() {
    return sysUserAddress;
  }

  public void setSysUserAddress(SysUserAddress sysUserAddress) {
    this.sysUserAddress = sysUserAddress;
  }

  public StoreCommodity getStoreCommodity() {
    return storeCommodity;
  }

  public void setStoreCommodity(StoreCommodity storeCommodity) {
    this.storeCommodity = storeCommodity;
  }

  public Long getStoreInfoId() {
    return storeInfoId;
  }

  public void setStoreInfoId(Long storeInfoId) {
    this.storeInfoId = storeInfoId;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setSysUserAddressId(Long sysUserAddressId) {
    this.sysUserAddressId = sysUserAddressId;
  }

  public Long getSysUserAddressId() {
    return sysUserAddressId;
  }

  public void setOrderPrice(BigDecimal orderPrice) {
    this.orderPrice = orderPrice;
  }

  public BigDecimal getOrderPrice() {
    return orderPrice;
  }

  public void setFksjTime(Date fksjTime) {
    this.fksjTime = fksjTime;
  }

  public Date getFksjTime() {
    return fksjTime;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public Long getNumber() {
    return number;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }

  public void setCreateByName(String createByName) {
    this.createByName = createByName;
  }

  public String getCreateByName() {
    return createByName;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
      .append("id", getId())
      .append("orderNo", getOrderNo())
      .append("sysUserAddressId", getSysUserAddressId())
      .append("orderPrice", getOrderPrice())
      .append("fksjTime", getFksjTime())
      .append("productId", getProductId())
      .append("number", getNumber())
      .append("unitPrice", getUnitPrice())
      .append("price", getPrice())
      .append("state", getState())
      .append("createBy", getCreateBy())
      .append("createByName", getCreateByName())
      .append("createTime", getCreateTime())
      .append("updateBy", getUpdateBy())
      .append("updateTime", getUpdateTime())
      .append("remark", getRemark())
      .toString();
  }
}
