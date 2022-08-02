import request from '@/utils/request'

// 查询用户入社申请管理列表
export function listIntoCorporation(query) {
  return request({
    url: '/ask/intoCorporation/list',
    method: 'get',
    params: query
  })
}

// 查询用户入社申请管理详细
export function getIntoCorporation(intoCorporationId) {
  return request({
    url: '/ask/intoCorporation/' + intoCorporationId,
    method: 'get'
  })
}

// 新增用户入社申请管理
export function addIntoCorporation(data) {
  return request({
    url: '/ask/intoCorporation',
    method: 'post',
    data: data
  })
}

// 修改用户入社申请管理
export function updateIntoCorporation(data) {
  return request({
    url: '/ask/intoCorporation',
    method: 'put',
    data: data
  })
}

// 删除用户入社申请管理
export function delIntoCorporation(intoCorporationId) {
  return request({
    url: '/ask/intoCorporation/' + intoCorporationId,
    method: 'delete'
  })
}

// 审核
export function audit(data) {
  return request({
    url: '/ask/intoCorporation/audit',
    method: 'post',
    data: data
  })
}
