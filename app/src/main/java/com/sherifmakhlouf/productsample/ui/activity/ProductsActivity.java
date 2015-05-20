package com.sherifmakhlouf.productsample.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sherifmakhlouf.productsample.R;
import com.sherifmakhlouf.productsample.data.api.model.Product;
import com.sherifmakhlouf.productsample.ui.adapter.ProductAdapter;
import com.sherifmakhlouf.productsample.ui.base.BaseActivity;
import com.sherifmakhlouf.productsample.ui.misc.SpaceItemDecorator;
import com.sherifmakhlouf.productsample.ui.preseneter.ProductsPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscription;


public class ProductsActivity extends BaseActivity implements ProductsPresenter.ProductsView, ProductAdapter.EndListListener {
    @Inject
    ProductsPresenter presenter;
    @InjectView(R.id.progress_bar)
    ProgressBar loadingBar;
    @InjectView(R.id.network_error_img)
    ImageView networkErrorImg;
    @InjectView(R.id.products_listview)
    RecyclerView recyclerView;

    private ProductAdapter adapter;
    private Subscription scrollingSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.inject(this);
        presenter.initialize();

        // load data from 0 if it's the first time activity is created
        if (savedInstanceState == null)
            presenter.loadData(0);
    }


    @Override
    protected Object getModule() {
        return new ProductsModule(this);
    }


    @Override
    public void initAdapter(List<Product> productList) {
        adapter = new ProductAdapter(productList, getApplicationContext(), this);
        recyclerView.setHasFixedSize(true);

        //Set LayoutManager
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(getColumnCount(), StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //Set card spaces
        RecyclerView.ItemDecoration itemDecoration =
                new SpaceItemDecorator(getSpaces());
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_products, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                presenter.refresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * loads the respective Card Spaces
     * @return cards space in pixels
     */
    private int getSpaces() {
        float px = getResources().getDimension(R.dimen.card_space);
        return (int) px;
    }

    /**
     * loads the column count from resources
     * @return column count
     */
    private int getColumnCount() {
        return getResources().getInteger(R.integer.column_count);
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            hideAll();
            loadingBar.setVisibility(View.VISIBLE);
        } else {
            loadingBar.setVisibility(View.GONE);
        }
    }

    private void hideAll() {
        loadingBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.INVISIBLE);
        networkErrorImg.setVisibility(View.GONE);
    }

    @Override
    public void showConnectionError(boolean fullError) {
        if (fullError) {
            hideAll();
            networkErrorImg.setVisibility(View.VISIBLE);
        } else
            Toast.makeText(getApplicationContext(), "Error Loading More Data", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void updateView() {
        recyclerView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onEndOfList(int position) {
        //load data from position+1 because the "from" is inclusive
        presenter.loadData(position + 1);
    }
}
