package com.ruoyi.pet.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUserShoppingCart;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.pet.service.IPetStoreService;
import com.ruoyi.store.domain.StoreCommodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 宠物商店信息Controller
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/pet/store")
public class PetStoreController extends BaseController {
  @Autowired
  private IPetStoreService petStoreService;

  /**
   * 查询商品信息列表
   */
  @PreAuthorize("@ss.hasPermi('pet:store:list')")
  @GetMapping("/list")
  public TableDataInfo list(StoreCommodity storeCommodity) {
    startPage();
    List<StoreCommodity> list = petStoreService.selectStoreCommodityList(storeCommodity);
    return getDataTable(list);
  }

  /**
   * 获取商品信息详细信息
   */
  @PreAuthorize("@ss.hasPermi('pet:store:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") Long id) {
    return success(petStoreService.selectStoreCommodityById(id));
  }

  /**
   * 新增商品信息
   */
  @PreAuthorize("@ss.hasPermi('pet:store:add')")
  @Log(title = "加入购物车", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody SysUserShoppingCart sysUserShoppingCart) {
    return toAjax(petStoreService.insertSysUserShoppingCart(sysUserShoppingCart));
  }

}
