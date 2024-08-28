package com.ruoyi.store.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商店信息对象 store_info
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public class StoreInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 商店名称 */
    @Excel(name = "商店名称")
    private String name;

    /** 经营范围 */
    @Excel(name = "经营范围")
    private String businessScope;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phonenumber;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

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
    public void setBusinessScope(String businessScope) 
    {
        this.businessScope = businessScope;
    }

    public String getBusinessScope() 
    {
        return businessScope;
    }
    public void setContacts(String contacts) 
    {
        this.contacts = contacts;
    }

    public String getContacts() 
    {
        return contacts;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
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
            .append("businessScope", getBusinessScope())
            .append("contacts", getContacts())
            .append("phonenumber", getPhonenumber())
            .append("address", getAddress())
            .append("createBy", getCreateBy())
            .append("createByName", getCreateByName())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
