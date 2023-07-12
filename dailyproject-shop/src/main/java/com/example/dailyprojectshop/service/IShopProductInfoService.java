package com.example.dailyprojectshop.service;

import java.util.List;
import com.example.dailyprojectshop.domain.ShopProductInfo;

/**
 * 商品历史信息Service接口
 * 
 * @author bxm
 * @date 2023-05-25
 */
public interface IShopProductInfoService 
{
    /**
     * 查询商品历史信息
     * 
     * @param id 商品历史信息主键
     * @return 商品历史信息
     */
    public ShopProductInfo selectShopProductInfoById(Long id);

    /**
     * 查询商品历史信息列表
     * 
     * @param shopProductInfo 商品历史信息
     * @return 商品历史信息集合
     */
    public List<ShopProductInfo> selectShopProductInfoList(ShopProductInfo shopProductInfo);

    /**
     * 新增商品历史信息
     * 
     * @param shopProductInfo 商品历史信息
     * @return 结果
     */
    public int insertShopProductInfo(ShopProductInfo shopProductInfo);

    /**
     * 修改商品历史信息
     * 
     * @param shopProductInfo 商品历史信息
     * @return 结果
     */
    public int updateShopProductInfo(ShopProductInfo shopProductInfo);

    /**
     * 批量删除商品历史信息
     * 
     * @param ids 需要删除的商品历史信息主键集合
     * @return 结果
     */
    public int deleteShopProductInfoByIds(Long[] ids);

    /**
     * 删除商品历史信息信息
     * 
     * @param id 商品历史信息主键
     * @return 结果
     */
    public int deleteShopProductInfoById(Long id);
}
