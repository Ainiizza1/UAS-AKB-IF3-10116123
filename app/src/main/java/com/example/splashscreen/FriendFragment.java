/* Tanggal Pengerjaan : 14 Agustus 2019
   Nim : 10116123
   Nama : Aini Izza
   Kelas : IF-3
*/

package com.example.splashscreen;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FriendFragment extends Fragment implements View.OnClickListener {

    View view;
    Button buttontlp, buttonemail, buttonig;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friend, null);

        buttontlp = view.findViewById(R.id.buttontlp);
        buttonemail = view.findViewById(R.id.buttonemail);
        buttonig = view.findViewById(R.id.buttonig);

        buttontlp.setOnClickListener(this);
        buttonemail.setOnClickListener(this);
        buttonig.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttontlp:
                String number = "082210820104";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
                break;
            case R.id.buttonemail:
                String email = "ainiizza123@gmail.com";
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_EMAIL, email);
                intent1.setType("message/rfc822");
                startActivity(intent1);
                break;
            case R.id.buttonig:
                Uri uri = Uri.parse("http://instagram.com/aini_izza1");
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                intent2.setPackage("com.instagram.android");

                try {
                    startActivity(intent2);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/aini_izza1")));
                }
                break;
        }
    }


}