package application.controller;

import application.model.CalculatorFunctions;
import application.validator.CalculatorFunctionValidator;
import application.validator.CalculatorWindowValidator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CalculatorController {

	//stage
	private Stage stage;

	//labels
	@FXML
	private Label lbl_superior;
	@FXML
	private Label lbl_inferior;

	//botões
	@FXML
	private Button btn_plusMinus;
	@FXML
	private Button btn_clear;
	@FXML
	private Button btn_backspace;
	@FXML
	private Button btn_division;
	@FXML
	private Button btn_seven;
	@FXML
	private Button btn_eight;
	@FXML
	private Button btn_nine;
	@FXML
	private Button btn_times;
	@FXML
	private Button btn_four;
	@FXML
	private Button btn_five;
	@FXML
	private Button btn_six;
	@FXML
	private Button btn_minus;
	@FXML
	private Button btn_one;
	@FXML
	private Button btn_two;
	@FXML
	private Button btn_three;
	@FXML
	private Button btn_plus;
	@FXML
	private Button btn_zero;
	@FXML
	private Button btn_dot;
	@FXML
	private Button btn_equal;
	
	//imagens
	private ImageView plus_minus_small = new ImageView(new Image(getClass().getResourceAsStream("../images/plus_minus_small.png")));
	private ImageView plus_minus_medium = new ImageView(new Image(getClass().getResourceAsStream("../images/plus_minus_medium.png")));
	private ImageView plus_minus_big = new ImageView(new Image(getClass().getResourceAsStream("../images/plus_minus_big.png")));
	private ImageView backspace_small = new ImageView(new Image(getClass().getResourceAsStream("../images/backspace_small.png")));
	private ImageView backspace_medium = new ImageView(new Image(getClass().getResourceAsStream("../images/backspace_medium.png")));
	private ImageView backspace_big = new ImageView(new Image(getClass().getResourceAsStream("../images/backspace_big.png")));
	
	
	//Validator
	private CalculatorWindowValidator windowValidator;
	private CalculatorFunctionValidator functionValidator = new CalculatorFunctionValidator();
	
	//Tamanhos da janela
	private int width;
	private int height;
	
	//Funções de calcular
	private CalculatorFunctions calculator = new CalculatorFunctions();
	
	private int numberOfClicksInEqualButton = 0;

	public void initStage(Stage stage) {
		this.stage = stage;
		windowValidator = new CalculatorWindowValidator((int)this.stage.getHeight());
		
		this.stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			width = newVal.intValue();
			
			changeFont();
		});

		this.stage.heightProperty().addListener((obs, oldVal, newVal) -> {
			height = newVal.intValue();
			
			changeFont();
		});
	}
	
	private void changeFont(){
		if(windowValidator.isInFirstPart(height, width)){
			lbl_inferior.setStyle("-fx-font-size: 15px;");
			lbl_superior.setStyle("-fx-padding: 0 0 0 2;");
			lbl_superior.setStyle("-fx-font-size: 8.5px;");
			changeFontButtons(12, plus_minus_small, backspace_small);
			
		}
		else if(windowValidator.isInSecondPart(height, width)){
			lbl_inferior.setStyle("-fx-font-size: 22px;");
			lbl_superior.setStyle("-fx-padding: 0 0 0 20;");
			lbl_superior.setStyle("-fx-font-size: 12px;");
			changeFontButtons(16, plus_minus_medium, backspace_medium);
		}
		else{
			lbl_inferior.setStyle("-fx-font-size: 30px;");
			lbl_superior.setStyle("-fx-padding: 0 0 0 30;");
			lbl_superior.setStyle("-fx-font-size: 15.5px;");
			changeFontButtons(22, plus_minus_big, backspace_big);
		}
	}
	
	private void changeFontButtons(int size, ImageView plusMinus, ImageView backspace){
		btn_plusMinus.setGraphic(plusMinus);	
		btn_clear.setStyle("-fx-font-size: " + size + "px;");
		btn_backspace.setGraphic(backspace);
		btn_division.setStyle("-fx-font-size: " + size + "px;");
		btn_seven.setStyle("-fx-font-size: " + size + "px;");
		btn_eight.setStyle("-fx-font-size: " + size + "px;");
		btn_nine.setStyle("-fx-font-size: " + size + "px;");
		btn_times.setStyle("-fx-font-size: " + size + "px;");
		btn_four.setStyle("-fx-font-size: " + size + "px;");
		btn_five.setStyle("-fx-font-size: " + size + "px;");
		btn_six.setStyle("-fx-font-size: " + size + "px;");
		btn_minus.setStyle("-fx-font-size: " + size + "px;");
		btn_one.setStyle("-fx-font-size: " + size + "px;");
		btn_two.setStyle("-fx-font-size: " + size + "px;");
		btn_three.setStyle("-fx-font-size: " + size + "px;");
		btn_plus.setStyle("-fx-font-size: " + size + "px;");
		btn_zero.setStyle("-fx-font-size: " + size + "px;");
		btn_dot.setStyle("-fx-font-size: " + size + "px;");
		btn_equal.setStyle("-fx-font-size: " + size + "px;");
	}

	@FXML
	private void btn_plusMinusOnAction(Event e) {
		lbl_inferior.setText(calculator.switchSignal(lbl_inferior.getText()));
	}
	@FXML
	private void btn_clearOnAction(Event e) {
		calculator.setNumber1(null);
		calculator.setNumber2(null);
		calculator.setOperation(CalculatorFunctions.NONE);
		
		numberOfClicksInEqualButton = 0;
		functionValidator.setOperationButtonPressed(false);
		
		
		lbl_inferior.setText("0");
		lbl_superior.setText("");
	}
	@FXML
	private void btn_backspaceOnAction(Event e) {
		if(lbl_inferior.getText().length() > 1){
			lbl_inferior.setText(lbl_inferior.getText().substring(0, lbl_inferior.getText().length() - 1));
		}
		else{
			lbl_inferior.setText("0");
		}
	}
	@FXML
	private void btn_divisionOnAction(Event e) {
		if(!functionValidator.isOperationButtonPressed()){
			numberOfClicksInEqualButton = 0;
			
			calculator.setNumber1(lbl_inferior.getText());
			lbl_inferior.setText("");
			
			calculator.setOperation(CalculatorFunctions.DIVISAO);
			
			functionValidator.setOperationButtonPressed(true);			
			
			setValueInSuperiorLabel();
		}
	}
	@FXML
	private void btn_sevenOnAction(Event e) {
		setNumberInInferiorLabel('7');
		functionValidator.setOperationButtonPressed(false);
	}
	@FXML
	private void btn_eightOnAction(Event e) {
		setNumberInInferiorLabel('8');
		functionValidator.setOperationButtonPressed(false);
	}
	@FXML
	private void btn_nineOnAction(Event e) {
		setNumberInInferiorLabel('9');
		functionValidator.setOperationButtonPressed(false);
	}
	@FXML
	private void btn_timesOnAction(Event e) {
		if(!functionValidator.isOperationButtonPressed()){
		numberOfClicksInEqualButton = 0;
		
		calculator.setNumber1(lbl_inferior.getText());
		lbl_inferior.setText("");
		
		calculator.setOperation(CalculatorFunctions.MULTIPLICAÇÃO);
		
		functionValidator.setOperationButtonPressed(true);
		
		setValueInSuperiorLabel();
		}
	}
	@FXML
	private void btn_fourOnAction(Event e) {
		setNumberInInferiorLabel('4');
		functionValidator.setOperationButtonPressed(false);
	}
	@FXML
	private void btn_fiveOnAction(Event e) {
		setNumberInInferiorLabel('5');
		functionValidator.setOperationButtonPressed(false);
	}
	@FXML
	private void btn_sixOnAction(Event e) {
		setNumberInInferiorLabel('6');
		functionValidator.setOperationButtonPressed(false);
	}
	@FXML
	private void btn_minusOnAction(Event e) {
		if(!functionValidator.isOperationButtonPressed()){
			numberOfClicksInEqualButton = 0;
			
			calculator.setNumber1(lbl_inferior.getText());
			lbl_inferior.setText("");
			
			calculator.setOperation(CalculatorFunctions.SUBTRAÇÃO);
			
			functionValidator.setOperationButtonPressed(true);
			
			setValueInSuperiorLabel();
		}
	}
	@FXML
	private void btn_oneOnAction(Event e) {
		setNumberInInferiorLabel('1');
		functionValidator.setOperationButtonPressed(false);
	}
	@FXML
	private void btn_twoOnAction(Event e) {
		setNumberInInferiorLabel('2');
		functionValidator.setOperationButtonPressed(false);
	}
	@FXML
	private void btn_threeOnAction(Event e) {
		setNumberInInferiorLabel('3');
		functionValidator.setOperationButtonPressed(false);
	}
	@FXML
	private void btn_plusOnAction(Event e) {
		if(!functionValidator.isOperationButtonPressed()){
			numberOfClicksInEqualButton = 0;
			
			calculator.setNumber1(lbl_inferior.getText());
			lbl_inferior.setText("");
			
			calculator.setOperation(CalculatorFunctions.SOMA);
			
			functionValidator.setOperationButtonPressed(true);
			
			setValueInSuperiorLabel();
		}
	}
	@FXML
	private void btn_zeroOnAction(Event e) {
		setNumberInInferiorLabel('0');
		functionValidator.setOperationButtonPressed(false);
	}
	@FXML
	private void btn_dotOnAction(Event e) {
		if(!functionValidator.hasDot(lbl_inferior.getText())){
			setNumberInInferiorLabel('.');
			functionValidator.setOperationButtonPressed(false);			
		}
	}
	@FXML
	private void btn_equalOnAction(Event e) {
		if(calculator.isOperationSetted()){
			try {
				++numberOfClicksInEqualButton;
				
				if(numberOfClicksInEqualButton >= 2){
					calculator.setNumber1(lbl_inferior.getText());
					
					setValueInSuperiorLabel();
					lbl_superior.setText(lbl_superior.getText() + " " + calculator.getNumber2Formated());					
				}
				else{
					calculator.setNumber2(lbl_inferior.getText());
					
					lbl_superior.setText(lbl_superior.getText() + " " + calculator.getNumber2Formated());
				}				
			} catch (NumberFormatException ex) {
				--numberOfClicksInEqualButton;
			}
			
			if(calculator.getNumber1() != null && calculator.getNumber2() != null){
				try {
					lbl_inferior.setText(calculator.calculate());
					
				} catch (ArithmeticException ex) {
					Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), ae -> {
						lbl_inferior.setText("0");
						btn_clearOnAction(e);
					}));
					lbl_inferior.setText("ERROR");
					timeline.play();
					
					ex.printStackTrace();
				}
			}			
		}
			
	}
	private void setNumberInInferiorLabel(char c) {
		if(functionValidator.isLessThanLimit(lbl_inferior.getText())){
			if(lbl_inferior.getText().equals("0") && c != '.'){
				lbl_inferior.setText("");
			}		
			lbl_inferior.setText(lbl_inferior.getText() + c);
		}
	}
	
	private void setValueInSuperiorLabel(){
		lbl_superior.setText(calculator.getNumber1Formated() + " " + calculator.getStringOperation());
	}
	

}
