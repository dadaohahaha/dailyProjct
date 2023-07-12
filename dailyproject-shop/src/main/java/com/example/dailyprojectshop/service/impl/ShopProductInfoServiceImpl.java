package com.example.dailyprojectshop.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dailyprojectshop.mapper.ShopProductInfoMapper;
import com.example.dailyprojectshop.domain.ShopProductInfo;
import com.example.dailyprojectshop.service.IShopProductInfoService;

/**
 * 商品历史信息Service业务层处理
 * 
 * @author bxm
 * @date 2023-05-25
 */
@Service
public class ShopProductInfoServiceImpl implements IShopProductInfoService 
{
    @Autowired
    private ShopProductInfoMapper shopProductInfoMapper;

    /**
     * 查询商品历史信息
     * 
     * @param id 商品历史信息主键
     * @return 商品历史信息
     */
    @Override
    public ShopProductInfo selectShopProductInfoById(Long id)
    {
        return shopProductInfoMapper.selectShopProductInfoById(id);
    }

    /**
     * 查询商品历史信息列表
     * 
     * @param shopProductInfo 商品历史信息
     * @return 商品历史信息
     */
    @Override
    public List<ShopProductInfo> selectShopProductInfoList(ShopProductInfo shopProductInfo)
    {
        return shopProductInfoMapper.selectShopProductInfoList(shopProductInfo);
    }

    /**
     * 新增商品历史信息
     * 
     * @param shopProductInfo 商品历史信息
     * @return 结果
     */
    @Override
    public int insertShopProductInfo(ShopProductInfo shopProductInfo)
    {
        shopProductInfo.setCreateTime(DateUtils.getNowDate());
        return shopProductInfoMapper.insertShopProductInfo(shopProductInfo);
    }

    /**
     * 修改商品历史信息
     * 
     * @param shopProductInfo 商品历史信息
     * @return 结果
     */
    @Override
    public int updateShopProductInfo(ShopProductInfo shopProductInfo)
    {
        shopProductInfo.setUpdateTime(DateUtils.getNowDate());
        return shopProductInfoMapper.updateShopProductInfo(shopProductInfo);
    }

    /**
     * 批量删除商品历史信息
     * 
     * @param ids 需要删除的商品历史信息主键
     * @return 结果
     */
    @Override
    public int deleteShopProductInfoByIds(Long[] ids)
    {
        return shopProductInfoMapper.deleteShopProductInfoByIds(ids);
    }

    /**
     * 删除商品历史信息信息
     * 
     * @param id 商品历史信息主键
     * @return 结果
     */
    @Override
    public int deleteShopProductInfoById(Long id)
    {
        return shopProductInfoMapper.deleteShopProductInfoById(id);
    }
}
