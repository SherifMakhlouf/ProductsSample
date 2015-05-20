package com.sherifmakhlouf.productsample.data.api;

import com.google.gson.Gson;
import com.sherifmakhlouf.productsample.data.api.model.Product;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module(
        library = true
)
public class ApiModule {

    public static final String API_URL = "http://grapesnberries.getsandbox.com";

    @Provides
    @Singleton
    RestAdapter providesRestAdapter() {
        return new RestAdapter.Builder()
                .setClient(new OkClient(new OkHttpClient()))
                .setEndpoint(API_URL)
                .setConverter(new GsonConverter(new Gson()))
                .build();
    }

    @Provides @Singleton
    GrapesService providesGrapeService(RestAdapter restAdapter){
        return restAdapter.create(GrapesService.class);
    }

    @Provides @Singleton
    List<Product> providesProductsList(){
        return new ArrayList<Product>();
    }
}
