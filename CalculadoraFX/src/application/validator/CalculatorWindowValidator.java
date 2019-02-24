package application.validator;

import java.awt.Dimension;
import java.awt.Toolkit;

public class CalculatorWindowValidator {

	//limites dos cortes da janela
	private final int FIRST_CUT_HEIGHT;
	private final int SECOND_CUT_HEIGHT;	
	private final int FIRST_CUT_WIDTH = 250;
	private final int SECOND_CUT_WIDTH = 330;
	
	
	public CalculatorWindowValidator(int heightSoftware){
		//calcula altura dos cortes
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		double result = d.getHeight() - heightSoftware;
		int parte = (int)(result / 3);
		FIRST_CUT_HEIGHT = heightSoftware + parte;
		SECOND_CUT_HEIGHT = heightSoftware + parte * 2;
	}
	
	public boolean isInFirstPart(int height, int width){
		if(height < FIRST_CUT_HEIGHT || width < FIRST_CUT_WIDTH){
			return true;
		}
		
		return false;
	}
	
	public boolean isInSecondPart(int height, int width){
		if(height < SECOND_CUT_HEIGHT || width < SECOND_CUT_WIDTH){
			return true;
		}
		
		return false;
	}
}
