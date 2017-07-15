package com.example.gentrit.contactsharingusingqrcode;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.zxing.integration.android.IntentResult;

public class Regjistro_kontaktin extends AppCompatActivity {
    EditText emri,mbiemri,email,nrtelefonit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regjistro_kontaktin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLiteDatabase objDb=(new Databaza(getApplicationContext())).getReadableDatabase();

        emri = (EditText)findViewById(R.id.etemri);
        mbiemri = (EditText)findViewById(R.id.etmbiemri);
        email = (EditText)findViewById(R.id.etEmail);
        nrtelefonit = (EditText)findViewById(R.id.etnrtelefonit);

        Intent intent = getIntent();
        String teDhenatPasSkenimit = intent.getStringExtra("Te dhenat pas skenimit");



        String[] array = teDhenatPasSkenimit.split(" ");
        emri.setText(array[0]);
        mbiemri.setText(array[1]);
        nrtelefonit.setText(array[2]);
        email.setText(array[3]);

        String inserto = "INSERT INTO " + Parametrat.TabelaRegjistri + " ("
                + Parametrat.Emri + ", " + Parametrat.Mbiemri + ", "
                + Parametrat.NrTelefonit + ", " + Parametrat.Email +") Values ('"+emri.getText().toString()+"', '"+mbiemri.getText().toString()+"', '"+nrtelefonit.getText().toString()+"', '"+email.getText().toString()+"')";
        objDb.execSQL(inserto);



        FloatingActionButton RegjistroButoni = (FloatingActionButton) findViewById(R.id.fab);
        RegjistroButoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);


                intent
                        .putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText())
                        .putExtra(ContactsContract.Intents.Insert.PHONE,nrtelefonit.getText())
                        .putExtra(ContactsContract.Intents.Insert.NAME,emri.getText()+ " " +mbiemri.getText());

                startActivity(intent);
                ;

            }
        });
    }

}
