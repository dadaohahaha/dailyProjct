package com.example.dailyprojectshop.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dailyprojectshop.mapper.ShopProductInventoryMapper;
import com.example.dailyprojectshop.domain.ShopProductInventory;
import com.example.dailyprojectshop.service.IShopProductInventoryService;

/**
 * 商品库存信息Service业务层处理
 * 
 * @author bxm
 * @date 2023-07-11
 */
@Service
public class ShopProductInventoryServiceImpl implements IShopProductInventoryService 
{
    @Autowired
    private ShopProductInventoryMapper shopProductInventoryMapper;

    /**
     * 查询商品库存信息
     * 
     * @param id 商品库存信息主键
     * @return 商品库存信息
     */
    @Override
    public ShopProductInventory selectShopProductInventoryById(Long id)
    {
        return shopProductInventoryMapper.selectShopProductInventoryById(id);
    }

    /**
     * 查询商品库存信息列表
     * 
     * @param shopProductInventory 商品库存信息
     * @return 商品库存信息
     */
    @Override
    public List<ShopProductInventory> selectShopProductInventoryList(ShopProductInventory shopProductInventory)
    {
        return shopProductInventoryMapper.selectShopProductInventoryList(shopProductInventory);
    }

    /**
     * 新增商品库存信息
     * 
     * @param shopProductInventory 商品库存信息
     * @return 结果
     */
    @Override
    public int insertShopProductInventory(ShopProductInventory shopProductInventory)
    {
        shopProductInventory.setCreateTime(DateUtils.getNowDate());
        return shopProductInventoryMapper.insertShopProductInventory(shopProductInventory);
    }

    /**
     * 修改商品库存信息
     * 
     * @param shopProductInventory 商品库存信息
     * @return 结果
     */
    @Override
    public int updateShopProductInventory(ShopProductInventory shopProductInventory)
    {
        shopProductInventory.setUpdateTime(DateUtils.getNowDate());
        return shopProductInventoryMapper.updateShopProductInventory(shopProductInventory);
    }

    /**
     * 批量删除商品库存信息
     * 
     * @param ids 需要删除的商品库存信息主键
     * @return 结果
     */
    @Override
    public int deleteShopProductInventoryByIds(Long[] ids)
    {
        return shopProductInventoryMapper.deleteShopProductInventoryByIds(ids);
    }

    /**
     * 删除商品库存信息信息
     * 
     * @param id 商品库存信息主键
     * @return 结果
     */
    @Override
    public int deleteShopProductInventoryById(Long id)
    {
        return shopProductInventoryMapper.deleteShopProductInventoryById(id);
    }
}
