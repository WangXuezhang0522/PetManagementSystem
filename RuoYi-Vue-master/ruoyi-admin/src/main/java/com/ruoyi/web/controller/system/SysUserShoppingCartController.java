package com.ruoyi.web.controller.system;

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
import com.ruoyi.common.core.domain.entity.SysUserShoppingCart;
import com.ruoyi.system.service.ISysUserShoppingCartService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 购物车Controller
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/system/shoppingCart")
public class SysUserShoppingCartController extends BaseController {
  @Autowired
  private ISysUserShoppingCartService sysUserShoppingCartService;

  /**
   * 查询购物车列表
   */
  @PreAuthorize("@ss.hasPermi('system:shoppingCart:list')")
  @GetMapping("/list")
  public TableDataInfo list(SysUserShoppingCart sysUserShoppingCart) {
    startPage();
    List<SysUserShoppingCart> list = sysUserShoppingCartService.selectSysUserShoppingCartList(sysUserShoppingCart);
    return getDataTable(list);
  }

  /**
   * 导出购物车列表
   */
  @PreAuthorize("@ss.hasPermi('system:shoppingCart:export')")
  @Log(title = "购物车", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response, SysUserShoppingCart sysUserShoppingCart) {
    List<SysUserShoppingCart> list = sysUserShoppingCartService.selectSysUserShoppingCartList(sysUserShoppingCart);
    ExcelUtil<SysUserShoppingCart> util = new ExcelUtil<SysUserShoppingCart>(SysUserShoppingCart.class);
    util.exportExcel(response, list, "购物车数据");
  }

  /**
   * 获取购物车详细信息
   */
  @PreAuthorize("@ss.hasPermi('system:shoppingCart:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") Long id) {
    return success(sysUserShoppingCartService.selectSysUserShoppingCartById(id));
  }

  /**
   * 新增购物车
   */
  @PreAuthorize("@ss.hasPermi('system:shoppingCart:add')")
  @Log(title = "购物车", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody SysUserShoppingCart sysUserShoppingCart) {
    return toAjax(sysUserShoppingCartService.insertSysUserShoppingCart(sysUserShoppingCart));
  }

  /**
   * 修改购物车
   */
  @PreAuthorize("@ss.hasPermi('system:shoppingCart:edit')")
  @Log(title = "购物车", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@RequestBody SysUserShoppingCart sysUserShoppingCart) {
    return toAjax(sysUserShoppingCartService.updateSysUserShoppingCart(sysUserShoppingCart));
  }
}
