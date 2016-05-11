/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psbk.modulperwalian.Controller;

import com.psbk.modulperwalian.Model.Dosen;
import com.psbk.modulperwalian.Model.Mahasiswa;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
    private String BASE_URL = "http://192.168.173.246:9090/Service/mahasiswa/";
    private List<Mahasiswa> mhsList;
    private List<Dosen> dosenList;
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
    
   
    public List<Mahasiswa> getListMahasiswa() throws Exception {
        mhsList = new ArrayList<>();
	String url = "http://192.168.173.246:9090/Service/mahasiswa/x/dos01";
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
}
