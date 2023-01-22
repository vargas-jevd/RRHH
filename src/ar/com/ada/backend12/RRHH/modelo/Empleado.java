package ar.com.ada.backend12.RRHH.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Empleado {
	private Integer id;
	private String nombre;
	private String apellido;
	private String di;
	private Date fechaNacimiento;
	private String departamento;
	private Date fechaContratacion;
	private BigDecimal salario;
	
	public Empleado() {
		super();
	}
	
	public Empleado(Integer id, String nombre, String apellido, String di, Date fechaNacimiento, String departamento,
			Date fechaContratacion, BigDecimal salario) {
		this();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.di = di;
		this.fechaNacimiento = fechaNacimiento;
		this.departamento = departamento;
		this.fechaContratacion = fechaContratacion;
		this.salario = salario;
	}
	
	public Empleado(String nombre, String apellido, String di, Date fechaNacimiento, String departamento,
			Date fechaContratacion, BigDecimal salario) {
		this(null, nombre, apellido, di, fechaNacimiento, departamento, fechaContratacion, salario);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDi() {
		return di;
	}

	public void setDi(String di) {
		this.di = di;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Date getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, departamento, di, fechaContratacion, fechaNacimiento, id, nombre, salario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(departamento, other.departamento)
				&& Objects.equals(di, other.di) && Objects.equals(fechaContratacion, other.fechaContratacion)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(salario, other.salario);
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", di=" + di
				+ ", fechaNacimiento=" + fechaNacimiento + ", departamento=" + departamento + ", fechaContratacion="
				+ fechaContratacion + ", salario=" + salario + "]";
	}
}
