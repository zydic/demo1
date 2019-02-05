package com.example.foldergallery.adapters;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foldergallery.activity.CategorySelectionActivity;
import com.example.foldergallery.activity.SubCategorySelectionActivity;
import com.example.foldergallery.data.CatDataListVariableModelData;
import com.example.foldergallery.util.CustomTFSpan;
import com.example.photobook.R;

import java.io.File;
import java.util.ArrayList;

//import com.example.notifymypic.Photobook.SelectedPhotoCurlActivity;
//import com.example.notifymypic.Photobook.SelectedPhotoCurlActivity;
//import com.example.mycamapppp.R;
//import com.example.notifymypic.Activity.CategorySelectionActivity;
//import com.example.notifymypic.Application.Api;
//import com.example.notifymypic.Bean.CatDataListVariableModel;
//import com.example.notifymypic.Bean.SubCategoryDataListModel;
//import com.example.notifymypic.Bean.SubCategoryDataListVariableModel;
//import com.example.notifymypic.Photobook.SelectedPhotoCurlActivity;
//import com.example.notifymypic.Utils.CustomTFSpan;
//import com.example.notifymypic.Utils.Utils;


public class CategorySelectionAdapter extends RecyclerView.Adapter<CategorySelectionAdapter.Holder> {
    CategorySelectionActivity context;
    ArrayList<CatDataListVariableModelData> arrhorizontalFrames;
    static ArrayList<String> imageUrls;
    public static String albumkey = "default";
    public static int catId;
    AlertDialog alert;
    private long mLastClickTime = 0;
    int cnt = 0;
    //    DownloadFromURL d;
    AlertDialog exitAlertDialog;
    ProgressBar prog;

    public CategorySelectionAdapter(CategorySelectionActivity context,
                                    ArrayList<CatDataListVariableModelData> arrhorizontalFrames) {
        this.context = context;
        this.arrhorizontalFrames = arrhorizontalFrames;

    }

    @Override
    public int getItemCount() {
        return arrhorizontalFrames.size();
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final CatDataListVariableModelData imageModel = arrhorizontalFrames.get(position);


        holder.title.setText(imageModel.getCatName());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, SubCategorySelectionActivity.class);
                i.putExtra("id", imageModel.getId());

                Toast.makeText(context, "" + imageModel.getId(), Toast.LENGTH_SHORT).show();
                context.startActivity(i);
            }
        });
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int pos) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.raw_recyclerviewcat, parent, false);
        return new Holder(itemView);
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView thumbnail;
        ImageView ivDownloadImageIncomplete;

        public Holder(View viewHolder) {
            super(viewHolder);
            title = (TextView) viewHolder.findViewById(R.id.title);
            thumbnail = (ImageView) viewHolder.findViewById(R.id.thumbnail);
            ivDownloadImageIncomplete = (ImageView) viewHolder
                    .findViewById(R.id.ivDownloadImageIncomplete);
            thumbnail.setVisibility(View.GONE);
            ivDownloadImageIncomplete.setVisibility(View.GONE);

        }
    }

    private void checkCorruptImages(Cursor imagecursor, int dataColumnIndex) {
        if (((double) new File(imagecursor.getString(dataColumnIndex)).length()) != 0.0d) {
            this.imageUrls.add(imagecursor.getString(dataColumnIndex));
        }
    }


