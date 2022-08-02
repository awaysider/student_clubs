import request from '@/utils/request'

// 查询活动申请管理列表
export function listSetActivity(query) {
  return request({
    url: '/ask/setActivity/list',
    method: 'get',
    params: query
  })
}

// 查询活动申请管理详细
export function getSetActivity(setActivityId) {
  return request({
    url: '/ask/setActivity/' + setActivityId,
    method: 'get'
  })
}

// 新增活动申请管理
export function addSetActivity(data) {
  return request({
    url: '/ask/setActivity',
    method: 'post',
    data: data
  })
}

// 修改活动申请管理
export function updateSetActivity(data) {
  return request({
    url: '/ask/setActivity',
    method: 'put',
    data: data
  })
}

// 删除活动申请管理
export function delSetActivity(setActivityId) {
  return request({
    url: '/ask/setActivity/' + setActivityId,
    method: 'delete'
  })
}

// 审核
export function audit(data) {
  return request({
    url: '/ask/setActivity/audit',
    method: 'post',
    data: data
  })
}
