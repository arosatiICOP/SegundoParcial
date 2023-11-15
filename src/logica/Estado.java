package logica;

public interface Estado {
	
	public void mostrarEstado(Mesa m) throws ExceptionEstado;
	public void liberar(Mesa m) throws ExceptionEstado;
	public void reservar(Mesa m) throws ExceptionEstado;
	public void ocupar(Mesa m) throws ExceptionEstado;
}