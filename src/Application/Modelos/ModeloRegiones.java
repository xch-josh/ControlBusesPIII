package Application.Modelos;

public class ModeloRegiones {
	private int idRegion;
	private String nombreRegion, recorrido;

	public ModeloRegiones(){}
	
	public ModeloRegiones(int idRegion, String nombreRegion, String recorrido) {
		this.idRegion = idRegion;
		this.nombreRegion = nombreRegion;
		this.recorrido = recorrido;
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getNombreRegion() {
		return nombreRegion;
	}

	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

	public String getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(String recorrido) {
		this.recorrido = recorrido;
	}
}
