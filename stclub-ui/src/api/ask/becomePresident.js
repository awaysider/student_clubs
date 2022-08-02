import request from '@/utils/request'

// 查询成员成为社长申请管理列表
export function listBecomePresident(query) {
  return request({
    url: '/ask/becomePresident/list',
    method: 'get',
    params: query
  })
}

// 查询成员成为社长申请管理详细
export function getBecomePresident(presidentId) {
  return request({
    url: '/ask/becomePresident/' + presidentId,
    method: 'get'
  })
}

// 新增成员成为社长申请管理
export function addBecomePresident(data) {
  return request({
    url: '/ask/becomePresident',
    method: 'post',
    data: data
  })
}

// 修改成员成为社长申请管理
export function updateBecomePresident(data) {
  return request({
    url: '/ask/becomePresident',
    method: 'put',
    data: data
  })
}

// 删除成员成为社长申请管理
export function delBecomePresident(presidentId) {
  return request({
    url: '/ask/becomePresident/' + presidentId,
    method: 'delete'
  })
}

// 审核
export function audit(data) {
  return request({
    url: '/ask/becomePresident/audit',
    method: 'post',
    data: data
  })
}
