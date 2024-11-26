package com.model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.Autor;
import com.beans.Editorial;
import com.beans.Genero;
import com.beans.Libro;

public class LibroModel extends Conexion{
	CallableStatement cs;
	ResultSet rs;
	
	public List<Libro> ListarLibro(){
		
		try {
			List<Libro> lista = new ArrayList<>();
			String sql = "CALL sp_listarLibro()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Libro libro = new Libro();	
				Autor autor = new Autor();
				Editorial edi = new Editorial();
				Genero gene = new Genero();
				
				libro.setIdLibro(rs.getInt(1));
				libro.setNombre(rs.getString(2));
				libro.setExistencia(rs.getInt(3));
				libro.setPrecio(rs.getDouble(4));
				
				libro.setDescripcion(rs.getString(5));
				libro.setAutor(new Autor(rs.getInt(6),rs.getString(7), ""));
				libro.setEditorial(new Editorial(rs.getInt(8), rs.getString(9), "",""));
				libro.setGenero(new Genero(rs.getInt(10),rs.getString(11),""));
				lista.add(libro);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			System.err.println("Error ListarLibro: "+e.getMessage());
			this.cerrarConexion();
			return null;
		}
		

	} 
	public List<Autor> ListarAutor(){
		try {
			List<Autor> lista = new ArrayList<>();
			String sql = "CALL sp_listarAutores()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Autor autor = new Autor();
				autor.setId(rs.getInt(1));
				autor.setNombre(rs.getString(2));
				lista.add(autor);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			System.err.println("Error ListarAutorLibro: "+e.getMessage());
			this.cerrarConexion();
			return null;
		}
	} 
	public List<Editorial> ListarEditorial(){
		try {
			List<Editorial> lista = new ArrayList<>();
			String sql = "CALL sp_listarEditorial()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Editorial edi = new Editorial();
				edi.setIdEditorial(rs.getInt(1));
				edi.setNombre(rs.getString(2));
				lista.add(edi);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			System.err.println("Error ListarEditorialLibro: "+e.getMessage());
			this.cerrarConexion();
			return null;
		}
	} 
	public List<Genero> ListarGenero(){
		try {
			List<Genero> lista = new ArrayList<>();
			String sql = "CALL sp_listarGenero()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Genero gene = new Genero();
				gene.setIdgenero(rs.getInt(1));
				gene.setNombre(rs.getString(2));
				lista.add(gene);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			System.err.println("Error ListarGeneroLibro: "+e.getMessage());
			this.cerrarConexion();
			return null;
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
				autor.setNombre(rs.getString("nombre"));			
			}
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}
		return autor;
	}
	
	public int insertarLibro(Libro libro) throws SQLException{
		try {
			int filaAfectadas = 0;
			String sql = "CALL sp_insertarLibro(?,?,?,?,?,?,?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setString(1, libro.getNombre());
			cs.setInt(2, libro.getExistencia());
			cs.setDouble(3, libro.getPrecio());
			cs.setString(4, libro.getDescripcion());
			cs.setInt(5, libro.getAutor().getId());
			cs.setInt(6, libro.getEditorial().getIdEditorial());
			cs.setInt(7, libro.getGenero().getIdgenero());
			filaAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filaAfectadas;
			
		} catch (SQLException e) {
			System.err.println("Error insertarLibro: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}	
	}
	
	public Libro obtenerLibro(int id) {
		Libro libro = new Libro();
		try {

			Autor autor = new Autor();
			Editorial edi = new Editorial();
			Genero gene = new Genero();
			String sql = "CALL sp_obtenerLibro(?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, id);
			rs=cs.executeQuery();
			if(rs.next()) {
				libro.setIdLibro(rs.getInt(1));
				libro.setNombre(rs.getString(2));
				libro.setExistencia(rs.getInt(3));
				libro.setPrecio(rs.getDouble(4));
				libro.setDescripcion(rs.getString(5));
				autor.setId(rs.getInt(6));
				libro.setAutor(autor);
				edi.setIdEditorial(rs.getInt(7));
				libro.setEditorial(edi);
				gene.setIdgenero(rs.getInt(8));
				libro.setGenero(gene);
				
			}
			
			
		} catch (Exception e) {
			System.err.println("Error en obtenerLibro: "+e.getMessage());
			this.cerrarConexion();
			return null;
		}
		return libro;
	}
	
	public int editarLibro (Libro libro) {
		try {
			int fila = 0;
			String sql = "CALL sp_editarLibro(?,?,?,?,?,?,?,?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, libro.getIdLibro());
			cs.setString(2, libro.getNombre());
			cs.setInt(3, libro.getExistencia());
			cs.setDouble(4, libro.getPrecio());
			cs.setString(5, libro.getDescripcion());
			cs.setInt(6, libro.getAutor().getId());
			cs.setInt(7, libro.getEditorial().getIdEditorial());
			cs.setInt(8, libro.getGenero().getIdgenero());
			fila = cs.executeUpdate();
			
			
			this.cerrarConexion();
			return fila;
		} catch (Exception e) {
			System.err.println("Error en editarLibro: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}
	}
	
	public int eliminarLibro(int id) {
		try {
			String sql =  "CALL sp_eliminarLibro(?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setInt(1, id);
			int fila = cs.executeUpdate();
			
			this.cerrarConexion();
			return fila;
		} catch (Exception e) {
			System.err.println("Error en eliminarLibro: "+e.getMessage());
			return 0;
		}
	}
	
}
