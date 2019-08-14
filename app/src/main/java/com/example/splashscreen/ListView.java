/* Tanggal Pengerjaan : 13 Agustus 2019
   Nim : 10116123
   Nama : Aini Izza
   Kelas : IF-3
*/
package com.example.splashscreen;

import android.app.Application;
        import android.widget.Adapter;

        import com.example.splashscreen.CustomAdapter.CustomAdapter;

        import io.realm.Realm;
        import io.realm.RealmConfiguration;

public class ListView extends Application {
    private Adapter adapter;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("RealmData.com.example.splashscreen.realm.realm").build();
        Realm.setDefaultConfiguration(configuration);
    }
    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    public Adapter getAdapter() {
        return adapter;
    }
}
