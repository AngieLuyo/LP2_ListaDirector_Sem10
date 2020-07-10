package dao;

import java.util.List;

import entidad.Club;
import entidad.Filtro;

public interface ClubDAO {

	public abstract int insertaClub(Club obj);
	
	public List<Club> listaClub(Filtro filtro);
	
}
