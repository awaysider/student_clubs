package com.stclub.common.core.mybatisplus.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stclub.common.utils.BeanCopyUtils;
import com.stclub.common.utils.reflect.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * IServicePlus 实现类
 *
 * @param <M> Mapper类
 * @param <T> 数据实体类
 * @param <V> vo类
 * @author Lion Li
 */
@Slf4j
@SuppressWarnings("unchecked")
public class ServicePlusImpl<M extends BaseMapperPlus<T>, T, V> extends ServiceImpl<M, T> implements IServicePlus<T, V> {

	@Autowired
	protected M baseMapper;

	@Override
	public M getBaseMapper() {
		return baseMapper;
	}


	protected Class<T> entityClass = currentModelClass();

	@Override
	public Class<T> getEntityClass() {
		return entityClass;
	}

	protected Class<M> mapperClass = currentMapperClass();

	protected Class<V> voClass = currentVoClass();

	public Class<V> getVoClass() {
		return voClass;
	}

	@Override
	protected Class<M> currentMapperClass() {
		return (Class<M>) ReflectionKit.getSuperClassGenericType(this.getClass(), ServicePlusImpl.class, 0);
	}

	@Override
	protected Class<T> currentModelClass() {
		return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), ServicePlusImpl.class, 1);
	}

	protected Class<V> currentVoClass() {
		return (Class<V>) ReflectionKit.getSuperClassGenericType(this.getClass(), ServicePlusImpl.class, 2);
	}

	/**
	 * 单条执行性能差 适用于列表对象内容不确定
	 */
	@Override
	public boolean saveBatch(Collection<T> entityList, int batchSize) {
		return super.saveBatch(entityList, batchSize);
	}

	@Override
	public boolean saveOrUpdate(T entity) {
		return super.saveOrUpdate(entity);
	}

	/**
	 * 单条执行性能差 适用于列表对象内容不确定
	 */
	@Override
	public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
		return super.saveOrUpdateBatch(entityList, batchSize);
	}

	@Override
	public boolean updateBatchById(Collection<T> entityList, int batchSize) {
		return super.updateBatchById(entityList, batchSize);
	}

	/**
	 * 单sql批量插入( 全量填充 无视数据库默认值 )
	 * 适用于无脑插入
	 */
	@Override
	public boolean saveBatch(Collection<T> entityList) {
		return saveBatch(entityList, DEFAULT_BATCH_SIZE);
	}

	@Override
	public boolean saveOrUpdateBatch(Collection<T> entityList) {
		return saveOrUpdateBatch(entityList, DEFAULT_BATCH_SIZE);
	}

	@Override
	public boolean updateBatchById(Collection<T> entityList) {
		return updateBatchById(entityList, DEFAULT_BATCH_SIZE);
	}

	/**
	 * 单sql批量插入( 全量填充 )
	 */
	@Override
	public boolean saveAll(Collection<T> entityList) {
		if (CollUtil.isEmpty(entityList)) {
			return false;
		}
		return baseMapper.insertAll(entityList) == entityList.size();
	}

	/**
	 * 全量保存或更新 ( 按主键区分 )
	 */
	@Override
	public boolean saveOrUpdateAll(Collection<T> entityList) {
		if (CollUtil.isEmpty(entityList)) {
			return false;
		}
		TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
		Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
		String keyProperty = tableInfo.getKeyProperty();
		Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
		List<T> addList = new ArrayList<>();
		List<T> updateList = new ArrayList<>();
		int row = 0;
		for (T entity : entityList) {
			Object id = ReflectUtils.invokeGetter(entity, keyProperty);
			if (ObjectUtil.isNull(id)) {
				addList.add(entity);
			} else {
				updateList.add(entity);
			}
		}
		if (CollUtil.isNotEmpty(updateList) && updateBatchById(updateList)) {
			row += updateList.size();
		}
        if (CollUtil.isNotEmpty(addList)) {
            row += baseMapper.insertAll(addList);
        }
		return row == entityList.size();
	}

	/**
	 * 根据 ID 查询
	 */
	@Override
	public V getVoById(Serializable id) {
        return getBaseMapper().selectVoById(id, voClass);
	}

	/**
	 * 查询（根据ID 批量查询）
	 */
	@Override
	public List<V> listVoByIds(Collection<? extends Serializable> idList) {
        return getBaseMapper().selectVoBatchIds(idList, voClass);
	}

	/**
	 * 查询（根据 columnMap 条件）
	 */
	@Override
	public List<V> listVoByMap(Map<String, Object> columnMap) {
        return getBaseMapper().selectVoByMap(columnMap, voClass);
	}

	/**
	 * 根据 Wrapper，查询一条记录 <br/>
	 * <p>结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")</p>
	 */
	@Override
	public V getVoOne(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectVoOne(queryWrapper, voClass);
	}

	/**
	 * 查询列表
	 */
	@Override
	public List<V> listVo(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectVoList(queryWrapper, voClass);
	}

//	/**
//	 * 翻页查询
//     * @deprecated 3.6.0 移除 请使用 {@link #pageVo(IPage, Wrapper)}
//	 */
//	@Override
//    @Deprecated
//	public PagePlus<T, V> pageVo(PagePlus<T, V> page, Wrapper<T> queryWrapper) {
//		PagePlus<T, V> result = getBaseMapper().selectPage(page, queryWrapper);
//		List<V> volist = BeanCopyUtils.copyList(result.getRecords(), voClass);
//		result.setRecordsVo(volist);
//		return result;
//	}

    /**
     * 翻页查询
     *
     * @param page         翻页对象
     * @param queryWrapper 实体对象封装操作类
     */
    public <P extends IPage<V>> P pageVo(IPage<T> page, Wrapper<T> queryWrapper) {
        return getBaseMapper().selectVoPage(page, queryWrapper, voClass);
    }

}
