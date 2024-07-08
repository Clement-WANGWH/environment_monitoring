package com.neusoft.ysu.nepmserve.controller;

import com.neusoft.ysu.nepmserve.entity.Statistics;
import com.neusoft.ysu.nepmserve.entity.vo.StatisticsVo;
import com.neusoft.ysu.nepmserve.service.IStatisticsService;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-27
 */
@CrossOrigin
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    IStatisticsService service;

    @GetMapping("/findAll")
    public CommonResult findAll(){
        return service.findAll();
    }

    //分页
    @GetMapping("/findAllByPage/{current}/{size}")
    public CommonResult findAllByPage(@PathVariable("current") int current,@PathVariable("size")int size){
        return service.findAllByPage(current,size);
    }

    //条件查询
    @PostMapping("/findBySql")
    public CommonResult findBySql(@RequestBody StatisticsVo statisticsVo){
        return service.findBySql(statisticsVo);
    }


    @GetMapping("/findByGidHistory/{gid}")
    public CommonResult findByGidHistory(@PathVariable("gid") int gid){
        return service.findByGidHistory(gid);
    }

    @GetMapping("/findByFid/{fid}")
    public CommonResult findByFid(@PathVariable("fid")int fid){
        return service.findByFid(fid);
    }

    @PutMapping ("/add")
    public CommonResult insert(@RequestBody Statistics statistics){
        return service.insert(statistics);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody Statistics statistics){
        return service.update(statistics);
    }

    //AQI级别分布统计
    @GetMapping("/distribution")
    public CommonResult statisticDistribution(){
        return service.statisticDistribution();
    }

    //AQI趋势统计  按月份
    @GetMapping("/trend")
    public CommonResult statisticTrend(){
        return service.statisticTrend();
    }

    //省分组统计
    @GetMapping("/proGroup")
    public CommonResult statisticByProGroup(){
        return service.statisticByProGroup();
    }

}