//    class loaderAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        private String resp;
//        CatDataListVariableModel imageModel;
//
//        public loaderAsyncTask(CatDataListVariableModel imageModel) {
//            this.imageModel = imageModel;
//        }
//
//        protected void onPreExecute() {
//
//            Typeface face = Typeface.createFromAsset(context.getAssets(), "Comfortaa-Regular.ttf");
//            CustomTFSpan tfSpan = new CustomTFSpan(face);
//            LayoutInflater inflate = context.getLayoutInflater();
//            View view = inflate.inflate(R.layout.custom, null);
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setView(R.layout.custom);
//            TextView tvDialogtitle = (TextView) view.findViewById(R.id.tvDialogtitle);
//            SpannableString spannableString2 = new SpannableString(
//                    "Loading");
//            spannableString2.setSpan(tfSpan, 0, spannableString2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            tvDialogtitle.setText(spannableString2);
//            tvDialogtitle.setTextColor((context.getResources().getColor(
//                    R.color.colorAccent)));
//            ProgressBar prog = (ProgressBar) view.findViewById(R.id.pbLoading);
//            prog.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(
//                    R.color.colorAccent
//            ), PorterDuff.Mode.SRC_IN);
//            TextView tvDialogMessage = (TextView) view.findViewById(R.id.tvDialogMessage);
//            SpannableString spannableString = new SpannableString("Please Wait...");
//            spannableString.setSpan(tfSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            tvDialogMessage.setText(spannableString);
//            tvDialogMessage.setTextColor(context.getResources().getColor(
//                    R.color.white));
//            builder.setView(view);
//            alert = builder.create();
//            alert.setCancelable(false);
//            alert.show();
//            super.onPreExecute();
//        }
//
//        protected Void doInBackground(Void... params) {
//            try {
//                Utils.bt2.clear();
//                Utils.arrListOfDownloadedFrmaes.clear();
//                catId = imageModel.getCategory_Id();
//                if (imageModel.getCategory_Id() == 0) {
//                    albumkey = imageModel.getName();
//                } else if (imageModel.getCategory_Id() == 1) {
//                    albumkey = imageModel.getName();
//                } else {
//                    Log.d("Folder", imageModel.getName());
//                    albumkey = imageModel.getName();
//                    File dir = new File(context.getFilesDir().getPath(),
//                            "/NotifyMyPicImages/" + imageModel.getName());
//                    File[] files = dir.listFiles();
//                    Log.d("Files", "Size: " + files.length);
//                    Utils.arrListOfDownloadedFrmaes.add(files[0].getAbsolutePath());
//                    for (int j = 1; j <= 7; j++) {
//                        Log.d("ino", "in");
//                        for (int i = 1; i <= files.length - 2; i++) {
//                            Utils.arrListOfDownloadedFrmaes.add(files[i].getAbsolutePath());
//                            if (cnt == 20) {
//                                cnt = 0;
//                                break;
//                            }
//                            cnt++;
//                        }
//                        if (cnt == 20) {
//                            cnt = 0;
//                            break;
//                        }
//                    }
//                    Utils.arrListOfDownloadedFrmaes.add(files[files.length - 1].getAbsolutePath());
//                }
//            } catch (Exception e) {
////                if (Utils.toast == null || Utils.toast.getView().getWindowVisibility() != View.VISIBLE) {
//                   Toast.makeText(context, "Error getting Albumbs List,try again", Toast.LENGTH_SHORT).show();
////                }
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        protected void onPostExecute(Void result) {
//            Intent intent = new Intent(context,
//                    SelectedPhotoCurlActivity.class);
//            intent.putExtra("activityIsFrom", "CategorySelectionAdapter");
//            context.startActivity(intent);
//            detail();
//            context.finish();
//        }
//    }

//    public class DownloadFromURL extends AsyncTask<String, String, String> {
//
//        ArrayList<SubCategoryDataListVariableModel> arrayList;
//        double noOfURLs = 14.28;
//        DecimalFormat decimalFormat = new DecimalFormat(".##");
//        TextView tvPercentage;
//        CatDataListVariableModel imageModel;
//        ImageView ivDownloadImageComplete;
//
//
//        public DownloadFromURL(ArrayList<SubCategoryDataListVariableModel> arrayList, CatDataListVariableModel imageModel, ImageView ivDownloadImageComplete) {
//            this.arrayList = arrayList;
//            this.imageModel = imageModel;
//            this.ivDownloadImageComplete = ivDownloadImageComplete;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            LayoutInflater inflate = context.getLayoutInflater();
//            View view = inflate.inflate(R.layout.custom, null);
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setView(R.layout.custom);
//            LinearLayout llCustom = (LinearLayout) view.findViewById(R.id.llCustom);
//            tvPercentage = (TextView) view.findViewById(R.id.tvPercentage);
//            tvPercentage.setText("0 %");
//            ImageView ivDownloading = (ImageView) view.findViewById(R.id.downloading);
//            ImageView imgClose = (ImageView) view.findViewById(R.id.imgClose);
//            imgClose.setVisibility(View.VISIBLE);
//            imgClose.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    exitAlertDialog();
//                }
//            });
//            prog = (ProgressBar) view.findViewById(R.id.pbLoading);
//            prog.setVisibility(View.GONE);
//            GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(
//                    ivDownloading);
//            Glide.with(context).load(R.drawable.ic_downlod).into(imageViewTarget);
//            llCustom.setBackgroundColor(context.getResources().getColor(R.color.download));
//            prog.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
//            TextView tvDialogMessage = (TextView) view.findViewById(R.id.tvDialogMessage);
//            tvDialogMessage.setText("Please wait your Album theme is downloading...");
//            builder.setView(view);
//            alert = builder.create();
//            alert.show();
//            alert.setCancelable(false);
//
//        }
//
//        @Override
//        protected String doInBackground(String... fileUrl) {
//            for (int i = 0; i < arrayList.size(); i++) {
//                if (isCancelled())
//                    break;
//                if (Utils.checkNetworkConnectivity(context)) {
//                    int count;
//                    try {
//                        URL url = new URL(arrayList.get(i).getImages());
//                        URLConnection urlConnection = url.openConnection();
//                        urlConnection.connect();
//                        int fileLength = urlConnection.getContentLength();
//                        InputStream inputStream = new BufferedInputStream(
//                                url.openStream(), 8192);
//                        File dir = new File(context.getFilesDir().getPath(),
//                                "/NotifyMyPicImages/" + imageModel.getName() + "/");
//                        if (!dir.exists()) {
//                            dir.mkdirs();
//                        }
//                        File file = new File(dir, arrayList.get(i).getName() + ".png");
//                        OutputStream outputStream = new FileOutputStream(
//                                file.getAbsoluteFile());
//                        byte data[] = new byte[1024];
//                        long total = 0;
//                        while ((count = inputStream.read(data)) != -1) {
//                            total += count;
//                            publishProgress("" + (int) ((total * 100) / fileLength));
//                            outputStream.write(data, 0, count);
//                        }
//                        outputStream.flush();
//                        outputStream.close();
//                        inputStream.close();
//                    } catch (Exception e) {
//                    }
//                } else {
//                    Log.d("failed", "fail");
//                }
//            }
//            return null;
//        }
//
//        protected void onProgressUpdate(String... progress) {
//            prog.setProgress(Integer.parseInt(progress[0]));
//            if (Integer.parseInt(progress[0]) == 100) {
//                if (noOfURLs > 96) {
//                    tvPercentage.setText(100 + " %");
//                } else {
//                    tvPercentage.setText(decimalFormat.format(noOfURLs) + " %");
//                }
//                noOfURLs = noOfURLs + 14.28;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String file_url) {
//            if (Utils.checkNetworkConnectivity(context)) {
//                imageModel.setIsStatic(1);
//                imageModel.setIsdownloaded(1);
//                context.dbHelpler.updateCategoryDetail(imageModel, imageModel.getCategory_Id());
//                context.arrGetData.clear();
//                context.arrGetData.addAll(context.dbHelpler.getAllCategoryData());
//                context.adapter.notifyDataSetChanged();
//                alert.dismiss();
//            } else {
//                downloadStoped();
//                alert.dismiss();
//                Log.d("failed", "fail22222");
//            }
//        }
//    }

//    public void detail() {
//        ArrayList<CatDataListVariableModel> hello = context.dbHelpler.getAllCategoryData();
//
//        for (int i = 0; i < hello.size(); i++) {
//            arrhorizontalFrames.get(i).setIsdownloaded(0);
//            context.dbHelpler.updateCategoryDetail(arrhorizontalFrames.get(i), arrhorizontalFrames.get(i).getCategory_Id());
//        }
//        context.arrGetData.clear();
//        context.arrGetData.addAll(context.dbHelpler.getAllCategoryData());
//        context.adapter.notifyDataSetChanged();
//    }

    private void downloadStoped() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                context, R.style.AppAlertDialog);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "Comfortaa-Regular.ttf");
        CustomTFSpan tfSpan = new CustomTFSpan(face);
        SpannableString spannableString = new SpannableString("No internet connection");
        spannableString.setSpan(tfSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setTitle(spannableString);
        SpannableString ss = new SpannableString("Check your internet connection please");
        ss.setSpan(tfSpan, 0, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setMessage(ss);
        builder.setCancelable(false);
        builder.setPositiveButton(context.getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        context.adapter.notifyDataSetChanged();

                        if (exitAlertDialog != null) {
                            exitAlertDialog.dismiss();
                        }
                    }

                });

        AlertDialog alert = builder.create();
        alert.show();
        Button btnYes = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        btnYes.setAllCaps(false);
        btnYes.setTextColor(context.getResources().getColor(R.color.white));
    }


