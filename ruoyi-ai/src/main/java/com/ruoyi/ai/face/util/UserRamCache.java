package com.ruoyi.ai.face.util;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import com.google.common.collect.Lists;

/**
 * @author hanzhenyong
 * @create 2020-11-02 21:26
 */
public class UserRamCache {

    private static ConcurrentHashMap<String, UserInfo> userInfoMap = new ConcurrentHashMap<>();

    public static void addUser(UserInfo userInfo) {
        userInfoMap.put(userInfo.getFaceId(), userInfo);
    }

    public static void removeUser(String faceId) {
        userInfoMap.remove(faceId);
    }

    public static List<UserInfo> getUserList() {
        List<UserInfo> userInfoList = Lists.newLinkedList();
        for (UserInfo value : userInfoMap.values()) {
            userInfoList.add(value);
        }
        return userInfoList;
    }



    public static class UserInfo {

        private String faceId;
        private String name;
        private byte[] faceFeature;

        public String getFaceId() {
            return faceId;
        }

        public void setFaceId(String faceId) {
            this.faceId = faceId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte[] getFaceFeature() {
            return faceFeature;
        }

        public void setFaceFeature(byte[] faceFeature) {
            this.faceFeature = faceFeature;
        }
    }

}