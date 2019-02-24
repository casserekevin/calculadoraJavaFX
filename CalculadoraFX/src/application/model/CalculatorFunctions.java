package application.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import application.DTO.NumberDTO;
import application.validator.CalculatorFunctionValidator;

public class CalculatorFunctions {

	public static final int NONE = 0;
	public static final int SOMA = 1;
	public static final int SUBTRA플O = 2;
	public static final int MULTIPLICA플O = 3;
	public static final int DIVISAO = 4;
	
	private BigDecimal number1;
	private BigDecimal number2;
	private BigDecimal result;
	
	private String number1Formated;
	private String number2Formated;
	
	private int operation;
	
	CalculatorFunctionValidator functionValidator = new CalculatorFunctionValidator();
	
	public String getNumber1() {
		String number = null;
		try {
			number = number1.toPlainString();
		} catch (NullPointerException e) {
			return number;
		}
		return number;
	}
	public void setNumber1(String number1) {
		if(number1 == null){
			this.number1 = null;
		}
		else{
			if(number1.contains("e+")){
				this.number1 = new BigDecimal(realNumber(number1)).multiply(new BigDecimal(createNumberOfMultiplication(number1))).stripTrailingZeros();
				this.number1Formated = number1;
			}
			else{
				this.number1 = new BigDecimal(number1);
				this.number1Formated = number1;
			}
		}
	}
	public String getNumber2() {
		String number = null;
		try {
			number = number2.toPlainString();
		} catch (NullPointerException e) {
			return number;
		}
		return number;
	}
	public void setNumber2(String number2) {
		if(number2 == null){
			this.number2 = null;
		}
		else{
			if(number2.contains("e+")){
				this.number2 = new BigDecimal(realNumber(number2)).multiply(new BigDecimal(createNumberOfMultiplication(number2))).stripTrailingZeros();
				this.number2Formated = number2;
			}
			else{
				this.number2 = new BigDecimal(number2);			
				this.number2Formated = number2;
			}
		}
	}
	
	public String getNumber1Formated() {
		return number1Formated;
	}
	public String getNumber2Formated() {
		return number2Formated;
	}
	public int getOperation() {
		return operation;
	}
	
	public char getStringOperation(){
		char op = 0;
		switch (operation) {
			case SOMA:
				op = '+';
				break;
			case SUBTRA플O:
				op = '-';
				break;
			case MULTIPLICA플O:
				op = '*';
				break;
			case DIVISAO:
				op = '/';
				break;
		}
		
		return op;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	
	public boolean isOperationSetted(){
		if(operation != NONE){
			return true;
		}
		
		return false;
	}
	
	public String calculate() throws ArithmeticException{
		if(operation == SOMA){
			result = number1.add(number2, new MathContext(functionValidator.getSizeNumber() * 2, RoundingMode.HALF_EVEN));
		}
		else if(operation == SUBTRA플O){
			result = number1.subtract(number2, new MathContext(functionValidator.getSizeNumber() * 2, RoundingMode.HALF_EVEN));
		}
		else if(operation == MULTIPLICA플O){
			result = number1.multiply(number2, new MathContext(functionValidator.getSizeNumber() * 2, RoundingMode.HALF_EVEN));
		}
		else if(operation == DIVISAO){
			result = number1.divide(number2, new MathContext(functionValidator.getSizeNumber() * 2, RoundingMode.HALF_EVEN));
		}
		
		return new NumberDTO(number1, number2, operation, result.toPlainString(), functionValidator.getSizeNumber()).format();
	}
	
	public String switchSignal(String n){
		if(n.contains("-")){
			n = n.substring(1, n.length());
		}
		else{
			n = "-" + n;
		}
		return n;
	}
	
	private String createNumberOfMultiplication(String number){
		String s = "";
		
		int n = Integer.parseInt(number.substring(number.indexOf('+'), number.length()));
		
		for(int i = 0; i < n; i++){
			s = s + "0";
		}
		
		return 1 + s;
	}
	
	private String realNumber(String number){
		return number.substring(0, number.indexOf('e'));
	}
	
	
}
