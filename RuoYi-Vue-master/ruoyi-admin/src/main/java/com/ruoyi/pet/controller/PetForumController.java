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
import com.ruoyi.pet.domain.PetForum;
import com.ruoyi.pet.service.IPetForumService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 宠物论坛Controller
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/pet/forum")
public class PetForumController extends BaseController
{
    @Autowired
    private IPetForumService petForumService;

    /**
     * 查询宠物论坛列表
     */
    @PreAuthorize("@ss.hasPermi('pet:forum:list')")
    @GetMapping("/list")
    public AjaxResult list(PetForum petForum)
    {
        List<PetForum> list = petForumService.selectPetForumList(petForum);
        return success(list);
    }

    /**
     * 导出宠物论坛列表
     */
    @PreAuthorize("@ss.hasPermi('pet:forum:export')")
    @Log(title = "宠物论坛", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetForum petForum)
    {
        List<PetForum> list = petForumService.selectPetForumList(petForum);
        ExcelUtil<PetForum> util = new ExcelUtil<PetForum>(PetForum.class);
        util.exportExcel(response, list, "宠物论坛数据");
    }

    /**
     * 获取宠物论坛详细信息
     */
    @PreAuthorize("@ss.hasPermi('pet:forum:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(petForumService.selectPetForumById(id));
    }

    /**
     * 新增宠物论坛
     */
    @PreAuthorize("@ss.hasPermi('pet:forum:add')")
    @Log(title = "宠物论坛", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetForum petForum)
    {
        return toAjax(petForumService.insertPetForum(petForum));
    }

    /**
     * 修改宠物论坛
     */
    @PreAuthorize("@ss.hasPermi('pet:forum:edit')")
    @Log(title = "宠物论坛", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetForum petForum)
    {
        return toAjax(petForumService.updatePetForum(petForum));
    }

    /**
     * 删除宠物论坛
     */
    @PreAuthorize("@ss.hasPermi('pet:forum:remove')")
    @Log(title = "宠物论坛", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petForumService.deletePetForumByIds(ids));
    }
}
