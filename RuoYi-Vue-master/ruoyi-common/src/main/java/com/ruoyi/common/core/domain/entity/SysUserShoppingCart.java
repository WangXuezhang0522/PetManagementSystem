package com.ruoyi.common.core.domain.entity;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 购物车对象 sys_user_shopping_cart
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public class SysUserShoppingCart extends BaseEntity {
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
   * 商品id
   */
  @Excel(name = "商品id")
  private Long productId;

  /**
   * 商品名称
   */
  @Excel(name = "商品名称")
  private String productName;

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
   * 创建者姓名
   */
  @Excel(name = "创建者姓名")
  private String createByName;

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

  public void setCreateByName(String createByName) {
    this.createByName = createByName;
  }

  public String getCreateByName() {
    return createByName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
      .append("id", getId())
      .append("productId", getProductId())
      .append("number", getNumber())
      .append("unitPrice", getUnitPrice())
      .append("createBy", getCreateBy())
      .append("createByName", getCreateByName())
      .append("createTime", getCreateTime())
      .append("updateBy", getUpdateBy())
      .append("updateTime", getUpdateTime())
      .append("remark", getRemark())
      .toString();
  }
}
