package com.neusoft.ysu.nepmserve.service.impl;

import com.neusoft.ysu.nepmserve.entity.Aqi;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import com.neusoft.ysu.nepmserve.mapper.AqiMapper;
import com.neusoft.ysu.nepmserve.service.IAqiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-24
 */
@Service
public class AqiServiceImpl extends ServiceImpl<AqiMapper, Aqi> implements IAqiService {

    @Autowired
    AqiMapper mapper;

    @Override
    public CommonResult findAll() {
        List<Aqi> list=mapper.selectList(null);
        CommonResult result=new CommonResult(list);
        return result;
    }
}
