package com.example.plusdemo.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Project :
 * @Package Name : com.example.plusdemo.event
 * @Description : 
 * @Author : zhangbin
 * @Create Date : 2019-11-07 14:45
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Data
@AllArgsConstructor
public class SaveUserEvent implements Serializable {

    private Long userId;

    private String name;
}
