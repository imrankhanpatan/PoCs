package com.vdrop.vdropsports.Campaignplaylist;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.amazonaws.regions.Regions;
import com.vdrop.vdropsports.R;
import com.vdrop.vdropsports.aws_Model.ModelTestSdkClient;
import com.vdrop.vdropsports.aws_Model.model.AllCampaignsItem;

import java.util.List;

public class VDSAWSActivity extends AppCompatActivity {
    private Button callButton;
    ApiClientFactory factory;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vdsaws);
        AWSCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                this,          // activity context
                "us-east-1:5482c706-f35d-44ce-84ab-89286aa65dcf", // Cognito identity pool id
                Regions.US_EAST_1); // region of Cognito identity pool

        factory = new ApiClientFactory()
                .credentialsProvider(credentialsProvider)
                .apiKey("UPwCv9bwobaJJGxAc3mmFlWlPF970tw8SucFQCuc");
        callButton = (Button) findViewById(R.id.btn_sports);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewCreated(v,savedInstanceState);
            }
        });


    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
            ModelTestSdkClient client = factory.build(ModelTestSdkClient.class);
            List<AllCampaignsItem> item = client.campaignsGet();
            Log.i("RESPONCE", "" + item.get(0));

        }
    }


}
