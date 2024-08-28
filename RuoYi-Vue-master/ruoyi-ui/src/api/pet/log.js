import request from '@/utils/request'

// 查询宠物日志列表
export function listLog(query) {
  return request({
    url: '/pet/log/list',
    method: 'get',
    params: query
  })
}

// 查询宠物日志详细
export function getLog(id) {
  return request({
    url: '/pet/log/' + id,
    method: 'get'
  })
}

// 新增宠物日志
export function addLog(data) {
  return request({
    url: '/pet/log',
    method: 'post',
    data: data
  })
}

// 修改宠物日志
export function updateLog(data) {
  return request({
    url: '/pet/log',
    method: 'put',
    data: data
  })
}

// 删除宠物日志
export function delLog(id) {
  return request({
    url: '/pet/log/' + id,
    method: 'delete'
  })
}
