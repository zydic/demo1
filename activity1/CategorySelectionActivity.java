package com.example.foldergallery.activity;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//import com.example.mycamapppp.R;
//import com.example.notifymypic.Adapter.CategorySelectionAdapter;
//import com.example.notifymypic.Application.Api;
//import com.example.notifymypic.Bean.CatDataListModel;
//import com.example.notifymypic.Bean.CatDataListVariableModel;
//import com.example.notifymypic.Utils.DBManager;
//import com.example.notifymypic.Utils.EPreferences;
//import com.example.notifymypic.Utils.Utils;

import com.example.foldergallery.activity.LauncherActivity;
import com.example.foldergallery.adapters.CategorySelectionAdapter;
import com.example.foldergallery.data.Api;
import com.example.foldergallery.data.CatDataListModel;
import com.example.foldergallery.data.CatDataListVariableModelData;
import com.example.foldergallery.data.DBManager;
import com.example.foldergallery.util.EPreferences;
import com.example.foldergallery.util.Utils;
import com.example.photobook.R;

import java.io.File;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategorySelectionActivity extends AppCompatActivity {
    public ArrayList<CatDataListVariableModelData> arrGetData;
    ArrayList<CatDataListVariableModelData> arrDataBaseData;
    ArrayList<String> arrDataBaseCatName;
    Toolbar toolbar;
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    public CategorySelectionAdapter adapter;
    public DBManager dbHelpler;
    AlertDialog alert;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        bindview();
        init();

        findViewById(R.id.testbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategorySelectionActivity.this, CatSubcatActivity.class));

            }
        });
    }

    private void init() {
        initDB();
        setSupportActionBar(toolbar);
        arrGetData = new ArrayList<>();
        arrDataBaseData = new ArrayList<>();
        arrDataBaseCatName = new ArrayList<>();

        arrGetData.clear();
        arrGetData.addAll(dbHelpler.getAllMainCategoryData());
        setRecyclerView();

        if (Utils.checkNetworkConnectivity(CategorySelectionActivity.this)) {
            loadData();
        } else {
            Toast.makeText(getApplicationContext(), "Please connect with internet", Toast.LENGTH_SHORT).show();

        }

        arrDataBaseData = dbHelpler.getAllMainCategoryData();


        for (int i = 0; i < arrDataBaseData.size(); i++) {
            arrDataBaseCatName.add(arrDataBaseData.get(i).getCatName());
        }

        setRecyclerView();


    }

    public void initDB() {
        DBManager.initializeDB(CategorySelectionActivity.this);
        dbHelpler = DBManager.getInstance();
    }



    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<CatDataListModel> call = api.getFrameList(10);
        adapter = new CategorySelectionAdapter(CategorySelectionActivity.this, arrGetData);
        call.enqueue(new Callback<CatDataListModel>() {

            @Override
            public void onFailure(Call<CatDataListModel> arg0, Throwable t) {
                Log.d("calldemo", "fail ");
            }

            @Override
            public void onResponse(Call<CatDataListModel> arg0,
                                   Response<CatDataListModel> response) {
                ArrayList<CatDataListVariableModelData> arrayList = (ArrayList<CatDataListVariableModelData>) response
                        .body().getData();


                Log.d("calldemo", "success " + arrayList.size());
                for (int i = 0; i < arrayList.size(); i++) {
                    CatDataListVariableModelData category = new CatDataListVariableModelData();

                    Log.d("tarang", "hello + " + arrayList.get(i).getId());
                    if (!arrDataBaseCatName.contains(arrayList.get(i).getCatName())) {
                        category.setId(arrayList.get(i).getId());
                        category.setApplicationId(arrayList.get(i).getApplicationId());
                        category.setCatName(arrayList.get(i).getCatName());
                        category.setIsStatic(0);
                        category.setIsdownloaded(0);
                        dbHelpler.insertCategoryDetail(category);

                    }


                }
                arrGetData.clear();
                arrGetData.addAll(dbHelpler.getAllMainCategoryData());
                adapter.notifyDataSetChanged();

            }
        });


    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        adapter = new CategorySelectionAdapter(CategorySelectionActivity.this, arrGetData);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(CategorySelectionActivity.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void bindview() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(CategorySelectionActivity.this, LauncherActivity.class);
        startActivity(i);
        finish();
//        adapter.detail();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
