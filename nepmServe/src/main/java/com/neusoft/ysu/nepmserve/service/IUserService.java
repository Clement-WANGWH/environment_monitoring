package com.neusoft.ysu.nepmserve.service;

import com.neusoft.ysu.nepmserve.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-26
 */
public interface IUserService extends IService<User> {

    CommonResult findGridmanByTid(int tId);

    CommonResult findSup();

    CommonResult findGrid();

    CommonResult findByName(String name);

    CommonResult fingAvatarByUsername(String username);

    CommonResult update(User user);

    CommonResult uploadAvatar(User user);

    CommonResult register(User user);

    CommonResult addGridman(User user);

    CommonResult registerWithAvatar(MultipartFile avatar, User user) throws IOException;

    CommonResult login(String loginName, String password) throws UnsupportedEncodingException;
}
