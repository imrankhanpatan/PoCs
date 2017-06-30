package com.vdrop.vdropsports.Campaignplaylist;

import android.Manifest;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContentResolverCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vdrop.vdropsports.R;
import com.vdrop.vdropsports.callback.ActivityCallback;
import com.vdrop.vdropsports.callback.PopupCallBack;
import com.vdrop.vdropsports.callback.ProgressCallback;
import com.vdrop.vdropsports.utils.Config;
import com.vdrop.vdropsports.utils.Constants;
import com.vdrop.vdropsports.utils.FileUtils;
import com.vdrop.vdropsports.utils.Utils;
import com.vdrop.vdropsports.utils.VDSActivityManager;
import com.vdrop.vdropsports.utils.VDSCompressVideoTask;
import com.vdrop.vdropsports.utils.VDSPermissionUtils;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by dennis on 26/5/17.
 */

public class VDSPopupImportOptionsDialog extends DialogFragment implements View.OnClickListener {
    private static final int VIDEO_REQUEST_VALUE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private VDSCampaignActivity activity;
    private String videoFormat = "video/*";
    private boolean isCompressing = false;
    private static final int RESULT_LOAD_VIDEO = 200;
    private Uri filePath = Uri.EMPTY;
    private VDSActivityManager VDSActivityManager;
    PopupCallBack popupCallBack;
    private VDSCompressVideoTask vdsCompressVideoTask;
    private String campaignID = "590ad782420e002f85701991";
    private VDSVideoCaptureActivity vdsVideoCaptureActivity = new VDSVideoCaptureActivity();

    public VDSPopupImportOptionsDialog() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.VDS_DIALOG);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            int screenHeight = Utils.deviceDimensions(getActivity(), 100, Constants.WIDTH);
            int halfScreenHeight = screenHeight;
            d.getWindow().setLayout(width, halfScreenHeight);
            d.setCanceledOnTouchOutside(true);
            d.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat
                    .getColor(getActivity(), R.color.colorTransparent1)));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vds_import_video_dialog, container);
        TextView vdsTVRecord = (TextView) view.findViewById(R.id.vds_tv_record);
        TextView vdsTVImport = (TextView) view.findViewById(R.id.vds_tv_import_photos);
        TextView vdsTVCancel = (TextView) view.findViewById(R.id.vds_tv_cancel);
        vdsTVRecord.setOnClickListener(this);
        vdsTVImport.setOnClickListener(this);
        vdsTVCancel.setOnClickListener(this);
        vdsCompressVideoTask = new VDSCompressVideoTask(getActivity());
        activity = (VDSCampaignActivity) getActivity();
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return false;
                }
                return false;

            }
        });
        VDSActivityManager = new VDSActivityManager(getActivity());
        popupCallBack = (PopupCallBack) getActivity();
        if (!VDSPermissionUtils.checkPermissionStatus(getActivity())) {
            VDSPermissionUtils.EnableRuntimePermission(getActivity());
        }

        return view;
    }

    @Override
    public void onClick(View v) {

        int Id = v.getId();
        if (Id == R.id.vds_tv_record) {
            int rc = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
            int sdCard = ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (sdCard == PackageManager.PERMISSION_GRANTED) {
                int recordAudio = ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.RECORD_AUDIO);
                if (recordAudio == PackageManager.PERMISSION_GRANTED) {
                    if (rc == PackageManager.PERMISSION_GRANTED) {
                        recordVideoFromCamera();
                    } else {
                        VDSPermissionUtils.requestCameraPermission(getActivity());
                    }
                } else {
                    VDSPermissionUtils.requestRecordAudioPermission(getActivity());
                }
            } else {
                VDSPermissionUtils.requestSDCardPermission(getActivity());
            }


        }
        if (Id == R.id.vds_tv_import_photos) {
            if (VDSPermissionUtils.checkPermissionStatus(getActivity())) {
                pickVideoFromGallery();
            } else {
                Toast.makeText(activity, "Permission not enabled", Toast.LENGTH_LONG).show();
            }

        }
        if (Id == R.id.vds_tv_cancel) {
            dismiss();
        }

    }

    public void recordVideoFromCamera() {
        Intent vdsCamera = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File video_file = getOutputMediaFile(MEDIA_TYPE_VIDEO);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authorities = getActivity().getApplicationContext().getPackageName() + ".fileprovider";
            Uri video_uri = FileProvider.getUriForFile(getActivity(), authorities, video_file);
            vdsCamera.putExtra(MediaStore.EXTRA_OUTPUT, video_uri);
        } else {
            Uri video_marsh = Uri.fromFile(video_file);
            vdsCamera.putExtra(MediaStore.EXTRA_OUTPUT, video_marsh);
        }
        vdsCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        vdsCamera.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);
        startActivityForResult(vdsCamera, VIDEO_REQUEST_VALUE);
    }
    public void pickVideoFromGallery() {
        Intent videoIntent = new Intent(Intent.ACTION_GET_CONTENT);
        videoIntent.setType(videoFormat);
        startActivityForResult(videoIntent, RESULT_LOAD_VIDEO);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_LOAD_VIDEO) {
                try {
                    filePath = data.getData();
                    getFilePath(filePath);
                    Log.i("FILE_PATH", "" + filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (requestCode == VIDEO_REQUEST_VALUE) {
                    try {
                        Uri videoFilePath = data.getData();
                        Log.i("videoFilePath", "" + videoFilePath);
                        File file = new File(videoFilePath.getPath());
                        Log.i("FILE_PATH", "" + file);
                        getDialog().dismiss();
                        createCampaignUser(file, campaignID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(activity, "VIDEO Captured Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void getFilePath(Uri filepath) {
        File file = FileUtils.getFile(getActivity(), filepath);
        getDialog().dismiss();
        createCampaignUser(file, campaignID);
    }

    private void createCampaignUser(File filePath, String campaignId) {
        Log.i("CAMPAIGN_ID", "" + campaignId);
        Log.i("CAMPAIGN_FILE_PATH", "" + filePath);
        VDSActivityManager.onCreateVideo(campaignId, filePath, new ActivityCallback() {
            @Override
            public void onSuccess(String success, Map map) {

            }

            @Override
            public void onError(String error) {

            }
        }, new ProgressCallback() {
            @Override
            public void videoProgress(String campaignId, int progress) {

            }
        });
    }
    private static File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.
                getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                Config.IMAGE_DIRECTORY_NAME);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("", "Oops! Failed create "
                        + Config.IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

}
