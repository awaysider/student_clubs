import request from '@/utils/request'

// 查询入社面试邀请管理列表
export function listIntoInterview(query) {
  return request({
    url: '/ask/intoInterview/list',
    method: 'get',
    params: query
  })
}

// 查询入社面试邀请管理详细
export function getIntoInterview(intoInterviewId) {
  return request({
    url: '/ask/intoInterview/' + intoInterviewId,
    method: 'get'
  })
}

// 新增入社面试邀请管理
export function addIntoInterview(data) {
  return request({
    url: '/ask/intoInterview',
    method: 'post',
    data: data
  })
}

// 修改入社面试邀请管理
export function updateIntoInterview(data) {
  return request({
    url: '/ask/intoInterview',
    method: 'put',
    data: data
  })
}

// 删除入社面试邀请管理
export function delIntoInterview(intoInterviewId) {
  return request({
    url: '/ask/intoInterview/' + intoInterviewId,
    method: 'delete'
  })
}

// 审核
export function audit(data) {
  return request({
    url: '/ask/intoInterview/audit',
    method: 'post',
    data: data
  })
}
