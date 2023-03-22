package com.admin.webAdmin.dao;

import com.admin.webAdmin.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

	void eliminar(Long id);

	void registrar(Usuario us);

    Usuario obtenerUsuarioPorCredenciales(Usuario us);









}
