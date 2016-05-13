/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psbk.modulperwalian.Controller;

import com.psbk.modulperwalian.Model.Dosen;
import com.psbk.modulperwalian.Model.Mahasiswa;
import com.psbk.modulperwalian.Model.Perwalian;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Rifki
 */
public class DosenMain {
    
    private Dosen dosen;
    private Mahasiswa mhs;
    private Perwalian p;
    private String BASE_URL = "http://192.168.173.207:9090/Service/";
    private List<Mahasiswa> mhsList;
    private List<Dosen> dosenList;
    private List<Perwalian> waliList;
    private URL obj;
    
    public List<Perwalian> getMhsHasPerwalian() throws Exception {
        waliList = new ArrayList<>();
        String url = "dosen/getMhsWali/dos01";
        obj = new URL(BASE_URL + url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        JSONObject result;
        JSONObject jsonObject = new JSONObject(response.toString());
        JSONArray jsonArray = (JSONArray) jsonObject.get("result");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objek = (JSONObject) jsonArray.get(i);
            result = (JSONObject) objek.get("map");
            String status = result.getString("status");
            if (status.equals("sudah")) {
                Perwalian p = new Perwalian();
                p.getMhs().setNama(result.getString("nama"));
                p.getMhs().setNrp(result.getString("nrp"));
                waliList.add(p);
            }

        }
        
        if (waliList.isEmpty()) {
            System.out.println("Kosong");
        }

        return waliList;
    }

    /* method untuk menampilkan daftar mahasiswa yang belum perwalian */
    public List<Perwalian> getMhsNotPerwalian() throws Exception {
        waliList = new ArrayList<>();
        String url = "dosen/getMhsWali/dos01";
        obj = new URL(BASE_URL + url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        JSONObject result;
        JSONObject jsonObject = new JSONObject(response.toString());
        JSONArray jsonArray = (JSONArray) jsonObject.get("result");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objek = (JSONObject) jsonArray.get(i);
            result = (JSONObject) objek.get("map");
            String status = result.getString("status");
            if (status.equals("belum")) {
                Perwalian p = new Perwalian();
                p.getMhs().setNama(result.getString("nama"));
                p.getMhs().setNrp(result.getString("nrp"));
                waliList.add(p);
            }

        }

        return waliList;
    }
    public static void main(String[] args) throws Exception {
        DosenMain dm = new DosenMain();
        if (dm.getMhsHasPerwalian().isEmpty()) {
            System.out.println("test");
        }
    }
}