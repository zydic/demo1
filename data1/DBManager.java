package com.example.foldergallery.data;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static com.example.foldergallery.data.DBContext.FRAME_CATEGORY_ID;
import static com.example.foldergallery.data.DBContext.FRAME_THEME_NAME;


public class DBManager {
    private static final String LOG_TAG = "Database";
    private static DBManager dbManager = null;
    private static DBContext databaseContext = null;
    private static SQLiteDatabase database = null;
    private AtomicInteger openCounter = new AtomicInteger();

    public synchronized static DBManager getInstance() {
        if (dbManager == null) {
            throw new IllegalStateException(
                    String.format(
                            "%s is not initialized, App initializeDB(..) method first.",
                            DBManager.class.getSimpleName()));
        }
        return dbManager;
    }

    public static synchronized boolean initializeDB(Context context) {
        try {
            if (dbManager == null) {
                dbManager = new DBManager();
                databaseContext = new DBContext(context);
            }
            return true;
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Error initializing DB", ex);
            return false;
        }
    }

    public synchronized SQLiteDatabase getWritableDB() {
        try {
            if (openCounter.incrementAndGet() == 1) {
                database = databaseContext.getWritableDatabase();
            }
            return database;
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Error opening DB in W mode ", ex);
            return null;
        }
    }

    public synchronized boolean closeDatabase() {
        if (openCounter.decrementAndGet() == 0) {
            database.close();
        }
        return false;
    }


    public synchronized void insertCategoryDetail(SubCategoryDataListVariableModel categoryModel) {
        ContentValues values = new ContentValues();
        values.put(DBContext.FRAME_ID,
                categoryModel.getId());
        values.put(FRAME_CATEGORY_ID,
                categoryModel.getCatId());
        values.put(DBContext.FRAME_APPLICATION_CATEGORY_ID, categoryModel.getApplicationId());
        values.put(DBContext.FRAME_APPLICATION_VERSION, categoryModel.getAppVersion());
        values.put(DBContext.FRAME_THEME_NAME, categoryModel.getThemeName());
        values.put(DBContext.FRAME_THEME_INFO, categoryModel.getThemeInfo());
        values.put(DBContext.FRAME_THEME_BUNDLE, categoryModel.getThemeBundle());
        values.put(DBContext.FRAME_THEME_BUNDLE_SIZE, categoryModel.getBundleSize());
        values.put(DBContext.FRAME_CATEGORY_THUMBNAILBIG, categoryModel.getThumnailBig());
        values.put(DBContext.FRAME_CATEGORY_THUMBNAILSMALL, categoryModel.getThumnailSmall());
        values.put(DBContext.FRAME_CATEGORY_SOUNDNAME, categoryModel.getSoundName());
        values.put(DBContext.FRAME_CATEGORY_SOUNDFILE, categoryModel.getSoundFile());
        values.put(DBContext.FRAME_CATEGORY_SOUNDFILESIZE, categoryModel.getSoundSize());
        values.put(DBContext.FRAME_CATEGORY_NOOFIMAGES, categoryModel.getNoOfImages());
        values.put(DBContext.FRAME_CATEGORY_HEIGHT, categoryModel.getHeight());
        values.put(DBContext.FRAME_CATEGORY_WIDTH, categoryModel.getWidth());
        values.put(DBContext.FRAME_CATEGORY_VIDEOORCARD, categoryModel.getVideoOrCard());
        values.put(DBContext.FRAME_CATEGORY_GAMEOBJECT, categoryModel.getGameobjectName());
        values.put(DBContext.FRAME_CATEGORY_GETISPREMIUM, categoryModel.getIsPreimum());
        values.put(DBContext.FRAME_CATEGORY_GET_THEME_COUNTER, categoryModel.getThemeCounter());
        values.put(DBContext.FRAME_CATEGORY_GET_STATUS, categoryModel.getStatus());
        values.put(DBContext.FRAME_CATEGORY_GET_STATUS_ISNEWRELEASE, categoryModel.getIsNewRealise());
        values.put(DBContext.FRAME_CATEGORY_ISSTATIC, categoryModel.getIsStatic());
        values.put(DBContext.FRAME_CATEGORY_ISDOWNLOADED, categoryModel.getIsdownloaded());

        getWritableDB().insertWithOnConflict(
                DBContext.TABLE_NAME, null, values,
                SQLiteDatabase.CONFLICT_IGNORE);
        closeDatabase();
    }


    public synchronized void insertCategoryDetail(CatDataListVariableModelData categoryModeldata) {
        ContentValues values = new ContentValues();
        values.put(DBContext.DATA_CATEGOTY_ID,
                categoryModeldata.getId());
        values.put(DBContext.DATA_APPLICATION_CATEGORY_ID,
                categoryModeldata.getApplicationId());
        values.put(DBContext.DATA_CATEGOTY_NAME, categoryModeldata.getCatName());
        values.put(DBContext.DATA_CATEGOTY_ISSTATIC, categoryModeldata.getIsStatic());
        values.put(DBContext.DATA_CATEGOTY_ISDOWNLOADED, categoryModeldata.getIsdownloaded());
        getWritableDB().insertWithOnConflict(
                DBContext.TABLE_NAME_DATA, null, values,
                SQLiteDatabase.CONFLICT_IGNORE);
        closeDatabase();
    }

