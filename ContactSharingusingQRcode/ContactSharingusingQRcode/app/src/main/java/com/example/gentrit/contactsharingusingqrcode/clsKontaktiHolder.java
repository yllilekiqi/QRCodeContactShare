package com.example.gentrit.contactsharingusingqrcode;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Gentrit on 07/07/2017.
 */

public class clsKontaktiHolder {
    View base;
    TextView tvEmri;
    TextView tvMbiemri;
    TextView tvNrTelefonit;
    TextView tvEmail;

    public clsKontaktiHolder(View base){this.base=base;}

    public TextView getTvEmri() {
        if(tvEmri==null)
        {
            tvEmri = (TextView)base.findViewById(R.id.tvEmriRow);
        }
        return tvEmri;
    }

    public void setTvEmri(TextView tvEmri) {
        this.tvEmri = tvEmri;
    }

    public TextView getTvMbiemri() {
        if(tvMbiemri==null)
        {
            tvMbiemri = (TextView)base.findViewById(R.id.tvMbiemriRow);
        }
        return tvMbiemri;
    }

    public void settvMbiemri(TextView tvMbiemri) {
        this.tvMbiemri = tvMbiemri;
    }




    public TextView getTvNrTelefonit() {
        if(tvNrTelefonit==null)
        {
            tvNrTelefonit = (TextView)base.findViewById(R.id.tvNrTelefonitRow);
        }
        return tvNrTelefonit;
    }

    public void setTvNrTelefonit(TextView TvNrTelefonit) {
        this.tvNrTelefonit = TvNrTelefonit;
    }


    public TextView getTvEmail() {
        if(tvEmail==null)
        {
            tvEmail = (TextView)base.findViewById(R.id.tvEmailRow);
        }
        return tvEmail;
    }

    public void setTvEmail(TextView TvEmail) {
        this.tvEmail = TvEmail;
    }

}
