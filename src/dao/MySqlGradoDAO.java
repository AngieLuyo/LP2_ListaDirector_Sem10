package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import entidad.Grado;
import util.MySqlDBConexion;

public class MySqlGradoDAO implements GradoDAO{


	private static final Log log = LogFactory.getLog(MySqlGradoDAO.class);
	
	@Override
	public List<Grado> listaGrado() {
		List<Grado> data = new ArrayList<Grado>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from grado";
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			Grado obj = null;
			while (rs.next()) {
				obj = new Grado();
				obj.setIdGrado(rs.getInt(1));
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
	
