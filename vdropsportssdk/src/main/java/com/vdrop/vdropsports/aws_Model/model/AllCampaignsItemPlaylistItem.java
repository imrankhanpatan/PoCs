/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.vdrop.vdropsports.aws_Model.model;

import com.vdrop.vdropsports.aws_Model.model.AllCampaignsItemPlaylistItemId;
import java.math.BigDecimal;

public class AllCampaignsItemPlaylistItem {
    @com.google.gson.annotations.SerializedName("__v")
    private BigDecimal v = null;
    @com.google.gson.annotations.SerializedName("likes")
    private BigDecimal likes = null;
    @com.google.gson.annotations.SerializedName("created")
    private String created = null;
    @com.google.gson.annotations.SerializedName("campaignId")
    private String campaignId = null;
    @com.google.gson.annotations.SerializedName("uploadUrl")
    private String uploadUrl = null;
    @com.google.gson.annotations.SerializedName("fileName")
    private String fileName = null;
    @com.google.gson.annotations.SerializedName("_id")
    private AllCampaignsItemPlaylistItemId id = null;
    @com.google.gson.annotations.SerializedName("stream")
    private String stream = null;
    @com.google.gson.annotations.SerializedName("thumbnailImageUrl")
    private String thumbnailImageUrl = null;

    /**
     * Gets v
     *
     * @return v
     **/
    public BigDecimal getV() {
        return v;
    }

    /**
     * Sets the value of v.
     *
     * @param v the new value
     */
    public void setV(BigDecimal v) {
        this.v = v;
    }

    /**
     * Gets likes
     *
     * @return likes
     **/
    public BigDecimal getLikes() {
        return likes;
    }

    /**
     * Sets the value of likes.
     *
     * @param likes the new value
     */
    public void setLikes(BigDecimal likes) {
        this.likes = likes;
    }

    /**
     * Gets created
     *
     * @return created
     **/
    public String getCreated() {
        return created;
    }

    /**
     * Sets the value of created.
     *
     * @param created the new value
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * Gets campaignId
     *
     * @return campaignId
     **/
    public String getCampaignId() {
        return campaignId;
    }

    /**
     * Sets the value of campaignId.
     *
     * @param campaignId the new value
     */
    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * Gets uploadUrl
     *
     * @return uploadUrl
     **/
    public String getUploadUrl() {
        return uploadUrl;
    }

    /**
     * Sets the value of uploadUrl.
     *
     * @param uploadUrl the new value
     */
    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    /**
     * Gets fileName
     *
     * @return fileName
     **/
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of fileName.
     *
     * @param fileName the new value
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets id
     *
     * @return id
     **/
    public AllCampaignsItemPlaylistItemId getId() {
        return id;
    }

    /**
     * Sets the value of id.
     *
     * @param id the new value
     */
    public void setId(AllCampaignsItemPlaylistItemId id) {
        this.id = id;
    }

    /**
     * Gets stream
     *
     * @return stream
     **/
    public String getStream() {
        return stream;
    }

    /**
     * Sets the value of stream.
     *
     * @param stream the new value
     */
    public void setStream(String stream) {
        this.stream = stream;
    }

    /**
     * Gets thumbnailImageUrl
     *
     * @return thumbnailImageUrl
     **/
    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    /**
     * Sets the value of thumbnailImageUrl.
     *
     * @param thumbnailImageUrl the new value
     */
    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

}
