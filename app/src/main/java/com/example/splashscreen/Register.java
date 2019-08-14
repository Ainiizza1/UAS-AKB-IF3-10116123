/* Tanggal Pengerjaan : 13 Agustus 2019
   Nim : 10116123
   Nama : Aini Izza
   Kelas : IF-3
*/
package com.example.splashscreen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Mitch on 2016-04-08.
 */
public class Register extends AppCompatActivity {

    private EditText mViewName, mViewEmail, mViewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final EditText userName = (EditText) findViewById(R.id.etNewName);
        final EditText password = (EditText) findViewById(R.id.etNewPassword);
        final EditText email = (EditText) findViewById(R.id.etNewEmail);
        Button btnRegister = (Button) findViewById(R.id.btnNewRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

                mViewName = findViewById(R.id.etNewName);
                mViewEmail = findViewById(R.id.etNewEmail);
                mViewPassword = findViewById(R.id.etNewPassword);

                String newUser = userName.getText().toString();
                String newPassword = password.getText().toString();
                String newEmail = email.getText().toString();

//                SharedPreferences.Editor editor = preferences.edit();

                //stores 3 new instances of sharedprefs. Both the user and password's keys are the same as the input.
                //Must be done this way because sharedprefs is stupid and inefficient. You cannot store Arrays easily
                //so I use strings instead.
//                editor.putString(newUser, newUser);
//                editor.commit();
//                editor.putString(newPassword, newPassword);
//                editor.commit();
//                editor.putString(newUser + newPassword + "data", newUser + "\n" + newEmail);
//                editor.commit();
                razia();
            }
        });

    }

    /**
     * Men-check inputan User dan Password dan Memberikan akses ke MainActivity
     */
    private void razia() {
        /* Mereset semua Error dan fokus menjadi default */
        mViewName.setError(null);
        mViewEmail.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari Form User, Password, Repassword dengan variable baru bertipe String*/
        String email = mViewEmail.getText().toString();
        String name = mViewName.getText().toString();
        String password = mViewPassword.getText().toString();

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekUser() maka, Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(name)) {
            mViewName.setError("This field is required");
            fokus = mViewName;
            cancel = true;
        } else if (cekName(name)) {
            mViewName.setError("This Username is already exist");
            fokus = mViewName;
            cancel = true;
        }

        /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword maka,
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)) {
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mViewEmail.setError("This field is required");
            fokus = mViewEmail;
            cancel = true;
        }
        /** Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
         *  Kembali ke LoginActivity dan Set User dan Password untuk data yang terdaftar */
        if (cancel) {
            fokus.requestFocus();
        } else {
            Preferences.setRegisteredUser(getBaseContext(), name);
            Preferences.setRegisteredEmail(getBaseContext(), email);
            Preferences.setRegisteredPass(getBaseContext(), password);
            finish();
        }
    }

    /**
     * True jika parameter password sama dengan parameter repassword
     */

    /**
     * True jika parameter user sama dengan data user yang terdaftar dari Preferences
     */
    private boolean cekName(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

}
