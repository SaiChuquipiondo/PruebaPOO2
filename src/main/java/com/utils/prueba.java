package com.utils;

import com.model.Conexion;

public class prueba {

	public static void main(String[] args) {
		
		Conexion conexion = new Conexion();
		conexion.abrirConexion();
		conexion.cerrarConexion();

	}

}
