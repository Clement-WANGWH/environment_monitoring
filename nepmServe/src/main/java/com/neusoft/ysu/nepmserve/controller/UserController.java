package com.neusoft.ysu.nepmserve.controller;


import com.neusoft.ysu.nepmserve.entity.User;
import com.neusoft.ysu.nepmserve.service.IUserService;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-26
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService service;

    //查找网格员
    @GetMapping("findGridmanByTid/{tId}")
    public CommonResult findGridmanByTid(@PathVariable("tId") int tId){
        return service.findGridmanByTid(tId);
    }

    //查找公众监督员
    @GetMapping("/findSup")
    public CommonResult findSup(){
        return service.findSup();
    }

    //查找网格员
    @GetMapping("/findGrid")
    public CommonResult findGrid(){
        return service.findGrid();
    }

    @GetMapping("/findByName/{name}")
    public CommonResult findByName(@PathVariable("name")String name){
        return service.findByName(name);
    }

    @GetMapping("/fingAvatarByUsername/{username}")
    public CommonResult fingAvatarByUsername(@PathVariable("username")String username){
        return service.fingAvatarByUsername(username);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user){
        return service.update(user);
    }

    @PostMapping("/uploadAvatar")
    public CommonResult uploadAvatar(@RequestBody User user){
        return service.uploadAvatar(user);
    }

    @PutMapping("/register")
    public CommonResult register(@RequestBody User user) {
        return service.register(user);
    }

    @PutMapping("/addGridman")
    public CommonResult addGridman(@RequestBody User user){
        return service.addGridman(user);
    }

    @PostMapping("/registerWithAvatar")
    public CommonResult registerWithAvatar(@RequestParam("avatar") MultipartFile avatar, @RequestBody User user) throws IOException {
        return service.registerWithAvatar(avatar,user);
    }

    @PostMapping ("/login")
    public CommonResult login(String loginName,String password) throws UnsupportedEncodingException {
        System.out.println("UserController.login");
        return service.login(loginName,password);
    }
}

