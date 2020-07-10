package com.example.shop.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@TableName("shop")
@Data
@ToString
public class ShopEntity {
    @TableId
    private Integer id;
    @TableField
    private String name;
    @TableField("nick_name")
    private String nickName;
}
