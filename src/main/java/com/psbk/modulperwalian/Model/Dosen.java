/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psbk.modulperwalian.Model;

import java.util.Date;

/**
 *
 * @author dilacim
 */
public class Dosen {
    
    private String idDosen, nama, email, alamat;
    private String tgl;

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    } 
    

    public String getIdDosen() {
        return idDosen;
    }

    public void setIdDosen(String idDosen) {
        this.idDosen = idDosen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
 
    
    
}
