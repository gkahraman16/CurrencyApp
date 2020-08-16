package com.gozdekahraman.currencyapp.middleware;

import com.google.gson.GsonBuilder;
import com.gozdekahraman.currencyapp.App;
import com.gozdekahraman.currencyapp.R;
import com.gozdekahraman.currencyapp.constant.Constants;
import com.gozdekahraman.currencyapp.constant.MobileConstants;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bekir.Dursun on 3.10.2017.
 */

public class ServiceCreator {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient okClient = getOkClient();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                    .client(okClient)
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getOkClient() {
        SSLSocketFactory sslSocketFactory = null;
        X509TrustManager x509TrustManager = null;
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            int cert = R.raw.lets;
            InputStream inputStream = App.getInstance().getResources().openRawResource(cert);
            Certificate certificate = certificateFactory.generateCertificate(inputStream);
            inputStream.close();

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", certificate);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm);
            trustManagerFactory.init(keyStore);

            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            x509TrustManager = (X509TrustManager) trustManagers[0];

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{x509TrustManager}, null);
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception ignored) {
        }

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request original = chain.request();

                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Key", MobileConstants.KEY)
                                        .method(original.method(), original.body());

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        })
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES);

        if (sslSocketFactory != null && x509TrustManager != null) {
            okHttpClient.sslSocketFactory(sslSocketFactory, x509TrustManager);
        }

        return okHttpClient.build();
    }
}