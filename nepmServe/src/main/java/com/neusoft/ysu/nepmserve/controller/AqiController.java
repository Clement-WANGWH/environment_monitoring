package com.neusoft.ysu.nepmserve.controller;


import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import com.neusoft.ysu.nepmserve.service.IAqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-24
 */
@CrossOrigin
@RestController
@RequestMapping("/aqi")
public class AqiController {

    @Autowired
    IAqiService service;

    @GetMapping("/findAll")
    public CommonResult findAll(){
        return service.findAll();
    }
}
