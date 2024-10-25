package com.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.beans.Autor;

public class AutoresModel extends Conexion{
	
	CallableStatement cs;
	ResultSet rs;
	
	public List<Autor> ListarAutores(){
		
		try {
			List<Autor> lista = new ArrayList<>();
			String sql = "CALL sp_listarAutor()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Autor autor = new Autor();
				autor.setId(rs.getInt("idAutor"));
				autor.setNombre(rs.getString("nombre"));
				autor.setPais(rs.getString("nacionalidad"));
				lista.add(autor);
			}
			
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}

	}
}
