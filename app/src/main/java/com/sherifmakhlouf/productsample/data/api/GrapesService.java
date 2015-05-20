package com.sherifmakhlouf.productsample.data.api;

import com.sherifmakhlouf.productsample.data.api.model.Product;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface GrapesService {

    @GET("/products")
    Observable<List<Product>> getProducts(@Query("count") int count,@Query("from") int from);

}
