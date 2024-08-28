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
import com.ruoyi.common.core.domain.entity.SysUserCollect;
import com.ruoyi.system.service.ISysUserCollectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收藏管理Controller
 *
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/system/collect")
public class SysUserCollectController extends BaseController {
  @Autowired
  private ISysUserCollectService sysUserCollectService;

  /**
   * 查询收藏管理列表
   */
  @PreAuthorize("@ss.hasPermi('system:collect:list')")
  @GetMapping("/list")
  public TableDataInfo list(SysUserCollect sysUserCollect) {
    startPage();
    List<SysUserCollect> list = sysUserCollectService.selectSysUserCollectList(sysUserCollect);
    return getDataTable(list);
  }

  /**
   * 导出收藏管理列表
   */
  @PreAuthorize("@ss.hasPermi('system:collect:export')")
  @Log(title = "收藏管理", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response, SysUserCollect sysUserCollect) {
    List<SysUserCollect> list = sysUserCollectService.selectSysUserCollectList(sysUserCollect);
    ExcelUtil<SysUserCollect> util = new ExcelUtil<SysUserCollect>(SysUserCollect.class);
    util.exportExcel(response, list, "收藏管理数据");
  }

  /**
   * 获取收藏管理详细信息
   */
  @PreAuthorize("@ss.hasPermi('system:collect:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") Long id) {
    return success(sysUserCollectService.selectSysUserCollectById(id));
  }

  /**
   * 新增收藏管理
   */
  @PreAuthorize("@ss.hasPermi('system:collect:add')")
  @Log(title = "收藏管理", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody SysUserCollect sysUserCollect) {
    return toAjax(sysUserCollectService.insertSysUserCollect(sysUserCollect));
  }

  /**
   * 修改收藏管理
   */
  @PreAuthorize("@ss.hasPermi('system:collect:edit')")
  @Log(title = "收藏管理", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@RequestBody SysUserCollect sysUserCollect) {
    return toAjax(sysUserCollectService.updateSysUserCollect(sysUserCollect));
  }

  /**
   * 删除收藏管理
   */
  @PreAuthorize("@ss.hasPermi('system:collect:remove')")
  @Log(title = "收藏管理", businessType = BusinessType.DELETE)
  @DeleteMapping("/{ids}")
  public AjaxResult remove(@PathVariable Long[] ids) {
    return toAjax(sysUserCollectService.deleteSysUserCollectByIds(ids));
  }
}