    public synchronized void updateCategoryDetail(SubCategoryDataListVariableModel categoryModel, int id) {
        ContentValues values = new ContentValues();

        values.put(DBContext.FRAME_CATEGORY_ISSTATIC,
                categoryModel.getIsStatic());
        values.put(DBContext.FRAME_CATEGORY_ISDOWNLOADED,
                categoryModel.getIsdownloaded());

        getWritableDB().updateWithOnConflict(
                DBContext.TABLE_NAME, values, FRAME_CATEGORY_ID + "=" + id, null,
                SQLiteDatabase.CONFLICT_IGNORE);
        closeDatabase();
    }

//	public synchronized ArrayList<SubCategoryDataListVariableModel> getCategoryListForCheckDuplicateData() {
//		ArrayList<SubCategoryDataListVariableModel> allData = new ArrayList<SubCategoryDataListVariableModel>();
//		String select_query = "SELECT * FROM "
//				+ DBContext.TABLE_NAME + " WHERE "
//				+ DBContext.COLUMN_PHOTO_FRAME_CATEGORY_TABLE_STATUS
//				+ " ='1' AND "
//				+ DBContext.COLUMN_PHOTO_FRAME_CATEGORY_IS_STATIC + " = '0'"
//				+ " ORDER BY "
//				+ DBContext.COLUMN_PHOTO_FRAME_CATEGORY_IS_STATIC + " DESC";
//		Cursor mCursor = getWritableDB().rawQuery(select_query, null);
//		if (mCursor.getCount() != 0) {
//			if (mCursor != null) {
//				mCursor.moveToFirst();
//				for (int i = 0; i < mCursor.getCount(); i++) {
//					CategoryModel categoryModel = new CategoryModel();
//					categoryModel
//							.setCategoryName(mCursor.getString(mCursor
//									.getColumnIndex(DBContext.COLUMN_PHOTO_FRAME_CATEGORY_NAME)));
//					categoryModel
//							.setFrameCategoryId(mCursor.getInt(mCursor
//									.getColumnIndex(DBContext.COLUMN_PHOTO_FRAME_CATEGORY_ID_NO)));
//					categoryModel
//							.setStaticCategory(mCursor.getInt(mCursor
//									.getColumnIndex(DBContext.COLUMN_PHOTO_FRAME_CATEGORY_IS_STATIC)));
//					allData.add(categoryModel);
//					mCursor.moveToNext();
//				}
//			}
//		}
//		mCursor.close();
//		closeDatabase();
//		return allData;
//	}

