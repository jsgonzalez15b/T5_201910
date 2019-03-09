package controller;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;

public class Controller<T extends Comparable<T>> {

	/* Instancia del Modelo del mundo */
	private static IArregloDinamico modelo = new ArregloDinamico(7);
	
	/**
	 * Requerimiento crear Arreglo Dinamico
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public static void crearModelo(int capacidad)
	{
		modelo = new ArregloDinamico(capacidad);
	}
	
	/**
	 * Requerimiento agregar elemento
	 * @param dato Dato a agregar al final del arreglo
	 */
	public void agregar(T dato)
	{
		modelo.agregar(dato);
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public T buscar(T dato)
	{
		return (T) modelo.buscar(dato);
	}
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public T eliminar(T dato)
	{
		return (T)modelo.eliminar(dato);
	}
	
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el arreglo 
	 * @return numero de elementos presentes en el arreglo
	 */
	public static int darTamano()
	{
		return modelo.darTamano();
	}
	
}
