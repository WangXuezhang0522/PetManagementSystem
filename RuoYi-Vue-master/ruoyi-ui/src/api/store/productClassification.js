import request from '@/utils/request'

// 查询商品分类列表
export function listProductClassification(query) {
  return request({
    url: '/store/productClassification/list',
    method: 'get',
    params: query
  })
}

// 查询全部商品分类
export function listProductClassificationAll(query) {
  return request({
    url: '/store/productClassification/listAll',
    method: 'get',
    params: query
  })
}

// 查询商品分类详细
export function getProductClassification(id) {
  return request({
    url: '/store/productClassification/' + id,
    method: 'get'
  })
}

// 新增商品分类
export function addProductClassification(data) {
  return request({
    url: '/store/productClassification',
    method: 'post',
    data: data
  })
}

// 修改商品分类
export function updateProductClassification(data) {
  return request({
    url: '/store/productClassification',
    method: 'put',
    data: data
  })
}

// 删除商品分类
export function delProductClassification(id) {
  return request({
    url: '/store/productClassification/' + id,
    method: 'delete'
  })
}
