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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author dilacim
 */
@ManagedBean(name="dosenC")
public class DosenController {
    
    private Dosen dosen;
    private Mahasiswa mhs;
    private String BASE_URL = "http://192.168.173.246:9090/Service/";
    private List<Mahasiswa> mhsList;
    private List<Dosen> dosenList;
    private List<Perwalian> waliList;
    private URL obj;

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public List<Dosen> getDosenList() {
        return dosenList;
    }

    public void setDosenList(List<Dosen> dosenList) {
        this.dosenList = dosenList;
    }

    public Mahasiswa getMhs() {
        return mhs;
    }

    public void setMhs(Mahasiswa mhs) {
        this.mhs = mhs;
    }

    public List<Mahasiswa> getMhsList() {
        return mhsList;
    }

    public void setMhsList(List<Mahasiswa> mhsList) {
        this.mhsList = mhsList;
    }
    
    /* method untuk mengambil data pribadi dosen */
    public Dosen getDataDosen() throws Exception {
        mhsList = new ArrayList<>();
	String url = "dosen/apa/";
	obj = new URL(BASE_URL + url);
	HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
//        connection.setDoInput(true);
//        connection.setDoOutput(true);
	connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("id_dosen", "dos02");
	connection.addRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");
//        JSONObject id = new JSONObject();
//        id.put("id_dosen", "dos01");
//        OutputStream os = connection.getOutputStream();
//        os.write(id.toString().getBytes());
//        os.flush();
        
	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();
	while ((inputLine = in.readLine()) != null ) {
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
        
        
        return dosen;
    }
    
    /* method untuk mengambil daftar anak wali testing using post*/
    public List<Mahasiswa> getMhsPerwalian() throws Exception {
        
        mhsList = new ArrayList<>();
	String url = "dosen/x/dos01";
	obj = new URL(BASE_URL + url);
	HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
	connection.addRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");
	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();
	while ((inputLine = in.readLine()) != null ) {
            response.append(inputLine);
	}
		
	in.close();        
	JSONObject result;
	JSONObject jsonObject = new JSONObject(response.toString());
	JSONArray jsonArray = (JSONArray) jsonObject.get("result");
	
	for(int i=0; i<jsonArray.length(); i++){
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
	String url = "dosen/x/dos01";
	obj = new URL(BASE_URL + url);
	HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	connection.setRequestMethod("GET");
	connection.addRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");
	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();
	while ((inputLine = in.readLine()) != null ) {
            response.append(inputLine);
	}
		
	in.close();
	JSONObject result;
	JSONObject jsonObject = new JSONObject(response.toString());
	JSONArray jsonArray = (JSONArray) jsonObject.get("result");
	
	for(int i=0; i<jsonArray.length(); i++){
            JSONObject objek = (JSONObject) jsonArray.get(i);
            result = (JSONObject) objek.get("map");
            Mahasiswa s = new Mahasiswa();
            s.setNrp(result.getString("nrp"));
            s.setNama(result.getString("nama"));
            mhsList.add(s);
        }	        
		
	return mhsList;
    }
    
    /* method untuk mengambil detail mahasiswa wali */
    public Mahasiswa getDetailMhs(String nrp) throws Exception {
        String url = "mhsByNrp/02";
	obj = new URL(url);
	HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	connection.setRequestMethod("GET");
	connection.addRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");
	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();
	
        while ((inputLine = in.readLine()) != null ) {
            response.append(inputLine);
	}
		
	in.close();
	JSONObject result;
	JSONObject jsonObject = new JSONObject(response.toString());
	result = (JSONObject) jsonObject.get("result");
	result = (JSONObject) result.get("map");
	Mahasiswa mhs = new Mahasiswa();
        mhs.setNama(result.getString("nama"));
        mhs.setNrp(result.getString("nrp"));
        
        return mhs;
    }
    
    
    public Perwalian getDetailPerwalian() throws Exception {
        String url = "mhsByNrp/02";
	obj = new URL(url);
	HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	connection.setRequestMethod("GET");
	connection.addRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");
	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();
	
        while ((inputLine = in.readLine()) != null ) {
            response.append(inputLine);
	}
		
	in.close();
	JSONObject result;
	JSONObject jsonObject = new JSONObject(response.toString());
	result = (JSONObject) jsonObject.get("result");
	result = (JSONObject) result.get("map");
	Perwalian p = new Perwalian();        
	
        return p;
    }
    
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
	while ((inputLine = in.readLine()) != null ) {
            response.append(inputLine);
	}
		
	in.close();
	JSONObject result;
	JSONObject jsonObject = new JSONObject(response.toString());
	JSONArray jsonArray = (JSONArray) jsonObject.get("result");
	
	for(int i=0; i<jsonArray.length(); i++){
            JSONObject objek = (JSONObject) jsonArray.get(i);
            result = (JSONObject) objek.get("map");
            String status = result.getString("status");
            if ( status.equals("sudah") ) {
                Perwalian p = new Perwalian();
                p.getMhs().setNama(result.getString("nama"));
                p.getMhs().setNrp(result.getString("nrp"));
                waliList.add(p);
            }
            
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
	while ((inputLine = in.readLine()) != null ) {
            response.append(inputLine);
	}
		
	in.close();
	JSONObject result;
	JSONObject jsonObject = new JSONObject(response.toString());
	JSONArray jsonArray = (JSONArray) jsonObject.get("result");
	
	for(int i=0; i<jsonArray.length(); i++){
            JSONObject objek = (JSONObject) jsonArray.get(i);
            result = (JSONObject) objek.get("map");
            String status = result.getString("status");
            if ( status.equals("belum") ) {
                Perwalian p = new Perwalian();
                p.getMhs().setNama(result.getString("nama"));
                p.getMhs().setNrp(result.getString("nrp"));
                waliList.add(p);
            }
            
        }	        
		
	return waliList;
    }
}
