package com.ghh.sample.mapper;

import com.ghh.sample.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {

}
