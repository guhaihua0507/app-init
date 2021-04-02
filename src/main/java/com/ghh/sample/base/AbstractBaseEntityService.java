package com.ghh.sample.base;

import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractBaseEntityService<T> implements IBaseEntityService<T> {
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public T selectByPK(Object id) {
        return getEntityMapper().selectByPrimaryKey(id);
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<T> selectAll() {
        return getEntityMapper().selectAll();
    }

    /**
     * 根据条件进行查询
     * @param condition
     * @return
     */
    @Override
    public List<T> selectByCondition(Condition condition) {
        return getEntityMapper().selectByCondition(condition);
    }

    /**
     * 新增一个实体
     * @param entity
     * @return
     */
    @Override
    public int insert(T entity) {
        return getEntityMapper().insert(entity);
    }

    /**
     * 根据主键更新实体，null值会被更新
     * @param entity
     * @return
     */
    @Override
    public int update(T entity) {
        return getEntityMapper().updateByPrimaryKey(entity);
    }

    /**
     * 根据主键更新属性不为null的值
     * @param entity
     * @return
     */
    @Override
    public int updateByPKSelective(T entity) {
        return getEntityMapper().updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据Condition条件更新实体`record`包含的全部属性，null值会被更新
     * @param record
     * @param condition
     * @return
     */
    @Override
    public int updateByCondition(T record, Condition condition) {
        return getEntityMapper().updateByCondition(record, condition);
    }

    /**
     * 根据Condition条件更新实体`record`包含的不是null的属性值
     * @param record
     * @param condition
     * @return
     */
    @Override
    public int updateByConditionSelective(T record, Condition condition) {
        return getEntityMapper().updateByConditionSelective(record, condition);
    }

    /**
     * 根据主键删除实体
     * @param id
     */
    @Override
    public void deleteByPK(Object id) {
        getEntityMapper().deleteByPrimaryKey(id);
    }

    /**
     * 根据Condition条件删除数据
     * @param condition
     */
    @Override
    public void deleteByCondition(Condition condition) {
        getEntityMapper().deleteByCondition(condition);
    }

    public Class<?> getEntityClass() {
        Type type = this.getClass().getGenericSuperclass();
        Type[] paramTypes = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) paramTypes[0];
    }
}
