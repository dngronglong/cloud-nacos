package com.example.security.service;

import com.example.security.damain.CustomData;
import com.example.security.damain.ResultDetails;
import com.example.security.exception.CustomizeException;

import java.util.List;

public interface SystemDataService {
    List<CustomData> get();

    CustomData select(String id) throws CustomizeException;

    ResultDetails delete(String id, String authorities) throws CustomizeException;

    CustomData create(CustomData customData);
}
