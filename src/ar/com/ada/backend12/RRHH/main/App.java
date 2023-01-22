package ar.com.ada.backend12.RRHH.main;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import ar.com.ada.backend12.RRHH.DAO.EmpleadoDAO;
import ar.com.ada.backend12.RRHH.DAO.MasterDAO;
import ar.com.ada.backend12.RRHH.modelo.Empleado;
import ar.com.ada.backend12.RRHH.util.DateUtil;

public class App {
	
	private static Scanner in = null;
	
	private static final String MENU_PRINCIPAL = ""
			+ "\n" + "----------------------------------------------------------"
			+ "\n" + "-- MENU PRINCIPAL"
			+ "\n" + "----------------------------------------------------------"
			+ "\n" + "1. Listar todos los empleados"
			+ "\n" + "2. Mostrar información de un empleado"
			+ "\n" + "3. Crear un empleado"
			+ "\n" + "4. Actualizar un empleado"
			+ "\n" + "5. Eliminar un empleado"
			+ "\n" + "6. Salir"
			+ "\n" + ""
			+ "\n" + "Ingrese el número de la ópcion deseada: ";
	
	public static void main(String[] args) {
		if (!init()) { return; }
		
		while (true) {
			imprimirMenuPrincipal();
			int opt = in.nextInt(); in.nextLine();
			
			if (opt == 6) {
				break;
			}
			
			switch (opt) {
				case 1:
					listarTodosLosEmpleados();
					break;
				case 2:
					mostrarLaInformacionDeUnEmpleado();
					break;
				case 3:
					crearEmpleado();
					break;
				case 4:
					actualizarEmpleado();
					break;
				case 5:
					eliminarUnEmpleado();
					break;
				default:
					System.out.println("\nOpcion no válida.");
			}
		}
		
		System.out.println("\nFin del programa.");
		
		end();
	}
	
	private static boolean init() {
		boolean success = true;
		
		//Abrimos el scanner
		in = new Scanner(System.in);
		
		//Abrimos la conexión con la base de datos
		try {
			MasterDAO.openDBConnection();
		} catch (Exception e) {
			System.out.println("Error estableciendo conexión con la base de datos: " + e.getMessage());
			success = false;
		}
		
		return success;
	}
	
	private static void end() {
		//Cerramos el scanner
		in.close();
		
		//Cerramos la conexión con la base de datos
		try {
			MasterDAO.closeDBConnection();
		} catch (Exception e) {
			System.out.println("Error cerrando conexión con la base de datos: " + e.getMessage());
		}
	}
	
	private static void imprimirMenuPrincipal() {
		System.out.print(MENU_PRINCIPAL);
	}
	
	private static void listarTodosLosEmpleados() {
		List<Empleado> empleados = EmpleadoDAO.getAll();
		
		if (empleados == null) {
			System.out.println("\nEn este momento no podemos listar los empleados. Por favor intente mas tarde.");
			return;
		}
		
		if (empleados.size() < 1) {
			System.out.println("\nNo hay empleados que listar.");
			return;
		}
		
		System.out.println();
		for (Empleado e : empleados) {
			System.out.println(e);
		}
	}
	
	private static void mostrarLaInformacionDeUnEmpleado() {
		System.out.print("\nIngrese el id del empleado: ");
		int id = in.nextInt(); in.nextLine();
		
		Empleado e = EmpleadoDAO.get(id);
		
		if (e == null) {
			System.out.println("\nEl empleado no existe.");
			return;
		}
		
		System.out.println(e);
	}
	
	private static void eliminarUnEmpleado() {
		System.out.print("\nIngrese el id del empleado a ELIMINAR: ");
		int id = in.nextInt(); in.nextLine();
		
		int borrado = EmpleadoDAO.delete(id);
		
		if (borrado < 1) {
			System.out.println("\nEl empleado no existe.");
			return;
		}
		
		System.out.println("\nEmpleado eliminado.");
	}
	
