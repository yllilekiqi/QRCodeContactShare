package com.example.gentrit.contactsharingusingqrcode;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AbsRuntimePermissions {
    EditText emri,mbiemri,nrtelefonit,email;
    private static final int REQUEST_PERMISSION =10;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.Gjenero_Kodin:
                    emri=(EditText)findViewById(R.id.etemri);
                    mbiemri = (EditText)findViewById(R.id.etmbiemri);
                    nrtelefonit=(EditText)findViewById(R.id.etnrtelefonit);
                    email=(EditText)findViewById(R.id.etEmail);

                    if((emri.getText().toString().equals("".trim()))||(nrtelefonit.getText().toString().equals("".trim())))
                    {

                        AlertDialog.Builder adBuilder1 = new AlertDialog.Builder(MainActivity.this);
                        adBuilder1.setTitle(R.string.titulli_alertit);
                        adBuilder1.setMessage("Qe te vazhdoni me tutje duhet te plotesoni se paku emrin dhe numrin e telefonit.");
                        adBuilder1.setNegativeButton("OK", null);
                        adBuilder1.setCancelable(false);
                        adBuilder1.show();
                    }
                    else
                    {

                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(MainActivity.this);
                    adBuilder.setTitle(R.string.titulli_alertit);
                    adBuilder.setMessage("A jane te dhenat e sakta?\nEmri: " +
                            emri.getText().toString() + "\nMbiemri: " +
                            mbiemri.getText().toString() +"\nNumri i Telefonit: " +
                            nrtelefonit.getText().toString() +"\nEmail: " +
                            email.getText().toString()

                    );

                    adBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent gjenerokodin =
                                    new Intent(MainActivity.this, QrCode_Activity.class);
                            gjenerokodin.putExtra("Emri",emri.getText().toString());
                            gjenerokodin.putExtra("Mbiemri",mbiemri.getText().toString());
                            gjenerokodin.putExtra("NrTelefonit",nrtelefonit.getText().toString());
                            gjenerokodin.putExtra("Email",email.getText().toString());
                            startActivity(gjenerokodin);

                        }
                    });
                    adBuilder.setNegativeButton("Anulo", null);
                    adBuilder.setCancelable(false);
                    adBuilder.show();}
                    return true;

                case R.id.Skano_Kodin:


                    IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    integrator.setPrompt("Skano QR kodin");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(true);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.initiateScan();
                    return true;

                case R.id.Shfaq_Kontaktet_Regjistruara:

                    Intent intenti = new Intent(MainActivity.this,Kontaktet_Regjistruara.class);
                    startActivity(intenti);

            }
            return false;
        }

    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result!=null)
        {
            if(result.getContents()==null)
            {
                Toast.makeText(this,"Ju ndaluat skenimin",Toast.LENGTH_LONG).show();
            }
            else
            {

                Intent intenti = new Intent(MainActivity.this,Regjistro_kontaktin.class);
                intenti.putExtra("Te dhenat pas skenimit",result.getContents());
                startActivity(intenti);

            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }



    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestAppPermissions(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_CONTACTS},
                R.string.msg,REQUEST_PERMISSION);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    public void onPermissionsGranted(int requestCode) {
        //Toast.makeText(getApplicationContext(),"Permissions u lejuan",Toast.LENGTH_SHORT).show();
    }

}
