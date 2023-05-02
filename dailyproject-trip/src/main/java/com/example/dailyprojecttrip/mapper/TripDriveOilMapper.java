package com.example.dailyprojecttrip.mapper;

import java.util.List;
import com.example.dailyprojecttrip.domain.TripDriveOil;

/**
 * 行驶加油信息Mapper接口
 * 
 * @author bxm
 * @date 2023-05-01
 */
public interface TripDriveOilMapper 
{
    /**
     * 查询行驶加油信息
     * 
     * @param oilId 行驶加油信息主键
     * @return 行驶加油信息
     */
    public TripDriveOil selectTripDriveOilByOilId(Long oilId);

    /**
     * 查询行驶加油信息列表
     * 
     * @param tripDriveOil 行驶加油信息
     * @return 行驶加油信息集合
     */
    public List<TripDriveOil> selectTripDriveOilList(TripDriveOil tripDriveOil);

    /**
     * 新增行驶加油信息
     * 
     * @param tripDriveOil 行驶加油信息
     * @return 结果
     */
    public int insertTripDriveOil(TripDriveOil tripDriveOil);

    /**
     * 修改行驶加油信息
     * 
     * @param tripDriveOil 行驶加油信息
     * @return 结果
     */
    public int updateTripDriveOil(TripDriveOil tripDriveOil);

    /**
     * 删除行驶加油信息
     * 
     * @param oilId 行驶加油信息主键
     * @return 结果
     */
    public int deleteTripDriveOilByOilId(Long oilId);

    /**
     * 批量删除行驶加油信息
     * 
     * @param oilIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTripDriveOilByOilIds(Long[] oilIds);
}
