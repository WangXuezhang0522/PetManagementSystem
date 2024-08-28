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
import com.ruoyi.pet.domain.PetLose;
import com.ruoyi.pet.service.IPetLoseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物丢失Controller
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/pet/lose")
public class PetLoseController extends BaseController
{
    @Autowired
    private IPetLoseService petLoseService;

    /**
     * 查询宠物丢失列表
     */
    @PreAuthorize("@ss.hasPermi('pet:lose:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetLose petLose)
    {
        startPage();
        List<PetLose> list = petLoseService.selectPetLoseList(petLose);
        return getDataTable(list);
    }

    /**
     * 导出宠物丢失列表
     */
    @PreAuthorize("@ss.hasPermi('pet:lose:export')")
    @Log(title = "宠物丢失", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetLose petLose)
    {
        List<PetLose> list = petLoseService.selectPetLoseList(petLose);
        ExcelUtil<PetLose> util = new ExcelUtil<PetLose>(PetLose.class);
        util.exportExcel(response, list, "宠物丢失数据");
    }

    /**
     * 获取宠物丢失详细信息
     */
    @PreAuthorize("@ss.hasPermi('pet:lose:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(petLoseService.selectPetLoseById(id));
    }

    /**
     * 新增宠物丢失
     */
    @PreAuthorize("@ss.hasPermi('pet:lose:add')")
    @Log(title = "宠物丢失", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetLose petLose)
    {
        return toAjax(petLoseService.insertPetLose(petLose));
    }

    /**
     * 修改宠物丢失
     */
    @PreAuthorize("@ss.hasPermi('pet:lose:edit')")
    @Log(title = "宠物丢失", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetLose petLose)
    {
        return toAjax(petLoseService.updatePetLose(petLose));
    }

    /**
     * 删除宠物丢失
     */
    @PreAuthorize("@ss.hasPermi('pet:lose:remove')")
    @Log(title = "宠物丢失", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petLoseService.deletePetLoseByIds(ids));
    }
}
