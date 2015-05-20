package com.sherifmakhlouf.productsample.ui.preseneter;


import android.util.Log;

import com.sherifmakhlouf.productsample.data.api.GrapesService;
import com.sherifmakhlouf.productsample.data.api.model.Product;
import com.sherifmakhlouf.productsample.ui.base.Presenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class ProductsPresenter extends Presenter {
    private static final int DEFAULT_ITEM_COUNT = 10;
    private final ProductsView view;
    private final List<Product> productList;
    private final GrapesService api;

    @Inject
    public ProductsPresenter(ProductsView view, GrapesService api, List<Product> productList) {
        this.view = view;
        this.api = api;
        this.productList = productList;
    }

    @Override
    public void initialize() {
        view.initAdapter(productList);

    }

    public void refresh() {
        loadData(productList.size());
    }

    public void loadData(int from) {
        //Shouldn't allow loading older items
        if (from < productList.size() - 1)
            return;

        if (productList.size() == 0)
            view.showLoading(true);

        //Warning!! Nasty Anonymous classes coming
        //TODO use RetroLambda
        api.getProducts(DEFAULT_ITEM_COUNT, from)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Product>>() {
                    @Override
                    public void onCompleted() {
                        view.updateView();
                        view.showLoading(false);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("Error", "Network " + throwable.getCause());
                        view.showLoading(false);
                        //if list is empty show full error page else show a toast
                        view.showConnectionError(productList.size() == 0);
                    }

                    @Override
                    public void onNext(List<Product> data) {
                        productList.addAll(data);

                    }
                });
    }

    public interface ProductsView {
        void initAdapter(List<Product> productList);

        void showLoading(boolean show);

        void showConnectionError(boolean fullError);

        void updateView();

    }


}
