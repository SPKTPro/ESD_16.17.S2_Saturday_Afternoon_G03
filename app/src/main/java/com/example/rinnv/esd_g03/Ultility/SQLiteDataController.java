package com.example.rinnv.esd_g03.Ultility;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rinnv.esd_g03.Models.Example;
import com.example.rinnv.esd_g03.Models.Phonetic;
import com.example.rinnv.esd_g03.Models.Pronounce;
import com.example.rinnv.esd_g03.Models.Sentence;
import com.example.rinnv.esd_g03.Models.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * Created by thaihuynh on 4/24/2017.
 */

public class SQLiteDataController extends SQLiteOpenHelper {
    public String DB_PATH = "//data//data//%s//databases//";
    // đường dẫn nơi chứa database
    private static String DB_NAME = "Pronuciation";
    public SQLiteDatabase database;
    private final Context mContext;

    public SQLiteDataController(Context context) {
        super(context, DB_NAME, null, 1);
        DB_PATH = String.format(DB_PATH, context.getPackageName());
        this.mContext = context;
    }

    private void copyDataBase() throws IOException {

        try {
            InputStream myInput = mContext.getAssets().open("Pronuciation.sqlite");
            OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            Log.d("Tag", "copyDataBase: " + e.getLocalizedMessage() + "  " + DB_PATH + DB_NAME);
        }
    }

    public boolean isCreatedDatabase() throws IOException {
        // Default là đã có DB
        boolean result = true;
        // Nếu chưa tồn tại DB thì copy từ Asses vào Data

        if (!checkExistDataBase()) {
            this.getReadableDatabase();

            try {
                copyDataBase();
                result = false;

            } catch (Exception e) {
                Log.d("Tag", "abc: " + e.getMessage());
            }
        }

        return result;
    }

    private boolean checkExistDataBase() {
        try {
            String myPath = DB_PATH + DB_NAME;
            File fileDB = new File(myPath);

            if (fileDB.exists()) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteDatabase() {
        File file = new File(DB_PATH + DB_NAME);
        if (file != null && file.exists()) {
            return file.delete();
        }
        return false;
    }

    public void openDataBase() throws SQLException {
        try {
            database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            Log.d("Tag", "openDataBase: " + e.getMessage());
        }
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //do nothing
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //do nothing
    }

    // Cac method get data

    public ArrayList<Example> getListExample(String IDExample) {
        ArrayList<Example> list = new ArrayList<>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from Example where Id_Example = "+'"'+IDExample+'"', null);
            Example example;
            while (cs.moveToNext()) {
                example = new Example(cs.getString(0), cs.getString(1), cs.getString(2));
                list.add(example);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    public ArrayList<Phonetic> getListPhonetic(String phoneticGrId) {
        ArrayList<Phonetic> list = new ArrayList<>();
        try {
            openDataBase();
            String query = "select * from PHONETIC where Pho_Group = " + '"' + phoneticGrId + '"';
            Cursor cs = database.rawQuery(query, null);
            Log.d("Tag", "getListPhonetic: " + query);
            Log.d("Tag", "getListPhonetic: " + cs.getCount());

            Phonetic phonetic;
            while (cs.moveToNext()) {
                phonetic = new Phonetic(Integer.parseInt(cs.getString(0)), cs.getString(1),
                        cs.getString(2), cs.getString(3), cs.getString(4), Integer.parseInt(cs.getString(5)));
                list.add(phonetic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    public ArrayList<Pronounce> getProunceByPhonetic(String phonetic) {
        ArrayList<Pronounce> list = new ArrayList<>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from PRONOUNCE where Id_Pronounce =  "+'"'+phonetic+'"', null);
            Pronounce pronounce;
            while (cs.moveToNext()) {
                pronounce = new Pronounce(cs.getString(0), cs.getString(1));
                list.add(pronounce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;

    }

    public ArrayList<Pronounce> getListPronounce() {
        ArrayList<Pronounce> list = new ArrayList<>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from PRONOUNCE", null);
            Pronounce pronounce;
            while (cs.moveToNext()) {
                pronounce = new Pronounce(cs.getString(0), cs.getString(1));
                list.add(pronounce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    public ArrayList<Sentence> getListSentence() {
        ArrayList<Sentence> list = new ArrayList<>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from SENTENCE", null);
            Sentence sentence;
            while (cs.moveToNext()) {
                sentence = new Sentence(cs.getString(0), cs.getString(1),
                        Integer.parseInt(cs.getString(2)), cs.getString(3), cs.getString(4));
                list.add(sentence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    //code getWordByPhoneticGrID
    public ArrayList<Word> getWordByPhoneticGrID(String phoneticGrId) {
        ArrayList<Word> list = new ArrayList<>();
        try {
            openDataBase();
            String query = "select * from WORD where Pho_Group = " + '"' + phoneticGrId + '"';
            Cursor cs = database.rawQuery(query, null);
            Log.d("Tag", "getWordByPhoneticGrID: " + query);
            Log.d("Tag", "getWordByPhoneticGrID: " + cs.getCount());

            Word word;
            while (cs.moveToNext()) {
                word = new Word(cs.getString(0), cs.getString(1),
                        Integer.parseInt(cs.getString(2)), cs.getString(3));
                list.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
    //code getSentenceByPhoneticGrID
    public ArrayList<Sentence> getSentenceByPhoneticGrID(String phoneticGrId) {
        ArrayList<Sentence> list = new ArrayList<>();
        try {
            openDataBase();
            String query = "select * from SENTENCE where Pho_Group = " + '"' + phoneticGrId + '"';
            Cursor cs = database.rawQuery(query, null);
            Log.d("Tag", "getSentenceByPhoneticGrID: " + query);
            Log.d("Tag", "getSentenceByPhoneticGrID: " + cs.getCount());

            Sentence sentence;
            while (cs.moveToNext()) {
                sentence = new Sentence(cs.getString(0), cs.getString(1),
                        Integer.parseInt(cs.getString(2)), cs.getString(3),cs.getString(4));
                list.add(sentence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
}
