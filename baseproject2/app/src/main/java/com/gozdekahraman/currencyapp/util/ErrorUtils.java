package com.gozdekahraman.currencyapp.util;

import com.gozdekahraman.currencyapp.middleware.ServiceCreator;
import com.gozdekahraman.currencyapp.model.response.ErrorModel;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by bekir on 27/09/16.
 */
public class ErrorUtils {

    public static ErrorModel parseError(Response<?> response) {
        Converter<ResponseBody, ErrorModel> converter = ServiceCreator.getClient().responseBodyConverter(ErrorModel.class, new Annotation[0]);

        ErrorModel error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException | NullPointerException e) {
            return new ErrorModel("Servis bağlantısında geçici bir problem yaşandı.");
        }

        return error;
    }
}