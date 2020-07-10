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

import dao.AuspiciadorDAO;
import dao.GradoDAO;
import entidad.Auspiciador;
import entidad.Grado;
import fabricas.Fabrica;

@WebServlet("/cargaCombo")
public class CargaComboControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(CargaComboControlador.class);

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String metodo = request.getParameter("metodo");
		if (metodo.equals("auspiciador")) {
			cargaAuspiciador(request, response);
		}if (metodo.equals("grado")) {
			cargaGrado(request, response);
		}
	}

	protected void cargaAuspiciador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 Traer los auspiciadores de la base de datos
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		AuspiciadorDAO dao = fabrica.getAuspiciadorDAO();
		List<Auspiciador> lista = dao.listaAuspiciador();

		// 2 Se convierte en formato JSON con la libreria gson
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		log.info("json-->" + json);

		// 3 Notificamos el tipo de archivo que se envía al browser
		response.setContentType("application/json;charset=UTF-8");

		// 4 Se genera el archivo JSON y se envía al browser
		PrintWriter out = response.getWriter();
		out.println(json);
	}
	
	protected void cargaGrado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 Traer los grados de la base de datos
		Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
		GradoDAO dao = fabrica.getGradoDAO();
		List<Grado> lista = dao.listaGrado();

		// 2 Se convierte en formato JSON con la libreria gson
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		log.info("json-->" + json);

		// 3 Notificamos el tipo de archivo que se envía al browser
		response.setContentType("application/json;charset=UTF-8");

		// 4 Se genera el archivo JSON y se envía al browser
		PrintWriter out = response.getWriter();
		out.println(json);
	}

}
