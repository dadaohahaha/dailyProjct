package com.example.dailyprojectshop.mapper;

import java.util.List;
import com.example.dailyprojectshop.domain.ShopProductInventory;

/**
 * 商品库存信息Mapper接口
 * 
 * @author bxm
 * @date 2023-07-11
 */
public interface ShopProductInventoryMapper 
{
    /**
     * 查询商品库存信息
     * 
     * @param id 商品库存信息主键
     * @return 商品库存信息
     */
    public ShopProductInventory selectShopProductInventoryById(Long id);

    /**
     * 查询商品库存信息列表
     * 
     * @param shopProductInventory 商品库存信息
     * @return 商品库存信息集合
     */
    public List<ShopProductInventory> selectShopProductInventoryList(ShopProductInventory shopProductInventory);

    /**
     * 新增商品库存信息
     * 
     * @param shopProductInventory 商品库存信息
     * @return 结果
     */
    public int insertShopProductInventory(ShopProductInventory shopProductInventory);

    /**
     * 修改商品库存信息
     * 
     * @param shopProductInventory 商品库存信息
     * @return 结果
     */
    public int updateShopProductInventory(ShopProductInventory shopProductInventory);

    /**
     * 删除商品库存信息
     * 
     * @param id 商品库存信息主键
     * @return 结果
     */
    public int deleteShopProductInventoryById(Long id);

    /**
     * 批量删除商品库存信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopProductInventoryByIds(Long[] ids);
}
