/* Tanggal Pengerjaan : 13 Agustus 2019
   Nim : 10116123
   Nama : Aini Izza
   Kelas : IF-3
*/
package com.example.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.util.AbstractSet;
//import java.util.prefs.Preferences;
import com.example.splashscreen.Preferences;

public class MainActivity2 extends AppCompatActivity {

    private EditText mViewUser, mViewPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                /* Menginisialisasi variable dengan Form User, Form Password, dan Form Repassword
//                 dari Layout RegisterActivity */
//
                mViewUser = findViewById(R.id.etName);
                mViewPassword = findViewById(R.id.etPassword);
//
                String user = etName.getText().toString();
                String password = etPassword.getText().toString();
//                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
//
//                String userDetails = preferences.getString(user + password + "data", "No information on that user.");
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("display", userDetails);
//                editor.commit();
//

                razia();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerScreen = new Intent(MainActivity2.this, Register.class);
                startActivity(registerScreen);
            }
        });
    }

    private void razia() {
        /* Mereset semua Error dan fokus menjadi default */
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari Form User, Password, Repassword dengan variable baru bertipe String*/
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekUser() maka, Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(user)) {
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        } else if (!cekUser(user)) {
            mViewUser.setError("This Username is not match");
            fokus = mViewUser;
            cancel = true;
        }

        /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword maka,
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)) {
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            mViewPassword.setError("This password is incorrect");
            fokus = mViewPassword;
            cancel = true;

        }

        if (cancel)fokus.requestFocus();
        else masuk();
    }

    private void masuk() {
        Preferences.setLoggedInUser(getBaseContext(), Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        Intent displayScreen = new Intent(MainActivity2.this, MainActivity3.class);
//                Intent displayScreen = new Intent(MainActivity2.this, DisplayScreen.class);
        startActivity(displayScreen);


    }

    /**
     * True jika parameter password sama dengan parameter repassword
     */
    private boolean cekPassword(String password) {
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    /**
     * True jika parameter user sama dengan data user yang terdaftar dari Preferences
     */
    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}