import request from '@/utils/request'

// 查询用户身份管理列表
export function listUserIdentity(query) {
  return request({
    url: '/student/userIdentity/list',
    method: 'get',
    params: query
  })
}

// 查询用户身份管理详细
export function getUserIdentity(id) {
  return request({
    url: '/student/userIdentity/' + id,
    method: 'get'
  })
}

// 新增用户身份管理
export function addUserIdentity(data) {
  return request({
    url: '/student/userIdentity',
    method: 'post',
    data: data
  })
}

// 修改用户身份管理
export function updateUserIdentity(data) {
  return request({
    url: '/student/userIdentity',
    method: 'put',
    data: data
  })
}

// 删除用户身份管理
export function delUserIdentity(id) {
  return request({
    url: '/student/userIdentity/' + id,
    method: 'delete'
  })
}
