package com.neusoft.ysu.nepmserve.service;

import com.neusoft.ysu.nepmserve.entity.Grid;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-26
 */
public interface IGridService extends IService<Grid> {

    CommonResult findProvince();

    CommonResult findCityByPid(int pid);

    CommonResult findTownByCid(int cid);

    CommonResult findTidByName(String name);
}