//    private void exitAlertDialog() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(
//                context, R.style.AppAlertDialog);
//        builder.setTitle("Stop download ?");
//        builder.setMessage("Are you sure want to stop dowloading processs ?");
//        builder.setCancelable(false);
//        builder.setPositiveButton(context.getResources().getString(R.string.yes),
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        d.cancel(true);
//                        alert.dismiss();
//                        context.adapter.notifyDataSetChanged();
//
//                    }
//
//                });
//
//        builder.setNegativeButton(context.getResources().getString(R.string.no),
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                    }
//                });
//
//        exitAlertDialog = builder.create();
//        exitAlertDialog.show();
//        TextView textView = (TextView) exitAlertDialog.getWindow().findViewById(android.R.id.message);
//        TextView alertTitle = (TextView) exitAlertDialog.getWindow().findViewById(R.id.alertTitle);
//        textView.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace());
//        alertTitle.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace());
//        Button btnYes = exitAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//        btnYes.setAllCaps(false);
//        btnYes.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace());
//        btnYes.setTextColor(context.getResources().getColor(R.color.white));
//        Button btnNo = exitAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//        btnNo.setAllCaps(false);
//        btnNo.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace());
//        textView.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace());
//        alertTitle.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace());
//        btnNo.setTextColor(context.getResources().getColor(R.color.white));
//        textView.setTextColor(context.getResources().getColor(R.color.white));
//    }


}
