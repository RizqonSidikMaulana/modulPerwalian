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
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author dilacim
 */
@ManagedBean(name = "dosenC")
public class DosenController extends BaseUrl {

    private Dosen dosen;
    private Mahasiswa mhs;
    private Perwalian p;
    private String BASE_URL = getIp();
    private List<Mahasiswa> mhsList;
    private List<Dosen> dosenList;
    private List<Perwalian> waliList;
    private URL obj;
    /* method POST ambil data dosen */
    public String getDataDosenTest() throws Exception {
        String nip = null;
        URL url = new URL(BASE_URL + "dosen/apa/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.addRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");

        String input = "{\"id_dosen\"dos02\"}";

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {

            nip = output;
        }

        
        return output;
    }
    
//    public Dosen getPostDosen() throws Exception {
//        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
//        String responseString = null;
//        Dosen dosen = new Dosen();
//        try {
//            String url = BASE_URL + "dosen/apa/";
//            HttpPost request = new HttpPost(url);
//            StringEntity params =new StringEntity("{request:{\"id_dosen\":\"dos01\"} }");
//            request.addHeader("content-type", "application/json");
//            request.setEntity(params);
//            
//            HttpResponse response = httpClient.execute(request);
//            HttpEntity entity = response.getEntity();
//            responseString = EntityUtils.toString(entity, "UTF-8");
//            JSONObject result;
//            JSONObject jsonObject = new JSONObject(responseString);
//            result = (JSONObject) jsonObject.get("result");
//            result = (JSONObject) result.get("map");
//            
//            dosen.setIdDosen(result.getString("id_dosen"));
//            dosen.setNama(result.getString("nama_dosen"));
//            dosen.setTgl(result.getString("tglLahir"));
//            dosen.setTelp(result.getString("noTelp"));
//        }catch (Exception ex) {
//            // handle exception here
//        }
//        
//        return dosen;
//    }

    /* method untuk mengambil data pribadi dosen */
    public Dosen getDataDosen() throws Exception {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        String nrp = request.getParameter("user");
//        System.out.println(nrp);
        mhsList = new ArrayList<>();
        String url = "dosen/getDosen/dos02";
        obj = new URL(BASE_URL + url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
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
        result = (JSONObject) jsonObject.get("result");
        result = (JSONObject) result.get("map");
        Dosen dosen = new Dosen();
        dosen.setIdDosen(result.getString("id_dosen"));
        dosen.setNama(result.getString("nama_dosen"));
        dosen.setTgl(result.getString("tglLahir"));
        dosen.setTelp(result.getString("noTelp"));

        return dosen;
    }

    /* method untuk mengambil daftar anak didik per wali*/
    public List<Mahasiswa> getMhsPerwalian() throws Exception {
        
        mhsList = new ArrayList<>();
        String url = "dosen/x/dos01";
        obj = new URL(BASE_URL + url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
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
            Mahasiswa s = new Mahasiswa();
            s.setNrp(result.getString("nrp"));
            s.setNama(result.getString("nama"));
            mhsList.add(s);
        }

        return mhsList;
    }

    /* method untuk mengambil daftar anak wali testing using post*/
    public List<Mahasiswa> getListMahasiswa() throws Exception {
        mhsList = new ArrayList<>();
        
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
            Mahasiswa s = new Mahasiswa();
            s.setNrp(result.getString("nrp"));
            s.setNama(result.getString("nama"));
            mhsList.add(s);
        }

        return mhsList;
    }

    /* method untuk mengambil detail krs mahasiswa wali */
    public List<Perwalian> getDetailKrs() throws Exception {
        waliList = new ArrayList<>();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        String nrp = request.getParameter("nrp");
        String url = "mahasiswa/detailKontrakMk/01";
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
            Perwalian p = new Perwalian();
            p.getMhs().setNama(result.getString("nama"));
            p.getMhs().setNrp(result.getString("nrp"));
            p.getMk().setKode(result.getString("kode_mk"));
            
            waliList.add(p);

        }
        return waliList;
    }
//    public Perwalian getDetailPerwalian() throws Exception {
//        String url = "mhsByNrp/02";
//	obj = new URL(url);
//	HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
//	connection.setRequestMethod("GET");
//	connection.addRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");
//	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//	String inputLine;
//	StringBuffer response = new StringBuffer();
//	
//        while ((inputLine = in.readLine()) != null ) {
//            response.append(inputLine);
//	}
//		
//	in.close();
//	JSONObject result;
//	JSONObject jsonObject = new JSONObject(response.toString());
//	result = (JSONObject) jsonObject.get("result");
//	result = (JSONObject) result.get("map");
//	Perwalian p = new Perwalian();        
//	
//        return p;
//    }
    
    /* method untuk menampilkan daftar mahasiswa yang sudah perwalian */
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
            if (status.equals("terima")) {
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
            if (status.matches("Tunda")) {
                Perwalian p = new Perwalian();
                p.getMhs().setNama(result.getString("nama"));
                p.getMhs().setNrp(result.getString("nrp"));
                waliList.add(p);
            }

        }

        return waliList;
    }
}
