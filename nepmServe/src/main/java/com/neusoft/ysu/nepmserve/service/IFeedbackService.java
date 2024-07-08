package com.neusoft.ysu.nepmserve.service;

import com.neusoft.ysu.nepmserve.entity.vo.FeedbackVo;
import com.neusoft.ysu.nepmserve.entity.vo.fVo;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import com.neusoft.ysu.nepmserve.entity.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-25
 */
public interface IFeedbackService extends IService<Feedback> {

    CommonResult findAll();

    CommonResult findAllByPage(int current, int size);

    CommonResult findBySql(FeedbackVo feedbackVo);

    CommonResult findBySid(int sid);

    CommonResult findBySidByPage(fVo fVo);

    CommonResult findByGid(int gid);

    CommonResult insert(Feedback feedback);
    
    CommonResult deleteById(int id);

    CommonResult update(Feedback feedback);
}
