package com.ghh.sample.mapper;

import com.ghh.sample.base.AppBaseMapper;
import com.ghh.sample.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends AppBaseMapper<User> {

    @Select("select * from t_user order by id desc")
    List<User> getAllUser();
}
