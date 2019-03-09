package model.vo;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations {

	private int Id;
	private String location; 
	private String ticketIssueDate;
	private int totalpaid; 
	private String accidenIndicator; 
	private String description; 
	private String violationcode; 
	private double fineAMT;


	public VOMovingViolations(int pId, String pLocation, String pTicketIssueDate, int pTotoalpaid, String pAccidentIndicator, String pDescription, String pViolationCode, double pfineAMT){
		Id=pId;
		location=pLocation;
		ticketIssueDate=pTicketIssueDate;
		totalpaid=pTotoalpaid;
		accidenIndicator=pAccidentIndicator;
		description=pDescription; 		
		violationcode=pViolationCode; 
		fineAMT=pfineAMT;
	}
	
	
	/**
	 * @return id - Identificador �nico de la infracci�n
	 */
	public int objectId() {
		// TODO Auto-generated method stub
		return Id;
	}	
	
	
	/**
	 * @return location - Direcci�n en formato de texto.
	 */
	public String getLocation() {
		// TODO Auto-generated method stub
		return location; 
	}

	/**
	 * @return date - Fecha cuando se puso la infracci�n .
	 */
	public String getTicketIssueDate() {
		// TODO Auto-generated method stub
		return ticketIssueDate;
	}
	
	/**
	 * @return totalPaid - Cuanto dinero efectivamente pag� el que recibi� la infracci�n en USD.
	 */
	public int getTotalPaid() {
		// TODO Auto-generated method stub
		return totalpaid;
	}
	
	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public String  getAccidentIndicator() {
		// TODO Auto-generated method stub
		return accidenIndicator; 
	}
		
	/**
	 * @return description - Descripci�n textual de la infracci�n.
	 */
	public String  getViolationDescription() {
		// TODO Auto-generated method stub
		return description;
	}
	public String getViolationCode(){
		return violationcode;
	}
	
	public double getFINEAMT() {
		return fineAMT;
	}
}

