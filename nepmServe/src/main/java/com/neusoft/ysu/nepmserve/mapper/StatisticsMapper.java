package com.neusoft.ysu.nepmserve.mapper;

import com.neusoft.ysu.nepmserve.entity.Feedback;
import com.neusoft.ysu.nepmserve.entity.Statistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.ysu.nepmserve.entity.vo.StatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-27
 */
@Mapper
public interface StatisticsMapper extends BaseMapper<Statistics> {

    @ResultMap("statisticsMap")
    @Select("select * from statistics")
    List<Statistics> selectAll();

    @ResultMap("statisticsMap")
    List<Statistics> selectBySql(StatisticsVo statisticsVo);

    @ResultMap("statisticsMap")
    @Select("select * from statistics where gId=#{gId}")
    List<Statistics> selectByGidHistory(int gId);

//    @Select("select(select category from aqi where id=#{aqiId}) as category,(select count(*) as num from statistics where aqiId =#{aqiId}) as nums")
    List<HashMap<String,Object>> selectDistribution();

    List<HashMap<String,Object>> selectTrend();

    List<HashMap<String,Object>> selectByProGroup();

    @ResultMap("statisticsMap")
    @Select("SELECT * FROM statistics ORDER BY confirmDate DESC LIMIT #{arg0},#{arg1}")
    List<Statistics> selectAllByPage(int start,int rows);

    @Select("SELECT * FROM statistics WHERE fId=#{fid}")
    Statistics selectByFid(int fid);
}
