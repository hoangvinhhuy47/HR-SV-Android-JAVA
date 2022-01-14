package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.myapplication.Model.DataCheckIn;
import com.example.myapplication.Model.SettingConfig;
import com.example.myapplication.Model.User;

public class DBHelper extends SQLiteOpenHelper {
    SQLiteDatabase db = this.getReadableDatabase();

    public DBHelper(@Nullable Context context) {
        super(context, "HR.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table SettingConfig(ConfigID int primary key,WebAPI text,SiteID text,StoreID text, GUIID text)");
        db.execSQL("Create Table User(UserID text primary key,UserName text,Password text,PasswordEncode text, FullName text)");
//        db.execSQL("Create Table CheckIn(ShiftName text primary key, DateTimeIn text, DateTimeOut text,IsCheckIn int, CheckTime text)");
        db.execSQL("Create Table CheckIn(IsCheckIn int primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists SettingConfig");
        db.execSQL("drop table if exists TaiKhoan");
        db.execSQL("drop table if exists CheckIn");
    }
    public boolean InsertCheckIn(Integer IsCheckIn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IsCheckIn",IsCheckIn);
        long rowinserted = db.insert("CheckIn",null,contentValues);
        if (rowinserted==-1)
            return false;
        return true;
    }
    public boolean InsertSettingConfig(SettingConfig settingConfig) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("ConfigID", settingConfig.getConfigID());
        contentValue.put("WebAPI", settingConfig.getWebAPI());
        contentValue.put("SiteID", settingConfig.getSiteID());
        contentValue.put("StoreID", settingConfig.getStoreID());
        contentValue.put("GUIID", settingConfig.getGuiID());
        long rowInserted = db.insert("SettingConfig", null, contentValue);
        if (rowInserted == -1) return false;
        else return true;
    }

    public boolean InsertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("UserID", user.getUserID());
        contentValue.put("UserName", user.getUserName());
        contentValue.put("Password", user.getPassword());
        contentValue.put("PasswordEncode", user.getPasswordEncode());
        contentValue.put("FullName", user.getFullName());
        long rowInserted = db.insert("User", null, contentValue);
        if (rowInserted == -1) return false;
        else return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    // là hàm kiểm tra dữ liệu
    public User GetUser() {
        User user = new User();
        try (Cursor cursor = db.rawQuery("Select * from User  ", null)) {
            if (cursor.moveToFirst()) {
                user.setUserID(cursor.getString(0));
                user.setPassword(cursor.getString(2));
                user.setPasswordEncode(cursor.getString(3));
                user.setFullName(cursor.getString(4));
                user.setUserName(cursor.getString(1));
            }
            return user;
        } catch (Exception ex) {
            return null;
        }
    }
    public boolean InsertCheckIn(int ischeckin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IsCheckIn",ischeckin);
        long ins = db.insert("CheckIn",null,contentValues);
        if (ins == -1) return false;
        else  return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    // là hàm kiểm tra dữ liệu
    public SettingConfig GetConfig() {
        SettingConfig settingConfig = new SettingConfig();
        try (Cursor cursor = db.rawQuery("Select * From SettingConfig  ", null)) {
            if (cursor.moveToFirst()) {
                settingConfig.setConfigID(cursor.getInt(0));
                settingConfig.setWebAPI(cursor.getString(1));
                settingConfig.setSiteID(cursor.getString(2));
                settingConfig.setStoreID(cursor.getString(3));
                settingConfig.setGuiID(cursor.getString(4));
            }
            return settingConfig;
        } catch (Exception ex) {
            return null;
        }
    }
    public int GetIscheck(){
        try(Cursor cursor =db.rawQuery("Select * From CheckIn ",null)){
          cursor.moveToFirst();
            return cursor.getInt(0);
        }
        catch (Exception ex){
            return 0;
        }
    }


    public boolean DeleteUser(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.isOpen();
        return db.delete("User", "UserID =?", new String[]{String.valueOf(userID)}) > 0;

    }

    public boolean DeleteConfig(int configID) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.isOpen();
        return db.delete("SettingConfig", "ConfigID =?", new String[]{String.valueOf(configID)}) > 0;

    }
    public boolean DeleteCheckIn(int IsCheckIn){
        SQLiteDatabase db = getReadableDatabase();
        db.isOpen();
        return db.delete("CheckIn","IsCheckIn=?", new String[]{String.valueOf(IsCheckIn)})>0;
    }

}