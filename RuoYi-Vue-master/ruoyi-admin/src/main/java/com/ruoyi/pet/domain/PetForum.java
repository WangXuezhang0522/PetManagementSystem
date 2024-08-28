package com.ruoyi.pet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 宠物论坛对象 pet_forum
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public class PetForum extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 父级id */
    @Excel(name = "父级id")
    private Long pid;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

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
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
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
            .append("pid", getPid())
            .append("title", getTitle())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createByName", getCreateByName())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
