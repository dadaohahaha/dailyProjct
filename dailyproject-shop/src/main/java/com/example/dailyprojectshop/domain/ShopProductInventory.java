package com.example.dailyprojectshop.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品库存信息对象 shop_product_inventory
 * 
 * @author bxm
 * @date 2023-07-11
 */
public class ShopProductInventory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 商品类别 */
    @Excel(name = "商品类别")
    private String productCategory;

    /** 商品品牌 */
    @Excel(name = "商品品牌")
    private String productBrand;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 容量 */
    @Excel(name = "容量")
    private String capacity;

    /** 容量单位 */
    @Excel(name = "容量单位")
    private String capacityUnit;

    /** 数量 */
    @Excel(name = "数量")
    private String number;

    /** 数量单位 */
    @Excel(name = "数量单位")
    private String numUnit;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date manufacturingDate;

    /** 有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expirationDate;

    /** 保质期 */
    @Excel(name = "保质期")
    private String qualityGuaranteePeriod;

    /** 购入日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购入日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buyDate;

    /** 删除标志（0代表存在 */
    private String delFlag;

    /** 状态（0失效 */
    @Excel(name = "状态", readConverterExp = "状态（0失效")
    private String status;

    /** 数据范围（1：全部数据权限 */
    @Excel(name = "数据范围", readConverterExp = "数据范围（1：全部数据权限")
    private String dataScope;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductCategory(String productCategory) 
    {
        this.productCategory = productCategory;
    }

    public String getProductCategory() 
    {
        return productCategory;
    }
    public void setProductBrand(String productBrand) 
    {
        this.productBrand = productBrand;
    }

    public String getProductBrand() 
    {
        return productBrand;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setCapacity(String capacity) 
    {
        this.capacity = capacity;
    }

    public String getCapacity() 
    {
        return capacity;
    }
    public void setCapacityUnit(String capacityUnit) 
    {
        this.capacityUnit = capacityUnit;
    }

    public String getCapacityUnit() 
    {
        return capacityUnit;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setNumUnit(String numUnit) 
    {
        this.numUnit = numUnit;
    }

    public String getNumUnit() 
    {
        return numUnit;
    }
    public void setManufacturingDate(Date manufacturingDate) 
    {
        this.manufacturingDate = manufacturingDate;
    }

    public Date getManufacturingDate() 
    {
        return manufacturingDate;
    }
    public void setExpirationDate(Date expirationDate) 
    {
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() 
    {
        return expirationDate;
    }
    public void setQualityGuaranteePeriod(String qualityGuaranteePeriod) 
    {
        this.qualityGuaranteePeriod = qualityGuaranteePeriod;
    }

    public String getQualityGuaranteePeriod() 
    {
        return qualityGuaranteePeriod;
    }
    public void setBuyDate(Date buyDate) 
    {
        this.buyDate = buyDate;
    }

    public Date getBuyDate() 
    {
        return buyDate;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("id", getId())
            .append("productCategory", getProductCategory())
            .append("productBrand", getProductBrand())
            .append("productName", getProductName())
            .append("capacity", getCapacity())
            .append("capacityUnit", getCapacityUnit())
            .append("number", getNumber())
            .append("numUnit", getNumUnit())
            .append("manufacturingDate", getManufacturingDate())
            .append("expirationDate", getExpirationDate())
            .append("qualityGuaranteePeriod", getQualityGuaranteePeriod())
            .append("buyDate", getBuyDate())
            .append("delFlag", getDelFlag())
            .append("status", getStatus())
            .append("dataScope", getDataScope())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
