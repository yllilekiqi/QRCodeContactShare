package com.example.gentrit.contactsharingusingqrcode;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Gentrit on 07/07/2017.
 */

public class Kontaktet_Regjistruara extends Activity{
    Adapteri objadapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.kontaktet_regjistruara);

        objadapter = new Adapteri();
        ListView lv = (ListView)findViewById(R.id.lista);

        lv.setAdapter(objadapter);

        new clsGetData().execute();


    }
    ArrayList<clsKontakti> lista = new ArrayList<>();
    public class Adapteri extends ArrayAdapter<clsKontakti> {

        public Adapteri() {
            super(getApplicationContext(), R.layout.layout_row,lista);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View row = convertView;
            clsKontaktiHolder holder = null;

            if(row==null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.layout_row, parent,false);
                holder = new clsKontaktiHolder(row);
                row.setTag(holder);

            }else
            {
                holder = (clsKontaktiHolder)row.getTag();
            }
            //Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/ANTIPASTO.TFF");
            //holder.getTv_item().setTypeface(tf);
            holder.getTvEmri().setText(lista.get(position).getEmri());
            holder.getTvMbiemri().setText(lista.get(position).getMbiemri());
            holder.getTvNrTelefonit().setText(lista.get(position).getNrTelefonit());
            holder.getTvEmail().setText(lista.get(position).getEmail());

            return row;

        }

    }

    public class clsGetData extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] params)
        {

            SQLiteDatabase objDB=(new Databaza(getApplicationContext())).getReadableDatabase();
            ContentValues objVlera=new ContentValues();




            Cursor c=objDB.rawQuery("Select * from tblRegjistri",null);
            c.moveToFirst();

            String[] dblVleratEmri=null;
            String[] dblVleratMbiemri=null;
            String[] dblVleratNrTelefonit=null;
            String[] dblVleratEmail=null;

            if (c.getCount()>0)
            {
                dblVleratEmri=new String[c.getCount()];
                dblVleratMbiemri=new String[c.getCount()];
                dblVleratNrTelefonit=new String[c.getCount()];
                dblVleratEmail=new String[c.getCount()];

                for (int i=0;i<dblVleratEmri.length;i++)
                {
                    dblVleratEmri[i]=c.getString(0);
                    dblVleratMbiemri[i]=c.getString(1);
                    dblVleratNrTelefonit[i]=c.getString(2);

                    dblVleratEmail[i]=c.getString(3);

                    c.moveToNext();

                }
                c.close();

            }
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i=0;i<dblVleratEmri.length;i++)
            {
                lista.add(new clsKontakti(i,dblVleratEmri[i],dblVleratMbiemri[i],dblVleratNrTelefonit[i],dblVleratEmail[i]));
                Log.i("Te dhenat", dblVleratEmri[i]+dblVleratMbiemri[i]+dblVleratNrTelefonit[i]+dblVleratEmail[i]);
            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            objadapter.notifyDataSetChanged();
            super.onPostExecute(o);
        }
    }


}
