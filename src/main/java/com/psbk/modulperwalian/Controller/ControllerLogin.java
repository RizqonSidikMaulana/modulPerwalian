package com.psbk.modulperwalian.Controller;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dilacim
 */

@ManagedBean (name = "login", eager = true)
public class ControllerLogin {
    private String name;
    private String password;


    public String getName ()
    {
        return name;
    }


    public void setName (String name)
    {
        this.name = name;
    }


    public String getPassword ()
    {
        return password;
    }


    public void setPassword (String password)
    {
        this.password = password;
    }
    
    public void validateUsernamePassword() {
    	System.out.println(getPassword());
    	
        if ( getName().matches("admin") && getPassword().matches("12345")) {
        	FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Login Sukses",
                            "Welcome Admin"));
        }
        else {
        	FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Username atau password salah",
                            "Password atau Username Salah"));
        }
    }
    
}
