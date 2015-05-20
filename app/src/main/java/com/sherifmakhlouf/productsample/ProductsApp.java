package com.sherifmakhlouf.productsample;


import android.app.Application;

import dagger.ObjectGraph;

public class ProductsApp extends Application{
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules());
        objectGraph.inject(this);
    }

    private Object[] getModules(){
        return new Object[]{new MainModule(this)};
    }

    /**
     * Used to Compose Modules
     */
    public ObjectGraph createScopedGraph(Object... modules) {
        return objectGraph.plus(modules);
    }
}
