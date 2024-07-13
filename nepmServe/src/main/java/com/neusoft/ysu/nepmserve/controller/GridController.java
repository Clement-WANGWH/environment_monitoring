package com.neusoft.ysu.nepmserve.controller;


import com.neusoft.ysu.nepmserve.service.IGridService;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-26
 */
@CrossOrigin
@RestController
@RequestMapping("/grid")
public class GridController {

    @Autowired
    IGridService service;
    @GetMapping("/findProvince")
    public CommonResult findProvince(){
        return service.findProvince();
    }

    @GetMapping("/findCityByPid/{pid}")
    public CommonResult findCityByPid(@PathVariable("pid") int pid){
        return service.findCityByPid(pid);
    }

    @GetMapping("/findTownByCid/{cid}")
    public CommonResult findTownByCid(@PathVariable("cid") int cid){
        return service.findTownByCid(cid);
    }

    @GetMapping("/findTidByName/{name}")
    public CommonResult findTidByName(@PathVariable("name") String name){
        return service.findTidByName(name);
    }
}
