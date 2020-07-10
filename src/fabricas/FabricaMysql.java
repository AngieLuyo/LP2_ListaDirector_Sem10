package fabricas;

import dao.AuspiciadorDAO;
import dao.ClubDAO;
import dao.DirectorDAO;
import dao.GradoDAO;
import dao.MySqlAuspiciadorDAO;
import dao.MySqlClubDAO;
import dao.MySqlDirectorDAO;
import dao.MySqlGradoDAO;

//Es una subfabrica que tiene objetos que acceden
//a la base de datos MYSQL
public class FabricaMysql extends Fabrica{


	@Override
	public ClubDAO getClubDAO() {
		return new MySqlClubDAO();
	}

	@Override
	public AuspiciadorDAO getAuspiciadorDAO() {
		return new MySqlAuspiciadorDAO();
	}

	@Override
	public DirectorDAO getDirectorDAO() {
		
		return new MySqlDirectorDAO();
	}

	@Override
	public GradoDAO getGradoDAO() {
		return new MySqlGradoDAO();
	}




}





