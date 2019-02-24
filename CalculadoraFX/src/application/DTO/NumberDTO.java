package application.DTO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import application.model.CalculatorFunctions;
import application.validator.CalculatorFunctionValidator;

public class NumberDTO {
	
	private static int INTEGER = 1;
	private static int DECIMAL = 2;
	
	private BigDecimal number1;
	private BigDecimal number2;
	private int operation;
	private String result;
	private int roundingNumber;
	private int type;
	private int numberOfDigits;
	
	CalculatorFunctionValidator functionValidator = new CalculatorFunctionValidator();

	public NumberDTO(BigDecimal number1, BigDecimal number2, int operation, String result, int roundingNumber){
		this.number1 = number1;
		this.number2 = number2;
		this.operation = operation;
		this.result = result;
		
		if(!functionValidator.hasDot(result)){
			this.type = INTEGER;
			this.roundingNumber = roundingNumber;
			if(functionValidator.isNegative(result)){
				this.numberOfDigits = result.length() - 1;								
			}
			else{
				this.numberOfDigits = result.length();				
			}
		}
		else{
			this.type = DECIMAL;
			this.roundingNumber = roundingNumber - 1;
			if(functionValidator.isNegative(result)){
				this.numberOfDigits = result.length() - 2;								
			}
			else{
				this.numberOfDigits = result.length() - 1;				
			}
		}
	}
	
	public String format(){
		String formated = null;
		if(type == DECIMAL && numberOfDigits > roundingNumber){
			switch (operation) {
				case CalculatorFunctions.SOMA:
						formated = number1.add(number2, new MathContext(roundingNumber, RoundingMode.HALF_EVEN)).toPlainString();
					break;
				case CalculatorFunctions.SUBTRA플O:
						formated = number1.subtract(number2, new MathContext(roundingNumber, RoundingMode.HALF_EVEN)).toPlainString();
					break;
				case CalculatorFunctions.MULTIPLICA플O:
						formated = number1.multiply(number2, new MathContext(roundingNumber, RoundingMode.HALF_EVEN)).toPlainString();
					break;
				case CalculatorFunctions.DIVISAO:
						formated = number1.divide(number2, new MathContext(roundingNumber, RoundingMode.HALF_EVEN)).toPlainString();
					break;
			}
			System.out.println(result + " D");	
			System.out.println(formated);	
			
		}
		else if(type == INTEGER && numberOfDigits > roundingNumber){
			switch (operation) {
			case CalculatorFunctions.SOMA:
					formated = number1.add(number2, new MathContext(roundingNumber, RoundingMode.HALF_EVEN)).toPlainString();
				break;
			case CalculatorFunctions.SUBTRA플O:
					formated = number1.subtract(number2, new MathContext(roundingNumber, RoundingMode.HALF_EVEN)).toPlainString();
				break;
			case CalculatorFunctions.MULTIPLICA플O:
					formated = number1.multiply(number2, new MathContext(roundingNumber, RoundingMode.HALF_EVEN)).toPlainString();
				break;
			case CalculatorFunctions.DIVISAO:
					formated = number1.divide(number2, new MathContext(roundingNumber, RoundingMode.HALF_EVEN)).toPlainString();
				break;
		}
			formated = new BigDecimal(formated).divide(new BigDecimal(createNumberOfDivision(formated))).toPlainString() + "e+" + (numberOfDigits - 1);
			System.out.println(result + " I");
			System.out.println(formated);
		}
		else{
			formated = result;
			System.out.println(result + " N");	
			System.out.println(formated);
		}
			
		return formated;
	}
	
	private String createNumberOfDivision(String number){
		String s = "";
		for(int i = 0; i < numberOfDigits - 1; i++){
			s = s + "0";
		}
		return "1" + s;
	}
}
