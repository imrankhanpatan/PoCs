package com.vdrop.vdropsports.Campaignplaylist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.vdrop.vdropsports.R;

public class VDSAdClickActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private ImageButton btn_mozilla;
    private TextView vdsBTAName,vdsBTEmailName;
    private Button vdsGoogleLogOut;
    private SignInButton vdsGoogleSignIn;
    private GoogleApiClient googleApiClient;
    private LinearLayout vdsLLContainer;
    private static final int RC_SIGN_IN = 9001;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vdsad_click);
        vdsLLContainer =(LinearLayout)findViewById(R.id.vds_linear_proof);
        btn_mozilla = (ImageButton)findViewById(R.id.btn_mozilla);
        vdsBTAName=(TextView)findViewById(R.id.vds_account_name);
        vdsBTEmailName=(TextView)findViewById(R.id.vds_mail_name);
        vdsGoogleLogOut=(Button)findViewById(R.id.vds_google_logout);
        vdsGoogleSignIn=(SignInButton)findViewById(R.id.vds_google_signin);
        vdsGoogleSignIn.setOnClickListener(this);
        vdsGoogleLogOut.setOnClickListener(this);
        vdsLLContainer.setVisibility(View.GONE);
        btn_mozilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String url = "https://vdrop.test-app.link/b7p3OUv3kE";
                    Intent intent = new Intent(Intent.ACTION_MAIN, null);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(new ComponentName("org.mozilla.firefox", "org.mozilla.firefox.App"));
                    intent.setAction("org.mozilla.gecko.BOOKMARK");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("args", "--url=" + url);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions)
                .build();
    }


    public static void openAdsActivity(Activity activity) {

        Intent intent = new Intent(activity,VDSAdClickActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.vds_google_signin) {
            signIn();
        }
        if (i == R.id.vds_google_logout){
            signOut();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.i("CONNECTION_Status",""+connectionResult);

    }

    private void signIn(){
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,RC_SIGN_IN);
    }

    private void signOut(){

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }
    private void updateUI(boolean isLogin){
        if (isLogin){
            vdsLLContainer.setVisibility(View.VISIBLE);
            vdsGoogleSignIn.setVisibility(View.GONE);
        }else {
            vdsLLContainer.setVisibility(View.GONE);
            vdsGoogleSignIn.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            int statuscode = result.getStatus().getStatusCode();
            Log.d("STATUS_CODE",""+result.getStatus());
                handleResult(result);

        }
    }

    private void handleResult(GoogleSignInResult result){
        Log.i("GOOGLE_SIGN_Result",""+result.isSuccess());
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getGivenName();
            String email = account.getEmail();
            Log.i("NAME",""+name);
            vdsBTAName.setText(name);
            vdsBTEmailName.setText(email);
            updateUI(true);
        }else {
            updateUI(false);
        }

    }
    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.

            GoogleSignInResult result = opr.get();
            handleResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleResult(googleSignInResult);
                }
            });
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
}
