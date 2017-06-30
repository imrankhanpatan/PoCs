package com.vdrop.vdropsports.Campaignplaylist;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.vdrop.vdropsports.R;
import com.vdrop.vdropsports.application.App;
import com.vdrop.vdropsports.callback.PopupCallBack;
import com.vdrop.vdropsports.discover.VDSDiscoverActivity;
import com.vdrop.vdropsports.utils.Utils;

/**
 * Created by dennis on 26/5/17.
 */

public class VDSPopupOptionsDialog extends DialogFragment implements View.OnClickListener {

    private VDSCampaignActivity activity;
    private FragmentManager fragmentManger;
    private VDSDiscoverActivity discoverActivity = new VDSDiscoverActivity();
    private VDSPopupVideoSignupDialog vdsPVSignup;
    private Context context;
    private VideoView videoView;
    private VDSPopupShareDialog vdsPopupShareDialog;
    Dialog d;
    int stopPosititon;
    PopupCallBack popupCallBack;

    public VDSPopupOptionsDialog() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.VDS_DIALOG);
    }

    @Override
    public void onStart() {
        super.onStart();
        d = getDialog();
        if (d != null) {
            d.getWindow().setBackgroundDrawable(new ColorDrawable(
                    ContextCompat.getColor(getActivity(), R.color.colorTransparent1)));
        }
    }


    public void setVideo(int stopPosition, VideoView videoView) {
        this.videoView = videoView;
        this.stopPosititon = stopPosition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vds_activity_popup_dialog, container);
        TextView vdsTVTitle = (TextView) view.findViewById(R.id.vds_tv_title);
        LinearLayout contiainer = (LinearLayout) view.findViewById(R.id.popup_dialog_container);
        TextView vdsTVSubtitle = (TextView) view.findViewById(R.id.vds_tv_subtitle);
        TextView vdsTVVideoQuestions = (TextView) view.findViewById(R.id.vds_tv_video_questions);
        TextView vdsTVFavourQuestions = (TextView) view.findViewById(R.id.vds_tv_favour_questions);
        ImageView vdsIVShare = (ImageView) view.findViewById(R.id.vds_iv_share);
        ImageView vdsIVGallery = (ImageView) view.findViewById(R.id.vds_iv_video_gallery);
        ImageView vdsIVVideo = (ImageView) view.findViewById(R.id.vds_iv_video);
        RelativeLayout vdsRLAddVideo = (RelativeLayout)view.findViewById(R.id.vds_rl_add_video);
        vdsIVShare.setOnClickListener(this);
        vdsIVGallery.setOnClickListener(this);
        vdsRLAddVideo.setOnClickListener(this);
        vdsTVTitle.setText(App.getInstance().getCampaignName());
        vdsTVSubtitle.setText(App.getInstance().getCampaign().getDescription());
        vdsTVVideoQuestions.setText(App.getInstance().getCampaign().getInstruction());
        vdsTVFavourQuestions.setText(App.getInstance().getCampaign().getSmallPrint());
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        fragmentManger = getActivity().getFragmentManager();
        activity = (VDSCampaignActivity) getActivity();
        popupCallBack = (PopupCallBack)getActivity();

        contiainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLOSE", ".................................");
                getDialog().dismiss();
                popupCallBack.onDismiss();
            }
        });

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    getDialog().dismiss();
                    popupCallBack.onDismiss();
                }
                return true;

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        int Id = v.getId();

        if (Id == R.id.vds_iv_share) {
            try{
                if (!activity.haveNetworkConnection()){
                    activity.customToastMessage();
                }else {
                    vdsPopupShareDialog = new VDSPopupShareDialog();
                    vdsPopupShareDialog.show(fragmentManger,"PopupShareDialog");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        if (Id == R.id.vds_iv_video_gallery) {
            VDSDiscoverActivity.openDiscoverActivity(getActivity());
        }
        if (Id == R.id.vds_rl_add_video) {
            if (!activity.haveNetworkConnection()){
                activity.customToastMessage();
                activity.showProgress();
            }else {
                activity.hideProgress();
                vdsPVSignup = new VDSPopupVideoSignupDialog();
                vdsPVSignup.show(fragmentManger, "PopupSignupDialog");
                getDialog().dismiss();
            }

        }
    }



}
