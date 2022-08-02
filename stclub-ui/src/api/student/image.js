import request from '@/utils/request'

// 查询首页轮番图列表
export function listImage(query) {
  return request({
    url: '/student/image/list',
    method: 'get',
    params: query
  })
}

// 查询首页轮番图详细
export function getImage(imageId) {
  return request({
    url: '/student/image/' + imageId,
    method: 'get'
  })
}

// 新增首页轮番图
export function addImage(data) {
  return request({
    url: '/student/image',
    method: 'post',
    data: data
  })
}

// 修改首页轮番图
export function updateImage(data) {
  return request({
    url: '/student/image',
    method: 'put',
    data: data
  })
}

// 删除首页轮番图
export function delImage(imageId) {
  return request({
    url: '/student/image/' + imageId,
    method: 'delete'
  })
}
