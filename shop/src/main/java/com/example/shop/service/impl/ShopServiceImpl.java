package com.example.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.utils.Result;
import com.example.shop.entity.ShopEntity;
import com.example.shop.mapper.ShopMapper;
import com.example.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public Result findAll() {
        QueryWrapper wrapper=new QueryWrapper<ShopEntity>();
        List<ShopEntity> list=shopMapper.selectList(wrapper);
        return Result.success(list);
    }
}
