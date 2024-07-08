package com.neusoft.ysu.nepmserve.service.impl;

import com.neusoft.ysu.nepmserve.entity.Grid;
import com.neusoft.ysu.nepmserve.mapper.GridMapper;
import com.neusoft.ysu.nepmserve.service.IGridService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-26
 */
@Service
public class GridServiceImpl extends ServiceImpl<GridMapper, Grid> implements IGridService {

    @Autowired
    GridMapper mapper;

    @Override
    public CommonResult findProvince() {
        List<Grid> list=mapper.selectProvince();
        CommonResult result = new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findCityByPid(int pid) {
        List<Grid> list=mapper.selectCityByPid(pid);
        CommonResult result = new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findTownByCid(int cid) {
        List<Grid> list=mapper.selectTownByCid(cid);
        CommonResult result = new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findTidByName(String name) {
        int tid=mapper.selectIdByName(name);
        CommonResult result=new CommonResult(tid);
        if (tid<=0){
            result.setNotFound();
        }
        return result;
    }
}
