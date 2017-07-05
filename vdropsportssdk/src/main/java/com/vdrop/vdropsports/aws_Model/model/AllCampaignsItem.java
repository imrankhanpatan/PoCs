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

import com.vdrop.vdropsports.aws_Model.model.AllCampaignsItemId;
import com.vdrop.vdropsports.aws_Model.model.AllCampaignsItemPlaylistItem;
import java.util.*;

public class AllCampaignsItem {
    @com.google.gson.annotations.SerializedName("_id")
    private AllCampaignsItemId id = null;
    @com.google.gson.annotations.SerializedName("name")
    private String name = null;
    @com.google.gson.annotations.SerializedName("description")
    private String description = null;
    @com.google.gson.annotations.SerializedName("instruction")
    private String instruction = null;
    @com.google.gson.annotations.SerializedName("smallPrint")
    private String smallPrint = null;
    @com.google.gson.annotations.SerializedName("created")
    private String created = null;
    @com.google.gson.annotations.SerializedName("playlist")
    private List<AllCampaignsItemPlaylistItem> playlist = null;

    /**
     * Gets id
     *
     * @return id
     **/
    public AllCampaignsItemId getId() {
        return id;
    }

    /**
     * Sets the value of id.
     *
     * @param id the new value
     */
    public void setId(AllCampaignsItemId id) {
        this.id = id;
    }

    /**
     * Gets name
     *
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the value of name.
     *
     * @param name the new value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description
     *
     * @return description
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of description.
     *
     * @param description the new value
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets instruction
     *
     * @return instruction
     **/
    public String getInstruction() {
        return instruction;
    }

    /**
     * Sets the value of instruction.
     *
     * @param instruction the new value
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Gets smallPrint
     *
     * @return smallPrint
     **/
    public String getSmallPrint() {
        return smallPrint;
    }

    /**
     * Sets the value of smallPrint.
     *
     * @param smallPrint the new value
     */
    public void setSmallPrint(String smallPrint) {
        this.smallPrint = smallPrint;
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
     * Gets playlist
     *
     * @return playlist
     **/
    public List<AllCampaignsItemPlaylistItem> getPlaylist() {
        return playlist;
    }

    /**
     * Sets the value of playlist.
     *
     * @param playlist the new value
     */
    public void setPlaylist(List<AllCampaignsItemPlaylistItem> playlist) {
        this.playlist = playlist;
    }

}
