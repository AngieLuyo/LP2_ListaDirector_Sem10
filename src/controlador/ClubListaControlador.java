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

import dao.ClubDAO;
import entidad.Club;
import entidad.Filtro;
import fabricas.Fabrica;

@WebServlet("/clubConsulta")
public class ClubListaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(ClubListaControlador.class);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String nom = "";
		if (request.getParameter("nombre") != null) {
			nom = request.getParameter("nombre");
		}
		
		int idAus = -1;
		if (request.getParameter("idAuspiciador") != null) {
			idAus = Integer.parseInt(request.getParameter("idAuspiciador"));
		}
		
		Filtro filtro = new Filtro();
		filtro.setIdAuspiciador(idAus);
		filtro.setNombre(nom);
		
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		ClubDAO dao = fabrica.getClubDAO();
		
		List<Club> lista  = dao.listaClub(filtro);
		
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		log.info("json-->" + json);

		response.setContentType("application/json;charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println(json);
	}

}

