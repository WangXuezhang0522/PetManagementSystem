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
import com.ruoyi.store.domain.StoreCommodity;
import com.ruoyi.store.service.IStoreCommodityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/store/commodity")
public class StoreCommodityController extends BaseController
{
    @Autowired
    private IStoreCommodityService storeCommodityService;

    /**
     * 查询商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('store:commodity:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoreCommodity storeCommodity)
    {
        startPage();
        List<StoreCommodity> list = storeCommodityService.selectStoreCommodityList(storeCommodity);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('store:commodity:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreCommodity storeCommodity)
    {
        List<StoreCommodity> list = storeCommodityService.selectStoreCommodityList(storeCommodity);
        ExcelUtil<StoreCommodity> util = new ExcelUtil<StoreCommodity>(StoreCommodity.class);
        util.exportExcel(response, list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:commodity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(storeCommodityService.selectStoreCommodityById(id));
    }

    /**
     * 新增商品信息
     */
    @PreAuthorize("@ss.hasPermi('store:commodity:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreCommodity storeCommodity)
    {
        return toAjax(storeCommodityService.insertStoreCommodity(storeCommodity));
    }

    /**
     * 修改商品信息
     */
    @PreAuthorize("@ss.hasPermi('store:commodity:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreCommodity storeCommodity)
    {
        return toAjax(storeCommodityService.updateStoreCommodity(storeCommodity));
    }

    /**
     * 删除商品信息
     */
    @PreAuthorize("@ss.hasPermi('store:commodity:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storeCommodityService.deleteStoreCommodityByIds(ids));
    }
}
