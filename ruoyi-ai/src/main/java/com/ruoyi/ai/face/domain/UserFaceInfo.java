package com.ruoyi.ai.face.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author hanzhenyong
 * @create 2020-10-22 21:49
 */

@Table(name = "user_face_info")
public class UserFaceInfo implements Serializable {

    private static final long serialVersionUID = 1356257283938525230L;

    /**
     * 表id
     */
    @Id
    private Integer id;


    /**
     * SDK版本算法版本
     */
    private String version;


    /**
     *  AgeInfo
     * 年龄
     */
    private Integer age;

    /**
     *  Face3DAngle
     * float yaw 偏航角
     * float roll 横滚角
     * float pitch 俯仰角
     * int status
     * 0: 正常
     * 非0: 异常
     */
    private Float yaw;
    private Float roll;
    private Float pitch;
    private Integer status;

    /**
     * FaceFeature
     * 人脸特征
     */
    private byte[] faceFeature;

    /**
     * 人脸建模情况，0 成功 其他失败
     */
    private Integer faceFeatureStatus;

    /**
     * FaceInfo
     * Rect rect 人脸框
     *     public int left;
     *     public int top;
     *     public int right;
     *     public int bottom;
     * int orient 人脸角度
     * int faceId 一张人脸从进入画面直到离开画面，faceId不变。
     * 该参数在VIDEO模式下有效
     */
    private Integer faceId;
    private Integer rectLeft;
    private Integer rectTop;
    private Integer rectRight;
    private Integer rectBottom;
    private Integer orient;


    /**
     * 姓名或者文件名
     */
    private String name;



    /**
     * GenderInfo
     * 性别 性别，，未知性别=-1 、男性=0 、女性=1
     */
    private Integer gender;

    /**
     * IrLivenessInfo
     * IR活体值，未知=-1 、非活体=0 、活体=1、超出人脸=-2
     */
    @Column(name = "livenes_ir")
    private Integer livenesIr;

    /**
     * livenessInfo
     * RGB活体值，未知=-1 、非活体=0 、活体=1、超出人脸=-2
     */
    @Column(name = "livenes_rgb")
    private Integer livenessRgb;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getYaw() {
        return yaw;
    }

    public void setYaw(Float yaw) {
        this.yaw = yaw;
    }

    public Float getRoll() {
        return roll;
    }

    public void setRoll(Float roll) {
        this.roll = roll;
    }

    public Float getPitch() {
        return pitch;
    }

    public void setPitch(Float pitch) {
        this.pitch = pitch;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public byte[] getFaceFeature() {
        return faceFeature;
    }

    public void setFaceFeature(byte[] faceFeature) {
        this.faceFeature = faceFeature;
    }

    public Integer getFaceFeatureStatus() {
        return faceFeatureStatus;
    }

    public void setFaceFeatureStatus(Integer faceFeatureStatus) {
        this.faceFeatureStatus = faceFeatureStatus;
    }

    public Integer getFaceId() {
        return faceId;
    }

    public void setFaceId(Integer faceId) {
        this.faceId = faceId;
    }

    public Integer getRectLeft() {
        return rectLeft;
    }

    public void setRectLeft(Integer rectLeft) {
        this.rectLeft = rectLeft;
    }

    public Integer getRectTop() {
        return rectTop;
    }

    public void setRectTop(Integer rectTop) {
        this.rectTop = rectTop;
    }

    public Integer getRectRight() {
        return rectRight;
    }

    public void setRectRight(Integer rectRight) {
        this.rectRight = rectRight;
    }

    public Integer getRectBottom() {
        return rectBottom;
    }

    public void setRectBottom(Integer rectBottom) {
        this.rectBottom = rectBottom;
    }

    public Integer getOrient() {
        return orient;
    }

    public void setOrient(Integer orient) {
        this.orient = orient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getLivenesIr() {
        return livenesIr;
    }

    public void setLivenesIr(Integer livenesIr) {
        this.livenesIr = livenesIr;
    }

    public Integer getLivenessRgb() {
        return livenessRgb;
    }

    public void setLivenessRgb(Integer livenessRgb) {
        this.livenessRgb = livenessRgb;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserFaceInfo{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", age=" + age +
                ", yaw=" + yaw +
                ", roll=" + roll +
                ", pitch=" + pitch +
                ", status=" + status +
                ", faceFeature=" + Arrays.toString(faceFeature) +
                ", faceFeatureStatus=" + faceFeatureStatus +
                ", faceId=" + faceId +
                ", rectLeft=" + rectLeft +
                ", rectTop=" + rectTop +
                ", rectRight=" + rectRight +
                ", rectBottom=" + rectBottom +
                ", orient=" + orient +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", livenesIr=" + livenesIr +
                ", livenessRgb=" + livenessRgb +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
