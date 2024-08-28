package com.ruoyi.store.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物分类对象 store_pet_classification
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public class StorePetClassification extends BaseEntity {
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
   * 分类
   */
  @Excel(name = "分类")
  private String name;

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

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
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
      .append("name", getName())
      .append("createBy", getCreateBy())
      .append("createByName", getCreateByName())
      .append("createTime", getCreateTime())
      .append("updateBy", getUpdateBy())
      .append("updateTime", getUpdateTime())
      .append("remark", getRemark())
      .toString();
  }
}
