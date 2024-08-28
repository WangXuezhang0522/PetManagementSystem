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
import com.ruoyi.pet.domain.PetAdoptApplication;
import com.ruoyi.pet.service.IPetAdoptApplicationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物领养申请Controller
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/pet/application")
public class PetAdoptApplicationController extends BaseController {
  @Autowired
  private IPetAdoptApplicationService petAdoptApplicationService;

  /**
   * 查询宠物领养申请列表
   */
  @PreAuthorize("@ss.hasPermi('pet:application:list')")
  @GetMapping("/list")
  public TableDataInfo list(PetAdoptApplication petAdoptApplication) {
    startPage();
    List<PetAdoptApplication> list = petAdoptApplicationService.selectPetAdoptApplicationList(petAdoptApplication);
    return getDataTable(list);
  }

  /**
   * 导出宠物领养申请列表
   */
  @PreAuthorize("@ss.hasPermi('pet:application:export')")
  @Log(title = "宠物领养申请", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response, PetAdoptApplication petAdoptApplication) {
    List<PetAdoptApplication> list = petAdoptApplicationService.selectPetAdoptApplicationList(petAdoptApplication);
    ExcelUtil<PetAdoptApplication> util = new ExcelUtil<PetAdoptApplication>(PetAdoptApplication.class);
    util.exportExcel(response, list, "宠物领养申请数据");
  }

  /**
   * 获取宠物领养申请详细信息
   */
  @PreAuthorize("@ss.hasPermi('pet:application:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") Long id) {
    return success(petAdoptApplicationService.selectPetAdoptApplicationById(id));
  }

  /**
   * 新增宠物领养申请
   */
  @PreAuthorize("@ss.hasPermi('pet:application:add')")
  @Log(title = "宠物领养申请", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody PetAdoptApplication petAdoptApplication) {
    return toAjax(petAdoptApplicationService.insertPetAdoptApplication(petAdoptApplication));
  }

  /**
   * 修改宠物领养申请
   */
  @PreAuthorize("@ss.hasPermi('pet:application:edit')")
  @Log(title = "宠物领养申请", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@RequestBody PetAdoptApplication petAdoptApplication) {
    return toAjax(petAdoptApplicationService.updatePetAdoptApplication(petAdoptApplication));
  }

  /**
   * 删除宠物领养申请
   */
  @PreAuthorize("@ss.hasPermi('pet:application:remove')")
  @Log(title = "宠物领养申请", businessType = BusinessType.DELETE)
  @DeleteMapping("/{ids}")
  public AjaxResult remove(@PathVariable Long[] ids) {
    return toAjax(petAdoptApplicationService.deletePetAdoptApplicationByIds(ids));
  }

  /**
   * 宠物领养申请审核
   */
  @PreAuthorize("@ss.hasPermi('pet:application:examine')")
  @Log(title = "宠物领养申请审核", businessType = BusinessType.UPDATE)
  @PostMapping("/editExamine")
  public AjaxResult editExamine(@RequestBody PetAdoptApplication petAdoptApplication) {
    return toAjax(petAdoptApplicationService.updateExamine(petAdoptApplication));
  }

  /**
   * 宠物领养支付
   */
  @PreAuthorize("@ss.hasPermi('pet:application:payment')")
  @Log(title = "宠物领养支付", businessType = BusinessType.UPDATE)
  @GetMapping("/payment/{id}")
  public AjaxResult payment(@PathVariable("id") Long id) {
    return toAjax(petAdoptApplicationService.payment(id));
  }
}
