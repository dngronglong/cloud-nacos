package com.example.security.service.impl;

import com.example.security.cache.SystemDataCache;
import com.example.security.config.WebSecurityConfig;
import com.example.security.damain.CustomData;
import com.example.security.damain.ResultDetails;
import com.example.security.exception.CustomizeException;
import com.example.security.service.SystemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SystemDataServiceImpl implements SystemDataService {

    private final SystemDataCache systemDataCache;

    @Autowired
    public SystemDataServiceImpl(SystemDataCache systemDataCache) {
        this.systemDataCache = systemDataCache;
    }

    @Override
    public List<CustomData> get() {
        return new ArrayList<>(systemDataCache.getMap().values());
    }

    @Override
    public CustomData select(String id) throws CustomizeException {
        int dataId;
        try {
            dataId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException("非法的参数输入！");
        }
        CustomData customData = systemDataCache.getMap().get(dataId);
        if (customData == null) {
            throw new CustomizeException("没有找到这个数据！");
        }
        return customData;
    }

    @Override
    public ResultDetails delete(String id, String authorities) throws CustomizeException {
        CustomData customData = select(id);
        ResultDetails resultDetails = new ResultDetails();
        resultDetails.setStatus(HttpStatus.OK.value());
        resultDetails.setMessage("删除成功！");
        resultDetails.setSuccess(true);
        resultDetails.setTimestamp(LocalDateTime.now());
        if (authorities.equals(WebSecurityConfig.ADMIN)) {
            systemDataCache.getMap().remove(customData.getId());
            return resultDetails;
        } else {
            if (customData.getData().charAt(0) == '#') {
                systemDataCache.getMap().remove(customData.getId());
                return resultDetails;
            } else {
                throw new CustomizeException("权限不足");
            }
        }
    }

    @Override
    public CustomData create(CustomData customData) {
        int id = systemDataCache.getMap().size();
        customData.setId(id);
        systemDataCache.getMap().put(id, customData);
        return customData;
    }
}
