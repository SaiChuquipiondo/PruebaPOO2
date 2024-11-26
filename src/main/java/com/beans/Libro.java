package com.beans;

public class Libro{
	 private int idLibro;
	 private String nombre;
	 private int existencia;
	 private double precio;
	 private String descripcion;
	 private Autor  autor;
	 private Editorial editorial;
	 private Genero genero;
	 
	 public Libro() {
		// TODO Auto-generated constructor stub
	}

	public Libro(int idLibro, String nombre, int existencia, double precio, String descripcion, Autor autor,
			Editorial editorial, Genero genero) {
		super();
		this.idLibro = idLibro;
		this.nombre = nombre;
		this.existencia = existencia;
		this.precio = precio;
		this.descripcion = descripcion;
		this.autor = autor;
		this.editorial = editorial;
		this.genero = genero;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	

	

	

	 
	 
}
