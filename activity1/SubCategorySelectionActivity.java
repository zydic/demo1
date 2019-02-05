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
//import com.example.notifymypic.Adapter.SubCategorySelectionAdapter;
//import com.example.notifymypic.Application.Api;
//import com.example.notifymypic.Bean.SubCategoryDataListModel;
//import com.example.notifymypic.Bean.SubCategoryDataListVariableModel;
//import com.example.notifymypic.Utils.DBManager;
//import com.example.notifymypic.Utils.EPreferences;
//import com.example.notifymypic.Utils.Utils;

import com.example.foldergallery.activity.LauncherActivity;
import com.example.foldergallery.adapters.SubCategorySelectionAdapter;
import com.example.foldergallery.data.Api;
import com.example.foldergallery.data.SubCategoryDataListModel;
import com.example.foldergallery.data.SubCategoryDataListVariableModel;
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

public class SubCategorySelectionActivity extends AppCompatActivity {
    public ArrayList<SubCategoryDataListVariableModel> arrGetDataFrame;
    ArrayList<SubCategoryDataListVariableModel> arrDataBaseDataFrame;
    ArrayList<String> arrDataBaseCatNameFrame;
    Toolbar toolbar;
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    public SubCategorySelectionAdapter adapter;
    File[] downloadedFileName;
    public DBManager dbHelpler;
    AlertDialog alert;
    private long mLastClickTime = 0;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        bindview();
        init();
    }

    private void init() {
        initDB();
        downloadedFileName = null;
        setSupportActionBar(toolbar);
        arrGetDataFrame = new ArrayList<>();
        arrDataBaseDataFrame = new ArrayList<>();
        arrDataBaseCatNameFrame = new ArrayList<>();
        id = getIntent().getIntExtra("id", 1);
        Log.i("ID", "" + id);



            arrGetDataFrame.clear();
            arrGetDataFrame.addAll(dbHelpler.getAllCategoryData(id));
            setRecyclerView();


        if (Utils.checkNetworkConnectivity(SubCategorySelectionActivity.this)) {
            loadData();
        } else {
//            if (Utils.toast == null || Utils.toast.getView().getWindowVisibility() != View.VISIBLE) {
            Toast.makeText(getApplicationContext(), "Please connect with internet", Toast.LENGTH_SHORT).show();
//                Utils.toast.show();

        }

        arrDataBaseDataFrame = dbHelpler.getAllCategoryData(id);


        for (int i = 0; i < arrDataBaseDataFrame.size(); i++) {
            arrDataBaseCatNameFrame.add(arrDataBaseDataFrame.get(i).getThemeName());
        }

        setRecyclerView();


    }

    public void initDB() {
        DBManager.initializeDB(SubCategorySelectionActivity.this);
        dbHelpler = DBManager.getInstance();
    }



    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<SubCategoryDataListModel> call = api.getFrameListSub(10, id);
        adapter = new SubCategorySelectionAdapter(SubCategorySelectionActivity.this, arrGetDataFrame);
        call.enqueue(new Callback<SubCategoryDataListModel>() {

            @Override
            public void onFailure(Call<SubCategoryDataListModel> arg0, Throwable t) {
                Log.d("calldemo", "fail ");
            }

            @Override
            public void onResponse(Call<SubCategoryDataListModel> arg0,
                                   Response<SubCategoryDataListModel> response) {
                ArrayList<SubCategoryDataListVariableModel> arrayList = (ArrayList<SubCategoryDataListVariableModel>) response
                        .body().getData();


                Log.d("calldemo", "success " + arrayList.size());
                for (int i = 0; i < arrayList.size(); i++) {
                    SubCategoryDataListVariableModel category = new SubCategoryDataListVariableModel();


                    if (!arrDataBaseCatNameFrame.contains(arrayList.get(i).getThemeName())) {
                        category.setId(arrayList.get(i).getId());
                        category.setCatId(arrayList.get(i).getCatId());
                        category.setApplicationId(arrayList.get(i).getApplicationId());
                        category.setAppVersion(arrayList.get(i).getAppVersion());
                        category.setThemeName(arrayList.get(i).getThemeName());
                        category.setThemeInfo(arrayList.get(i).getThemeInfo());
                        category.setThemeBundle(arrayList.get(i).getThemeBundle());
                        category.setBundleSize(arrayList.get(i).getBundleSize());
                        category.setThumnailBig(arrayList.get(i).getThumnailBig());
                        category.setThumnailSmall(arrayList.get(i).getThumnailSmall());
                        category.setSoundName(arrayList.get(i).getSoundName());
                        category.setSoundFile(arrayList.get(i).getSoundFile());
                        category.setSoundSize(arrayList.get(i).getSoundSize());
                        category.setNoOfImages(arrayList.get(i).getNoOfImages());
                        category.setHeight(arrayList.get(i).getHeight());
                        category.setWidth(arrayList.get(i).getWidth());
                        category.setVideoOrCard(arrayList.get(i).getVideoOrCard());
                        category.setGameobjectName(arrayList.get(i).getGameobjectName());
                        category.setIsPreimum(arrayList.get(i).getIsPreimum());
                        category.setThemeCounter(arrayList.get(i).getThemeCounter());
                        category.setStatus(arrayList.get(i).getStatus());
                        category.setIsNewRealise(arrayList.get(i).getIsNewRealise());

                        category.setIsStatic(0);
                        category.setIsdownloaded(0);

                        Log.i("CATEGORY", " : " + category.toString());
                        dbHelpler.insertCategoryDetail(category);

                    }


                }
                arrGetDataFrame.clear();
                arrGetDataFrame.addAll(dbHelpler.getAllCategoryData(id));
                adapter.notifyDataSetChanged();

            }
        });


    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        adapter = new SubCategorySelectionAdapter(SubCategorySelectionActivity.this, arrGetDataFrame);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(SubCategorySelectionActivity.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(2), true));
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
        Intent i = new Intent(SubCategorySelectionActivity.this, CategorySelectionActivity.class);
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
