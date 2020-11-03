package com.ruoyi.ai.face.service.impl;

import com.arcsoft.face.toolkit.ImageInfo;

import com.ruoyi.ai.face.domain.UserFaceInfo;
import com.ruoyi.ai.face.mapper.UserFaceInfoMapper;
import com.ruoyi.ai.face.service.UserFaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hanzhenyong
 * @create 2020-10-22 22:11
 */
@Service
public class UserFaceInfoServiceImpl implements UserFaceInfoService {

    @Autowired
    protected UserFaceInfoMapper userFaceInfoMapper;

    @Override
    public int save(UserFaceInfo userFaceInfo) {
        return userFaceInfoMapper.insert(userFaceInfo);
    }
}
