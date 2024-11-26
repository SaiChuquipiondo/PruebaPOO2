package com.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


import com.beans.Editorial;

public class EditorialModel extends Conexion{
	
	CallableStatement cs;
	ResultSet rs;
	public List<Editorial> ListaEditorial(){
	try {
		List<Editorial> editorial = new ArrayList<>();
		String sql = "CALL sp_listarEditorial()";
		this.abrirConexion();
		cs=conexion.prepareCall(sql);
		rs=cs.executeQuery();
		while(rs.next()) {
			Editorial mieditorial = new Editorial();
			mieditorial.setIdEditorial(rs.getInt("ideditorial"));
			mieditorial.setNombre(rs.getString("nombre"));
			mieditorial.setContacto(rs.getString("contacto"));
			mieditorial.setTelefono(rs.getString("telefono"));
			editorial.add(mieditorial);
		}
		this.cerrarConexion();
		return editorial;
	} catch (Exception e) {
		this.cerrarConexion();
		e.printStackTrace();
		return null;
	}
	}
	
	
	public int insertarEditorial(Editorial edi) {
		try {
			int fila=0;
			String sql = "CALL sp_insertarEditorial(?,?,?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setString(1, edi.getNombre());
			cs.setString(2, edi.getContacto());
			cs.setString(3, edi.getTelefono());
			fila = cs.executeUpdate();
			this.cerrarConexion();
			return fila;
		} catch (Exception e) {
			this.cerrarConexion();
			return 0;
		}
	}
	
	public Editorial obtenerEditorial(int id) {
		try {
			Editorial edi = new Editorial();
			String sql = "CALL sp_obtenerEditorial(?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, id);
			rs=cs.executeQuery();
			if(rs.next()) {
				edi.setIdEditorial(rs.getInt("ideditorial"));
				edi.setNombre(rs.getString("nombre"));
				edi.setContacto(rs.getString("contacto"));
				edi.setTelefono(rs.getString("telefono"));
			}
			this.cerrarConexion();
			return edi;
		} catch (Exception e) {
			this.cerrarConexion();
			e.printStackTrace();
			return null;
		}
	}
	
	public int editarEditorial(Editorial edi) {
		try {
			String sql = "CALL sp_editarEditorial(?,?,?,?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, edi.getIdEditorial());
			cs.setString(2, edi.getNombre());
			cs.setString(3, edi.getContacto());
			cs.setString(4,edi.getTelefono());
			int fila = cs.executeUpdate();
			
			this.cerrarConexion();
			return fila;
			
		} catch (Exception e) {
			this.cerrarConexion();
			e.printStackTrace();
			return 0;
			
		}
	}
	
	
	public int eliminarEditorial(int id) {
		try {
			String sql = "CALL sp_eliminarEditorial(?)"; 
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, id);
			int fila = cs.executeUpdate();
			
			this.cerrarConexion();
			return fila;
			
		} catch (Exception e) {
			this.cerrarConexion();
			e.printStackTrace();
			return 0;
		}
	}
}
