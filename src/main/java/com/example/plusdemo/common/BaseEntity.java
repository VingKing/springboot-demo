package com.example.plusdemo.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author VingKing
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
