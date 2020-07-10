package controlador;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClubDAO;
import entidad.Auspiciador;
import entidad.Club;
import fabricas.Fabrica;

@WebServlet("/registraClub")
public class ClubRegistroControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// 1 Recibe los parámetros
			// Son los nombres de las caja de textos en el JSP
			String nom = request.getParameter("nombre");
			String fec = request.getParameter("fechaCreacion");
			String pais = request.getParameter("pais");
			String auspiciador = request.getParameter("auspiciador");

			// 2 Se crea el objeto Alumno
			Auspiciador objAus = new Auspiciador();
			objAus.setIdAuspiciador(Integer.parseInt(auspiciador));

			Club obj = new Club();
			obj.setNombre(nom);
			obj.setFechaCreacion(new Date(sdf.parse(fec).getTime()));
			obj.setPais(pais);
			obj.setAuspiciador(objAus);

			Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			ClubDAO dao = fabrica.getClubDAO();

			int s = dao.insertaClub(obj);
			if (s > 0)
				request.getSession().setAttribute("MENSAJE", "registro exitoso");
			else
				request.getSession().setAttribute("MENSAJE", "registro erróneo");

		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("MENSAJE", "registro erróneo");
		} finally {
			response.sendRedirect("registraClub.jsp");
		}

	}

}
