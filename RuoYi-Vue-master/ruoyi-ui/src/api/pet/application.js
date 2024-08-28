import request from '@/utils/request'

// 查询宠物领养申请列表
export function listApplication(query) {
  return request({
    url: '/pet/application/list',
    method: 'get',
    params: query
  })
}

// 查询宠物领养申请详细
export function getApplication(id) {
  return request({
    url: '/pet/application/' + id,
    method: 'get'
  })
}

// 新增宠物领养申请
export function addApplication(data) {
  return request({
    url: '/pet/application',
    method: 'post',
    data: data
  })
}

// 修改宠物领养申请
export function updateApplication(data) {
  return request({
    url: '/pet/application',
    method: 'put',
    data: data
  })
}

// 删除宠物领养申请
export function delApplication(id) {
  return request({
    url: '/pet/application/' + id,
    method: 'delete'
  })
}

// 宠物领养申请审核
export function updateExamine(data) {
  return request({
    url: '/pet/application/editExamine',
    method: 'post',
    data: data
  })
}

// 宠物领养支付
export function getPayment(id) {
  return request({
    url: '/pet/application/payment/' + id,
    method: 'get'
  })
}
