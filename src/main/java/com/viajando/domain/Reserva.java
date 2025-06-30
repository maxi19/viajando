package com.viajando.domain;

public class Reserva {

	private int id;
	private String identificador;
	private String nombre;
	private String apellido;
	private String sexo;
	private String dni;
	private String tipoServicio;
	private Integer idVuelo;
	private Integer idHotel;
	private Integer idExcursion;
	private Integer idPaquete;
	private int precio;
	private int servicio_id;
	private String butaca;

	// Constructor completo
	public Reserva(int id, String identificador, String nombre, String apellido, String sexo, String dni, String tipoServicio,
			int idVuelo, int idHotel, int idExcursion, int idPaquete, int precio) {
		this.id = id;
		this.identificador = identificador;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.dni = dni;
		this.tipoServicio = tipoServicio;
		this.idVuelo = idVuelo;
		this.idHotel = idHotel;
		this.idExcursion = idExcursion;
		this.idPaquete = idPaquete;
		this.precio = precio;
	}
	
	public String getButaca() {
	    return butaca;
	}

	public void setButaca(String butaca) {
	    this.butaca = butaca;
	}

	// Constructor vacío
	public Reserva() {}

	// Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
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

	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public Integer getIdVuelo() {
		return idVuelo;
	}
	public void setIdVuelo(int idVuelo) {
		this.idVuelo = idVuelo;
	}

	public Integer getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public Integer getIdExcursion() {
		return idExcursion;
	}
	public void setIdExcursion(int idExcursion) {
		this.idExcursion = idExcursion;
	}

	public Integer getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}

	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getServicio_id() {
	    return servicio_id;
	}

	public void setServicio_id(int servicio_id) {
	    this.servicio_id = servicio_id;
	}
}
