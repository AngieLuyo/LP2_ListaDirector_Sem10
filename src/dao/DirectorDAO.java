package dao;

import java.util.List;

import entidad.Director;
import entidad.Filtro;

public interface DirectorDAO {
	
	public abstract int insertaDirector(Director obj);
	
	public List<Director> listaDirector(Filtro filtro);

}
