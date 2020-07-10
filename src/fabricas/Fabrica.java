package fabricas;

import dao.AuspiciadorDAO;
import dao.ClubDAO;
import dao.DirectorDAO;
import dao.GradoDAO;

//Es una fábrica de objetos
//Se usa el patrón DAO (Data Access Object)
public abstract class Fabrica {

	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;

	public abstract ClubDAO getClubDAO();
	public abstract AuspiciadorDAO getAuspiciadorDAO();

	public abstract DirectorDAO getDirectorDAO();
	public abstract GradoDAO getGradoDAO();
	
	//Va fabricar subfabricas (Mysql y sqlserver)
	public static Fabrica getFabrica(int tipo){
		Fabrica salida = null;
		switch(tipo){
			case MYSQL: 
				salida = new FabricaMysql();
				break;
			
		}
		return salida;
	}
}
