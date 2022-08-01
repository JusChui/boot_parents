package com.boot.slaver.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TestSlaverMapper.interface
 * @Author Xuhui
 * @Date 2022/7/29 12:42
 * @Description
 */
public interface TestSlaverMapper {

    @Select("select * from pg_tables WHERE schemaname = 'public'")
    List<Map> getSlaverDatas();

}
