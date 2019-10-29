package com.example.plusdemo.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Project :
 * @Package Name : com.example.plusdemo.common
 * @Description : 
 * @Author : zhangbin
 * @Create Date : 2019-10-29 15:33
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 删除标识
     */
    @TableLogic
    private Boolean isDeleted;
}
