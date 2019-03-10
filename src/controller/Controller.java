package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.opencsv.CSVReader;

import model.data_structures.*;
import model.util.Sort;
import model.vo.LocationVO;
import model.vo.VOMovingViolation;
import view.MovingViolationsManagerView;


public class Controller {

	private MovingViolationsManagerView view;

	// TODO Definir las estructuras de datos para cargar las infracciones del periodo definido

	/**
	 * Cola donde se van a cargar los datos de los archivos
	 */
	private IQueue<VOMovingViolation> movingViolationsQueue;

	/**
	 * Pila donde se van a cargar los datos de los archivos
	 */
	private IStack<VOMovingViolation> movingViolationsStack;


	// Muestra obtenida de los datos cargados 
	Comparable<VOMovingViolation> [ ] muestra;

	// Copia de la muestra de datos a ordenar 
	Comparable<VOMovingViolation> [ ] muestraCopia;

	//CONSTRUCTOR
	public Controller() 
	{
		//TODO inicializar las estructuras de datos para la carga de informacion de archivos
		view = new MovingViolationsManagerView();
		movingViolationsQueue = null;
		movingViolationsStack = null;	
	}

	/**
	 * Leer los datos de las infracciones de los archivos. Cada infraccion debe ser Comparable para ser usada en los ordenamientos.
	 * Todas infracciones (MovingViolation) deben almacenarse en una Estructura de Datos (en el mismo orden como estan los archivos)
	 * A partir de estos datos se obtendran muestras para evaluar los algoritmos de ordenamiento
	 * @return numero de infracciones leidas 
	 */
	public int loadMovingViolations() {
		movingViolationsQueue=new Queue<VOMovingViolation>();
		movingViolationsStack= new Stack<VOMovingViolation>();
		int infracciones=0; 
		String[] nombresArchivos=new String[4];
		nombresArchivos[0]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_January_2018.csv";
		nombresArchivos[1]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_February_2018.csv";
		nombresArchivos[2]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_March_2018.csv";
		nombresArchivos[3]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_April_2018.csv";
		CSVReader reader=null;
		for(int i=0; i<nombresArchivos.length;i++){
			try{
				reader=new CSVReader(new FileReader(nombresArchivos[i]));
				String[] linea=reader.readNext();
				linea=reader.readNext();
				while(linea!=null){
					infracciones++;
					movingViolationsStack.push(new VOMovingViolation(Integer.parseInt(linea[0]), linea[2], linea[13], Double.parseDouble(linea[9]), linea[12], linea[15], linea[14], Double.parseDouble(linea[8]), Integer.parseInt(linea[3]) ));
					movingViolationsQueue.enqueue(new VOMovingViolation(Integer.parseInt(linea[0]), linea[2], linea[13], Double.parseDouble(linea[9]), linea[12], linea[15], linea[14], Double.parseDouble(linea[8]), Integer.parseInt(linea[3])));
					linea=reader.readNext();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(reader!=null){
					try{
						reader.close();
					}catch(IOException e){
						e.printStackTrace();	
					}
				}
			}
		}
		return infracciones;
	}




	/**
	 * Generar una muestra aleatoria de tamaNo n de los datos leidos.
	 * Los datos de la muestra se obtienen de las infracciones guardadas en la Estructura de Datos.
	 * @param n tamaNo de la muestra, n > 0
	 * @return muestra generada
	 */
	public Comparable<VOMovingViolation>[ ] generarMuestra( int n )
	{
		int contador=1; 
		muestra = new Comparable[n];
		for(int i=0; i<n; i++) {
			VOMovingViolation a=movingViolationsQueue.darElemento((int)(Math.random()*movingViolationsQueue.size()));
			muestra[i]=a;
			System.out.println(contador+"");
			contador++; 
		}
		// TODO Llenar la muestra aleatoria con los datos guardados en la estructura de datos
		MaxColaPrioridad<VOMovingViolation> cola=new MaxColaPrioridad<>();
		long starTime=System.currentTimeMillis();
		for(int i=0; i<muestra.length; i++) {
			cola.agregar((VOMovingViolation) muestra[i]);
		}
		long enTime=System.currentTimeMillis()-starTime; 
		return muestra;
	}
	
	public void darTiempoAgregarMaxCola( int N) {
		MaxColaPrioridad<VOMovingViolation> cola=new MaxColaPrioridad<>();
		Iterador<VOMovingViolation> iter=(Iterador<VOMovingViolation>) movingViolationsStack.iterator();
		VOMovingViolation actual=iter.next();
		long starTime=System.currentTimeMillis();
		for(int i=0; i<N&&iter.hasNext(); i++) {
			cola.agregar(actual);
			actual=iter.next();
		}
		long enTime=System.currentTimeMillis()-starTime;
		System.out.println(enTime+" milisegundos");
		
		long starTime2=System.currentTimeMillis(); 
		cola.delMax(); 
		long endTime2=System.currentTimeMillis()-starTime; 
		System.out.println(endTime2 +"milisegundos delmax");
	}

	/**
	 * Generar una copia de una muestra. Se genera un nuevo arreglo con los mismos elementos.
	 * @param muestra - datos de la muestra original
	 * @return copia de la muestra
	 */
	public Comparable<VOMovingViolation> [ ] obtenerCopia( Comparable<VOMovingViolation> [ ] muestra)
	{
		Comparable<VOMovingViolation> [ ] copia = new Comparable[ muestra.length ]; 
		for ( int i = 0; i < muestra.length; i++)
		{    copia[i] = muestra[i];    }
		return copia;
	}

	/**
	 * Ordenar datos aplicando el algoritmo ShellSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public void ordenarShellSort( Comparable<VOMovingViolation>[ ] datos ) {

		Sort.ordenarShellSort(datos);
	}

	/**
	 * Ordenar datos aplicando el algoritmo MergeSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public void ordenarMergeSort( Comparable<VOMovingViolation>[ ] datos ) {

		Sort.ordenarMergeSort(datos);
	}

	/**
	 * Ordenar datos aplicando el algoritmo QuickSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public void ordenarQuickSort( Comparable<VOMovingViolation>[ ] datos ) {

		Sort.ordenarQuickSort(datos);
	}

	/**
	 * Invertir una muestra de datos (in place).
	 * datos[0] y datos[N-1] se intercambian, datos[1] y datos[N-2] se intercambian, datos[2] y datos[N-3] se intercambian, ...
	 * @param datos - conjunto de datos a invertir (inicio) y conjunto de datos invertidos (final)
	 */
	public void invertirMuestra( Comparable[ ] datos ) {

		int i=0; 
		int j=datos.length-1;
		while(j>i) {
			Comparable<VOMovingViolation> inicio=datos[i];
			Comparable<VOMovingViolation> fin=datos[j];
			datos[i]=fin; 
			datos[j]=inicio;
			i++;
			j--;
		}
	}

	public void run() {
		long startTime;
		long endTime;
		long duration;

		int nDatos = 0;
		int nMuestra = 0;

		Scanner sc = new Scanner(System.in);
		boolean fin = false;

		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 1:
				// Cargar infracciones
				nDatos = this.loadMovingViolations();
				view.printMensage("Numero infracciones cargadas:" + nDatos);
				break;

			case 2:
				// Generar muestra de infracciones a ordenar
				view.printMensage("Dar tamaNo de la muestra: ");
				nMuestra = sc.nextInt();
				muestra = this.generarMuestra( nMuestra );
				view.printMensage("Muestra generada");
				break;

			case 3:
				// Mostrar los datos de la muestra actual (original)
				if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
				{    
					view.printDatosMuestra( nMuestra, muestra);
				}
				else
				{
					view.printMensage("Muestra invalida");
				}
				break;

			case 4:
				// Aplicar ShellSort a una copia de la muestra
				view.printMensage("Ingrese tama�o a probar");
				int datos=sc.nextInt(); 
				darTiempoAgregarMaxCola(datos);
				break;

			case 5:
				// Aplicar MergeSort a una copia de la muestra
				if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
				{
					muestraCopia = this.obtenerCopia(muestra);
					startTime = System.currentTimeMillis();
					this.ordenarMergeSort(muestraCopia);
					endTime = System.currentTimeMillis();
					duration = endTime - startTime;
					view.printMensage("Ordenamiento generado en una copia de la muestra");
					view.printMensage("Tiempo de ordenamiento MergeSort: " + duration + " milisegundos");
				}
				else
				{
					view.printMensage("Muestra invalida");
				}
				break;

			case 6:
				// Aplicar QuickSort a una copia de la muestra
				if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
				{
					muestraCopia = this.obtenerCopia(muestra);
					startTime = System.currentTimeMillis();
					this.ordenarQuickSort(muestraCopia);
					endTime = System.currentTimeMillis();
					duration = endTime - startTime;
					view.printMensage("Ordenamiento generado en una copia de la muestra");
					view.printMensage("Tiempo de ordenamiento QuickSort: " + duration + " milisegundos");
				}
				else
				{
					view.printMensage("Muestra invalida");
				}
				break;

			case 7:
				// Mostrar los datos de la muestra ordenada (muestra copia)
				if ( nMuestra > 0 && muestraCopia != null && muestraCopia.length == nMuestra )
				{    view.printDatosMuestra( nMuestra, muestraCopia);    }
				else
				{
					view.printMensage("Muestra Ordenada invalida");
				}
				break;

			case 8:	
				// Una muestra ordenada se convierte en la muestra a ordenar
				if ( nMuestra > 0 && muestraCopia != null && muestraCopia.length == nMuestra )
				{    
					muestra = muestraCopia;
					view.printMensage("La muestra ordenada (copia) es ahora la muestra de datos a ordenar");
				}
				break;

			case 9:
				// Invertir la muestra a ordenar
				if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
				{    
					this.invertirMuestra(muestra);
					view.printMensage("La muestra de datos a ordenar fue invertida");
				}
				else
				{
					view.printMensage("Muestra invalida");
				}

				break;

			case 10:	
				fin=true;
				sc.close();
				break;
			}
		}
	}
	
	
	public 	MaxColaPrioridad<LocationVO> CrearMaxColaP (LocalDateTime fInicial, LocalDateTime fFinal){
		MaxColaPrioridad<LocationVO> retornar= new MaxColaPrioridad<>();
		int mesinicio=fInicial.getMonthValue();
		int mesfin=fFinal.getMonthValue();
		int diaInicio=fInicial.getDayOfMonth(); 
		int diaFin=fFinal.getDayOfMonth();
		ArrayList<Integer> agregadas= new ArrayList<>();
		Iterador<VOMovingViolation> iter= (Iterador<VOMovingViolation>) movingViolationsStack.iterator();
		VOMovingViolation actual= iter.next();
		while (iter.hasNext()) {
			int mesactual=Integer.parseInt(actual.getTicketIssueDate().split("T")[0].split("-")[1]);
			int diaActual= Integer.parseInt(actual.getTicketIssueDate().split("T")[0].split("-")[2]);
			if(mesactual>=mesinicio&&diaActual>=diaInicio&&mesactual<=mesfin&&diaActual<=diaFin) {
				if(!verificar(agregadas,actual.getAdressId())) {
					int id=actual.getAdressId();
					String locacion=actual.getLocation();
					Iterador<VOMovingViolation> iter2= (Iterador<VOMovingViolation>) movingViolationsStack.iterator();
					VOMovingViolation actual2=iter.next(); 
					int registros=0; 
					while(iter2.hasNext()) {
						if(actual2.getAdressId()==id) {
							registros++; 
						}
					}
				LocationVO agregar= new LocationVO(id, locacion, registros);
				agregadas.add(id);
				retornar.agregar(agregar);
				}
			}
			actual=iter.next(); 
		}
		return retornar; 
	}
	
	public boolean verificar(ArrayList<Integer> arreglo, int num) {
		boolean retornar=false; 
		for(int i=0; i<arreglo.size(); i++) {
			if(arreglo.get(i)==num) {
				retornar=true; 
			}
		}
		return retornar; 
	}

}
