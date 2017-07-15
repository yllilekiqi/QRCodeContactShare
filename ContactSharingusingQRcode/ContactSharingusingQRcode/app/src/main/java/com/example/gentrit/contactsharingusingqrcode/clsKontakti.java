package com.example.gentrit.contactsharingusingqrcode;

import android.provider.ContactsContract;

/**
 * Created by Gentrit on 07/07/2017.
 */

public class clsKontakti {


    int ID;
    String Emri, Mbiemri,NrTelefonit,Email;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmri() {
        return Emri;
    }

    public void setEmri(String emri) {
        Emri = emri;
    }

    public String getMbiemri() {
        return Mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        Mbiemri = mbiemri;
    }

    public String getNrTelefonit() {
        return Email;
    }

    public void setNrTelefonit(String email) {
        Email = email;
    }

    public String getEmail() {
        return NrTelefonit;
    }

    public void setEmail(String nrTelefonit) {
        Mbiemri = nrTelefonit;
    }

    public clsKontakti(int ID, String emri, String mbiemri,String nrTelefonit, String email) {
        this.ID = ID;
        Emri = emri;
        Mbiemri = mbiemri;
        NrTelefonit = nrTelefonit;
        Email= email;
    }

}
