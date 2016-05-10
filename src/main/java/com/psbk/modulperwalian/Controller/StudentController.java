/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psbk.modulperwalian.Controller;

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

@ManagedBean(name="mahasiswa")
public class StudentController {
    private Mahasiswa student;
	private List<Mahasiswa> listStudents;

	public Mahasiswa getStudent() {
		return student;
	}

	public void setStudent(Mahasiswa student) {
		this.student = student;
	}
	
	
	public List<Mahasiswa> getListStudents() throws Exception {
		listStudents = new ArrayList<>();
		String url = "http://192.168.173.246:9090/Service/mahasiswa/mhsByNrp/02";
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
		listStudents.add(s);
		return listStudents;
		
//		in.close();
//		JSONObject result;
//		JSONArray jsonArray = new JSONArray(response.toString());
//		for(int i=0; i<jsonArray.length(); i++){
//	        JSONObject results = jsonArray.getJSONObject(i);
//	        results = (JSONObject) results.get("result");

//	        Mahasiswa s = new Mahasiswa();
//			s.setId(results.get("id").toString());
//			s.setName(results.getString("name"));
//			s.setAddress(results.getString("address"));
			
//			String name = s.getName().toString();
//			System.out.println(name);
//			
//			listStudents.add(s);
//	    } 
		
//		return listStudents;
	}

	public void setListStudents(List<Mahasiswa> listStudents) {
		this.listStudents = listStudents;
	}

	public List<Mahasiswa> getListStudent() {
		listStudents = new ArrayList<Mahasiswa>();
		Mahasiswa s1 = new Mahasiswa();
		s1.setNrp("1");
		s1.setNama("satu");
		s1.setAlamat("satu");
		listStudents.add(s1);
		
		return listStudents;
	}
	
	public void invalidateStudent() {
		student = new Mahasiswa();
	}
}
