package com.neusoft.ysu.nepmserve.mapper;

import com.neusoft.ysu.nepmserve.entity.Aqi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-24
 */
@Mapper
public interface AqiMapper extends BaseMapper<Aqi> {

    @Select("select * from aqi where id=#{id}")
    Aqi selectById(int id);

}
