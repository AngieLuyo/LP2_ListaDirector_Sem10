package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;

import dao.DirectorDAO;
import entidad.Director;
import entidad.Filtro;
import fabricas.Fabrica;

/**
 * Servlet implementation class DirectorListaControlador
 */
@WebServlet("/directorConsulta")
public class DirectorListaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

private static final Log log = LogFactory.getLog(DirectorListaControlador.class);
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String nom = "";
		if (request.getParameter("nombre") != null) {
			nom = request.getParameter("nombre");
		}
		
		int idAus = -1;
		if (request.getParameter("idGrado") != null) {
			idAus = Integer.parseInt(request.getParameter("idGrado"));
		}
		
		Filtro filtro = new Filtro();
		filtro.setIdGrado(idAus);
		filtro.setNombre(nom);
		
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		DirectorDAO dao = fabrica.getDirectorDAO();
		
		List<Director> lista  = dao.listaDirector(filtro);
		
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		log.info("json-->" + json);

		response.setContentType("application/json;charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println(json);
	}

}


