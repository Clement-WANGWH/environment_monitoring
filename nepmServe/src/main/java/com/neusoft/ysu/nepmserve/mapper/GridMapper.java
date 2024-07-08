package com.neusoft.ysu.nepmserve.mapper;

import com.neusoft.ysu.nepmserve.entity.Grid;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface GridMapper extends BaseMapper<Grid> {

    @Select("select * from grid where id=#{id}")
    Grid selectById(int id);

    @Select("SELECT id FROM grid where name=#{name}")
    int selectIdByName(String name);

    @Select("SELECT * FROM grid WHERE leaderId=0")
    List<Grid> selectProvince();

    @Select("select * from grid where leaderId=#{leaderId}")
    List<Grid> selectCityByPid(int leaderId);

    @Select("select * from grid where leaderId=#{leaderId}")
    List<Grid> selectTownByCid(int leaderId);
}
