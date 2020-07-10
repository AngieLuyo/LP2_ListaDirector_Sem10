package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import entidad.Auspiciador;
import util.MySqlDBConexion;

public class MySqlAuspiciadorDAO implements AuspiciadorDAO {

	private static final Log log = LogFactory.getLog(MySqlAuspiciadorDAO.class);

	@Override
	public List<Auspiciador> listaAuspiciador() {
		List<Auspiciador> data = new ArrayList<Auspiciador>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from auspiciador";
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			Auspiciador obj = null;
			while (rs.next()) {
				obj = new Auspiciador();
				obj.setIdAuspiciador(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				data.add(obj);
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