    public synchronized ArrayList<SubCategoryDataListVariableModel> getAllCategoryData(int catid) {
        ArrayList<SubCategoryDataListVariableModel> allData = new ArrayList<SubCategoryDataListVariableModel>();
        String select_query = "SELECT * FROM "
                + DBContext.TABLE_NAME + " WHERE " + FRAME_CATEGORY_ID + "=" + catid;
        Cursor mCursor = getWritableDB().rawQuery(select_query, null);
        if (mCursor.getCount() != 0) {
            if (mCursor != null) {
                mCursor.moveToFirst();
                for (int i = 0; i < mCursor.getCount(); i++) {
                    SubCategoryDataListVariableModel categoryModel = new SubCategoryDataListVariableModel();
                    categoryModel
                            .setId(mCursor.getInt(mCursor
                                    .getColumnIndex(DBContext.FRAME_ID)));


                    categoryModel
                            .setCatId(mCursor.getString(mCursor
                                    .getColumnIndex(FRAME_CATEGORY_ID)));
                    categoryModel
                            .setApplicationId(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_APPLICATION_CATEGORY_ID)));
                    categoryModel
                            .setAppVersion(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_APPLICATION_VERSION)));
                    categoryModel
                            .setThemeName(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_THEME_NAME)));
                    categoryModel
                            .setThemeInfo(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_THEME_INFO)));
                    categoryModel
                            .setThemeBundle(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_THEME_BUNDLE)));
                    categoryModel
                            .setBundleSize(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_THEME_BUNDLE_SIZE)));
                    categoryModel
                            .setThumnailBig(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_THUMBNAILBIG)));
                    categoryModel
                            .setThumnailSmall(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_THUMBNAILSMALL)));
                    categoryModel
                            .setSoundName(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_SOUNDNAME)));
                    categoryModel
                            .setSoundFile(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_SOUNDFILE)));
                    categoryModel
                            .setSoundSize(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_SOUNDFILESIZE)));
                    categoryModel
                            .setNoOfImages(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_NOOFIMAGES)));
                    categoryModel
                            .setHeight(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_HEIGHT)));
                    categoryModel
                            .setWidth(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_WIDTH)));
                    categoryModel
                            .setVideoOrCard(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_VIDEOORCARD)));
                    categoryModel
                            .setGameobjectName(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_GAMEOBJECT)));
                    categoryModel
                            .setIsPreimum(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_GETISPREMIUM)));
                    categoryModel
                            .setThemeCounter(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_GET_THEME_COUNTER)));
                    categoryModel
                            .setStatus(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_GET_STATUS)));
                    categoryModel
                            .setIsNewRealise(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_GET_STATUS_ISNEWRELEASE)));


                    categoryModel
                            .setIsStatic(mCursor.getInt(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_ISSTATIC)));
                    categoryModel
                            .setIsdownloaded(mCursor.getInt(mCursor
                                    .getColumnIndex(DBContext.FRAME_CATEGORY_ISDOWNLOADED)));
                    allData.add(categoryModel);
                    mCursor.moveToNext();
                }
            }
        }
        mCursor.close();
        closeDatabase();
        return allData;
    }


    public synchronized ArrayList<CatDataListVariableModelData> getAllMainCategoryData() {
        ArrayList<CatDataListVariableModelData> allData = new ArrayList<CatDataListVariableModelData>();
        String select_query = "SELECT * FROM "
                + DBContext.TABLE_NAME_DATA;
        Cursor mCursor = getWritableDB().rawQuery(select_query, null);
        if (mCursor.getCount() != 0) {
            if (mCursor != null) {
                mCursor.moveToFirst();
                for (int i = 0; i < mCursor.getCount(); i++) {
                    CatDataListVariableModelData categoryModel = new CatDataListVariableModelData();
                    categoryModel
                            .setId(mCursor.getInt(mCursor
                                    .getColumnIndex(DBContext.DATA_CATEGOTY_ID)));
                    categoryModel
                            .setApplicationId(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.DATA_APPLICATION_CATEGORY_ID)));
                    categoryModel
                            .setCatName(mCursor.getString(mCursor
                                    .getColumnIndex(DBContext.DATA_CATEGOTY_NAME)));

                    categoryModel
                            .setIsStatic(mCursor.getInt(mCursor
                                    .getColumnIndex(DBContext.DATA_CATEGOTY_ISSTATIC)));
                    categoryModel
                            .setIsdownloaded(mCursor.getInt(mCursor
                                    .getColumnIndex(DBContext.DATA_CATEGOTY_ISDOWNLOADED)));
                    allData.add(categoryModel);
                    mCursor.moveToNext();
                }
            }
        }
        mCursor.close();
        closeDatabase();
        return allData;
    }

    public synchronized boolean isAlreadyAdded(String themeName) {

        String select_query = "SELECT * FROM "
                + DBContext.TABLE_NAME + " WHERE " + FRAME_THEME_NAME + " = '" + themeName +"'";
        Cursor mCursor = getWritableDB().rawQuery(select_query, null);
        if (mCursor.getCount() != 0) {
            return true;
        }
        return false;
    }


//
//	public synchronized ArrayList<ModelPhotoCount> getFrames(int frameType,
//			int photoCount) {
//		ArrayList<ModelPhotoCount> allData = new ArrayList<ModelPhotoCount>();
//		String select_query = "SELECT * FROM " + DBContext.TABLE_PHOTO_COUNT
//				+ " WHERE " + DBContext.COLUMN_PHOTO_COUNT_TABLE_STATUS
//				+ " ='1' AND " + DBContext.COLUMN_PHOTO_COUNT_FRAME_TYPE
//				+ " = " + frameType + " AND "
//				+ DBContext.COLUMN_PHOTO_COUNT_VALUE + " = '" + photoCount
//				+ "'" + " ORDER BY " + DBContext.COLUMN_PHOTO_COUNT_ID
//				+ " DESC";
//
//		Cursor mCursor = getWritableDB().rawQuery(select_query, null);
//		if (mCursor.getCount() != 0) {
//			if (mCursor != null) {
//				mCursor.moveToFirst();
//				for (int i = 0; i < mCursor.getCount(); i++) {
//					ModelPhotoCount modelPhotoCount = new ModelPhotoCount();
//					modelPhotoCount
//							.setFrameName(mCursor.getString(mCursor
//									.getColumnIndex(DBContext.COLUMN_PHOTO_COUNT_FRAME_NAME)));
//					modelPhotoCount
//							.setFrameType(mCursor.getInt(mCursor
//									.getColumnIndex(DBContext.COLUMN_PHOTO_COUNT_FRAME_TYPE)));
//					modelPhotoCount
//							.setPhotoCount(mCursor.getInt(mCursor
//									.getColumnIndex(DBContext.COLUMN_PHOTO_COUNT_VALUE)));
//					modelPhotoCount
//							.setFrameCategory(mCursor.getString(mCursor
//									.getColumnIndex(DBContext.COLUMN_PHOTO_COUNT_FRAME_CATEGORY)));
//					allData.add(modelPhotoCount);
//					mCursor.moveToNext();
//				}
//			}
//		}
//		mCursor.close();
//		closeDatabase();
//		return allData;
//	}

}