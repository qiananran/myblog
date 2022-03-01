package com.dyilin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: myblog
 * @Author: Dyilin
 * @Description:
 * @create: 2020-12-09 13:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogQuery {

    private String title;
    private Long typeId;
    private Long tagId;
    private boolean recommend;

}
