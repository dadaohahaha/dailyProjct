import request from '@/utils/request'

// 查询行驶加油信息列表
export function listOil(query) {
  return request({
    url: '/trip/oil/list',
    method: 'get',
    params: query
  })
}

// 查询行驶加油信息详细
export function getOil(oilId) {
  return request({
    url: '/trip/oil/' + oilId,
    method: 'get'
  })
}

// 新增行驶加油信息
export function addOil(data) {
  return request({
    url: '/trip/oil',
    method: 'post',
    data: data
  })
}

// 修改行驶加油信息
export function updateOil(data) {
  return request({
    url: '/trip/oil',
    method: 'put',
    data: data
  })
}

// 删除行驶加油信息
export function delOil(oilId) {
  return request({
    url: '/trip/oil/' + oilId,
    method: 'delete'
  })
}
