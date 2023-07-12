package com.example.dailyprojectshop.controller;

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
import com.example.dailyprojectshop.domain.ShopProductInfo;
import com.example.dailyprojectshop.service.IShopProductInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品历史信息Controller
 * 
 * @author bxm
 * @date 2023-05-25
 */
@RestController
@RequestMapping("/shop/product")
public class ShopProductInfoController extends BaseController
{
    @Autowired
    private IShopProductInfoService shopProductInfoService;

    /**
     * 查询商品历史信息列表
     */
    @PreAuthorize("@ss.hasPermi('shop:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopProductInfo shopProductInfo)
    {
        startPage();
        List<ShopProductInfo> list = shopProductInfoService.selectShopProductInfoList(shopProductInfo);
        return getDataTable(list);
    }

    /**
     * 导出商品历史信息列表
     */
    @PreAuthorize("@ss.hasPermi('shop:product:export')")
    @Log(title = "商品历史信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopProductInfo shopProductInfo)
    {
        List<ShopProductInfo> list = shopProductInfoService.selectShopProductInfoList(shopProductInfo);
        ExcelUtil<ShopProductInfo> util = new ExcelUtil<ShopProductInfo>(ShopProductInfo.class);
        util.exportExcel(response, list, "商品历史信息数据");
    }

    /**
     * 获取商品历史信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopProductInfoService.selectShopProductInfoById(id));
    }

    /**
     * 新增商品历史信息
     */
    @PreAuthorize("@ss.hasPermi('shop:product:add')")
    @Log(title = "商品历史信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopProductInfo shopProductInfo)
    {
        return toAjax(shopProductInfoService.insertShopProductInfo(shopProductInfo));
    }

    /**
     * 修改商品历史信息
     */
    @PreAuthorize("@ss.hasPermi('shop:product:edit')")
    @Log(title = "商品历史信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopProductInfo shopProductInfo)
    {
        return toAjax(shopProductInfoService.updateShopProductInfo(shopProductInfo));
    }

    /**
     * 删除商品历史信息
     */
    @PreAuthorize("@ss.hasPermi('shop:product:remove')")
    @Log(title = "商品历史信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopProductInfoService.deleteShopProductInfoByIds(ids));
    }
}
