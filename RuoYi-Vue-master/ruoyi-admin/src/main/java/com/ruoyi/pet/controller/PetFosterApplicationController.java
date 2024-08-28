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
import com.ruoyi.pet.domain.PetFosterApplication;
import com.ruoyi.pet.service.IPetFosterApplicationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物寄养申请Controller
 *
 * @author ruoyi
 * @date 2024-03-10
 */
@RestController
@RequestMapping("/pet/fosterApplication")
public class PetFosterApplicationController extends BaseController {
  @Autowired
  private IPetFosterApplicationService petFosterApplicationService;

  /**
   * 查询宠物寄养申请列表
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterApplication:list')")
  @GetMapping("/list")
  public TableDataInfo list(PetFosterApplication petFosterApplication) {
    startPage();
    List<PetFosterApplication> list = petFosterApplicationService.selectPetFosterApplicationList(petFosterApplication);
    return getDataTable(list);
  }

  /**
   * 导出宠物寄养申请列表
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterApplication:export')")
  @Log(title = "宠物寄养申请", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response, PetFosterApplication petFosterApplication) {
    List<PetFosterApplication> list = petFosterApplicationService.selectPetFosterApplicationList(petFosterApplication);
    ExcelUtil<PetFosterApplication> util = new ExcelUtil<PetFosterApplication>(PetFosterApplication.class);
    util.exportExcel(response, list, "宠物寄养申请数据");
  }

  /**
   * 获取宠物寄养申请详细信息
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterApplication:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") Long id) {
    return success(petFosterApplicationService.selectPetFosterApplicationById(id));
  }

  /**
   * 新增宠物寄养申请
   */
//  @PreAuthorize("@ss.hasPermi('pet:fosterApplication:add')")
  @Log(title = "宠物寄养申请", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody PetFosterApplication petFosterApplication) {
    return toAjax(petFosterApplicationService.insertPetFosterApplication(petFosterApplication));
  }

  /**
   * 修改宠物寄养申请
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterApplication:edit')")
  @Log(title = "宠物寄养申请", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@RequestBody PetFosterApplication petFosterApplication) {
    return toAjax(petFosterApplicationService.updatePetFosterApplication(petFosterApplication));
  }

  /**
   * 删除宠物寄养申请
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterApplication:remove')")
  @Log(title = "宠物寄养申请", businessType = BusinessType.DELETE)
  @DeleteMapping("/{ids}")
  public AjaxResult remove(@PathVariable Long[] ids) {
    return toAjax(petFosterApplicationService.deletePetFosterApplicationByIds(ids));
  }

  /**
   * 宠物领养申请审核
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterApplication:examine')")
  @Log(title = "宠物寄养申请审核", businessType = BusinessType.UPDATE)
  @PostMapping("/editExamine")
  public AjaxResult editExamine(@RequestBody PetFosterApplication petFosterApplication) {
    return toAjax(petFosterApplicationService.updateExamine(petFosterApplication));
  }

}
