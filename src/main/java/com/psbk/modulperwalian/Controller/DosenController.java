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
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author dilacim
 */
public class DosenController {
    
    private Dosen dosen;
    private Mahasiswa mhs;
    private String BASE_URL = "http://192.168.173.246:9090/Service/mahasiswa/";
    private List<Mahasiswa> mhsList;
    private List<Dosen> dosenList;

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
    
   
    public List<Mahasiswa> getListStudents() throws Exception {
		mhsList = new ArrayList<>();
		String url = "mhsByNrp/02";
		URL obj = new URL(url);
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
		Mahasiswa s = new Mahasiswa();
		s.setNrp(result.get("nrp").toString());
		s.setNama(result.getString("nama"));
		s.setAlamat(result.getString("alamat"));
		mhsList.add(s);
                
		return mhsList;
	}
}
