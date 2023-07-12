import request from '@/utils/request'

// 查询商品库存信息列表
export function listInventory(query) {
  return request({
    url: '/shop/inventory/list',
    method: 'get',
    params: query
  })
}

// 查询商品库存信息详细
export function getInventory(id) {
  return request({
    url: '/shop/inventory/' + id,
    method: 'get'
  })
}

// 新增商品库存信息
export function addInventory(data) {
  return request({
    url: '/shop/inventory',
    method: 'post',
    data: data
  })
}

// 修改商品库存信息
export function updateInventory(data) {
  return request({
    url: '/shop/inventory',
    method: 'put',
    data: data
  })
}

// 删除商品库存信息
export function delInventory(id) {
  return request({
    url: '/shop/inventory/' + id,
    method: 'delete'
  })
}
