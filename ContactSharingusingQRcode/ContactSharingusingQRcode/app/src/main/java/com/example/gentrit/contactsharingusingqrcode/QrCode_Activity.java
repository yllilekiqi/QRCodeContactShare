package com.example.gentrit.contactsharingusingqrcode;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class QrCode_Activity extends AbsRuntimePermissions {
    Button RuajFoton;
    ImageView ImgQrKode;
    private static final int REQUEST_PERMISSION =10;
    TextView TekstiNgaIntenti;
    int width = 1000,height=1000;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_);


        RuajFoton = (Button)findViewById(R.id.RuajFoton);
        ImgQrKode  =(ImageView)findViewById(R.id.imgQr);
        TekstiNgaIntenti=(TextView)findViewById(R.id.tvTekstiNgaIntenti);
        Intent intent = getIntent();
        final String emri = intent.getStringExtra("Emri");
        final String mbiemri = intent.getStringExtra("Mbiemri");
        String nrtelefonit = intent.getStringExtra("NrTelefonit");
        String email = intent.getStringExtra("Email");


        String teDhenat = "Emri: "+emri+ "\nMbiemri: "+mbiemri+"\nNumri i Telefonit: "+nrtelefonit+"\nEmail: "+email;
        String teDhenatPerSkaner=emri+ " "+mbiemri+" "+nrtelefonit+" "+email;



        TekstiNgaIntenti.setText(teDhenat);
        try {
            Bitmap bitmap = encodeAsBitmap(teDhenatPerSkaner);
            ImgQrKode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        RuajFoton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                requestAppPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},R.string.msg,REQUEST_PERMISSION);
                BitmapDrawable drawable = (BitmapDrawable) ImgQrKode.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                RuajFoton(bitmap,emri,mbiemri);
            }
        });

    }


    private void RuajFoton(Bitmap bitmap, String emri_foto,String mbiemri_foto) {

        String root = Environment.getExternalStorageDirectory().toString();
        File Direktoriumi = new File(root);
        Direktoriumi.mkdirs();
        String emri_fajllit = "Image- " + emri_foto+mbiemri_foto+ ".jpg";
        File file = new File(Direktoriumi, emri_fajllit);
        if (file.exists())
        {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(getApplicationContext(),"Foto u ruajt me sukses!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Foto nuk u ruajt! Ju lutem lejoni aplikacionin te kete casje ne ruajten e fotove.", Toast.LENGTH_LONG).show();
        }
    }



    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, width, height, null);
        } catch (IllegalArgumentException iae) {

            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, w, h);
        return bitmap;
    }
    @Override
    public void onPermissionsGranted(int requestCode) {
       // Toast.makeText(getApplicationContext(),"Permissions u lejuan",Toast.LENGTH_LONG).show();
    }
}
