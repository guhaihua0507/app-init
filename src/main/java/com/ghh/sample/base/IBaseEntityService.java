package com.ghh.sample.base;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;

public interface IBaseEntityService<T> {
    AppBaseMapper<T> getEntityMapper();

    T selectByPK(Object id);

    int insert(T entity);

    int update(T entity);

    int updateByPKSelective(T entity);

    int updateByConditionSelective(T record, Condition condition);

    void deleteByPK(Object id);

    List<T> selectAll();

    List<T> selectByCondition(Condition condition);

    int updateByCondition(T record, Condition condition);

    void deleteByCondition(Condition condition);
}
