package com.dyilin.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @program: myblog
 * @Author: Dyilin
 * @Description:
 * @create: 2020-12-08 21:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeOrTagBlog {

    private BigInteger id;
    private String name;
    private BigInteger blog_number;
}
