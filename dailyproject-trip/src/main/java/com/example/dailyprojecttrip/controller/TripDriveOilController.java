package com.example.dailyprojecttrip.controller;

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
import com.example.dailyprojecttrip.domain.TripDriveOil;
import com.example.dailyprojecttrip.service.ITripDriveOilService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 行驶加油信息Controller
 * 
 * @author bxm
 * @date 2023-05-01
 */
@RestController
@RequestMapping("/trip/oil")
public class TripDriveOilController extends BaseController
{
    @Autowired
    private ITripDriveOilService tripDriveOilService;

    /**
     * 查询行驶加油信息列表
     */
    @PreAuthorize("@ss.hasPermi('trip:oil:list')")
    @GetMapping("/list")
    public TableDataInfo list(TripDriveOil tripDriveOil)
    {
        startPage();
        List<TripDriveOil> list = tripDriveOilService.selectTripDriveOilList(tripDriveOil);
        return getDataTable(list);
    }

    /**
     * 导出行驶加油信息列表
     */
    @PreAuthorize("@ss.hasPermi('trip:oil:export')")
    @Log(title = "行驶加油信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TripDriveOil tripDriveOil)
    {
        List<TripDriveOil> list = tripDriveOilService.selectTripDriveOilList(tripDriveOil);
        ExcelUtil<TripDriveOil> util = new ExcelUtil<TripDriveOil>(TripDriveOil.class);
        util.exportExcel(response, list, "行驶加油信息数据");
    }

    /**
     * 获取行驶加油信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('trip:oil:query')")
    @GetMapping(value = "/{oilId}")
    public AjaxResult getInfo(@PathVariable("oilId") Long oilId)
    {
        return success(tripDriveOilService.selectTripDriveOilByOilId(oilId));
    }

    /**
     * 新增行驶加油信息
     */
    @PreAuthorize("@ss.hasPermi('trip:oil:add')")
    @Log(title = "行驶加油信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TripDriveOil tripDriveOil)
    {
        return toAjax(tripDriveOilService.insertTripDriveOil(tripDriveOil));
    }

    /**
     * 修改行驶加油信息
     */
    @PreAuthorize("@ss.hasPermi('trip:oil:edit')")
    @Log(title = "行驶加油信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TripDriveOil tripDriveOil)
    {
        return toAjax(tripDriveOilService.updateTripDriveOil(tripDriveOil));
    }

    /**
     * 删除行驶加油信息
     */
    @PreAuthorize("@ss.hasPermi('trip:oil:remove')")
    @Log(title = "行驶加油信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{oilIds}")
    public AjaxResult remove(@PathVariable Long[] oilIds)
    {
        return toAjax(tripDriveOilService.deleteTripDriveOilByOilIds(oilIds));
    }
}
