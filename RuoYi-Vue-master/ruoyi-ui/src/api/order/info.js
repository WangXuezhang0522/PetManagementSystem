import request from '@/utils/request'

// 查询订单管理列表
export function listInfo(query) {
  return request({
    url: '/order/info/list',
    method: 'get',
    params: query
  })
}

// 查询订单管理详细
export function getInfo(id) {
  return request({
    url: '/order/info/' + id,
    method: 'get'
  })
}

// 新增订单管理
export function addInfo(data) {
  return request({
    url: '/order/info',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateInfo(data) {
  return request({
    url: '/order/info',
    method: 'put',
    data: data
  })
}

// 删除订单管理
export function delInfo(id) {
  return request({
    url: '/order/info/' + id,
    method: 'delete'
  })
}

// 生成订单
export function getGenerateOrder(data) {
  return request({
    url: '/order/info/getGenerateOrder/' + data.sysUserAddressId + "/" + data.ids,
    method: 'get'
  })
}
