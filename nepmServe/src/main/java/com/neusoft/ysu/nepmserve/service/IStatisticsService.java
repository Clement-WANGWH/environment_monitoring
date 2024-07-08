package com.neusoft.ysu.nepmserve.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ysu.nepmserve.entity.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.ysu.nepmserve.entity.vo.StatisticsVo;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-27
 */
public interface IStatisticsService extends IService<Statistics> {

    CommonResult findAll();

    CommonResult findBySql(StatisticsVo statisticsVo);

    CommonResult findByGidHistory(int gid);

    CommonResult findByFid(int fid);

    CommonResult insert(Statistics statistics);

    CommonResult update(Statistics statistics);

    CommonResult statisticDistribution();

    CommonResult statisticTrend();

    CommonResult statisticByProGroup();

    IPage<Statistics> pagination4Index(int index, int count, int id);

    CommonResult findAllByPage(int current,int size);
}
