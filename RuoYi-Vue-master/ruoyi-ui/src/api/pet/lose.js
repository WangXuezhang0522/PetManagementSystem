import request from '@/utils/request'

// 查询宠物丢失列表
export function listLose(query) {
  return request({
    url: '/pet/lose/list',
    method: 'get',
    params: query
  })
}

// 查询宠物丢失详细
export function getLose(id) {
  return request({
    url: '/pet/lose/' + id,
    method: 'get'
  })
}

// 新增宠物丢失
export function addLose(data) {
  return request({
    url: '/pet/lose',
    method: 'post',
    data: data
  })
}

// 修改宠物丢失
export function updateLose(data) {
  return request({
    url: '/pet/lose',
    method: 'put',
    data: data
  })
}

// 删除宠物丢失
export function delLose(id) {
  return request({
    url: '/pet/lose/' + id,
    method: 'delete'
  })
}
