import request from '@/utils/request'

// 查询宠物寄养机构列表
export function listFosterInstitution(query) {
  return request({
    url: '/pet/fosterInstitution/list',
    method: 'get',
    params: query
  })
}

// 查询宠物寄养机构详细
export function getFosterInstitution(id) {
  return request({
    url: '/pet/fosterInstitution/' + id,
    method: 'get'
  })
}

// 新增宠物寄养机构
export function addFosterInstitution(data) {
  return request({
    url: '/pet/fosterInstitution',
    method: 'post',
    data: data
  })
}

// 修改宠物寄养机构
export function updateFosterInstitution(data) {
  return request({
    url: '/pet/fosterInstitution',
    method: 'put',
    data: data
  })
}

// 删除宠物寄养机构
export function delFosterInstitution(id) {
  return request({
    url: '/pet/fosterInstitution/' + id,
    method: 'delete'
  })
}

// 宠物领养图表数据
export function getJYEchartsData() {
  return request({
    url: '/pet/fosterInstitution/getEchartsData',
    method: 'post'
  })
}
