/* Tanggal Pengerjaan : 14 Agustus 2019
   Nim : 10116123
   Nama : Aini Izza
   Kelas : IF-3
*/
package com.example.splashscreen.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.splashscreen.DetailActivity;
import com.example.splashscreen.Model.Teman;
import com.example.splashscreen.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Teman> teman;

    public CustomAdapter(Context c, ArrayList<Teman> teman) {
        this.c = c;
        this.teman = teman;
    }

    @Override
    public int getCount() {
        return teman.size();
    }

    @Override
    public Object getItem(int position) {
        return teman.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item, parent, false);
        TextView txtName, txtNim, txtKelas, txtTelp, txtEmail, txtMedia;
        txtNim = view.findViewById(R.id.txt_nim);
        txtName = view.findViewById(R.id.txt_name);
        txtKelas = view.findViewById(R.id.txt_kelas);
        txtTelp = view.findViewById(R.id.txt_telp);
        txtEmail = view.findViewById(R.id.txt_email);
        txtMedia = view.findViewById(R.id.txt_media);

        Teman t = (Teman) this.getItem(position);
        final int numPosition = t.getTeman_id();
        txtNim.setText(t.getTeman_nim());
        txtName.setText(t.getTeman_nama());
        txtKelas.setText(t.getTeman_kelas());
        txtTelp.setText(t.getTeman_telp());
        txtEmail.setText(t.getTeman_email());
        txtMedia.setText(t.getTeman_media());

        //On Click
        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("numPosition", numPosition);
                v.getContext().startActivity(intent);
            }
        });

        return view;
    }
}

