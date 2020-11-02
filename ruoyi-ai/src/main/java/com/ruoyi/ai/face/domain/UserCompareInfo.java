package com.ruoyi.ai.face.domain;

import com.ruoyi.ai.face.util.UserRamCache;

/**
 * @author hanzhenyong
 * @create 2020-11-02 22:06
 */
public class UserCompareInfo extends UserRamCache.UserInfo {
    private Float similar;

    public Float getSimilar() {
        return similar;
    }

    public void setSimilar(Float similar) {
        this.similar = similar;
    }
}
