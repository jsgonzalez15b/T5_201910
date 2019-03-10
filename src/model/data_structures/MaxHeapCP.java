package model.data_structures;
import java.lang.*;
/**
 *Heap de prioridad siempre ordenado de mayor a menor, mediante una implementación de una arreglo variable generica.
 */
public class MaxHeapCP <T extends Comparable<T>>
{
  //Atributos

	/**
	 * Lista generica
	 */
	public ArregloDinamico<T> arreglo;
	
	/**
	 * numero de elementos en Heap
	 */
	public int cantidad;
	
 	/**
	 * altura actual del Heap
	 */
	public int generacion;
  
	//Metodo Constructor
	public MaxHeapCP ()
	{
		//Inicializa el maximo nodo como vacio, y la cantidad y altura como 0.
		arreglo= null;
		altura = 0;
		cantidad= 0;
	}
	//Metodos del Heap

	/**
	 * Retorna true si Heap esta vacio
	 * @return true si la Cola esta vacia, false de lo contrario
	 */
	public boolean isEmpty()
	{
		return arreglo==null;
	}
	
	/**
	 * Recalcula la altura actual del Heap cada vez que el metodo sea llamado
	 */
	public int recalcularAltura()
	{
		return (int) ((Math.log(arreglo.length+1) / Math.log(2))-1);
	}
	
	/**
	 * Retorna el numero de elementos contenidos
	 * @return el numero de elemntos contenidos
	 */
	public int size()
	{
		return arreglo.length;
	}
	
	/**
	 * 
	 */
	public void swim(T pElemento)
	{
		
		
		cantidad++;
		recalcularAltura();
	}

	/**
	 * 
	 */
	public T sift()
	{

		
		cantidad--;
		recalcularAltura();

	}

	public Iterador<T> iterator() {
		return new Iterador<T>(primero);
	}

  
}