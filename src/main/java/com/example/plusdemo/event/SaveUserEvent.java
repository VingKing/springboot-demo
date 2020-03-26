package com.example.plusdemo.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author VingKing
 */
@Data
@AllArgsConstructor
public class SaveUserEvent implements Serializable {

    private Long userId;

    private String name;
}
