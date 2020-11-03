package com.ruoyi.ai.face.service;


import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageInfo;

import com.ruoyi.ai.face.domain.UserCompareInfo;
import com.ruoyi.ai.face.domain.UserFaceInfo;
import com.ruoyi.ai.face.util.UserRamCache;


import java.util.List;


public interface FaceEngineService {

    /** 人脸检测 */
    List<FaceInfo> detectFaces(ImageInfo imageInfo);

    /** 1v1 人脸比对 */
    Float compareFace(ImageInfo imageInfo1, ImageInfo imageInfo2) ;

    /** 人脸建模数据*/
    byte[] extractFaceFeature(ImageInfo imageInfo, FaceInfo faceInfo);


    /** 人脸识别功能 */
    List<UserCompareInfo> faceRecognition(byte[] faceFeature, List<UserRamCache.UserInfo> userInfoList, float passRate) ;


    /** 人脸属性检测 */
    List<UserFaceInfo> process(ImageInfo imageInfo, List<FaceInfo> faceInfoList);






}
