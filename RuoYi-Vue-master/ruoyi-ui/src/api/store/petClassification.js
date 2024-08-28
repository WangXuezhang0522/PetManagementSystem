import request from '@/utils/request'

// 查询宠物分类列表
export function listPetClassification(query) {
  return request({
    url: '/store/petClassification/list',
    method: 'get',
    params: query
  })
}

// 查询全部的宠物分类数据
export function listPetClassificationAll(query) {
  return request({
    url: '/store/petClassification/listAll',
    method: 'get',
    params: query
  })
}

// 查询宠物分类详细
export function getPetClassification(id) {
  return request({
    url: '/store/petClassification/' + id,
    method: 'get'
  })
}

// 新增宠物分类
export function addPetClassification(data) {
  return request({
    url: '/store/petClassification',
    method: 'post',
    data: data
  })
}

// 修改宠物分类
export function updatePetClassification(data) {
  return request({
    url: '/store/petClassification',
    method: 'put',
    data: data
  })
}

// 删除宠物分类
export function delPetClassification(id) {
  return request({
    url: '/store/petClassification/' + id,
    method: 'delete'
  })
}
