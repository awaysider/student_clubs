import request from '@/utils/request'

// 查询社团列表
export function listCorporation(query) {
  return request({
    url: '/club/corporation/list',
    method: 'get',
    params: query
  })
}

// 查询社团详细
export function getCorporation(corporationId) {
  return request({
    url: '/club/corporation/' + corporationId,
    method: 'get'
  })
}

// 新增社团
export function addCorporation(data) {
  return request({
    url: '/club/corporation',
    method: 'post',
    data: data
  })
}

// 修改社团
export function updateCorporation(data) {
  return request({
    url: '/club/corporation',
    method: 'put',
    data: data
  })
}

// 删除社团
export function delCorporation(corporationId) {
  return request({
    url: '/club/corporation/' + corporationId,
    method: 'delete'
  })
}

//上传控件
export function upload(data){
  return request({
    url: '/common/upload',
    method: 'post',
    data: data
  })
}

export function getStuUserIdentityList(corporationId){
  return request({
    url:'/club/corporation/getStuUserIdentityList/' + corporationId,
    method: 'get'
  })
}
