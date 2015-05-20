package com.sherifmakhlouf.productsample;

import com.sherifmakhlouf.productsample.data.api.ApiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                ProductsApp.class
        },
        includes ={
                ApiModule.class
        },
        library = true

)
public class MainModule {

        private final ProductsApp app;

        public MainModule(ProductsApp app){
                this.app = app;
        }

        @Provides @Singleton
        ProductsApp providesApplication(){
                return app;
        }
}
