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
import javax.faces.bean.ManagedBean;
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
 * @author dilacim
 */

@ManagedBean(name="mahasiswa")
public class StudentController extends BaseUrl{
    private String BASE_URL = getIp()+"mahasiswa/";
    private Mahasiswa s;
	private List<Mahasiswa> listStudents;

	public Mahasiswa getStudent() {
		return s;
	}

	public void setStudent(Mahasiswa student) {
		this.s = student;
	}
        
	
        public Mahasiswa getDataMhs() throws Exception {
            String url = "mhsByNrp/02";
		URL obj = new URL(BASE_URL + url);
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
                s.setTgl(result.getString("tglLahir"));
                s.setNoTelp(result.getString("noTelp"));
                s.setEmail(result.getString("email"));
                s.setIpk(result.getDouble("ipk"));
                
                return s;
        }
        
//        public List<Perwalian> addKrs() throws Exception {
//            HttpClient httpClient = HttpClientBuilder.create().build();
//            Perwalian p = new Perwalian();
//            try {
//                String url = BASE_URL + "dosen/apa/";
//                HttpPost request = new HttpPost(url);
//                StringEntity params =new StringEntity("{request:{\"id_dosen\":\"dos01\"} }");
//                request.addHeader("content-type", "application/json");
//                request.setEntity(params);
//
//                HttpResponse response = httpClient.execute(request);
//                HttpEntity entity = response.getEntity();
//                responseString = EntityUtils.toString(entity, "UTF-8");
//                JSONObject result;
//                JSONObject jsonObject = new JSONObject(responseString);
//                result = (JSONObject) jsonObject.get("result");
//                result = (JSONObject) result.get("map");
//
//                dosen.setIdDosen(result.getString("id_dosen"));
//                dosen.setNama(result.getString("nama_dosen"));
//                dosen.setTgl(result.getString("tglLahir"));
//                dosen.setTelp(result.getString("noTelp"));
//            }catch (Exception ex) {
//                // handle exception here
//            }
//
//            return dosen;
//        }
	
	public List<Mahasiswa> getListStudents() throws Exception {
		listStudents = new ArrayList<>();
		String url = "mhsByNrp/02";
		URL obj = new URL(BASE_URL + url);
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
	}

	public void setListStudents(List<Mahasiswa> listStudents) {
		this.listStudents = listStudents;
	}

//	public List<Mahasiswa> getListStudent() {
//		listStudents = new ArrayList<Mahasiswa>();
//		Mahasiswa s1 = new Mahasiswa();
//		s1.setNrp("1");
//		s1.setNama("satu");
//		s1.setAlamat("satu");
//		listStudents.add(s1);
//		
//		return listStudents;
//	}
	
	public void invalidateStudent() {
		s = new Mahasiswa();
	}
}
