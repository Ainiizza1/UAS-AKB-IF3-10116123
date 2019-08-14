/* Tanggal Pengerjaan : 14 Agustus 2019
   Nim : 10116123
   Nama : Aini Izza
   Kelas : IF-3
*/
package com.example.splashscreen.MyHelper;

import com.example.splashscreen.Model.Teman;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyHelper {
    Realm realm;
    RealmResults<Teman> teman;

    public MyHelper(Realm realm) {
        this.realm = realm;
    }

    public void selectFromDB() {
        teman = realm.where(Teman.class).findAll();
    }

    public ArrayList<Teman> justRefresh() {
        ArrayList<Teman> listItem = new ArrayList<>();
        for (Teman t : teman) {
            listItem.add(t);
        }
        return listItem;
    }
}

