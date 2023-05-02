package com.example.dailyprojecttrip.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dailyprojecttrip.mapper.TripDriveOilMapper;
import com.example.dailyprojecttrip.domain.TripDriveOil;
import com.example.dailyprojecttrip.service.ITripDriveOilService;

/**
 * 行驶加油信息Service业务层处理
 * 
 * @author bxm
 * @date 2023-05-01
 */
@Service
public class TripDriveOilServiceImpl implements ITripDriveOilService 
{
    @Autowired
    private TripDriveOilMapper tripDriveOilMapper;

    /**
     * 查询行驶加油信息
     * 
     * @param oilId 行驶加油信息主键
     * @return 行驶加油信息
     */
    @Override
    public TripDriveOil selectTripDriveOilByOilId(Long oilId)
    {
        return tripDriveOilMapper.selectTripDriveOilByOilId(oilId);
    }

    /**
     * 查询行驶加油信息列表
     * 
     * @param tripDriveOil 行驶加油信息
     * @return 行驶加油信息
     */
    @Override
    public List<TripDriveOil> selectTripDriveOilList(TripDriveOil tripDriveOil)
    {
        return tripDriveOilMapper.selectTripDriveOilList(tripDriveOil);
    }

    /**
     * 新增行驶加油信息
     * 
     * @param tripDriveOil 行驶加油信息
     * @return 结果
     */
    @Override
    public int insertTripDriveOil(TripDriveOil tripDriveOil)
    {
        tripDriveOil.setCreateTime(DateUtils.getNowDate());
        return tripDriveOilMapper.insertTripDriveOil(tripDriveOil);
    }

    /**
     * 修改行驶加油信息
     * 
     * @param tripDriveOil 行驶加油信息
     * @return 结果
     */
    @Override
    public int updateTripDriveOil(TripDriveOil tripDriveOil)
    {
        tripDriveOil.setUpdateTime(DateUtils.getNowDate());
        return tripDriveOilMapper.updateTripDriveOil(tripDriveOil);
    }

    /**
     * 批量删除行驶加油信息
     * 
     * @param oilIds 需要删除的行驶加油信息主键
     * @return 结果
     */
    @Override
    public int deleteTripDriveOilByOilIds(Long[] oilIds)
    {
        return tripDriveOilMapper.deleteTripDriveOilByOilIds(oilIds);
    }

    /**
     * 删除行驶加油信息信息
     * 
     * @param oilId 行驶加油信息主键
     * @return 结果
     */
    @Override
    public int deleteTripDriveOilByOilId(Long oilId)
    {
        return tripDriveOilMapper.deleteTripDriveOilByOilId(oilId);
    }
}
