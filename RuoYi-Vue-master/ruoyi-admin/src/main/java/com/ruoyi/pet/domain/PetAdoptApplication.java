package com.ruoyi.pet.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物领养申请对象 pet_adopt_application
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public class PetAdoptApplication extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 编码
   */
  private Long id;

  /**
   * 领养宠物id
   */
  @Excel(name = "领养宠物id")
  private Long adoptId;
  private PetAdopt petAdopt;

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
   * 领养费用
   */
  @Excel(name = "领养费用")
  private BigDecimal adoptionExpenses;

  /**
   * 支付状态(0未支付,1已支付)
   */
  @Excel(name = "支付状态(0未支付,1已支付)")
  private String paymentStatus;

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

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setAdoptId(Long adoptId) {
    this.adoptId = adoptId;
  }

  public Long getAdoptId() {
    return adoptId;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSex() {
    return sex;
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

  public void setAdoptionExpenses(BigDecimal adoptionExpenses) {
    this.adoptionExpenses = adoptionExpenses;
  }

  public BigDecimal getAdoptionExpenses() {
    return adoptionExpenses;
  }

  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public String getPaymentStatus() {
    return paymentStatus;
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

  public PetAdopt getPetAdopt() {
    return petAdopt;
  }

  public void setPetAdopt(PetAdopt petAdopt) {
    this.petAdopt = petAdopt;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
      .append("id", getId())
      .append("adoptId", getAdoptId())
      .append("sex", getSex())
      .append("phonenumber", getPhonenumber())
      .append("email", getEmail())
      .append("address", getAddress())
      .append("adoptionExpenses", getAdoptionExpenses())
      .append("paymentStatus", getPaymentStatus())
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
