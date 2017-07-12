package com.vdrop.vdropsports.application;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import android.util.Log;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.vdrop.vdropsports.R;
import com.vdrop.vdropsports.model.Campaign;
import com.vdrop.vdropsports.model.Playlist;
import com.vdrop.vdropsports.utils.Constants;
import com.vdrop.vdropsports.utils.Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

/**
 * Created by dennis on 29/5/17.
 */

public class App extends Application {

    private Campaign campaign;
    private static App mInstance;
    private ArrayList<Campaign> uploadCampaigns;
    private Playlist playlist;
    private static final String VDS_FILES_DIR = "/DCIM/";
    private String campaignId;
    private String publishVideoId;
    private String campaignName;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Branch.getAutoInstance(this);
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getResources().getString(R.string.com_twitter_sdk_android_CONSUMER_KEY), getResources().getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    /*@Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }*/

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getPublishVideoId() {
        return publishVideoId;
    }

    public void setPublishVideoId(String publishVideoId) {
        this.publishVideoId = publishVideoId;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public ArrayList<Campaign> getUploadCampaigns() {
        return uploadCampaigns;
    }

    public void setUploadCampaigns(ArrayList<Campaign> uploadCampaigns) {
        this.uploadCampaigns = uploadCampaigns;
    }

    public void addNewCampaign(Campaign campaign) {
        uploadCampaigns.add(campaign);
    }

    private String getFileStoragePath() {
        return Environment.getExternalStorageDirectory().toString();
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCompressedClip() {
        String croppedClip = null;
        try {
            croppedClip = getFileStoragePath() + VDS_FILES_DIR + Utils.getProperty(Constants.COMPRESSED_CLIP_NAME, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return croppedClip;
    }

}
