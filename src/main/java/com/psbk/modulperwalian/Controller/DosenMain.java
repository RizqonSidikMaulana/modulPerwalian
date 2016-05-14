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
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Rifki
 */
public class DosenMain {
    
    private Dosen dosen;
    private Mahasiswa mhs;
    
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
//        
//        if (jsonArray.isNull(0)) {
//            waliList = null;
//                    
//        }
//        else {
            System.out.println(jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objek = (JSONObject) jsonArray.get(i);
            result = (JSONObject) objek.get("map");
            String status = result.getString("status");
//                
            if (status.matches("sudah")) {
                Perwalian ps = new Perwalian();
                ps.getMhs().setNama(result.getString("nama"));
                System.out.println(ps.getMhs().getNama());
                ps.getMhs().setNrp(result.getString("nrp"));
                waliList.add(ps);
            }

        }  
//        }

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
    
    public Dosen getPostDosen() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
        String responseString = null;
        Dosen dosen = new Dosen();
        try {
            String url = BASE_URL + "dosen/apa/";
            HttpPost request = new HttpPost(url);
            StringEntity params =new StringEntity("{request:{\"id_dosen\":\"dos01\"} }");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            responseString = EntityUtils.toString(entity, "UTF-8");
            JSONObject result;
            JSONObject jsonObject = new JSONObject(responseString);
            result = (JSONObject) jsonObject.get("result");
            result = (JSONObject) result.get("map");
            
            dosen.setIdDosen(result.getString("id_dosen"));
            dosen.setNama(result.getString("nama_dosen"));
            dosen.setTgl(result.getString("tglLahir"));
            dosen.setTelp(result.getString("noTelp"));
        }catch (Exception ex) {
            // handle exception here
        }
        
        return dosen;
    }
    
    
    public static void main(String[] args) throws Exception {
        DosenMain dm = new DosenMain();
        if (dm.getMhsHasPerwalian().isEmpty()) {
            System.out.println("test");
        }
        
        if (dm.getMhsNotPerwalian().isEmpty() ) {
            System.out.println("testjj");
        }
        
        System.out.println(dm.getPostDosen().getNama());
    }
}