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
public class Nilai {
    private char grade;
    private String idNilai;
    private MataKuliah mk;
    private Mahasiswa mhs;
    private float nilaiKuis, nilaiTugas, nilaiUas, nilaiUts;

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public String getIdNilai() {
        return idNilai;
    }

    public void setIdNilai(String idNilai) {
        this.idNilai = idNilai;
    }

    public MataKuliah getMk() {
        return mk;
    }

    public void setMk(MataKuliah mk) {
        this.mk = mk;
    }

    public Mahasiswa getMhs() {
        return mhs;
    }

    public void setMhs(Mahasiswa mhs) {
        this.mhs = mhs;
    }

    public float getNilaiKuis() {
        return nilaiKuis;
    }

    public void setNilaiKuis(float nilaiKuis) {
        this.nilaiKuis = nilaiKuis;
    }

    public float getNilaiTugas() {
        return nilaiTugas;
    }

    public void setNilaiTugas(float nilaiTugas) {
        this.nilaiTugas = nilaiTugas;
    }

    public float getNilaiUas() {
        return nilaiUas;
    }

    public void setNilaiUas(float nilaiUas) {
        this.nilaiUas = nilaiUas;
    }

    public float getNilaiUts() {
        return nilaiUts;
    }

    public void setNilaiUts(float nilaiUts) {
        this.nilaiUts = nilaiUts;
    }
    
    
}
