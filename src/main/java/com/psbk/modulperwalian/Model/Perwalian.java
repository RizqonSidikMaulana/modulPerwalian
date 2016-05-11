/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psbk.modulperwalian.Model;

/**
 *
 * @author dilacim
 */
public class Perwalian {
    
    private String idBeritaAcara, semester, status;
    private Mahasiswa mhs;
    private Dosen dosen;

    public String getIdBeritaAcara() {
        return idBeritaAcara;
    }

    public void setIdBeritaAcara(String idBeritaAcara) {
        this.idBeritaAcara = idBeritaAcara;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Mahasiswa getMhs() {
        return mhs;
    }

    public void setMhs(Mahasiswa mhs) {
        this.mhs = mhs;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }
    
    
    
}
