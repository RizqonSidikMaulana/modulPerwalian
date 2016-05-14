package com.psbk.modulperwalian.Controller;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dilacim
 */

@ManagedBean (name = "login")
public class ControllerLogin extends BaseUrl {
    private String password;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }           


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public JSONObject getDataLogin() {
        JSONObject obj = null;
        HttpClient httpClient = HttpClientBuilder.create().build();
        String status = null;
            try {
                String url = getIp() + "login";
                HttpPost request = new HttpPost(url);
                StringEntity params =new StringEntity("{request:{\"username\":\""+ getUsername()  +"\","
                        + "\"password\":\"" + getPassword() + "\" } }");
                request.addHeader("content-type", "application/json");
                request.setEntity(params);
                
                HttpResponse response = httpClient.execute(request);
                HttpEntity entity = response.getEntity();
                status = EntityUtils.toString(entity, "UTF-8");
                JSONObject result;
                obj = new JSONObject(status);

            }catch (Exception ex) {
                System.out.println("Error karena : " + ex.getMessage());
            }

            return obj;
    }
    
    public String validateUsernamePassword() throws Exception {
       
        JSONObject obj = getDataLogin();
        String kel = obj.getString("result");
        String status = obj.getString("status");
        if ( status.equalsIgnoreCase("true")) {
            if ( kel.contains("dos")) {
                return "dosen-page/home-dosen.xhtml?faces-redirect=true";
            } else {                
                return "mahasiswa-page/home-mahasiswa.xhtml?faces-redirect=true";
            }
        }
        else {
        	FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Username atau password salah",
                            "Password atau Username Salah"));
        }
        return null;
    }
    
}
