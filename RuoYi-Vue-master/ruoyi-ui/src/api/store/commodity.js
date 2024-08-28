import request from '@/utils/request'

// 查询商品信息列表
export function listCommodity(query) {
  return request({
    url: '/store/commodity/list',
    method: 'get',
    params: query
  })
}

// 查询商品信息详细
export function getCommodity(id) {
  return request({
    url: '/store/commodity/' + id,
    method: 'get'
  })
}

// 新增商品信息
export function addCommodity(data) {
  return request({
    url: '/store/commodity',
    method: 'post',
    data: data
  })
}

// 修改商品信息
export function updateCommodity(data) {
  return request({
    url: '/store/commodity',
    method: 'put',
    data: data
  })
}

// 删除商品信息
export function delCommodity(id) {
  return request({
    url: '/store/commodity/' + id,
    method: 'delete'
  })
}
