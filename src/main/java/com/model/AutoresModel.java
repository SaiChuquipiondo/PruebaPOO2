package com.model;

import java.util.ArrayList;
import java.util.List;

import com.beans.Autor;

public class AutoresModel {

	public List<Autor> ListarAutores(){
		ArrayList<Autor> autor = new ArrayList<>();
		 autor.add(new Autor(1,"Gracia Marquez","Colombiana"));
		 autor.add(new Autor(2,"Maria Torre","Peruana"));
		 autor.add(new Autor(3,"Jose Diaz","Argentina"));
		return autor;
		
	}
}
