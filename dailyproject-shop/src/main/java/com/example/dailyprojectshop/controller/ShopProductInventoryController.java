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
import com.example.dailyprojectshop.domain.ShopProductInventory;
import com.example.dailyprojectshop.service.IShopProductInventoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品库存信息Controller
 * 
 * @author bxm
 * @date 2023-07-11
 */
@RestController
@RequestMapping("/shop/inventory")
public class ShopProductInventoryController extends BaseController
{
    @Autowired
    private IShopProductInventoryService shopProductInventoryService;

    /**
     * 查询商品库存信息列表
     */
    @PreAuthorize("@ss.hasPermi('shop:inventory:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopProductInventory shopProductInventory)
    {
        startPage();
        List<ShopProductInventory> list = shopProductInventoryService.selectShopProductInventoryList(shopProductInventory);
        return getDataTable(list);
    }

    /**
     * 导出商品库存信息列表
     */
    @PreAuthorize("@ss.hasPermi('shop:inventory:export')")
    @Log(title = "商品库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopProductInventory shopProductInventory)
    {
        List<ShopProductInventory> list = shopProductInventoryService.selectShopProductInventoryList(shopProductInventory);
        ExcelUtil<ShopProductInventory> util = new ExcelUtil<ShopProductInventory>(ShopProductInventory.class);
        util.exportExcel(response, list, "商品库存信息数据");
    }

    /**
     * 获取商品库存信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:inventory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopProductInventoryService.selectShopProductInventoryById(id));
    }

    /**
     * 新增商品库存信息
     */
    @PreAuthorize("@ss.hasPermi('shop:inventory:add')")
    @Log(title = "商品库存信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopProductInventory shopProductInventory)
    {
        return toAjax(shopProductInventoryService.insertShopProductInventory(shopProductInventory));
    }

    /**
     * 修改商品库存信息
     */
    @PreAuthorize("@ss.hasPermi('shop:inventory:edit')")
    @Log(title = "商品库存信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopProductInventory shopProductInventory)
    {
        return toAjax(shopProductInventoryService.updateShopProductInventory(shopProductInventory));
    }

    /**
     * 删除商品库存信息
     */
    @PreAuthorize("@ss.hasPermi('shop:inventory:remove')")
    @Log(title = "商品库存信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopProductInventoryService.deleteShopProductInventoryByIds(ids));
    }
}
