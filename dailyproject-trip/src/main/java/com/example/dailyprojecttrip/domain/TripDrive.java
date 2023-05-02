package com.example.dailyprojecttrip.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 行驶信息对象 trip_drive
 * 
 * @author bxm
 * @date 2023-04-26
 */
public class TripDrive extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long driveId;

    /** 出发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 耗时/分钟 */
    @Excel(name = "耗时/分钟")
    private String spendTime;

    /** 行驶里程 */
    @Excel(name = "行驶里程")
    private String distance;

    /** 自启动起油耗 */
    @Excel(name = "自启动起油耗")
    private String oilConsumption;

    /** 剩余里程 */
    @Excel(name = "剩余里程")
    private String remainingMileage;

    /** 累计行驶里程 */
    @Excel(name = "累计行驶里程")
    private String totalMileage;

    /** 型号 */
    @Excel(name = "型号")
    private String carType;

    /** 删除标志（0代表存在 */
    private String delFlag;

    /** 数据范围（1：全部数据权限 */
    @Excel(name = "数据范围", readConverterExp = "数据范围（1：全部数据权限")
    private String dataScope;

    public void setDriveId(Long driveId) 
    {
        this.driveId = driveId;
    }

    public Long getDriveId() 
    {
        return driveId;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setSpendTime(String spendTime) 
    {
        this.spendTime = spendTime;
    }

    public String getSpendTime() 
    {
        return spendTime;
    }
    public void setDistance(String distance) 
    {
        this.distance = distance;
    }

    public String getDistance() 
    {
        return distance;
    }
    public void setOilConsumption(String oilConsumption) 
    {
        this.oilConsumption = oilConsumption;
    }

    public String getOilConsumption() 
    {
        return oilConsumption;
    }
    public void setRemainingMileage(String remainingMileage) 
    {
        this.remainingMileage = remainingMileage;
    }

    public String getRemainingMileage() 
    {
        return remainingMileage;
    }
    public void setTotalMileage(String totalMileage) 
    {
        this.totalMileage = totalMileage;
    }

    public String getTotalMileage() 
    {
        return totalMileage;
    }
    public void setCarType(String carType) 
    {
        this.carType = carType;
    }

    public String getCarType() 
    {
        return carType;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setDataScope(String dataScope) 
    {
        this.dataScope = dataScope;
    }

    public String getDataScope() 
    {
        return dataScope;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("driveId", getDriveId())
            .append("startTime", getStartTime())
            .append("spendTime", getSpendTime())
            .append("distance", getDistance())
            .append("oilConsumption", getOilConsumption())
            .append("remainingMileage", getRemainingMileage())
            .append("totalMileage", getTotalMileage())
            .append("carType", getCarType())
            .append("delFlag", getDelFlag())
            .append("dataScope", getDataScope())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
