package com.example.gentrit.contactsharingusingqrcode;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gentrit on 07/07/2017.
 */

public class Databaza extends SQLiteOpenHelper {

    public Databaza(Context context)
    {
        super(context, Parametrat.Databaza,null,Parametrat.Verzioni);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Parametrat.TabelaRegjistri + " (" + Parametrat.Emri + " varchar(30)," +
                Parametrat.Mbiemri + " varchar(20), " + Parametrat.NrTelefonit + " varchar(20),"+ Parametrat.Email + " varchar(20))");
        db.execSQL("Insert into tblRegjistri(Emri, Mbiemri, NrTelefonit, Email) values('Emri','Mbiemri','Numri i Telefonit','Email')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
