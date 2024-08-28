import request from '@/utils/request'

// 查询商品信息列表
export function listCommodity(query) {
  return request({
    url: '/pet/store/list',
    method: 'get',
    params: query
  })
}

// 查询商品信息详细
export function getCommodity(id) {
  return request({
    url: '/pet/store/' + id,
    method: 'get'
  })
}

// 新增商品信息
export function addCommodity(data) {
  return request({
    url: '/pet/store',
    method: 'post',
    data: data
  })
}