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
import com.ruoyi.pet.domain.PetFosterInstitution;
import com.ruoyi.pet.service.IPetFosterInstitutionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物寄养机构Controller
 *
 * @author ruoyi
 * @date 2024-03-10
 */
@RestController
@RequestMapping("/pet/fosterInstitution")
public class PetFosterInstitutionController extends BaseController {
  @Autowired
  private IPetFosterInstitutionService petFosterInstitutionService;

  /**
   * 查询宠物寄养机构列表
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterInstitution:list')")
  @GetMapping("/list")
  public TableDataInfo list(PetFosterInstitution petFosterInstitution) {
    startPage();
    List<PetFosterInstitution> list = petFosterInstitutionService.selectPetFosterInstitutionList(petFosterInstitution);
    return getDataTable(list);
  }

  /**
   * 导出宠物寄养机构列表
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterInstitution:export')")
  @Log(title = "宠物寄养机构", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response, PetFosterInstitution petFosterInstitution) {
    List<PetFosterInstitution> list = petFosterInstitutionService.selectPetFosterInstitutionList(petFosterInstitution);
    ExcelUtil<PetFosterInstitution> util = new ExcelUtil<PetFosterInstitution>(PetFosterInstitution.class);
    util.exportExcel(response, list, "宠物寄养机构数据");
  }

  /**
   * 获取宠物寄养机构详细信息
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterInstitution:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") Long id) {
    return success(petFosterInstitutionService.selectPetFosterInstitutionById(id));
  }

  /**
   * 新增宠物寄养机构
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterInstitution:add')")
  @Log(title = "宠物寄养机构", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody PetFosterInstitution petFosterInstitution) {
    return toAjax(petFosterInstitutionService.insertPetFosterInstitution(petFosterInstitution));
  }

  /**
   * 修改宠物寄养机构
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterInstitution:edit')")
  @Log(title = "宠物寄养机构", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@RequestBody PetFosterInstitution petFosterInstitution) {
    return toAjax(petFosterInstitutionService.updatePetFosterInstitution(petFosterInstitution));
  }

  /**
   * 删除宠物寄养机构
   */
  @PreAuthorize("@ss.hasPermi('pet:fosterInstitution:remove')")
  @Log(title = "宠物寄养机构", businessType = BusinessType.DELETE)
  @DeleteMapping("/{ids}")
  public AjaxResult remove(@PathVariable Long[] ids) {
    return toAjax(petFosterInstitutionService.deletePetFosterInstitutionByIds(ids));
  }

  /**
   * 查询寄养机构图表数据
   */
  @PostMapping("/getEchartsData")
  public AjaxResult getEchartsData(PetFosterInstitution petFosterInstitution) {
    return AjaxResult.success(petFosterInstitutionService.getEchartsData(petFosterInstitution));
  }
}
