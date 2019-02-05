package com.example.foldergallery.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.foldergallery.util.EPreferences;
import com.example.foldergallery.util.PermissionModelUtil;
import com.example.foldergallery.util.TypeFaceUtil;
import com.example.foldergallery.util.Utils;
import com.example.photobook.R;

public class LauncherActivity extends AppCompatActivity implements
        OnClickListener {
    PermissionModelUtil modelUtil;
    EPreferences ePref;
    ComponentName SecurityComponentName = null;
    private static final int PERMISSIONS_REQUEST = 922;
    private boolean isOpenFisrtTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);
        ePref = EPreferences.getInstance(LauncherActivity.this);
        bindView();
        init();
        addListener();
    }


    private void bindView() {
    }

    private void init() {
        Utils.isVideoCreationRunning = true;
        modelUtil = new PermissionModelUtil(this);
        if (modelUtil.needPermissionCheck()) {
            permissionDialogSecond();
        } else {
//            MyApplication.getInstance().getFolderList();
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {

        if (requestCode != PERMISSIONS_REQUEST) {
            return;
        }
        if (grantResults[0] == 0) {
            if (Build.VERSION.SDK_INT < 23) {
                return;
            }
            if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0
                    || checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                permissionDialogSecond();
            }
        } else if (Build.VERSION.SDK_INT < 23) {

        } else {
            if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0
                    || checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                permissionDialogSecond();
            }
        }
    }

    private void addListener() {
        findViewById(R.id.tvSetting).setOnClickListener(this);
        findViewById(R.id.ivcreatepb).setOnClickListener(this);
//		findViewById(R.id.tvInvite).setOnClickListener(this);
//		findViewById(R.id.tvLikeUs).setOnClickListener(this);
//		findViewById(R.id.tvRateUs).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivcreatepb:
                if (!modelUtil.needPermissionCheck()) {



                    Intent i = new Intent(LauncherActivity.this,
                            CatSubcatActivity.class);
                    startActivity(i);


                } else {
                    permissionDialogSecond();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        rateUS_alert_Dialog();
    }

    private void rateUS_alert_Dialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AppAlertDialogSetting)
                .setTitle("Rate 5 Stars before exit")
                .setMessage(("Support us to serve you better. Rate 5 stars before exit"))
                .setNegativeButton(getResources().getString(R.string.exit),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        })
                .setPositiveButton(("Give us 5 star"),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                                startActivity(new Intent(
                                        "android.intent.action.VIEW", Uri
                                        .parse("market://details?id="
                                                + getPackageName())));

                            }
                        })
                .setNeutralButton(
                        R.string.no,
                        (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(
                                    final DialogInterface dialogInterface,
                                    final int n) {
                                dialogInterface.cancel();

                            }
                        })


                .create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_;

        alertDialog.show();
        TextView textView = (TextView) alertDialog.getWindow().findViewById(android.R.id.message);
        TextView alertTitle = (TextView) alertDialog.getWindow().findViewById(R.id.alertTitle);
        Button button1 = (Button) alertDialog.getWindow().findViewById(android.R.id.button1);
        Button button2 = (Button) alertDialog.getWindow().findViewById(android.R.id.button2);
        Button button3 = (Button) alertDialog.getWindow().findViewById(android.R.id.button3);
        textView.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
        alertTitle.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
        button1.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
        button2.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
        button3.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
        button1.setTextColor(getResources().getColor(R.color.white));
        button2.setTextColor(getResources().getColor(R.color.white));
        button3.setTextColor(getResources().getColor(R.color.white));
        textView.setTextColor(getResources().getColor(R.color.white));
    }


    private void permissionDialogSecond() {
        TextView tvpermission, tvstorage, tvpermissiontips, tvgrantpermission, tvPhone;
        final Dialog dialog = new Dialog(LauncherActivity.this, R.style.AppAlertDialogSetting);
        dialog.setContentView(R.layout.permissionsdialog);
        tvPhone = (TextView) dialog.findViewById(R.id.tvPhone);
        tvgrantpermission = (TextView) dialog.findViewById(R.id.tvgrantpermission);
        tvstorage = (TextView) dialog.findViewById(R.id.tvstorage);
        tvpermissiontips = (TextView) dialog.findViewById(R.id.tvpermissiontips);
        tvpermission = (TextView) dialog.findViewById(R.id.tvpermission);
        tvgrantpermission.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
        tvstorage.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
        tvpermissiontips.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
        tvpermission.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
        tvPhone.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));

        dialog.setTitle(getResources().getString(R.string.permission)
                .toString());


        dialog.setCancelable(false);
        Button ok = (Button) dialog.findViewById(R.id.ok);
        ok.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
        ok.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    LauncherActivity.this
                            .requestPermissions(
                                    new String[]{

                                            "android.permission.READ_EXTERNAL_STORAGE",
                                            "android.permission.WRITE_EXTERNAL_STORAGE",
                                            "android.hardware.camera", "android.permission.READ_PHONE_STATE"},
                                    LauncherActivity.PERMISSIONS_REQUEST);
                }
                dialog.dismiss();
            }
        });
        if (this.isOpenFisrtTime) {
            Button setting = (Button) dialog.findViewById(R.id.settings);
            setting.setTypeface(TypeFaceUtil.TEXTSTYLE.getTypeFace(getApplicationContext()));
            setting.setVisibility(0);
            setting.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(
                            "android.settings.APPLICATION_DETAILS_SETTINGS",
                            Uri.fromParts("package",
                                    LauncherActivity.this.getPackageName(),
                                    null));
                    intent.addFlags(268435456);
                    LauncherActivity.this.startActivityForResult(intent, 101);
                    dialog.dismiss();
                }
            });
        } else {
            this.isOpenFisrtTime = true;
        }
        dialog.show();


    }

}