package com.ruoyi.pet.controller;

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
import com.ruoyi.pet.domain.PetInfo;
import com.ruoyi.pet.service.IPetInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物信息Controller
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/pet/info")
public class PetInfoController extends BaseController
{
    @Autowired
    private IPetInfoService petInfoService;

    /**
     * 查询宠物信息列表
     */
    @PreAuthorize("@ss.hasPermi('pet:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetInfo petInfo)
    {
        startPage();
        List<PetInfo> list = petInfoService.selectPetInfoList(petInfo);
        return getDataTable(list);
    }

    /**
     * 导出宠物信息列表
     */
    @PreAuthorize("@ss.hasPermi('pet:info:export')")
    @Log(title = "宠物信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetInfo petInfo)
    {
        List<PetInfo> list = petInfoService.selectPetInfoList(petInfo);
        ExcelUtil<PetInfo> util = new ExcelUtil<PetInfo>(PetInfo.class);
        util.exportExcel(response, list, "宠物信息数据");
    }

    /**
     * 获取宠物信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('pet:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(petInfoService.selectPetInfoById(id));
    }

    /**
     * 新增宠物信息
     */
    @PreAuthorize("@ss.hasPermi('pet:info:add')")
    @Log(title = "宠物信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetInfo petInfo)
    {
        return toAjax(petInfoService.insertPetInfo(petInfo));
    }

    /**
     * 修改宠物信息
     */
    @PreAuthorize("@ss.hasPermi('pet:info:edit')")
    @Log(title = "宠物信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetInfo petInfo)
    {
        return toAjax(petInfoService.updatePetInfo(petInfo));
    }

    /**
     * 删除宠物信息
     */
    @PreAuthorize("@ss.hasPermi('pet:info:remove')")
    @Log(title = "宠物信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petInfoService.deletePetInfoByIds(ids));
    }
}
