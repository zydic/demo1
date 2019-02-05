package com.example.foldergallery.activity;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foldergallery.adapters.SubCategorySelectionAdapter;
import com.example.foldergallery.data.Api;
import com.example.foldergallery.data.CatDataListModel;
import com.example.foldergallery.data.CatDataListVariableModelData;
import com.example.foldergallery.data.DBManager;
import com.example.foldergallery.data.SubCategoryDataListModel;
import com.example.foldergallery.data.SubCategoryDataListVariableModel;
import com.example.foldergallery.util.CustomTFSpan;
import com.example.foldergallery.util.Log;
import com.example.foldergallery.util.Utils;
import com.example.photobook.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatSubcatActivity extends AppCompatActivity {

    DBManager dbHelpler;
    public ArrayList<CatDataListVariableModelData> arrGetData;
    ArrayList<String> arrDataBaseCatName;

    public ArrayList<SubCategoryDataListVariableModel> arrGetDataFrame;
    ArrayList<String> arrDataBaseCatNameFrame;
    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapter mPagerAdapter;
    private Typeface face;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.catsubcat);
        face = Typeface.createFromAsset(getAssets(), "Comfortaa-Regular.ttf");
        initDB();

        arrGetData = new ArrayList<>();
        arrDataBaseCatName = new ArrayList<>();

        arrGetDataFrame = new ArrayList<>();
        arrDataBaseCatNameFrame = new ArrayList<>();

        arrGetData.clear();
        arrGetData.addAll(dbHelpler.getAllMainCategoryData());


        if (Utils.checkNetworkConnectivity(CatSubcatActivity.this)) {
            loadData();
        } else {
            Toast.makeText(getApplicationContext(), "Please connect with internet", Toast.LENGTH_SHORT).show();

        }


        for (int i = 0; i < arrGetData.size(); i++) {
            arrDataBaseCatName.add(arrGetData.get(i).getCatName());
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mPagerAdapter = new PAdapter(this, arrGetData);
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }


    class PAdapter extends PagerAdapter {
        CatSubcatActivity catSubcatActivity;
        ArrayList<CatDataListVariableModelData> arrData;

        public PAdapter(CatSubcatActivity catSubcatActivity, ArrayList<CatDataListVariableModelData> arrData) {
            this.catSubcatActivity = catSubcatActivity;
            this.arrData = arrData;
        }

        @Override
        public int getCount() {
            return arrData.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            arrGetDataFrame.clear();
            arrGetDataFrame.addAll(dbHelpler.getAllCategoryData(arrData.get(position).getId()));
            for (int i = 0; i < arrGetDataFrame.size(); i++) {
                arrDataBaseCatNameFrame.add(arrGetDataFrame.get(i).getThemeName());
            }
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.raw_pager, null);
            TextView mTextView = view.findViewById(R.id.tv);
            mTextView.setText("Size : " + dbHelpler.getAllCategoryData(arrData.get(position).getId()).size());
            mRecyclerView = view.findViewById(R.id.rvCatData);

            if (Utils.checkNetworkConnectivity(CatSubcatActivity.this)) {
                new loaderAsyncTask(container, arrData.get(position).getId()).execute(new Void[0]);
            } else {
//            if (Utils.toast == null || Utils.toast.getView().getWindowVisibility() != View.VISIBLE) {
                Toast.makeText(getApplicationContext(), "Please connect with internet", Toast.LENGTH_SHORT).show();
//                Utils.toast.show();

            }


            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new GridLayoutManager(CatSubcatActivity.this, 2));
            mRecyclerView.removeAllViews();
            mRecyclerView.invalidate();
            mRecyclerView.setAdapter(new SubCategorySelectionAdapter(CatSubcatActivity.this, dbHelpler.getAllCategoryData(arrData.get(position).getId())));
            container.addView(view);
            return view;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
            container.removeView((View) view);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrData.get(position).getCatName();
        }
    }

    public void initDB() {
        DBManager.initializeDB(CatSubcatActivity.this);
        dbHelpler = DBManager.getInstance();
    }


    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<CatDataListModel> call = api.getFrameList(10);
        mPagerAdapter = new PAdapter(this, arrGetData);
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
                mPagerAdapter.notifyDataSetChanged();

            }
        });


    }


    class loaderAsyncTask extends AsyncTask<Void, Void, Void> {

        private String resp;
        AlertDialog alert;
        int position;
        ViewGroup container;

        public loaderAsyncTask(ViewGroup container, int position) {
            this.position = position;
            this.container = container;
        }

        protected void onPreExecute() {

            CustomTFSpan tfSpan = new CustomTFSpan(face);
            LayoutInflater inflate = CatSubcatActivity.this.getLayoutInflater();
            View view = inflate.inflate(R.layout.custom, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(CatSubcatActivity.this);
            builder.setView(R.layout.custom);
            TextView tvDialogtitle = (TextView) view.findViewById(R.id.tvDialogtitle);
            SpannableString spannableString2 = new SpannableString(
                    "Loding...");
            spannableString2.setSpan(tfSpan, 0, spannableString2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            tvDialogtitle.setText(spannableString2);
            tvDialogtitle.setTextColor(getResources().getColor(R.color.colorAccent));
            ProgressBar prog = (ProgressBar) view.findViewById(R.id.pbLoading);
            prog.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
            TextView tvDialogMessage = (TextView) view.findViewById(R.id.tvDialogMessage);

            SpannableString spannableString = new SpannableString("Fetching Videos");
            spannableString.setSpan(tfSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            tvDialogMessage.setText(spannableString);
            tvDialogMessage.setTextColor(getResources().getColor(R.color.colorAccent));
            builder.setView(view);
            alert = builder.create();
            alert.show();
            alert.setCancelable(false);
            alert.setCanceledOnTouchOutside(false);

            super.onPreExecute();
        }

        protected Void doInBackground(Void... params) {
            loadData(position);
            return null;
        }

        protected void onPostExecute(Void result) {
            mRecyclerView.setAdapter(new SubCategorySelectionAdapter(CatSubcatActivity.this, dbHelpler.getAllCategoryData(position)));
            alert.dismiss();

        }
    }

    private void loadData(final int position) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<SubCategoryDataListModel> call = api.getFrameListSub(10, position);
//        adapter = new SubCategorySelectionAdapter(CatSubcatActivity.this, arrGetDataFrame);


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
                arrGetDataFrame.addAll(dbHelpler.getAllCategoryData(position));
//                adapter.notifyDataSetChanged();

            }
        });


    }
}
