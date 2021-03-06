package com.vdrop.vdropsports.discover;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.vdrop.vdropsports.Campaignplaylist.VDSCampaignActivity;
import com.vdrop.vdropsports.R;
import com.vdrop.vdropsports.application.App;
import com.vdrop.vdropsports.callback.ActivityCallback;
import com.vdrop.vdropsports.model.AddImageUrl;
import com.vdrop.vdropsports.model.Campaign;
import com.vdrop.vdropsports.model.Discover;
import com.vdrop.vdropsports.utils.Utils;
import com.vdrop.vdropsports.utils.VDSActivityManager;
import com.vdrop.vdropsports.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VDSDiscoverActivity extends AppCompatActivity {

    ArrayList<Campaign> campaigns;
    ArrayList<Discover> discoverList;
    private RecyclerView vdsRVDiscoverList,vdsRVAddsList;
    private VDSActivityManager VDSActivityManager;
    RecyclerView.LayoutManager mLayoutManager;
    private String campaignId = "590ad8a4420e002f85701996";
    VDSDiscoverRecyclerViewAdapter vdsDRVAdapter;
   /* private ImageView vdsIVDiscoverCancel;
    private TextView vdsTVDiscoverTitle;*/
    Context context = this;
    int stopPosition;
  /*  private Button vdsTHSelect;*/
    VideoView videoview;
    List<AddImageUrl> addImageUrls;
    VDSCampaignActivity vdsCampaignActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Random r = new Random();
        int theme = r.nextInt(4) +1;
        switch (theme){
            case 1:
                setTheme(R.style.text_large_wh);
                break;
            case 2:
                setTheme(R.style.text_regular_wh);
                break;
            case 3:
                setTheme(R.style.page_background_wh);
                break;
            case 4:
                setTheme(R.style.Theme_Material_Light);
                break;

        }*/
        /*setContentView(R.layout.vds_activity_discover);*/
        setContentView(R.layout.activity_home);
        Log.d("IN", "DISCOVER");
        VDSActivityManager = new VDSActivityManager(this);
        vdsRVDiscoverList = (RecyclerView) findViewById(R.id.vds_recycle_view);
       /* vdsIVDiscoverCancel = (ImageView) findViewById(R.id.vds_iv_discover_cancel);
        vdsTVDiscoverTitle = (TextView) findViewById(R.id.vds_tv_discover_title);
       vdsTHSelect=(Button)findViewById(R.id.vds_theme_select);*/
        mLayoutManager = new LinearLayoutManager(this);
        vdsRVDiscoverList.setLayoutManager(mLayoutManager);
        vdsDRVAdapter = new VDSDiscoverRecyclerViewAdapter(this);
        getDiscoverlist(campaignId);
         vdsCampaignActivity = new VDSCampaignActivity();
        /*vdsIVDiscoverCancel.setOnClickListener(this);*/

        vdsRVAddsList=(RecyclerView)findViewById(R.id.vds_adds_recycle_view);
        vdsRVAddsList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        vdsRVAddsList.setLayoutManager(layoutManager);
        addImageUrls = new ArrayList<>();
        VDSADDSActivity vdsaddsActivity = new VDSADDSActivity(getApplicationContext(),addImageUrls);
        vdsRVAddsList.setAdapter(vdsaddsActivity);
        prepareImageList();
    }

    private void prepareImageList() {
        int[] images = new int[]{
          R.drawable.vdropimage,
                R.drawable.vdropimage
        };
        AddImageUrl a = new AddImageUrl(images[0]);
        addImageUrls.add(a);
        a = new AddImageUrl(images[1]);
        addImageUrls.add(a);
    }


    private void getDiscoverlist(String campaignId) {
        VDSActivityManager.getDiscoverThumbnaillist(campaignId, new ActivityCallback() {
            @Override
            public void onSuccess(String success, Map map) {
                Log.i("Get", "COMMING");
                discoverList = (ArrayList<Discover>) map.get(Constants.RESPONSE);
                Log.i("RESPONCE_DISCOVER", "" + discoverList.size());

               /* vdsTVDiscoverTitle.setText("#"+App.getInstance().getCampaignName());*/
                vdsDRVAdapter.setData(discoverList);
                 vdsRVDiscoverList.setAdapter(vdsDRVAdapter);
                vdsDRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
            }
        });
    }
    public static void openDiscoverActivity(Context context) {
        Log.d("IN", "OPEN");
        Intent intent = new Intent(context, VDSDiscoverActivity.class);
        context.startActivity(intent);
    }
    /*@Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.vds_iv_discover_cancel) {
            onStop();
        }
    }*/


    public void setPosition(int stopPosition, VideoView vdsVideoView) {
       this.stopPosition = stopPosition;
        this.videoview = vdsVideoView;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
        onPause();
    }
}
