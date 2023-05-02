package com.example.dailyprojecttrip.service;

import java.util.List;
import com.example.dailyprojecttrip.domain.TripDrive;

/**
 * 行驶信息Service接口
 * 
 * @author bxm
 * @date 2023-04-26
 */
public interface ITripDriveService 
{
    /**
     * 查询行驶信息
     * 
     * @param driveId 行驶信息主键
     * @return 行驶信息
     */
    public TripDrive selectTripDriveByDriveId(Long driveId);

    /**
     * 查询行驶信息列表
     * 
     * @param tripDrive 行驶信息
     * @return 行驶信息集合
     */
    public List<TripDrive> selectTripDriveList(TripDrive tripDrive);

    /**
     * 新增行驶信息
     * 
     * @param tripDrive 行驶信息
     * @return 结果
     */
    public int insertTripDrive(TripDrive tripDrive);

    /**
     * 修改行驶信息
     * 
     * @param tripDrive 行驶信息
     * @return 结果
     */
    public int updateTripDrive(TripDrive tripDrive);

    /**
     * 批量删除行驶信息
     * 
     * @param driveIds 需要删除的行驶信息主键集合
     * @return 结果
     */
    public int deleteTripDriveByDriveIds(Long[] driveIds);

    /**
     * 删除行驶信息信息
     * 
     * @param driveId 行驶信息主键
     * @return 结果
     */
    public int deleteTripDriveByDriveId(Long driveId);
}
