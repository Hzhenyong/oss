package com.ruoyi.web.controller.ai;

import cn.hutool.core.collection.CollectionUtil;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.ai.face.domain.UserCompareInfo;
import com.ruoyi.ai.face.domain.UserFaceInfo;
import com.ruoyi.ai.face.rpc.Response;
import com.ruoyi.ai.face.service.FaceEngineService;
import com.ruoyi.ai.face.service.UserFaceInfoService;
import com.ruoyi.ai.face.util.Base64Util;
import com.ruoyi.ai.face.util.UserRamCache;
import com.ruoyi.common.utils.file.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author hanzhenyong
 * @create 2020-11-02 22:31
 */
@Api("人脸模块")
@RestController
@RequestMapping("/ai/face")
public class FaceController {

    @Autowired
    private FaceEngineService faceEngineService;

    @Autowired
    private UserFaceInfoService userFaceService;

//    @Value("${server.port}")
//    private int port;


    //初始化注册人脸，注册到本地内存
//    @PostConstruct
//    public void initFace() throws FileNotFoundException {
//        Map<String, String> fileMap = Maps.newHashMap();
//        fileMap.put("zhao1", "赵丽颖");
//        fileMap.put("yang1", "杨紫");
//        //fileMap.put("yxxsj", "寇萌萌");
//
//        for (String f : fileMap.keySet()) {
//            File file = ResourceUtils.getFile("classpath:static/images/" + f + ".jpg");
//            ImageInfo rgbData = ImageFactory.getRGBData(file);
//            List<FaceInfo> faceInfoList = faceEngineService.detectFaces(rgbData);
//            if (CollectionUtil.isNotEmpty(faceInfoList)) {
//                byte[] feature = faceEngineService.extractFaceFeature(rgbData, faceInfoList.get(0));
//                UserRamCache.UserInfo userInfo = new UserCompareInfo();
//                UserFaceInfo user = new UserFaceInfo();
//                //user.setFaceId(f);
//                user.setName(fileMap.get(f));
//                user.setFaceFeature(feature);
//                //userFaceService.save(user);
//                //UserRamCache.addUser(userInfo);
//            }
//        }
//
//        //log.info("http://127.0.0.1:" + port + "/");
//
//    }


    /*
    人脸添加
     */
    @RequestMapping(value = "/faceAdd", method = RequestMethod.POST)
    @ResponseBody
    public Response faceAdd(String file, String faceId, String name) {
        return null;
    }

    /*
    人脸识别
     */
    @RequestMapping(value = "/faceRecognition", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("人脸识别")
    public Response<List<UserFaceInfo>> faceRecognition(String image) {

        List<UserFaceInfo> faceRecognitionResDTOList = Lists.newLinkedList();
        byte[] bytes = Base64Util.base64ToBytes(image);
        ImageInfo rgbData = ImageFactory.getRGBData(bytes);
        List<FaceInfo> faceInfoList = faceEngineService.detectFaces(rgbData);
        if (CollectionUtil.isNotEmpty(faceInfoList)) {
            for (FaceInfo faceInfo : faceInfoList) {
                UserFaceInfo faceRecognitionResDTO = new UserFaceInfo();

                byte[] feature = faceEngineService.extractFaceFeature(rgbData, faceInfo);
                if (feature != null) {
                    List<UserCompareInfo> userCompareInfos = faceEngineService.faceRecognition(feature, UserRamCache.getUserList(), 0.8f);
                    if (CollectionUtil.isNotEmpty(userCompareInfos)) {

                    }
                }
                faceRecognitionResDTOList.add(faceRecognitionResDTO);
            }

        }


        return Response.newSuccessResponse(faceRecognitionResDTOList);
    }


    @RequestMapping(value = "/detectFaces", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("检测人脸并写到数据库")
    public Response<List<UserFaceInfo>> detectFaces(@RequestParam("file") MultipartFile file) throws IOException {

        //File newfile = FileUtils.changeFile(file);
        //byte[] bytes = Base64Util.base64ToBytes(image);
        byte[] bytes = file.getBytes();
        ImageInfo rgbData = ImageFactory.getRGBData(bytes);

        List<FaceInfo> faceInfoList = faceEngineService.detectFaces(rgbData);
        List<UserFaceInfo> process = null;
        if (CollectionUtil.isNotEmpty(faceInfoList)) {
            process = faceEngineService.process(rgbData, faceInfoList);

            for (int i = 0; i < faceInfoList.size(); i++) {
                userFaceService.save(process.get(i));
                //userFaceService.




            }
        }

        //List<UserFaceInfo> process;
        return Response.newSuccessResponse(process);
    }

    @RequestMapping(value = "/compareFaces", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("1V1比对结果")
    public Response<Float> compareFaces(@RequestParam("face1") MultipartFile face1, @RequestParam("face2") MultipartFile face2) throws IOException {

        byte[] bytes1 = face1.getBytes();
        byte[] bytes2 = face2.getBytes();
        ImageInfo rgbData1 = ImageFactory.getRGBData(bytes1);
        ImageInfo rgbData2 = ImageFactory.getRGBData(bytes2);

        Float similar = faceEngineService.compareFace(rgbData1, rgbData2);

        return Response.newSuccessResponse(similar);
    }

}
