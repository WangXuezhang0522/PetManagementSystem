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
import com.ruoyi.store.domain.StoreProductClassification;
import com.ruoyi.store.service.IStoreProductClassificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品分类Controller
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/store/productClassification")
public class StoreProductClassificationController extends BaseController {
  @Autowired
  private IStoreProductClassificationService storeProductClassificationService;

  /**
   * 查询商品分类列表
   */
  @PreAuthorize("@ss.hasPermi('store:productClassification:list')")
  @GetMapping("/list")
  public TableDataInfo list(StoreProductClassification storeProductClassification) {
    startPage();
    List<StoreProductClassification> list = storeProductClassificationService.selectStoreProductClassificationList(storeProductClassification);
    return getDataTable(list);
  }


  /**
   * 查询商品分类列表
   */
  @GetMapping("/listAll")
  public TableDataInfo listAll(StoreProductClassification storeProductClassification) {
    startPage();
    List<StoreProductClassification> list = storeProductClassificationService.selectStoreProductClassificationListAll(storeProductClassification);
    return getDataTable(list);
  }

  /**
   * 导出商品分类列表
   */
  @PreAuthorize("@ss.hasPermi('store:productClassification:export')")
  @Log(title = "商品分类", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response, StoreProductClassification storeProductClassification) {
    List<StoreProductClassification> list = storeProductClassificationService.selectStoreProductClassificationList(storeProductClassification);
    ExcelUtil<StoreProductClassification> util = new ExcelUtil<StoreProductClassification>(StoreProductClassification.class);
    util.exportExcel(response, list, "商品分类数据");
  }

  /**
   * 获取商品分类详细信息
   */
  @PreAuthorize("@ss.hasPermi('store:productClassification:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") Long id) {
    return success(storeProductClassificationService.selectStoreProductClassificationById(id));
  }

  /**
   * 新增商品分类
   */
  @PreAuthorize("@ss.hasPermi('store:productClassification:add')")
  @Log(title = "商品分类", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StoreProductClassification storeProductClassification) {
    return toAjax(storeProductClassificationService.insertStoreProductClassification(storeProductClassification));
  }

  /**
   * 修改商品分类
   */
  @PreAuthorize("@ss.hasPermi('store:productClassification:edit')")
  @Log(title = "商品分类", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@RequestBody StoreProductClassification storeProductClassification) {
    return toAjax(storeProductClassificationService.updateStoreProductClassification(storeProductClassification));
  }

  /**
   * 删除商品分类
   */
  @PreAuthorize("@ss.hasPermi('store:productClassification:remove')")
  @Log(title = "商品分类", businessType = BusinessType.DELETE)
  @DeleteMapping("/{ids}")
  public AjaxResult remove(@PathVariable Long[] ids) {
    return toAjax(storeProductClassificationService.deleteStoreProductClassificationByIds(ids));
  }
}
