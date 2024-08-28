import request from '@/utils/request'

// 查询宠物论坛列表
export function listForum(query) {
  return request({
    url: '/pet/forum/list',
    method: 'get',
    params: query
  })
}

// 查询宠物论坛详细
export function getForum(id) {
  return request({
    url: '/pet/forum/' + id,
    method: 'get'
  })
}

// 新增宠物论坛
export function addForum(data) {
  return request({
    url: '/pet/forum',
    method: 'post',
    data: data
  })
}

// 修改宠物论坛
export function updateForum(data) {
  return request({
    url: '/pet/forum',
    method: 'put',
    data: data
  })
}

// 删除宠物论坛
export function delForum(id) {
  return request({
    url: '/pet/forum/' + id,
    method: 'delete'
  })
}
