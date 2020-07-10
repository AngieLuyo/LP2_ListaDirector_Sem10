package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import entidad.Director;
import entidad.Filtro;
import entidad.Grado;
import util.MySqlDBConexion;

public class MySqlDirectorDAO implements DirectorDAO{

	private static final Log log = LogFactory.getLog(MySqlDirectorDAO.class);

	@Override
	public int insertaDirector(Director obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into director values(null,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setDate(2, obj.getFechaNacimiento());
			pstm.setInt(3, obj.getGrado().getIdGrado());
			salida = pstm.executeUpdate();
			log.info(pstm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}

		return salida;
	}

	@Override
	public List<Director> listaDirector(Filtro filtro) {
		List<Director> data = new ArrayList<Director>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = null;
			
			if (filtro.getIdGrado() == -1 ) {
				sql = "SELECT c.*, a.nombre FROM director c inner join grado a on c.idgrado = a.idgrado";
				pstm = conn.prepareStatement(sql);
			}else {
				sql = "SELECT c.*, a.nombre FROM director c inner join grado a on c.idgrado = a.idgrado where c.idgrado = ?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, filtro.getIdGrado());
			}
		
			log.info(pstm);
			rs = pstm.executeQuery();
			Director objClub = null;
			Grado objAus = null;
			while (rs.next()) {
				
				objClub = new Director();
				objClub.setIdDirector(rs.getInt(1));
				objClub.setNombre(rs.getString(2));
				objClub.setFechaNacimiento(rs.getDate(3));
				
				
				objAus = new Grado();
				objAus.setIdGrado(rs.getInt(4));
				objAus.setNombre(rs.getString(5));
				
				objClub.setGrado(objAus);
				data.add(objClub);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}
		return data;
	}

}
