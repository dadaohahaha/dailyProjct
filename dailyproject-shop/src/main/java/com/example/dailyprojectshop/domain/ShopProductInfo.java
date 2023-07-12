package com.example.dailyprojectshop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品历史信息对象 shop_product_info
 * 
 * @author bxm
 * @date 2023-05-25
 */
public class ShopProductInfo extends BaseEntity
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

    /** 活动时间 */
    @Excel(name = "活动时间")
    private String activityTime;

    /** 图片 */
    @Excel(name = "图片")
    private String pic;

    /** 删除标志（0代表存在 */
    private String delFlag;

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
    public void setActivityTime(String activityTime) 
    {
        this.activityTime = activityTime;
    }

    public String getActivityTime() 
    {
        return activityTime;
    }
    public void setPic(String pic) 
    {
        this.pic = pic;
    }

    public String getPic() 
    {
        return pic;
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
            .append("id", getId())
            .append("productCategory", getProductCategory())
            .append("productBrand", getProductBrand())
            .append("productName", getProductName())
            .append("activityTime", getActivityTime())
            .append("pic", getPic())
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
