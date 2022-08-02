import request from '@/utils/request'

// 查询社团类型列表
export function listType(query) {
  return request({
    url: '/club/type/list',
    method: 'get',
    params: query
  })
}

// 查询社团类型详细
export function getType(coporationTypeId) {
  return request({
    url: '/club/type/' + coporationTypeId,
    method: 'get'
  })
}

// 新增社团类型
export function addType(data) {
  return request({
    url: '/club/type',
    method: 'post',
    data: data
  })
}

// 修改社团类型
export function updateType(data) {
  return request({
    url: '/club/type',
    method: 'put',
    data: data
  })
}

// 删除社团类型
export function delType(coporationTypeId) {
  return request({
    url: '/club/type/' + coporationTypeId,
    method: 'delete'
  })
}
