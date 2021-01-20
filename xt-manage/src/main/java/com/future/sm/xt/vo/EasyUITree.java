package com.future.sm.xt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITree {
    private Long id;
    private String text;
    private String state;      //open or closed

}
