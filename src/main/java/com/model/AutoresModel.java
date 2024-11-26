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
			String sql = "CALL sp_listarAutores()";
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
	
	public int insertarAutor(Autor autor) throws SQLException{
		try {
			int filaAfectadas = 0;
			String sql = "CALL sp_insertarAutor(?,?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setString(1, autor.getNombre());
			cs.setString(2, autor.getPais());
			filaAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filaAfectadas;
			
		} catch (SQLException e) {
			this.cerrarConexion();
			return 0;
		}	
	}
	
	public int eliminarAutor(int idAutor) throws SQLException {
		try {
			
			String sql = "CALL sp_eliminarAutor(?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, idAutor);
			int fila = cs.executeUpdate();
			this.cerrarConexion();
			return fila;
		} catch ( SQLException e) {
			this.cerrarConexion();
			return 0;
		}
		
	}
	
	public Autor obtenerAutor(int idAutor) {
		Autor autor = new Autor();
		try {
			String sql = "CALL sp_obtenerAutor(?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, idAutor);
			rs=cs.executeQuery();
			if(rs.next()) {
				autor.setId(rs.getInt("idAutor"));
				autor.setNombre(rs.getString("nombre"));
				autor.setPais(rs.getString("nacionalidad"));			
			}
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}
		return autor;
	}
	
	public int modificarAutor(Autor autor) {
		try {
			int filaAfectadas = 0;
			String sql = "CALL sp_modificarAutor(?,?,?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, autor.getId());
			cs.setString(2, autor.getNombre());
			cs.setString(3, autor.getPais());
			filaAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filaAfectadas;
			
		} catch (SQLException e) {
			this.cerrarConexion();
			return 0;
		}	
	}
	
	public int totalAutores() {
		try {
			int totalAutores = 0;
			String sql = "CALL sp_totalAutores()";
			this.abrirConexion();
			
			cs=conexion.prepareCall(sql);
			rs= cs.executeQuery();
			while(rs.next()) {
				totalAutores=rs.getInt("totalAutores");
				
			}
			this.cerrarConexion();
			return totalAutores;
		} catch (Exception e) {
			this.cerrarConexion();
			e.printStackTrace();
			return 0;
		}
		
	}
}
