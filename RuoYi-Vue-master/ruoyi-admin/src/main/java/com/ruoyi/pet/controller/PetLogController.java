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
import com.ruoyi.pet.domain.PetLog;
import com.ruoyi.pet.service.IPetLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物日志Controller
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/pet/log")
public class PetLogController extends BaseController
{
    @Autowired
    private IPetLogService petLogService;

    /**
     * 查询宠物日志列表
     */
    @PreAuthorize("@ss.hasPermi('pet:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetLog petLog)
    {
        startPage();
        List<PetLog> list = petLogService.selectPetLogList(petLog);
        return getDataTable(list);
    }

    /**
     * 导出宠物日志列表
     */
    @PreAuthorize("@ss.hasPermi('pet:log:export')")
    @Log(title = "宠物日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetLog petLog)
    {
        List<PetLog> list = petLogService.selectPetLogList(petLog);
        ExcelUtil<PetLog> util = new ExcelUtil<PetLog>(PetLog.class);
        util.exportExcel(response, list, "宠物日志数据");
    }

    /**
     * 获取宠物日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('pet:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(petLogService.selectPetLogById(id));
    }

    /**
     * 新增宠物日志
     */
    @PreAuthorize("@ss.hasPermi('pet:log:add')")
    @Log(title = "宠物日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetLog petLog)
    {
        return toAjax(petLogService.insertPetLog(petLog));
    }

    /**
     * 修改宠物日志
     */
    @PreAuthorize("@ss.hasPermi('pet:log:edit')")
    @Log(title = "宠物日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetLog petLog)
    {
        return toAjax(petLogService.updatePetLog(petLog));
    }

    /**
     * 删除宠物日志
     */
    @PreAuthorize("@ss.hasPermi('pet:log:remove')")
    @Log(title = "宠物日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petLogService.deletePetLogByIds(ids));
    }
}
