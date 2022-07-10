package com.boot.business.testDatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @File: MasterMapper.interface
 * @Author: JusChui
 * @CreateTime: 2022-07-10 12:58:12
 * @Description:
 */
//@DS("slaver")
//@Mapper
public interface MasterMapper {

    @DS("slaver")
    @Select("select * from sys_books")
    List<Map<String, Object>> getBooks();

    @DS("master")
    @Select("select * from s_user")
    List<Map<String, Object>> getAll();
}
