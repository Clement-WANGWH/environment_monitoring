package com.neusoft.ysu.nepmserve.service;

import com.neusoft.ysu.nepmserve.entity.State;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-30
 */
public interface IStateService extends IService<State> {

    CommonResult findAll();
}
