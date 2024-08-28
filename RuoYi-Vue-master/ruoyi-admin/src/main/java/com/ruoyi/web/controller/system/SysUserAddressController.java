package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.domain.entity.SysUserAddress;
import com.ruoyi.system.service.ISysUserAddressService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收货地址Controller
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/system/address")
public class SysUserAddressController extends BaseController
{
    @Autowired
    private ISysUserAddressService sysUserAddressService;

    /**
     * 查询收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('system:address:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserAddress sysUserAddress)
    {
        startPage();
        List<SysUserAddress> list = sysUserAddressService.selectSysUserAddressList(sysUserAddress);
        return getDataTable(list);
    }

    /**
     * 导出收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('system:address:export')")
    @Log(title = "收货地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserAddress sysUserAddress)
    {
        List<SysUserAddress> list = sysUserAddressService.selectSysUserAddressList(sysUserAddress);
        ExcelUtil<SysUserAddress> util = new ExcelUtil<SysUserAddress>(SysUserAddress.class);
        util.exportExcel(response, list, "收货地址数据");
    }

    /**
     * 获取收货地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:address:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysUserAddressService.selectSysUserAddressById(id));
    }

    /**
     * 新增收货地址
     */
    @PreAuthorize("@ss.hasPermi('system:address:add')")
    @Log(title = "收货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserAddress sysUserAddress)
    {
        return toAjax(sysUserAddressService.insertSysUserAddress(sysUserAddress));
    }

    /**
     * 修改收货地址
     */
    @PreAuthorize("@ss.hasPermi('system:address:edit')")
    @Log(title = "收货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserAddress sysUserAddress)
    {
        return toAjax(sysUserAddressService.updateSysUserAddress(sysUserAddress));
    }

    /**
     * 删除收货地址
     */
    @PreAuthorize("@ss.hasPermi('system:address:remove')")
    @Log(title = "收货地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysUserAddressService.deleteSysUserAddressByIds(ids));
    }
}
