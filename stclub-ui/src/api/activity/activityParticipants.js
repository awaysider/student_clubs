import request from '@/utils/request'

// 查询活动参加人员管理列表
export function listActivityParticipants(query) {
  return request({
    url: '/activity/activityParticipants/list',
    method: 'get',
    params: query
  })
}

// 查询活动参加人员管理详细
export function getActivityParticipants(participantsId) {
  return request({
    url: '/activity/activityParticipants/' + participantsId,
    method: 'get'
  })
}

// 新增活动参加人员管理
export function addActivityParticipants(data) {
  return request({
    url: '/activity/activityParticipants',
    method: 'post',
    data: data
  })
}

// 修改活动参加人员管理
export function updateActivityParticipants(data) {
  return request({
    url: '/activity/activityParticipants',
    method: 'put',
    data: data
  })
}

// 删除活动参加人员管理
export function delActivityParticipants(participantsId) {
  return request({
    url: '/activity/activityParticipants/' + participantsId,
    method: 'delete'
  })
}
