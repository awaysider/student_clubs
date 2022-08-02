import request from '@/utils/request'

// 查询成员创建社团申请管理列表
export function listSetCorporation(query) {
  return request({
    url: '/ask/setCorporation/list',
    method: 'get',
    params: query
  })
}

// 查询成员创建社团申请管理详细
export function getSetCorporation(setCorporationId) {
  return request({
    url: '/ask/setCorporation/' + setCorporationId,
    method: 'get'
  })
}

// 新增成员创建社团申请管理
export function addSetCorporation(data) {
  return request({
    url: '/ask/setCorporation',
    method: 'post',
    data: data
  })
}

// 修改成员创建社团申请管理
export function updateSetCorporation(data) {
  return request({
    url: '/ask/setCorporation',
    method: 'put',
    data: data
  })
}

// 删除成员创建社团申请管理
export function delSetCorporation(setCorporationId) {
  return request({
    url: '/ask/setCorporation/' + setCorporationId,
    method: 'delete'
  })
}

// 审核
export function audit(data) {
  return request({
    url: '/ask/setCorporation/audit',
    method: 'post',
    data: data
  })
}
