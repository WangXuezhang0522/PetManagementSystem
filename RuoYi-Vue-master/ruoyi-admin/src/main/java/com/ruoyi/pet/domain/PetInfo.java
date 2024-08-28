package com.ruoyi.pet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物信息对象 pet_info
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public class PetInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private String age;

    /** 品种 */
    @Excel(name = "品种")
    private String breed;

    /** 健康状况 */
    @Excel(name = "健康状况")
    private String healthCondition;

    /** 生活习惯 */
    @Excel(name = "生活习惯")
    private String habitsAndCustoms;

    /** 创建者姓名 */
    @Excel(name = "创建者姓名")
    private String createByName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAge(String age)
    {
        this.age = age;
    }

    public String getAge()
    {
        return age;
    }
    public void setBreed(String breed) 
    {
        this.breed = breed;
    }

    public String getBreed() 
    {
        return breed;
    }
    public void setHealthCondition(String healthCondition) 
    {
        this.healthCondition = healthCondition;
    }

    public String getHealthCondition() 
    {
        return healthCondition;
    }
    public void setHabitsAndCustoms(String habitsAndCustoms) 
    {
        this.habitsAndCustoms = habitsAndCustoms;
    }

    public String getHabitsAndCustoms() 
    {
        return habitsAndCustoms;
    }
    public void setCreateByName(String createByName) 
    {
        this.createByName = createByName;
    }

    public String getCreateByName() 
    {
        return createByName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sex", getSex())
            .append("age", getAge())
            .append("breed", getBreed())
            .append("healthCondition", getHealthCondition())
            .append("habitsAndCustoms", getHabitsAndCustoms())
            .append("createBy", getCreateBy())
            .append("createByName", getCreateByName())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
