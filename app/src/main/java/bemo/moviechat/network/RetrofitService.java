package bemo.moviechat.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public class RetrofitService {

    private RetrofitService(){}

    public static MovieInterface getRetrofitClient(){
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://api.themoviedb.org/");

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client =
                new OkHttpClient.Builder()
                        .build();

        builder.client(client);

        return builder.build().create(MovieInterface.class);
    }
}
