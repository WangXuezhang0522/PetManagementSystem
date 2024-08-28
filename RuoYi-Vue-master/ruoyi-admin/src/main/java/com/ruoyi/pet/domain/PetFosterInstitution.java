package com.ruoyi.pet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物寄养机构对象 pet_foster_institution
 *
 * @author ruoyi
 * @date 2024-03-10
 */
public class PetFosterInstitution extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 编号
   */
  private Long id;

  /**
   * 机构名称
   */
  @Excel(name = "机构名称")
  private String name;

  /**
   * 联系人
   */
  @Excel(name = "联系人")
  private String contacts;

  /**
   * 手机号
   */
  @Excel(name = "手机号")
  private String phonenumber;

  /**
   * 经营范围
   */
  @Excel(name = "经营范围")
  private String businessScope;

  /**
   * 创建者姓名
   */
  @Excel(name = "创建者姓名")
  private String createByName;

  private Integer count;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
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

  public void setContacts(String contacts) {
    this.contacts = contacts;
  }

  public String getContacts() {
    return contacts;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setBusinessScope(String businessScope) {
    this.businessScope = businessScope;
  }

  public String getBusinessScope() {
    return businessScope;
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
      .append("contacts", getContacts())
      .append("phonenumber", getPhonenumber())
      .append("businessScope", getBusinessScope())
      .append("createBy", getCreateBy())
      .append("createByName", getCreateByName())
      .append("createTime", getCreateTime())
      .append("updateBy", getUpdateBy())
      .append("updateTime", getUpdateTime())
      .append("remark", getRemark())
      .toString();
  }
}
