import request from '@/utils/request'

// 查询活动信息管理列表
export function listActivity(query) {
  return request({
    url: '/activity/activity/list',
    method: 'get',
    params: query
  })
}

// 查询活动信息管理详细
export function getActivity(actId) {
  return request({
    url: '/activity/activity/' + actId,
    method: 'get'
  })
}

// 新增活动信息管理
export function addActivity(data) {
  return request({
    url: '/activity/activity',
    method: 'post',
    data: data
  })
}

// 修改活动信息管理
export function updateActivity(data) {
  return request({
    url: '/activity/activity',
    method: 'put',
    data: data
  })
}

// 删除活动信息管理
export function delActivity(actId) {
  return request({
    url: '/activity/activity/' + actId,
    method: 'delete'
  })
}
