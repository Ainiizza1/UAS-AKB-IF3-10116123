/* Tanggal Pengerjaan : 13 Agustus 2019
   Nim : 10116123
   Nama : Aini Izza
   Kelas : IF-3
*/
package com.example.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.splashscreen.Model.Teman;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {

    Realm realm;

    EditText editNameDetail, editNimDetail, editKelasDetail, editTelpDetail, editEmailDetail, editMediaDetail;
    Button btnUpdate, btnDelete;
    Teman teman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        realm = Realm.getDefaultInstance();
        editNimDetail = findViewById(R.id.edit_nimDetail);
        editNameDetail = findViewById(R.id.edit_nameDetail);
        editKelasDetail = findViewById(R.id.edit_kelasDetail);
        editTelpDetail = findViewById(R.id.edit_telpDetail);
        editEmailDetail = findViewById(R.id.edit_emailDetail);
        editMediaDetail = findViewById(R.id.edit_mediaDetail);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        Intent getintent = getIntent();
        int position = getintent.getIntExtra("numPosition", 0);

        teman = realm.where(Teman.class).equalTo("teman_id", position).findFirst();
        editNimDetail.setText(teman.getTeman_nim());
        editNameDetail.setText(teman.getTeman_nama());
        editKelasDetail.setText(teman.getTeman_kelas());
        editTelpDetail.setText(teman.getTeman_telp());
        editEmailDetail.setText(teman.getTeman_email());
        editMediaDetail.setText(teman.getTeman_media());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
                onBackPressed();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                onBackPressed();
            }
        });
    }

    private void deleteData() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                teman.deleteFromRealm();
            }

        });
    }

    private void updateDate() {
        realm.beginTransaction();
        teman.setTeman_nim(editNimDetail.getText().toString());
        teman.setTeman_nama(editNameDetail.getText().toString());
        teman.setTeman_kelas(editKelasDetail.getText().toString());
        teman.setTeman_telp(editTelpDetail.getText().toString());
        teman.setTeman_email(editEmailDetail.getText().toString());
        teman.setTeman_media(editMediaDetail.getText().toString());
        realm.commitTransaction();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

