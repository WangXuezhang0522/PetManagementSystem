import request from '@/utils/request'

// 查询宠物寄养申请列表
export function listFosterApplication(query) {
  return request({
    url: '/pet/fosterApplication/list',
    method: 'get',
    params: query
  })
}

// 查询宠物寄养申请详细
export function getFosterApplication(id) {
  return request({
    url: '/pet/fosterApplication/' + id,
    method: 'get'
  })
}

// 新增宠物寄养申请
export function addFosterApplication(data) {
  return request({
    url: '/pet/fosterApplication',
    method: 'post',
    data: data
  })
}

// 修改宠物寄养申请
export function updateFosterApplication(data) {
  return request({
    url: '/pet/fosterApplication',
    method: 'put',
    data: data
  })
}

// 删除宠物寄养申请
export function delFosterApplication(id) {
  return request({
    url: '/pet/fosterApplication/' + id,
    method: 'delete'
  })
}

// 宠物寄养申请审核
export function updateExamine(data) {
  return request({
    url: '/pet/fosterApplication/editExamine',
    method: 'post',
    data: data
  })
}