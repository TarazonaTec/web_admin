package com.admin.webAdmin.controllers;



import com.admin.webAdmin.dao.UsuarioDao;
import com.admin.webAdmin.models.Usuario;
import com.admin.webAdmin.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;
    
    
    
    

    @RequestMapping(value="api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization")String token){

          if(!validarToken(token)) {
        	  return null;
          }
    	
          return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
    	
    	String idUsuario=jwtUtil.getKey(token);
    
    		return idUsuario!=null;
    
    	
    	
    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario us){
    	 
    	 Argon2 argon2=Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);  
    	 String hash=argon2.hash(1, 1024, 1, us.getPassword());
    	 us.setPassword(hash);
         usuarioDao.registrar(us);
         
    }

    
 

    @RequestMapping(value="api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization")String token ,@PathVariable Long id){
    	if(!validarToken(token)) {
       	  return;
         }
    	 
    	usuarioDao.eliminar(id);
    }
    



}
