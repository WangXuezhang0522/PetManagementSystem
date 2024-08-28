import request from '@/utils/request'

// 查询宠物领养列表
export function listAdopt(query) {
  return request({
    url: '/pet/adopt/list',
    method: 'get',
    params: query
  })
}

// 查询宠物领养详细
export function getAdopt(id) {
  return request({
    url: '/pet/adopt/' + id,
    method: 'get'
  })
}

// 新增宠物领养
export function addAdopt(data) {
  return request({
    url: '/pet/adopt',
    method: 'post',
    data: data
  })
}

// 修改宠物领养
export function updateAdopt(data) {
  return request({
    url: '/pet/adopt',
    method: 'put',
    data: data
  })
}

// 删除宠物领养
export function delAdopt(id) {
  return request({
    url: '/pet/adopt/' + id,
    method: 'delete'
  })
}

// 宠物领养图表数据
export function getEchartsData() {
  return request({
    url: '/pet/adopt/getEchartsData',
    method: 'post'
  })
}
