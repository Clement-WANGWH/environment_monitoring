package com.neusoft.ysu.nepmserve;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ysu.nepmserve.entity.Statistics;
import com.neusoft.ysu.nepmserve.mapper.StatisticsMapper;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class NepmServeApplicationTests {

    @Autowired
    StatisticsMapper statisticsMapper;

    @Test
    public void testIPage(){
        int index=1;
        int count=6;
        IPage<Statistics> page=new Page<>(index,count);
        IPage<Statistics> rest=statisticsMapper.selectPage(page,null);
        List<Statistics> records=rest.getRecords();
        System.out.println(Arrays.toString(records.toArray()));
    }

    @Test
    void testDistribution(){
        List<HashMap<String,Object>> list = statisticsMapper.selectDistribution();
        CommonResult result=new CommonResult(list);
        System.out.println(result);
    }

    @Test
    void testTrend() {
        List<HashMap<String,Object>> list=statisticsMapper.selectTrend();
//        for (int i=0;i<list.size();i++){
//            HashMap<String,Object> hashMap=list.get(i);
//            for (String key:hashMap.keySet()){
//                System.out.println( hashMap.get(key));
//            }
//        }
        CommonResult result=new CommonResult(list);
        System.out.println(result);
    }

    @Test
    void testProGroup(){
        List<HashMap<String,Object>> list=statisticsMapper.selectByProGroup();
        CommonResult result=new CommonResult(list);
        System.out.println(result);
    }

}
