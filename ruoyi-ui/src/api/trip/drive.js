import request from '@/utils/request'

// 查询行驶信息列表
export function listDrive(query) {
  return request({
    url: '/trip/drive/list',
    method: 'get',
    params: query
  })
}

// 查询行驶信息详细
export function getDrive(driveId) {
  return request({
    url: '/trip/drive/' + driveId,
    method: 'get'
  })
}

// 新增行驶信息
export function addDrive(data) {
  return request({
    url: '/trip/drive',
    method: 'post',
    data: data
  })
}

// 修改行驶信息
export function updateDrive(data) {
  return request({
    url: '/trip/drive',
    method: 'put',
    data: data
  })
}

// 删除行驶信息
export function delDrive(driveId) {
  return request({
    url: '/trip/drive/' + driveId,
    method: 'delete'
  })
}
