package com.ruoyi.pet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物寄养申请对象 pet_foster_application
 *
 * @author ruoyi
 * @date 2024-03-10
 */
public class PetFosterApplication extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 编号
   */
  private Long id;

  /**
   * 机构id
   */
  @Excel(name = "机构id")
  private Long institutionId;
  private String institutionName;

  /**
   * 姓名
   */
  @Excel(name = "姓名")
  private String name;

  /**
   * 性别
   */
  @Excel(name = "性别")
  private String sex;

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
   * 邮箱
   */
  @Excel(name = "邮箱")
  private String email;

  /**
   * 地址
   */
  @Excel(name = "地址")
  private String address;

  /**
   * 审核状态（0待审核,1审核成功,2审核失败）
   */
  @Excel(name = "审核状态", readConverterExp = "0=待审核,1审核成功,2审核失败")
  private String state;

  /**
   * 创建者姓名
   */
  @Excel(name = "创建者姓名")
  private String createByName;

  public String getInstitutionName() {
    return institutionName;
  }

  public void setInstitutionName(String institutionName) {
    this.institutionName = institutionName;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setInstitutionId(Long institutionId) {
    this.institutionId = institutionId;
  }

  public Long getInstitutionId() {
    return institutionId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getSex() {
    return sex;
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

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
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
      .append("institutionId", getInstitutionId())
      .append("name", getName())
      .append("sex", getSex())
      .append("contacts", getContacts())
      .append("phonenumber", getPhonenumber())
      .append("email", getEmail())
      .append("address", getAddress())
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
