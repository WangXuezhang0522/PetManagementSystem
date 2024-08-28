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
import com.ruoyi.store.domain.StorePetClassification;
import com.ruoyi.store.service.IStorePetClassificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物分类Controller
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/store/petClassification")
public class StorePetClassificationController extends BaseController {
  @Autowired
  private IStorePetClassificationService storePetClassificationService;

  /**
   * 查询宠物分类列表
   */
  @PreAuthorize("@ss.hasPermi('store:petClassification:list')")
  @GetMapping("/list")
  public TableDataInfo list(StorePetClassification storePetClassification) {
    startPage();
    List<StorePetClassification> list = storePetClassificationService.selectStorePetClassificationList(storePetClassification);
    return getDataTable(list);
  }

  /**
   * 查询宠物分类列表
   */
  @GetMapping("/listAll")
  public TableDataInfo listAll(StorePetClassification storePetClassification) {
    startPage();
    List<StorePetClassification> list = storePetClassificationService.selectStorePetClassificationListAll(storePetClassification);
    return getDataTable(list);
  }

  /**
   * 导出宠物分类列表
   */
  @PreAuthorize("@ss.hasPermi('store:petClassification:export')")
  @Log(title = "宠物分类", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response, StorePetClassification storePetClassification) {
    List<StorePetClassification> list = storePetClassificationService.selectStorePetClassificationList(storePetClassification);
    ExcelUtil<StorePetClassification> util = new ExcelUtil<StorePetClassification>(StorePetClassification.class);
    util.exportExcel(response, list, "宠物分类数据");
  }

  /**
   * 获取宠物分类详细信息
   */
  @PreAuthorize("@ss.hasPermi('store:petClassification:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") Long id) {
    return success(storePetClassificationService.selectStorePetClassificationById(id));
  }

  /**
   * 新增宠物分类
   */
  @PreAuthorize("@ss.hasPermi('store:petClassification:add')")
  @Log(title = "宠物分类", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StorePetClassification storePetClassification) {
    return toAjax(storePetClassificationService.insertStorePetClassification(storePetClassification));
  }

  /**
   * 修改宠物分类
   */
  @PreAuthorize("@ss.hasPermi('store:petClassification:edit')")
  @Log(title = "宠物分类", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@RequestBody StorePetClassification storePetClassification) {
    return toAjax(storePetClassificationService.updateStorePetClassification(storePetClassification));
  }

  /**
   * 删除宠物分类
   */
  @PreAuthorize("@ss.hasPermi('store:petClassification:remove')")
  @Log(title = "宠物分类", businessType = BusinessType.DELETE)
  @DeleteMapping("/{ids}")
  public AjaxResult remove(@PathVariable Long[] ids) {
    return toAjax(storePetClassificationService.deleteStorePetClassificationByIds(ids));
  }
}
