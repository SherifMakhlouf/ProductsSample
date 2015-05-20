package com.sherifmakhlouf.productsample.ui.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sherifmakhlouf.productsample.ProductsApp;

import dagger.ObjectGraph;

public abstract class BaseActivity extends AppCompatActivity {

    private ObjectGraph activityGraph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGraph = ((ProductsApp)getApplication()).createScopedGraph(getModule());
        activityGraph.inject(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    abstract protected Object getModule();
}
