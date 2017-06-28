package com.vdrop.vdropsports.api;

import com.vdrop.vdropsports.model.Campaign;
import com.vdrop.vdropsports.model.Discover;
import com.vdrop.vdropsports.model.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface VDSService {

    @GET("/campaigns/{campaignId}")
    Call<Campaign> getCampaignList(@Path("campaignId") String campaignId);


    @GET("/campaigns/{campaignId}/discover")
    Call<List<Discover>> getThumbnaliList(@Path("campaignId") String campaignId);


    @POST("/campaigns")
    Call<Campaign> createCampaign(@Body Campaign campaign);

    @POST("/campaigns/{campaignId}")
    Call<Playlist> createVideoForCampaign(@Path("campaignId") String campaignId);

    @POST("/videos/{videoId}/publish")
    Call<String> publishVideo(@Path("videoId") String videoId);
}
