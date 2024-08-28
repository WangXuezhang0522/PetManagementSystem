package com.ruoyi.pet.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物丢失对象 pet_lose
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public class PetLose extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 宠物名称 */
    @Excel(name = "宠物名称")
    private String name;

    /** 照片 */
    @Excel(name = "照片")
    private String img;

    /** 遗失地点 */
    @Excel(name = "遗失地点")
    private String adress;

    /** 遗失时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "遗失时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loseTime;

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
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setAdress(String adress) 
    {
        this.adress = adress;
    }

    public String getAdress() 
    {
        return adress;
    }
    public void setLoseTime(Date loseTime) 
    {
        this.loseTime = loseTime;
    }

    public Date getLoseTime() 
    {
        return loseTime;
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
            .append("img", getImg())
            .append("adress", getAdress())
            .append("loseTime", getLoseTime())
            .append("createBy", getCreateBy())
            .append("createByName", getCreateByName())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
