/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psbk.modulperwalian.Controller;

import com.psbk.modulperwalian.Model.Dosen;
import com.psbk.modulperwalian.Model.Mahasiswa;
import com.psbk.modulperwalian.Model.MataKuliah;
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
public class MatakuliahController {
    private MataKuliah mk;
    private String BASE_URL = "http://192.168.173.246:9090/Service/mahasiswa/";
    private List<MataKuliah> mkList;

    public void setMk(MataKuliah mk) {
        this.mk = mk;
    }

    public void setMkList(List<MataKuliah> mkList) {
        this.mkList = mkList;
    }
    
   
    public List<MataKuliah> getListStudents() throws Exception {
		mkList = new ArrayList<>();
		String url = "";
		URL obj = new URL(BASE_URL+url);
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
		MataKuliah mk = new MataKuliah();
		mk.setKode(result.getString("kode"));
//                
		return mkList;
	}
}
