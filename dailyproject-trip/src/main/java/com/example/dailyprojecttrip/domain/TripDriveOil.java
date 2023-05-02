package com.example.dailyprojecttrip.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 行驶加油信息对象 trip_drive_oil
 * 
 * @author bxm
 * @date 2023-05-01
 */
public class TripDriveOil extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long oilId;

    /** 加油日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "加油日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oilTime;

    /** 金额/元 */
    @Excel(name = "金额/元")
    private String amount;

    /** 油量/升L */
    @Excel(name = "油量/升L")
    private String oilLitre;

    /** 单价（元/升L） */
    @Excel(name = "单价", readConverterExp = "元=/升L")
    private String oilPrice;

    /** 型号 */
    @Excel(name = "型号")
    private String carType;

    /** 加前剩余里程 */
    @Excel(name = "加前剩余里程")
    private String beforeRemainingMileage;

    /** 加后剩余里程 */
    @Excel(name = "加后剩余里程")
    private String afterRemainingMileage;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String payType;

    /** 优惠金额 */
    @Excel(name = "优惠金额")
    private String discountAmount;

    /** 实付金额 */
    @Excel(name = "实付金额")
    private String actualPayment;

    /** 删除标志（0代表存在 */
    private String delFlag;

    /** 数据范围（1：全部数据权限 */
    @Excel(name = "数据范围", readConverterExp = "数据范围（1：全部数据权限")
    private String dataScope;

    public void setOilId(Long oilId) 
    {
        this.oilId = oilId;
    }

    public Long getOilId() 
    {
        return oilId;
    }
    public void setOilTime(Date oilTime) 
    {
        this.oilTime = oilTime;
    }

    public Date getOilTime() 
    {
        return oilTime;
    }
    public void setAmount(String amount) 
    {
        this.amount = amount;
    }

    public String getAmount() 
    {
        return amount;
    }
    public void setOilLitre(String oilLitre) 
    {
        this.oilLitre = oilLitre;
    }

    public String getOilLitre() 
    {
        return oilLitre;
    }
    public void setOilPrice(String oilPrice) 
    {
        this.oilPrice = oilPrice;
    }

    public String getOilPrice() 
    {
        return oilPrice;
    }
    public void setCarType(String carType) 
    {
        this.carType = carType;
    }

    public String getCarType() 
    {
        return carType;
    }
    public void setBeforeRemainingMileage(String beforeRemainingMileage) 
    {
        this.beforeRemainingMileage = beforeRemainingMileage;
    }

    public String getBeforeRemainingMileage() 
    {
        return beforeRemainingMileage;
    }
    public void setAfterRemainingMileage(String afterRemainingMileage) 
    {
        this.afterRemainingMileage = afterRemainingMileage;
    }

    public String getAfterRemainingMileage() 
    {
        return afterRemainingMileage;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setDiscountAmount(String discountAmount)
    {
        this.discountAmount = discountAmount;
    }

    public String getDiscountAmount()
    {
        return discountAmount;
    }
    public void setActualPayment(String actualPayment)
    {
        this.actualPayment = actualPayment;
    }

    public String getActualPayment()
    {
        return actualPayment;
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
            .append("oilId", getOilId())
            .append("oilTime", getOilTime())
            .append("amount", getAmount())
            .append("oilLitre", getOilLitre())
            .append("oilPrice", getOilPrice())
            .append("carType", getCarType())
            .append("beforeRemainingMileage", getBeforeRemainingMileage())
            .append("afterRemainingMileage", getAfterRemainingMileage())
            .append("payType", getPayType())
            .append("discountAmount", getDiscountAmount())
            .append("actualPayment", getActualPayment())
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
