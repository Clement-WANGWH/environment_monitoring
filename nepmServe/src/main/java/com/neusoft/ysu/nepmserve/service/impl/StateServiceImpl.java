package com.neusoft.ysu.nepmserve.service.impl;

import com.neusoft.ysu.nepmserve.entity.State;
import com.neusoft.ysu.nepmserve.mapper.StateMapper;
import com.neusoft.ysu.nepmserve.service.IStateService;
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
 * @since 2023-08-30
 */
@Service
public class StateServiceImpl extends ServiceImpl<StateMapper, State> implements IStateService {

    @Autowired
    StateMapper mapper;

    @Override
    public CommonResult findAll() {
        List<State> list=mapper.selectList(null);
        CommonResult result=new CommonResult(list);
        return result;
    }
}
