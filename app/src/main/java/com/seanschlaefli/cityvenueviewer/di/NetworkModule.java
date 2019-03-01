package com.seanschlaefli.cityvenueviewer.di;

import com.seanschlaefli.cityvenueviewer.BuildConfig;
import com.seanschlaefli.cityvenueviewer.data.remote.FoursquareService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit provideRetrofitClient(OkHttpClient client) {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(BuildConfig.FOURSQUARE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    FoursquareService provideFoursquareService(Retrofit retrofit) {
        return retrofit.create(FoursquareService.class);
    }
}
