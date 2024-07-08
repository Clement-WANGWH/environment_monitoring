package com.neusoft.ysu.nepmserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ysu.nepmserve.entity.Feedback;
import com.neusoft.ysu.nepmserve.entity.Statistics;
import com.neusoft.ysu.nepmserve.entity.vo.StatisticsVo;
import com.neusoft.ysu.nepmserve.mapper.StatisticsMapper;
import com.neusoft.ysu.nepmserve.service.IStatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-27
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements IStatisticsService {

    @Autowired
    StatisticsMapper mapper;

    @Override
    public CommonResult findAll() {
        List<Statistics> list=mapper.selectAll();
        CommonResult result = new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }
    @Override
    public CommonResult findAllByPage(int current, int size) {
//        mybatisplus提供的分页
//        IPage<Statistics> page=new Page<>(current,10);
//        IPage<Statistics> iPage=mapper.selectPage(page,null);
//        for (int i = 0; i < iPage.getRecords().size(); i++) {
//            Statistics statistics = iPage.getRecords().get(i);
//            User user =  userMapper.selelctById(statistics.getgId());
//        }
//        CommonResult result=new CommonResult(iPage);
//        if (iPage==null||iPage.getSize()==0){
//            result.setNotFound();
//        }

        int start=(current-1)*size;
        List<Statistics> list=mapper.selectAllByPage(start,size);
        List<Statistics> getTotal=mapper.selectList(null);
        int total=getTotal.size();
//        int total=(int)Math.ceil(size/10);
//        System.out.println(total);
        CommonResult result=new CommonResult(String.valueOf(total),list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findBySql(StatisticsVo statisticsVo) {
        List<Statistics> list=mapper.selectBySql(statisticsVo);
        int total=list.size();
        CommonResult result=new CommonResult(String.valueOf(total),list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findByGidHistory(int gid) {
        List<Statistics> list=mapper.selectByGidHistory(gid);
        CommonResult result = new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findByFid(int fid) {
        Statistics statistics=mapper.selectByFid(fid);
        CommonResult result=new CommonResult(statistics);
        if (statistics==null){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult insert(Statistics statistics) {
        statistics.setId(null);
        int i = mapper.insert(statistics);
        CommonResult result = new CommonResult(statistics);
        if(i<=0){
            result.setNotInserted();
        }
        return result;
    }

    @Override
    public CommonResult update(Statistics statistics) {
        int i=mapper.updateById(statistics);
        CommonResult result=new CommonResult(i);
        if(i<=0){
            result.setNotUpdate();
        }
        return result;
    }

    @Override
    public CommonResult statisticDistribution() {
        List<HashMap<String,Object>> list= mapper.selectDistribution();
        CommonResult result=new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult statisticTrend() {
        List<HashMap<String,Object>> list= mapper.selectTrend();
        CommonResult result=new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult statisticByProGroup() {
        List<HashMap<String,Object>> list= mapper.selectByProGroup();
        CommonResult result=new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public IPage<Statistics> pagination4Index(int index, int count, int id) {
        IPage<Statistics> page=new Page<>(index,count);
        QueryWrapper<Statistics> wrapper=new QueryWrapper<>();
        wrapper.gt("id",id);
//        wrapper.orderByAsc("")
        return mapper.selectPage(page,wrapper);
    }

}
