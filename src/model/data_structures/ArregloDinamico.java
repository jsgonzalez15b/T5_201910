package model.data_structures;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico<T> implements IArregloDinamico {
		/**
		 * Capacidad maxima del arreglo
		 */
        private int tamanoMax;
		/**
		 * Numero de elementos en el arreglo (de forma compacta desde la posicion 0)
		 */
        private int tamanoAct;
        /**
         * Arreglo de elementos de tamaNo maximo
         */
        private T elementos[ ];

        /**
         * Construir un arreglo con la capacidad maxima inicial.
         * @param max Capacidad maxima inicial
         */
		public ArregloDinamico( int max )
        {
               elementos = new T[max];
               tamanoMax = max;
               tamanoAct = 0;
        }
        
		public void agregar( String dato )
        {
               if ( tamanoAct == tamanoMax )
               {  // caso de arreglo lleno (aumentar tamaNo)
                    tamanoMax = 2 * tamanoMax;
                    T [ ] copia = elementos;
                    elementos = new T[tamanoMax];
                    for ( int i = 0; i < tamanoAct; i++)
                    {
                     	 elementos[i] = copia[i];
                    } 
            	    System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
               }	
               elementos[tamanoAct] = dato;
               tamanoAct++;
       }

		public int darTamano() {
			// TODO implementar
			return tamanoAct;
		}

		public T darElemento(int i) {
			// TODO implementar
			T buscado=null;   
			for(int j=0; j<tamanoAct && buscado==null;j++) {
				if(j==i) {
					buscado=elementos[j];
				}
			}
			return buscado;
		}

		public T buscar(T dato) {
			// TODO implementar
			// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
			int inicio=0;
			int fin=elementos.length-1;
			T buscado=null; 
			while (inicio<=fin && buscado==null) {
				int mitad=(inicio+fin)/2;
				if(dato.compareTo(elementos[mitad])==0){
					buscado=elementos[mitad];
				}else if(dato.compareTo(elementos[mitad])>0) {
					inicio=mitad+1;
				}else {
					fin=mitad-1;
				}
			}
			return buscado; 
		}

		public T eliminar(T dato) {
			// TODO implementar
			// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
			T aEliminar=null;
			for (int i=0; i<elementos.length; i++) {
				if(elementos[i].compareTo(dato)==0) {
					aEliminar=elementos[i];
					for(int j=i; j<elementos.length-1; j++) {
						elementos[j]=elementos[j+1];
					}
				}
			}
			tamanoAct--;
			return aEliminar;
		}
}