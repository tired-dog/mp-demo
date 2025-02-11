package com.itheima.mp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tired_dog
 * @date 2025年01月26日 15:26
 */
@Api(tags = "用戶管理接口")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    @PostMapping
    @ApiOperation("新增用户接口")
    public void saveUser(@RequestBody UserFormDTO userDTO){

        User user = BeanUtil.copyProperties(userDTO, User.class);
        userService.save(user);
    }

    @ApiOperation("刪除用户接口")
    @DeleteMapping("{id}")
    public void deleteUserById(@ApiParam("用戶id") @PathVariable("id") Long id){
        userService.removeById(id);
    }

    @ApiOperation("根据id查询用户接口")
    @GetMapping ("{id}")
    public UserVO queryUserById(@ApiParam("用戶id") @PathVariable("id") Long id){
        User user = userService.getById(id);
        return BeanUtil.copyProperties(user,UserVO.class);
    }

    @ApiOperation("根据id批量查询用户接口")
    @GetMapping
    public List<UserVO> queryUserByIds(@ApiParam("用戶id集合") @RequestParam("ids") List<Long> ids){
        List<User> users = userService.listByIds(ids);
        return BeanUtil.copyToList(users,UserVO.class);
    }
}
