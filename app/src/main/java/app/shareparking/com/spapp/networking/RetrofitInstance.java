package app.shareparking.com.spapp.networking;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String BASE_URL_TEMP = "http://sheplus.savsign.org/api/";

    /**
     * addConverterFactory() — converter factory for serialization and deserialization of objects
     * addCallAdapterFactory() — Adapter factory for supporting service method return types, add instance of RxJava2CallAdapterFactory for Rxjava 2 support.
     *
     * @return retrofitInstance
     */

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.BODY;
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(logLevel);

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_TEMP)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                    .build();
        }
        return retrofit;
    }

    /**
     * @return restServiceInstance
     */
    public static RestService getRestServiceInstance(){
        return RetrofitInstance.getRetrofitInstance().create(RestService.class);
    }
}
