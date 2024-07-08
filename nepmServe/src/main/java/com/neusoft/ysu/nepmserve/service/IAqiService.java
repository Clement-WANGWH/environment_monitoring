package com.neusoft.ysu.nepmserve.service;

import com.neusoft.ysu.nepmserve.entity.Aqi;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-24
 */
public interface IAqiService extends IService<Aqi> {

    CommonResult findAll();
}
