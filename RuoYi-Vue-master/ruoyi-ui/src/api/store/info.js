import request from '@/utils/request'

// 查询商店信息列表
export function listInfo(query) {
  return request({
    url: '/store/info/list',
    method: 'get',
    params: query
  })
}

// 查询商店信息详细
export function getInfo(id) {
  return request({
    url: '/store/info/' + id,
    method: 'get'
  })
}

// 新增商店信息
export function addInfo(data) {
  return request({
    url: '/store/info',
    method: 'post',
    data: data
  })
}

// 修改商店信息
export function updateInfo(data) {
  return request({
    url: '/store/info',
    method: 'put',
    data: data
  })
}

// 删除商店信息
export function delInfo(id) {
  return request({
    url: '/store/info/' + id,
    method: 'delete'
  })
}
