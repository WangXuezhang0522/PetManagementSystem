package com.ruoyi.pet.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物领养对象 pet_adopt
 *
 * @author ruoyi
 * @date 2024-03-08
 */
public class PetAdopt extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 编号
   */
  private Long id;

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
   * 类别
   */
  @Excel(name = "类别")
  private String category;

  /**
   * 照片
   */
  @Excel(name = "照片")
  private String img;

  /**
   * 年龄
   */
  @Excel(name = "年龄")
  private String age;

  /**
   * 性情
   */
  @Excel(name = "性情")
  private String temperament;

  /**
   * 疫苗情况
   */
  @Excel(name = "疫苗情况")
  private String vaccinum;

  /**
   * 领养费用
   */
  @Excel(name = "领养费用")
  private BigDecimal adoptionExpenses;

  /**
   * 宠物状态（0未领养,1领养申请中,2已领养）
   */
  @Excel(name = "宠物状态", readConverterExp = "0=未领养,1领养申请中,2已领养")
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

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
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

  public void setAge(String age) {
    this.age = age;
  }

  public String getAge() {
    return age;
  }

  public void setTemperament(String temperament) {
    this.temperament = temperament;
  }

  public String getTemperament() {
    return temperament;
  }

  public void setVaccinum(String vaccinum) {
    this.vaccinum = vaccinum;
  }

  public String getVaccinum() {
    return vaccinum;
  }

  public void setAdoptionExpenses(BigDecimal adoptionExpenses) {
    this.adoptionExpenses = adoptionExpenses;
  }

  public BigDecimal getAdoptionExpenses() {
    return adoptionExpenses;
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

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
      .append("id", getId())
      .append("name", getName())
      .append("category", getCategory())
      .append("img", getImg())
      .append("age", getAge())
      .append("temperament", getTemperament())
      .append("vaccinum", getVaccinum())
      .append("adoptionExpenses", getAdoptionExpenses())
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
