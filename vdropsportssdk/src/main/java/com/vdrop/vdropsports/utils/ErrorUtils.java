package com.vdrop.vdropsports.utils;

import com.vdrop.vdropsports.api.VDSClient;
import com.vdrop.vdropsports.model.APIError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by dennis on 29/5/17.
 */

public class ErrorUtils {

    public static APIError parseError(Response<?> response) {

        Converter<ResponseBody, APIError> converter = VDSClient.getRetrofit()
                .nextResponseBodyConverter(null,APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }
}
