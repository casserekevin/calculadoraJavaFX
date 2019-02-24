package application.validator;

import java.math.BigDecimal;

public class CalculatorFunctionValidator {

	//Tamanho da string do número
	private final int SIZE_NUMBER = 12;
	
	private boolean operationButtonPressed = false;
	
	public boolean isLessThanLimit(String text){
		if(text.length() >= SIZE_NUMBER){
			return false;
		}
		
		return true;
	}
	
	public boolean isEqualLimit(String text){
		if(text.length() == SIZE_NUMBER){
			return true;
		}
		
		return false;
	}
	
	public boolean hasDot(String number){
		if(number.contains(".")){
			return true;
		}
		
		return false;
	}
	
	public boolean hasDot(BigDecimal number){
		if(number.toPlainString().contains(".")){
			return true;
		}
		
		return false;
	}
	
	public boolean isNegative(String number){
		if(number.contains("-")){
			return true;
		}
		return false;
	}
	
	public boolean isOperationButtonPressed() {
		return operationButtonPressed;
	}

	public void setOperationButtonPressed(boolean operationButtonPressed) {
		this.operationButtonPressed = operationButtonPressed;
	}
	
	public int getSizeNumber(){
		return SIZE_NUMBER;
	}
}
