package com.admin.webAdmin.dao;

import com.admin.webAdmin.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImp implements  UsuarioDao{
     @PersistenceContext
     EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
         String query="from Usuario";
       return entityManager.createQuery(query).getResultList();

    }

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		 Usuario query=entityManager.find(Usuario.class,id);
	     entityManager.remove(query);
	       
	}

	@Override
	public void registrar(Usuario us) {
		
	    entityManager.merge(us);
		
	}
	
	public Usuario obtenerUsuarioPorCredenciales(Usuario us) {
		
		  String query="from Usuario where email= :email";
	      List<Usuario> lista =entityManager.createQuery(query)
	    		  .setParameter("email",us.getEmail())
	    		  .getResultList();
	      
	     if(lista.isEmpty()) {
	    	 return null;
	     }
	      
	     String passwordHashed=lista.get(0).getPassword();
	     
	     Argon2 argon2=Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
	      
	     if(argon2.verify(passwordHashed,us.getPassword())) {
	    	 return lista.get(0);
	    	 
	     } 
	     
	    	 return null;
	     
	     
	}
	
	

}
