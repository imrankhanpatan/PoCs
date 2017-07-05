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

package com.vdrop.vdropsports.aws_Model;

import java.util.*;

import com.vdrop.vdropsports.aws_Model.model.AllCampaignsItem;
import com.vdrop.vdropsports.aws_Model.model.AllCampaigns;
import com.vdrop.vdropsports.aws_Model.model.Empty;


@com.amazonaws.mobileconnectors.apigateway.annotation.Service(endpoint = "https://s1wh07mhmb.execute-api.us-east-1.amazonaws.com/modeltestsdk")
public interface ModelTestSdkClient {


    /**
     * A generic invoker to invoke any API Gateway endpoint.
     * @param request
     * @return ApiResponse
     */
    com.amazonaws.mobileconnectors.apigateway.ApiResponse execute(com.amazonaws.mobileconnectors.apigateway.ApiRequest request);
    
    /**
     * 
     * 
     * @return AllCampaigns
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/campaigns", method = "GET")
    List<AllCampaignsItem> campaignsGet();
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/campaigns", method = "OPTIONS")
    Empty campaignsOptions();
    
}

