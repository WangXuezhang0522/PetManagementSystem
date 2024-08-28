package com.ruoyi.store.controller;

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
import com.ruoyi.store.domain.StoreInfo;
import com.ruoyi.store.service.IStoreInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商店信息Controller
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/store/info")
public class StoreInfoController extends BaseController
{
    @Autowired
    private IStoreInfoService storeInfoService;

    /**
     * 查询商店信息列表
     */
    @PreAuthorize("@ss.hasPermi('store:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoreInfo storeInfo)
    {
        startPage();
        List<StoreInfo> list = storeInfoService.selectStoreInfoList(storeInfo);
        return getDataTable(list);
    }

    /**
     * 导出商店信息列表
     */
    @PreAuthorize("@ss.hasPermi('store:info:export')")
    @Log(title = "商店信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreInfo storeInfo)
    {
        List<StoreInfo> list = storeInfoService.selectStoreInfoList(storeInfo);
        ExcelUtil<StoreInfo> util = new ExcelUtil<StoreInfo>(StoreInfo.class);
        util.exportExcel(response, list, "商店信息数据");
    }

    /**
     * 获取商店信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(storeInfoService.selectStoreInfoById(id));
    }

    /**
     * 新增商店信息
     */
    @PreAuthorize("@ss.hasPermi('store:info:add')")
    @Log(title = "商店信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreInfo storeInfo)
    {
        return toAjax(storeInfoService.insertStoreInfo(storeInfo));
    }

    /**
     * 修改商店信息
     */
    @PreAuthorize("@ss.hasPermi('store:info:edit')")
    @Log(title = "商店信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreInfo storeInfo)
    {
        return toAjax(storeInfoService.updateStoreInfo(storeInfo));
    }

    /**
     * 删除商店信息
     */
    @PreAuthorize("@ss.hasPermi('store:info:remove')")
    @Log(title = "商店信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storeInfoService.deleteStoreInfoByIds(ids));
    }
}
