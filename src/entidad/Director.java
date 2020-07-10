package entidad;

import java.sql.Date;

import lombok.Data;


@Data
public class Director {

	
	private int idDirector;
	private String nombre;
	private Date fechaNacimiento;
	private Grado grado;
}
