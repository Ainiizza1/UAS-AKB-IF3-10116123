/* Tanggal Pengerjaan : 13 Agustus 2019
   Nim : 10116123
   Nama : Aini Izza
   Kelas : IF-3
*/
package com.example.splashscreen.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Teman extends RealmObject {
    @PrimaryKey
    private int teman_id;
    private String teman_nama, teman_nim, teman_kelas, teman_telp, teman_email, teman_media;

    public int getTeman_id() {
        return teman_id;
    }

    public void setTeman_id(int teman_id) {
        this.teman_id = teman_id;
    }

    public String getTeman_nim() {
        return teman_nim;
    }

    public void setTeman_nim(String teman_nim) {
        this.teman_nim = teman_nim;
    }

    public String getTeman_nama() {
        return teman_nama;
    }

    public void setTeman_nama(String teman_nama) {
        this.teman_nama = teman_nama;
    }

    public String getTeman_kelas() {
        return teman_kelas;
    }

    public void setTeman_kelas(String teman_kelas) {
        this.teman_kelas = teman_kelas;
    }

    public String getTeman_telp() {
        return teman_telp;
    }

    public void setTeman_telp(String teman_telp) {
        this.teman_telp = teman_telp;
    }

    public String getTeman_email() {
        return teman_email;
    }

    public void setTeman_email(String teman_email) {
        this.teman_email = teman_email;
    }

    public String getTeman_media() {
        return teman_media;
    }

    public void setTeman_media(String teman_media) {
        this.teman_media = teman_media;
    }
}

