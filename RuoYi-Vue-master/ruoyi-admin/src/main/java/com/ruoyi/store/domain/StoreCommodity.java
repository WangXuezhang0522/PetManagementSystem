package com.ruoyi.store.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品信息对象 store_commodity
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public class StoreCommodity extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 编号
   */
  private Long id;

  /**
   * 商店id
   */
  private Long storeInfoId;

  /**
   * 商店名称
   */
  private String storeInfoName;

  /**
   * 商品名称
   */
  @Excel(name = "商品名称")
  private String name;

  /**
   * 价格
   */
  @Excel(name = "价格")
  private BigDecimal price;

  /**
   * 类型（0商品 1宠物）
   */
  @Excel(name = "类型", readConverterExp = "0=商品,1=宠物")
  private String type;

  /**
   * 类别
   */
  @Excel(name = "类别")
  private String category;

  /**
   * 图片
   */
  @Excel(name = "图片")
  private String img;

  /**
   * 品牌
   */
  @Excel(name = "品牌")
  private String brand;

  /**
   * 规格
   */
  @Excel(name = "规格")
  private String specifications;

  /**
   * 保质期
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Excel(name = "保质期", width = 30, dateFormat = "yyyy-MM-dd")
  private Date bzqTime;

  /**
   * 生产日期
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
  private Date scrqTime;

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

  public String getStoreInfoName() {
    return storeInfoName;
  }

  public void setStoreInfoName(String storeInfoName) {
    this.storeInfoName = storeInfoName;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCategory() {
    return category;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getImg() {
    return img;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getBrand() {
    return brand;
  }

  public void setSpecifications(String specifications) {
    this.specifications = specifications;
  }

  public String getSpecifications() {
    return specifications;
  }

  public void setBzqTime(Date bzqTime) {
    this.bzqTime = bzqTime;
  }

  public Date getBzqTime() {
    return bzqTime;
  }

  public void setScrqTime(Date scrqTime) {
    this.scrqTime = scrqTime;
  }

  public Date getScrqTime() {
    return scrqTime;
  }

  public void setCreateByName(String createByName) {
    this.createByName = createByName;
  }

  public String getCreateByName() {
    return createByName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
      .append("id", getId())
      .append("price", getPrice())
      .append("type", getType())
      .append("category", getCategory())
      .append("img", getImg())
      .append("brand", getBrand())
      .append("specifications", getSpecifications())
      .append("bzqTime", getBzqTime())
      .append("scrqTime", getScrqTime())
      .append("createBy", getCreateBy())
      .append("createByName", getCreateByName())
      .append("createTime", getCreateTime())
      .append("updateBy", getUpdateBy())
      .append("updateTime", getUpdateTime())
      .append("remark", getRemark())
      .toString();
  }
}
