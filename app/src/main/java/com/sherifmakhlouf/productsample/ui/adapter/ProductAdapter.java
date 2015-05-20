package com.sherifmakhlouf.productsample.ui.adapter;


import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sherifmakhlouf.productsample.R;
import com.sherifmakhlouf.productsample.data.api.model.Product;
import com.sherifmakhlouf.productsample.ui.misc.ResizableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    public interface EndListListener {
        void onEndOfList(int position);
    }

    private final List<Product> productList;
    private final Context context;
    private final EndListListener listListener;

    public ProductAdapter(List<Product> productList, Context context, EndListListener listListener) {
        this.productList = productList;
        this.context = context;
        this.listListener = listListener;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item, viewGroup, false);

        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductHolder productHolder, int position) {
        //Notify listener that end of list is reached
        if(position >= getItemCount() -1){
            listListener.onEndOfList(position);
        }

        productHolder.productPrice.setText("$ " + productList.get(position).price);
        productHolder.productDescription.setText(productList.get(position).productDescription);
        Product.Image image = productList.get(position).image;
        productHolder.productImage.setImageSize(calculatepx(image.height));
        //Load Image
        Picasso.with(context).load(image.url).into(productHolder.productImage);
    }

    private int calculatepx(int dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int) px;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductHolder extends RecyclerView.ViewHolder {
        final ResizableImageView productImage;
        final TextView productPrice;
        final TextView productDescription;

        public ProductHolder(View itemView) {
            super(itemView);
            productImage = (ResizableImageView) itemView.findViewById(R.id.product_image);
            productDescription = (TextView) itemView.findViewById(R.id.product_description);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
        }
    }


}
