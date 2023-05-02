package com.example.dailyprojecttrip.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dailyprojecttrip.mapper.TripDriveMapper;
import com.example.dailyprojecttrip.domain.TripDrive;
import com.example.dailyprojecttrip.service.ITripDriveService;

/**
 * 行驶信息Service业务层处理
 * 
 * @author bxm
 * @date 2023-04-26
 */
@Service
public class TripDriveServiceImpl implements ITripDriveService 
{
    @Autowired
    private TripDriveMapper tripDriveMapper;

    /**
     * 查询行驶信息
     * 
     * @param driveId 行驶信息主键
     * @return 行驶信息
     */
    @Override
    public TripDrive selectTripDriveByDriveId(Long driveId)
    {
        return tripDriveMapper.selectTripDriveByDriveId(driveId);
    }

    /**
     * 查询行驶信息列表
     * 
     * @param tripDrive 行驶信息
     * @return 行驶信息
     */
    @Override
    public List<TripDrive> selectTripDriveList(TripDrive tripDrive)
    {
        return tripDriveMapper.selectTripDriveList(tripDrive);
    }

    /**
     * 新增行驶信息
     * 
     * @param tripDrive 行驶信息
     * @return 结果
     */
    @Override
    public int insertTripDrive(TripDrive tripDrive)
    {
        tripDrive.setCreateTime(DateUtils.getNowDate());
        return tripDriveMapper.insertTripDrive(tripDrive);
    }

    /**
     * 修改行驶信息
     * 
     * @param tripDrive 行驶信息
     * @return 结果
     */
    @Override
    public int updateTripDrive(TripDrive tripDrive)
    {
        tripDrive.setUpdateTime(DateUtils.getNowDate());
        return tripDriveMapper.updateTripDrive(tripDrive);
    }

    /**
     * 批量删除行驶信息
     * 
     * @param driveIds 需要删除的行驶信息主键
     * @return 结果
     */
    @Override
    public int deleteTripDriveByDriveIds(Long[] driveIds)
    {
        return tripDriveMapper.deleteTripDriveByDriveIds(driveIds);
    }

    /**
     * 删除行驶信息信息
     * 
     * @param driveId 行驶信息主键
     * @return 结果
     */
    @Override
    public int deleteTripDriveByDriveId(Long driveId)
    {
        return tripDriveMapper.deleteTripDriveByDriveId(driveId);
    }
}
