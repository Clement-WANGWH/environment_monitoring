package com.neusoft.ysu.nepmserve.mapper;

import com.neusoft.ysu.nepmserve.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.ysu.nepmserve.entity.vo.FeedbackVo;
import com.neusoft.ysu.nepmserve.entity.vo.fVo;
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
 * @since 2023-08-25
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {

    @ResultMap("feedbackMap")
    @Select("select * from feedback ORDER BY id DESC")
    List<Feedback> selectAll();

    @ResultMap("feedbackMap")
    @Select("select * from feedback where id=#{id}")
    List<Feedback> selectById(int id);

    //公众监督员反馈列表
    @ResultMap("feedbackMap")
    @Select("select * from feedback where sId=#{sId}")
    List<Feedback> selectBySid(int sId);

    //网格员任务列表
    @ResultMap("feedbackMap")
    @Select("select * from feedback where gId=#{gId} and state!=2")
    List<Feedback> selectByGid(int gId);

    //条件查询
    @ResultMap("feedbackMap")
    List<Feedback> selectBySql(FeedbackVo feedbackVo);

    //分页查询所有
    @ResultMap("feedbackMap")
    @Select("SELECT * FROM feedback ORDER BY id DESC LIMIT #{arg0},#{arg1}")
    List<Feedback> selectAllByPage(int start, int size);

    @ResultMap("feedbackMap")
//    @Select("SELECT * FROM feedback WHERE sId=#{arg0} ORDER BY fDate DESC LIMIT #{arg1},#{arg2}")
    List<Feedback> selectBySidByPage(fVo fVo);
}
