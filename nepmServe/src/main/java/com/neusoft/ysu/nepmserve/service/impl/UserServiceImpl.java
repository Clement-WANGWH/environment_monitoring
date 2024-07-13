package com.neusoft.ysu.nepmserve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neusoft.ysu.nepmserve.entity.User;
import com.neusoft.ysu.nepmserve.mapper.UserMapper;
import com.neusoft.ysu.nepmserve.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.ysu.nepmserve.util.JWTUtil;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper mapper;

    @Override
    public CommonResult findGridmanByTid(int tId) {
        List<User> list=mapper.selectGridmanByTid(tId);
        CommonResult result=new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findSup() {
        List<User> list=mapper.selectSup();
        CommonResult result=new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findGrid() {
        List<User> list=mapper.selectGrid();
        CommonResult result=new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findByName(String name) {
        List<User> list=mapper.selectByName(name);
        CommonResult result=new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult fingAvatarByUsername(String username) {
//        String path=this.getClass().getClassLoader().getResource("static/avatar/").getPath();
        String path="http://localhost:18081/avatar/";
        System.out.println(path);
        String filePath=path+username+".jpg";
        System.out.println(filePath);
        CommonResult result=new CommonResult(filePath);
        return result;
    }

    @Override
    public CommonResult update(User user) {
        int i=mapper.updateById(user);
        CommonResult result=new CommonResult(i);
        if(i<=0){
            result.setNotUpdate();
        }
        System.out.println(result);
        return result;
    }

    @Override
    public CommonResult uploadAvatar(User user) {
        String username=user.getUsername();
        System.out.println(username);
        String avatarName=user.getAvatar();
        System.out.println(avatarName);
        String avatar=username+".jpg";
        System.out.println(avatar);
        user.setAvatar(avatar);
        int i=mapper.updateById(user);
        CommonResult result=new CommonResult(avatar);
        if(i<=0){
            result.setNotUpdate();
        }
        System.out.println(result);
        return result;
    }

    @Override
    public CommonResult register( User user) {
        CommonResult result=new CommonResult();
        QueryWrapper<User> wrapper1=new QueryWrapper<>();
        wrapper1.eq("username",user.getUsername());
        User exist1=mapper.selectOne(wrapper1);

        QueryWrapper<User> wrapper2=new QueryWrapper<>();
        wrapper2.eq("telephone",user.getTelephone());
        User exist2=mapper.selectOne(wrapper2);

        if (exist1!=null){
            result.usernameExist(); //104
        }
        else if(exist2!=null){
            result.phoneExist(); //105
        }
        else {
            int i=mapper.insert(user);
            if (i<=0){
                result.setNotInserted(); //101
            }
            else {
                result.setOK(user);
            }
        }
        return result;
    }

    @Override
    public CommonResult addGridman(User user) {
        user.setId(null);
        int i=mapper.insert(user);
        CommonResult result = new CommonResult(user);
        if(i<=0){
            result.setNotInserted();
        }
        return result;
    }

    private String saveAvatarFile(MultipartFile avatar) throws IOException {
        // 生成唯一的文件名
        String fileName = UUID.randomUUID().toString() + "." + getFileExtension(avatar.getOriginalFilename());

        // 保存头像文件的逻辑
        // ...

        // 返回保存的文件路径
        return "/uploads/" + fileName;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    @Override
    public CommonResult registerWithAvatar(MultipartFile avatar,User user) throws IOException {
        // 将头像保存到数据库
//        byte[] avatarBytes;
//        try {
//            avatarBytes = avatar.getBytes();
//        } catch (IOException e) {
//            throw new RuntimeException("头像上传失败", e);
//        }
//        user.setAvatar(avatarBytes);
        try {
            // 保存用户头像文件
            String avatarUrl = saveAvatarFile(avatar);
            user.setAvatar(avatarUrl);
            return getCommonResult(user);
        }catch (IOException e) {
            CommonResult result=new CommonResult();
            result.wrongAvatar();
            return result;
        }
    }

    private CommonResult getCommonResult(User user) {
        CommonResult result=new CommonResult();
        QueryWrapper<User> wrapper1=new QueryWrapper<>();
        wrapper1.eq("username",user.getUsername());
        User exist1=mapper.selectOne(wrapper1);

        QueryWrapper<User> wrapper2=new QueryWrapper<>();
        wrapper2.eq("telephone",user.getTelephone());
        User exist2=mapper.selectOne(wrapper2);

        if (exist1!=null){
            result.usernameExist(); //104
        }
        else if(exist2!=null){
            result.phoneExist(); //105
        }
        else {
            int i=mapper.insert(user);
            if (i<=0){
                result.setNotInserted(); //101
            }
            else {
                result.setOK(user);
            }
        }
        return result;
    }

    @Override
    public CommonResult login(String loginName, String password) throws UnsupportedEncodingException {
        String phone="^[1][23456789]\\d{9}$";
        //手机号登录
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        if (loginName.matches(phone)){
            wrapper.eq("telephone",loginName);
            wrapper.eq("password",password);
            User byPhone=mapper.selectOne(wrapper);
            CommonResult result=new CommonResult();
            if (byPhone!=null&&loginName.equals(byPhone.getTelephone())){//登录名正确
                HashMap<String,String> map=new HashMap<>();
                map.put("telephone",loginName);
                map.put("password",password);
                String token= JWTUtil.buildToken(map);
                //为实体类注入token
                byPhone.setToken(token);
                result.setOK(byPhone);
            } else {
                result.setNotFound();//100
            }
            return result;
        }
        //用户名登录
        else {
            wrapper.eq("username",loginName);
            wrapper.eq("password",password);
            User byUsername=mapper.selectOne(wrapper);
            CommonResult result=new CommonResult();
            if (byUsername!=null&&loginName.equals(byUsername.getUsername())){//登录名正确
                HashMap<String,String> map=new HashMap<>();
                map.put("username",loginName);
                map.put("password",password);
                String token= JWTUtil.buildToken(map);
                //为实体类注入token
                byUsername.setToken(token);
                result.setOK(byUsername);
            }
            else {
                result.setNotFound();
            }
            return result;
        }
    }
}
