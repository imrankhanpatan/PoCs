package com.vdrop.vdropsports.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.vdrop.vdropsports.Campaignplaylist.VDSVideoCaptureActivity;

/**
 * Created by dennis on 5/6/17.
 */

public class VDSPermissionUtils extends Activity {

    private static final int RequestPermissionCode = 1;
    private static final int RC_HANDLE_CAMERA_PERM = 2;
    private static final int RC_HANDLE_WRITE_EXTERNAL_STORAGE_PERM = 3;
    private static final int RC_HANDLE_RECORD_AUDIO_PERM = 4;

    public static void EnableRuntimePermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int RequestCode, String permissions[]
            , int[] PermisionResult) {
        switch (RequestCode) {
            case RequestPermissionCode:
                if (PermisionResult.length > 0 && PermisionResult[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted," +
                            " Now your application can access Storage.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Canceled, " +
                            "Now your application cannot access Storage.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public static boolean checkPermissionStatus(Activity activity) {
        int RequestCheckResult = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        return RequestCheckResult == PackageManager.PERMISSION_GRANTED;

    }

    public static void requestCameraPermission(Activity activity) {
        Log.w(VDSVideoCaptureActivity.class.getName(), "Camera permission is not granted. Requesting permission");

        final String[] permissions = new String[]{Manifest.permission.CAMERA};
        ActivityCompat.requestPermissions(activity, permissions,
                RC_HANDLE_CAMERA_PERM);

    }

    public static void requestSDCardPermission(Activity activity) {
        Log.w(VDSVideoCaptureActivity.class.getName(), "SDCard permission is not granted. Requesting permission");

        final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(activity, permissions,
                RC_HANDLE_WRITE_EXTERNAL_STORAGE_PERM);

    }

    public static void requestRecordAudioPermission(Activity activity) {
        Log.w(VDSVideoCaptureActivity.class.getName(), "Record audio permission is not granted. Requesting permission");

        final String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO};
        ActivityCompat.requestPermissions(activity, permissions,
                RC_HANDLE_RECORD_AUDIO_PERM);

    }
}
