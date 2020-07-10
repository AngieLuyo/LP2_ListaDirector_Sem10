package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import entidad.Auspiciador;
import entidad.Club;
import entidad.Filtro;
import util.MySqlDBConexion;

public class MySqlClubDAO implements ClubDAO {

	private static final Log log = LogFactory.getLog(MySqlClubDAO.class);

	@Override
	public int insertaClub(Club obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into club values(null,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setDate(2, obj.getFechaCreacion());
			pstm.setString(3, obj.getPais());
			pstm.setInt(4, obj.getAuspiciador().getIdAuspiciador());
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
	public List<Club> listaClub(Filtro filtro) {
		List<Club> data = new ArrayList<Club>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = null;
			
			if (filtro.getNombre().trim().equals("") && filtro.getIdAuspiciador() == -1 ) {
				sql = "SELECT c.*, a.nombre FROM club c inner join auspiciador a on c.idauspiciador = a.idauspiciador";
				pstm = conn.prepareStatement(sql);
			}else if (filtro.getNombre().trim().equals("") ) {
				sql = "SELECT c.*, a.nombre FROM club c inner join auspiciador a on c.idauspiciador = a.idauspiciador  where c.idauspiciador = ?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, filtro.getIdAuspiciador());
			}else if (filtro.getIdAuspiciador() == -1  ) {
				sql = "SELECT c.*, a.nombre FROM club c inner join auspiciador a on c.idauspiciador = a.idauspiciador where c.nombre like ?";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, filtro.getNombre()+"%");
			}else {
				sql = "SELECT c.*, a.nombre FROM club c inner join auspiciador a on c.idauspiciador = a.idauspiciador where c.idauspiciador = ? and c.nombre like ?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, filtro.getIdAuspiciador());
				pstm.setString(2, filtro.getNombre()+"%");
			}
		
			log.info(pstm);
			rs = pstm.executeQuery();
			Club objClub = null;
			Auspiciador objAus = null;
			while (rs.next()) {
				
				objClub = new Club();
				objClub.setIdClub(rs.getInt(1));
				objClub.setNombre(rs.getString(2));
				objClub.setFechaCreacion(rs.getDate(3));
				objClub.setPais(rs.getString(4));
				
				objAus = new Auspiciador();
				objAus.setIdAuspiciador(rs.getInt(5));
				objAus.setNombre(rs.getString(6));
				
				objClub.setAuspiciador(objAus);
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
