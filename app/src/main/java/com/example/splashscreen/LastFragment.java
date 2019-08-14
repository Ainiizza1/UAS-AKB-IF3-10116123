/* Tanggal Pengerjaan : 14 Agustus 2019
   Nim : 10116123
   Nama : Aini Izza
   Kelas : IF-3
*/
package com.example.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splashscreen.CustomAdapter.CustomAdapter;
import com.example.splashscreen.Model.Teman;
import com.example.splashscreen.MyHelper.MyHelper;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastFragment extends Fragment {
    Realm realm;
    EditText editName,editNim,editKelas,editTelp,editEmail,editMedia;
    Button btnSave;
    ListView listView;
    MyHelper helper;
    RealmChangeListener realmChangeListener;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_last, container, false);
        Realm.init(getContext());
        realm = Realm.getDefaultInstance();

        editName = view.findViewById(R.id.edit_name);
        editNim = view.findViewById(R.id.edit_nim);
        editKelas = view.findViewById(R.id.edit_kelas);
        editTelp = view.findViewById(R.id.edit_telp);
        editEmail = view.findViewById(R.id.edit_email);
        editMedia = view.findViewById(R.id.edit_media);
        btnSave = view.findViewById(R.id.btn_save);


        listView = view.findViewById(R.id.listview);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }
        });

        helper = new MyHelper(realm);
        helper.selectFromDB();

        CustomAdapter adapter = new CustomAdapter(getContext(), helper.justRefresh());
        listView.setAdapter(adapter);

        Refresh();
        return view;
    }

    private void saveData() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Number maxId = bgRealm.where(Teman.class).max("teman_id");

                int newKey = (maxId == null) ? 1 : maxId.intValue() + 1;
                Teman people = bgRealm.createObject(Teman.class, newKey);
                people.setTeman_nim(editNim.getText().toString());
                people.setTeman_nama(editName.getText().toString());
                people.setTeman_kelas(editKelas.getText().toString());
                people.setTeman_telp(editTelp.getText().toString());
                people.setTeman_email(editEmail.getText().toString());
                people.setTeman_media(editMedia.getText().toString());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Toast.makeText(getContext(), "Tidak Success", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Refresh() {
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                CustomAdapter adapter = new CustomAdapter(getContext(), helper.justRefresh());
                listView.setAdapter(adapter);
            }
        };
        realm.addChangeListener(realmChangeListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}

