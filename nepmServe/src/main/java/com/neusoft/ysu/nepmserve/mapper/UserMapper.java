package com.neusoft.ysu.nepmserve.mapper;

import com.neusoft.ysu.nepmserve.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where id=#{id}")
    User selectById(int id);

    @Select("select * from user where tId=#{tId} and roleId=2 and state!=2 and state!=3 order by state asc")
    List<User> selectGridmanByTid(int tId);

    @ResultMap("userMap")
    @Select("select * from user where roleId=3")
    List<User> selectSup();

    @ResultMap("userMap")
    @Select("select * from user where roleId=2 and state!=3")
    List<User> selectGrid();

    @ResultMap("userMap")
    @Select("SELECT * FROM user WHERE name like concat('%',#{name},'%')")
    List<User> selectByName(String name);
}