	private static void crearEmpleado() {
		System.out.print("\nIngrese el nombre del empleado: ");
		String nombre = in.nextLine();
		
		System.out.print("\nIngrese el apellido del empleado: ");
		String apellido = in.nextLine();
		
		System.out.print("\nIngrese el DI del empleado: ");
		String di = in.nextLine();
		
		Date fechaNacimiento = null;
		boolean ok = false;
		do {
			System.out.print("\nIngrese la fecha de nacimiento del empleado (YYYY-MM-DD): ");
			try {
				fechaNacimiento = DateUtil.parse(in.nextLine());
				ok = true;
			} catch (Exception e) {
				System.out.println("\n" + e.getMessage());
			}
		} while (!ok);
		
		System.out.print("\nIngrese el departamento del empleado: ");
		String departamento = in.nextLine();
		
		Date fechaContratacion = null;
		ok = false;
		do {
			System.out.print("\nIngrese la fecha de contratación del empleado (YYYY-MM-DD): ");
			try {
				fechaContratacion = DateUtil.parse(in.nextLine());
				ok = true;
			} catch (Exception e) {
				System.out.println("\n" + e.getMessage());
			}
		} while (!ok);
		
		System.out.print("\nIngrese el salario del empleado: ");
		BigDecimal salario = BigDecimal.valueOf(in.nextLong());
		in.nextLine();
		
		Empleado e = new Empleado(nombre, apellido, di, fechaNacimiento, departamento, fechaContratacion, salario);
		
		int success = EmpleadoDAO.insert(e);
		
		if (success < 1) {
			System.out.println("\nEl empleado no pudo ser creado.");
			return;
		}
		
		System.out.println("\nEmpleado creado satisfactoriamente.");
		
	}
	
	private static void actualizarEmpleado() {
		System.out.print("\nIngrese el id del empleado: ");
		int id = in.nextInt(); in.nextLine();
		
		Empleado emp = EmpleadoDAO.get(id);
		
		if (emp == null) {
			System.out.println("\nEl empleado no existe.");
			return;
		}
		
		System.out.print("\nIngrese el nombre del empleado:" + emp.getNombre() + ": ");
		String nombre = in.nextLine();
		if (!"".equals(nombre.trim())) { emp.setNombre(nombre); }
		
		System.out.print("\nIngrese el apellido del empleado:" + emp.getApellido() + ": ");
		String apellido = in.nextLine();
		if (!"".equals(apellido.trim())) { emp.setApellido(apellido); }
		
		System.out.print("\nIngrese el DI del empleado:" + emp.getDi() + ": ");
		String di = in.nextLine();
		if (!"".equals(di.trim())) { emp.setDi(di); }
		
		boolean ok = false;
		do {
			System.out.print("\nIngrese la fecha de nacimiento del empleado (YYYY-MM-DD):" + DateUtil.format(emp.getFechaNacimiento()) + ": ");
			try {
				String fechaNacimiento = in.nextLine();
				if (!"".equals(fechaNacimiento.trim())) { emp.setFechaNacimiento(DateUtil.parse(fechaNacimiento)); }
				ok = true;
			} catch (Exception e) {
				System.out.println("\n" + e.getMessage());
			}
		} while (!ok);
		
		System.out.print("\nIngrese el departamento del empleado:" + emp.getDepartamento() + ": ");
		String departamento = in.nextLine();
		if (!"".equals(departamento.trim())) { emp.setDepartamento(departamento); }
		
		//Date fechaContratacion = null;
		ok = false;
		do {
			System.out.print("\nIngrese la fecha de contratación del empleado (YYYY-MM-DD):" + DateUtil.format(emp.getFechaContratacion()) + ": ");
			try {
				String fechaContratacion = in.nextLine();
				if (!"".equals(fechaContratacion.trim())) { emp.setFechaContratacion(DateUtil.parse(fechaContratacion)); }
				ok = true;
			} catch (Exception e) {
				System.out.println("\n" + e.getMessage());
			}
		} while (!ok);
		
		System.out.print("\nIngrese el salario del empleado:" + emp.getSalario() + ": ");
		String salario = in.nextLine();
		if (!"".equals(salario.trim())) { emp.setSalario(BigDecimal.valueOf(Long.parseLong(salario))); }
		
		int success = EmpleadoDAO.update(emp);
		
		if (success < 1) {
			System.out.println("\nEl empleado no pudo ser actualizado.");
			return;
		}
		
		System.out.println("\nEmpleado actualizado satisfactoriamente.");
		
	}
}
