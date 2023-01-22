package ar.com.ada.backend12.RRHH.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.ada.backend12.RRHH.modelo.Empleado;

public class EmpleadoDAO extends MasterDAO {
	
	private static final String INSERT =
			"INSERT INTO EMPLEADO (NOMBRE, APELLIDO, DI, FECHA_NACIMIENTO, DEPARTAMENTO, FECHA_CONTRATACION, SALARIO) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE =
			"UPDATE EMPLEADO SET NOMBRE = ?, APELLIDO = ?, DI = ?, FECHA_NACIMIENTO = ?, DEPARTAMENTO = ?, FECHA_CONTRATACION = ?, SALARIO = ? WHERE ID = ?";
	
	private static final String DELETE =
			"DELETE FROM EMPLEADO WHERE ID = ?";
	
	private static final String GET =
			"SELECT ID, NOMBRE, APELLIDO, DI, FECHA_NACIMIENTO, DEPARTAMENTO, FECHA_CONTRATACION, SALARIO FROM EMPLEADO WHERE ID = ?";
	
	private static final String GET_ALL =
			"SELECT ID, NOMBRE, APELLIDO, DI, FECHA_NACIMIENTO, DEPARTAMENTO, FECHA_CONTRATACION, SALARIO FROM EMPLEADO";
	
	public static int insert(Empleado emp) {
		int rowCount = 0;
		PreparedStatement stmt = null;
		try {
			Connection conn = getDBConnection();
			
			stmt = conn.prepareStatement(INSERT);
			int p = 1;
			stmt.setString(p++, emp.getNombre());
			stmt.setString(p++, emp.getApellido());
			stmt.setString(p++, emp.getDi());
			stmt.setDate(p++, new Date(emp.getFechaNacimiento().getTime()));
			stmt.setString(p++, emp.getDepartamento());
			stmt.setDate(p++, new Date(emp.getFechaContratacion().getTime()));
			stmt.setBigDecimal(p++, emp.getSalario());
			
			rowCount = stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error insertando empleado en la base de datos: " + e.getMessage());
		} finally {
			try { stmt.close(); } catch(SQLException f) {System.err.println("Error cerrando statement: " + f.getMessage());}
		}
		return rowCount;
	}
	
	public static int update(Empleado emp) {
		int rowCount = 0;
		PreparedStatement stmt = null;
		try {
			Connection conn = getDBConnection();
			
			stmt = conn.prepareStatement(UPDATE);
			int p = 1;
			stmt.setString(p++, emp.getNombre());
			stmt.setString(p++, emp.getApellido());
			stmt.setString(p++, emp.getDi());
			stmt.setDate(p++, new Date(emp.getFechaNacimiento().getTime()));
			stmt.setString(p++, emp.getDepartamento());
			stmt.setDate(p++, new Date(emp.getFechaContratacion().getTime()));
			stmt.setBigDecimal(p++, emp.getSalario());
			stmt.setInt(p++, emp.getId());
			
			rowCount = stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error actualizando empleado en la base de datos: " + e.getMessage());
		} finally {
			try { stmt.close(); } catch(SQLException f) {System.err.println("Error cerrando statement: " + f.getMessage());}
		}
		return rowCount;
	}
	
	public static int delete(int id) {
		int rowCount = 0;
		PreparedStatement stmt = null;
		try {
			Connection conn = getDBConnection();
			
			stmt = conn.prepareStatement(DELETE);
			int p = 1;
			stmt.setInt(p++, id);
			
			rowCount = stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error eliminando empleado de la base de datos: " + e.getMessage());
		} finally {
			try { stmt.close(); } catch(SQLException f) {System.err.println("Error cerrando statement: " + f.getMessage());}
		}
		return rowCount;
	}
	
	public static Empleado get(int id) {
		Empleado emp = null;
		PreparedStatement stmt = null;
		try {
			Connection conn = getDBConnection();
			
			stmt = conn.prepareStatement(GET);
			int p = 1;
			stmt.setInt(p++, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				emp = new Empleado();
				emp.setId(rs.getInt("ID"));
				emp.setNombre(rs.getString("NOMBRE"));
				emp.setApellido(rs.getString("APELLIDO"));
				emp.setDi(rs.getString("DI"));
				emp.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
				emp.setDepartamento(rs.getString("DEPARTAMENTO"));
				emp.setFechaContratacion(rs.getDate("FECHA_CONTRATACION"));
				emp.setSalario(rs.getBigDecimal("SALARIO"));
				break;
			}
			
		} catch (SQLException e) {
			System.err.println("Error obteniendo empleado desde la base de datos: " + e.getMessage());
		} finally {
			try { stmt.close(); } catch(SQLException f) {System.err.println("Error cerrando statement: " + f.getMessage());}
		}
		return emp;
	}
	
	public static List<Empleado> getAll() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		Statement stmt = null;
		try {
			Connection conn = getDBConnection();
			
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(GET_ALL);
			
			while(rs.next()) {
				Empleado emp = new Empleado();
				emp.setId(rs.getInt("ID"));
				emp.setNombre(rs.getString("NOMBRE"));
				emp.setApellido(rs.getString("APELLIDO"));
				emp.setDi(rs.getString("DI"));
				emp.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
				emp.setDepartamento(rs.getString("DEPARTAMENTO"));
				emp.setFechaContratacion(rs.getDate("FECHA_CONTRATACION"));
				emp.setSalario(rs.getBigDecimal("SALARIO"));
				
				empleados.add(emp);
			}
			
		} catch (SQLException e) {
			System.err.println("Error obteniendo empleados desde la base de datos: " + e.getMessage());
			empleados = null;
		} finally {
			try { stmt.close(); } catch(SQLException f) {System.err.println("Error cerrando statement: " + f.getMessage());}
		}
		return empleados;
	}
}
