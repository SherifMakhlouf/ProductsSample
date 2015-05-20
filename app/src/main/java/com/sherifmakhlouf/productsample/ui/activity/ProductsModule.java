package com.sherifmakhlouf.productsample.ui.activity;

import com.sherifmakhlouf.productsample.MainModule;
import com.sherifmakhlouf.productsample.data.api.GrapesService;
import com.sherifmakhlouf.productsample.data.api.model.Product;
import com.sherifmakhlouf.productsample.ui.preseneter.ProductsPresenter;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                ProductsActivity.class
        },
        addsTo = MainModule.class,
        library = true

)
public class ProductsModule {

    private final ProductsPresenter.ProductsView view;

    ProductsModule(ProductsPresenter.ProductsView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    ProductsPresenter.ProductsView providesView() {
        return view;
    }

    @Provides
    @Singleton
    ProductsPresenter providesPresenter(ProductsPresenter.ProductsView view,GrapesService api,List<Product> productList) {
        return new ProductsPresenter(view,api,productList);
    }
}
