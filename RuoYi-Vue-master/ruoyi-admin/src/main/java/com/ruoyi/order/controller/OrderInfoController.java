package com.ruoyi.order.controller;

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
import com.ruoyi.order.domain.OrderInfo;
import com.ruoyi.order.service.IOrderInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/order/info")
public class OrderInfoController extends BaseController {
  @Autowired
  private IOrderInfoService orderInfoService;

  /**
   * 查询订单管理列表
   */
  @PreAuthorize("@ss.hasPermi('order:info:list')")
  @GetMapping("/list")
  public TableDataInfo list(OrderInfo orderInfo) {
    startPage();
    List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
    return getDataTable(list);
  }

  /**
   * 导出订单管理列表
   */
  @PreAuthorize("@ss.hasPermi('order:info:export')")
  @Log(title = "订单管理", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response, OrderInfo orderInfo) {
    List<OrderInfo> list = orderInfoService.selectOrderInfoList(orderInfo);
    ExcelUtil<OrderInfo> util = new ExcelUtil<OrderInfo>(OrderInfo.class);
    util.exportExcel(response, list, "订单管理数据");
  }

  /**
   * 获取订单管理详细信息
   */
  @PreAuthorize("@ss.hasPermi('order:info:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") Long id) {
    return success(orderInfoService.selectOrderInfoById(id));
  }

  /**
   * 新增订单管理
   */
  @PreAuthorize("@ss.hasPermi('order:info:add')")
  @Log(title = "订单管理", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody OrderInfo orderInfo) {
    return toAjax(orderInfoService.insertOrderInfo(orderInfo));
  }

  /**
   * 修改订单管理
   */
  @PreAuthorize("@ss.hasPermi('order:info:edit')")
  @Log(title = "订单管理", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@RequestBody OrderInfo orderInfo) {
    return toAjax(orderInfoService.updateOrderInfo(orderInfo));
  }

  /**
   * 删除订单管理
   */
  @PreAuthorize("@ss.hasPermi('order:info:remove')")
  @Log(title = "订单管理", businessType = BusinessType.DELETE)
  @DeleteMapping("/{ids}")
  public AjaxResult remove(@PathVariable Long[] ids) {
    return toAjax(orderInfoService.deleteOrderInfoByIds(ids));
  }

  /**
   * 生成订单
   */
  @PreAuthorize("@ss.hasPermi('order:info:order')")
  @GetMapping("/getGenerateOrder/{sysUserAddressId}/{ids}")
  public AjaxResult getGenerateOrder(@PathVariable("sysUserAddressId") Long sysUserAddressId, @PathVariable Long[] ids) {
    return toAjax(orderInfoService.getGenerateOrder(sysUserAddressId, ids));
  }
}
