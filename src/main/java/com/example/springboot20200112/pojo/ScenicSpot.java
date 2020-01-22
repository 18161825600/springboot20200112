package com.example.springboot20200112.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_scenic_spot")
@Data
public class ScenicSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scenic_spot_name")
    private String scenicSpotName;

    @Column(name = "scenic_spot_address")
    private String scenicSpotAddress;

    @Column(name = "scenic_spot_img")
    private String scenicSpotImg;

    @Column(name = "scenic_spot_describe")
    private String scenicSpotDescribe;

    @Column(name = "scenic_spot_imgs")
    private String scenicSpotImgs;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return scenic_spot_name
     */
    public String getScenicSpotName() {
        return scenicSpotName;
    }

    /**
     * @param scenicSpotName
     */
    public void setScenicSpotName(String scenicSpotName) {
        this.scenicSpotName = scenicSpotName == null ? null : scenicSpotName.trim();
    }

    /**
     * @return scenic_spot_address
     */
    public String getScenicSpotAddress() {
        return scenicSpotAddress;
    }

    /**
     * @param scenicSpotAddress
     */
    public void setScenicSpotAddress(String scenicSpotAddress) {
        this.scenicSpotAddress = scenicSpotAddress == null ? null : scenicSpotAddress.trim();
    }

    /**
     * @return scenic_spot_img
     */
    public String getScenicSpotImg() {
        return scenicSpotImg;
    }

    /**
     * @param scenicSpotImg
     */
    public void setScenicSpotImg(String scenicSpotImg) {
        this.scenicSpotImg = scenicSpotImg == null ? null : scenicSpotImg.trim();
    }

    /**
     * @return scenic_spot_describe
     */
    public String getScenicSpotDescribe() {
        return scenicSpotDescribe;
    }

    /**
     * @param scenicSpotDescribe
     */
    public void setScenicSpotDescribe(String scenicSpotDescribe) {
        this.scenicSpotDescribe = scenicSpotDescribe == null ? null : scenicSpotDescribe.trim();
    }

    /**
     * @return scenic_spot_imgs
     */
    public String getScenicSpotImgs() {
        return scenicSpotImgs;
    }

    /**
     * @param scenicSpotImgs
     */
    public void setScenicSpotImgs(String scenicSpotImgs) {
        this.scenicSpotImgs = scenicSpotImgs == null ? null : scenicSpotImgs.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}