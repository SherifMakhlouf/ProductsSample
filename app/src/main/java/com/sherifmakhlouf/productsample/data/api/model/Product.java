package com.sherifmakhlouf.productsample.data.api.model;


public class Product {

    public long id;
    public String productDescription;
    public Image image;
    public String price;


    @Override
    public String toString() {
        return "[ id : " + id + ", description: " + productDescription + ", width: " + image.width + ", height: " + image.height + " ]";
    }

    public static class Image {
        public int width;
        public int height;
        public String url;
    }
}
