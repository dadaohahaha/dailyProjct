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
import com.example.dailyprojecttrip.domain.TripDrive;
import com.example.dailyprojecttrip.service.ITripDriveService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 行驶信息Controller
 * 
 * @author bxm
 * @date 2023-04-26
 */
@RestController
@RequestMapping("/trip/drive")
public class TripDriveController extends BaseController
{
    @Autowired
    private ITripDriveService tripDriveService;

    /**
     * 查询行驶信息列表
     */
    @PreAuthorize("@ss.hasPermi('trip:drive:list')")
    @GetMapping("/list")
    public TableDataInfo list(TripDrive tripDrive)
    {
        startPage();
        List<TripDrive> list = tripDriveService.selectTripDriveList(tripDrive);
        return getDataTable(list);
    }

    /**
     * 导出行驶信息列表
     */
    @PreAuthorize("@ss.hasPermi('trip:drive:export')")
    @Log(title = "行驶信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TripDrive tripDrive)
    {
        List<TripDrive> list = tripDriveService.selectTripDriveList(tripDrive);
        ExcelUtil<TripDrive> util = new ExcelUtil<TripDrive>(TripDrive.class);
        util.exportExcel(response, list, "行驶信息数据");
    }

    /**
     * 获取行驶信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('trip:drive:query')")
    @GetMapping(value = "/{driveId}")
    public AjaxResult getInfo(@PathVariable("driveId") Long driveId)
    {
        return success(tripDriveService.selectTripDriveByDriveId(driveId));
    }

    /**
     * 新增行驶信息
     */
    @PreAuthorize("@ss.hasPermi('trip:drive:add')")
    @Log(title = "行驶信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TripDrive tripDrive)
    {
        tripDrive.setCreateBy(getUsername());
        return toAjax(tripDriveService.insertTripDrive(tripDrive));
    }

    /**
     * 修改行驶信息
     */
    @PreAuthorize("@ss.hasPermi('trip:drive:edit')")
    @Log(title = "行驶信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TripDrive tripDrive)
    {
        tripDrive.setUpdateBy(getUsername());
        return toAjax(tripDriveService.updateTripDrive(tripDrive));
    }

    /**
     * 删除行驶信息
     */
    @PreAuthorize("@ss.hasPermi('trip:drive:remove')")
    @Log(title = "行驶信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{driveIds}")
    public AjaxResult remove(@PathVariable Long[] driveIds)
    {
        return toAjax(tripDriveService.deleteTripDriveByDriveIds(driveIds));
    }
}
