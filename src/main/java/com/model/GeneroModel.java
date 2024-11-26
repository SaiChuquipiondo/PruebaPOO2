package com.model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.Autor;
import com.beans.Genero;

public class GeneroModel extends Conexion {
	CallableStatement cs;
	ResultSet rs;
	
	public List<Genero> ListarGenero(){
		
		try {
			List<Genero> lista = new ArrayList<>();
			String sql = "CALL sp_listarGenero()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Genero genero = new Genero();
				genero.setIdgenero(rs.getInt("idgenero"));
				genero.setNombre(rs.getString("nombre"));
				genero.setDescripcion(rs.getString("descripcion"));
				lista.add(genero);
			}
			
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}

	}
	
	public int insertarGenero(Genero genero) throws SQLException{
		try {
			int filaAfectadas = 0;
			String sql = "CALL sp_insertarGenero(?,?)";
			this.abrirConexion();
			cs=conexion.prepareCall(sql);
			cs.setString(1, genero.getNombre());
			cs.setString(2, genero.getDescripcion());
			filaAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filaAfectadas;
			
		} catch (SQLException e) {
			this.cerrarConexion();
			return 0;
		}	
	}
}
