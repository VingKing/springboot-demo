package com.example.plusdemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.plusdemo.entity.User;
import com.example.plusdemo.event.SaveUserEvent;
import com.example.plusdemo.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.plusdemo.common.BaseController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangbin
 * @since 2019-10-29
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id) {
        User user = iUserService.getById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * 集合查询
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity getUserList() {
        List<User> list = iUserService.list();
        return ResponseEntity.ok(list);
    }

    /**
     * 分页+动态条件查询
     */
    @GetMapping("/page")
    public ResponseEntity getUserListPage(Page page, User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(user.getName()), "name", user.getName());
        queryWrapper.lt(user.getAge() != null, "age", user.getAge());
        // 排序
        queryWrapper.orderByAsc("age");
        IPage result = iUserService.page(page, queryWrapper);
        return ResponseEntity.ok(result);
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody User user) {
        boolean save = iUserService.save(user);
        rocketMQTemplate.convertAndSend("save_user",new SaveUserEvent(user.getId(),user.getName()));
        return ResponseEntity.ok(save);
    }

    /**
     * 主键更新
     */
    @PutMapping("/{id}/update")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        boolean save = iUserService.updateById(user);
        return ResponseEntity.ok(save);
    }

    /**
     * 条件更新
     */
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 将年龄大于30的更新
        updateWrapper.gt("age", 30);
        // plus自动不会更新null属性的值
        boolean save = iUserService.update(user, updateWrapper);
        return ResponseEntity.ok(save);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable Long id) {
        // 如果配置了逻辑删除，则默认执行update逻辑删除
        boolean b = iUserService.removeById(id);
        return ResponseEntity.ok(b);
    }
}
