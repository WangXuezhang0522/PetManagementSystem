package com.ruoyi.common.core.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收货地址对象 sys_user_address
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
public class SysUserAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 收件人 */
    @Excel(name = "收件人")
    private String recipient;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phonenumber;

    /** 所在省 */
    @Excel(name = "所在省")
    private String province;

    /** 所在市 */
    @Excel(name = "所在市")
    private String city;

    /** 所在区 */
    @Excel(name = "所在区")
    private String area;

    /** 详细地址 */
    @Excel(name = "详细地址")
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
    public void setRecipient(String recipient) 
    {
        this.recipient = recipient;
    }

    public String getRecipient() 
    {
        return recipient;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
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
            .append("recipient", getRecipient())
            .append("phonenumber", getPhonenumber())
            .append("province", getProvince())
            .append("city", getCity())
            .append("area", getArea())
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
