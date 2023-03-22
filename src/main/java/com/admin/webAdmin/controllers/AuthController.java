package com.admin.webAdmin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admin.webAdmin.dao.UsuarioDao;
import com.admin.webAdmin.models.Usuario;
import com.admin.webAdmin.utils.JWTUtil;

@RestController
public class AuthController {
	 @Autowired
	    private UsuarioDao usuarioDao;
	 @Autowired
	    private JWTUtil jwtUtil;
		 
	 
    @RequestMapping(value="api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario us){
         Usuario uc=usuarioDao.obtenerUsuarioPorCredenciales(us);
         
         if(uc!=null) {
        	String token=jwtUtil.create(String.valueOf(uc.getId()),us.getEmail());
        	 
        	 return token;
         }
        	 return "nn";
         
    }

    
}
