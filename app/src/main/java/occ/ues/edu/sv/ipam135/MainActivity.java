package occ.ues.edu.sv.ipam135;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Melvin Jimenez JM17001
 */
public class MainActivity extends AppCompatActivity {

    private TextView _display;
    private String textDisplay="";
    private TextView result;
    private Button on;
    private double resul=0;
    Boolean onOff=true;
    private ImageButton [] botones= new ImageButton[20];
    char root = '\u221A';
    private List<String> content = new ArrayList<>();
    List<String> operationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _display = (TextView) findViewById(R.id.display);
        result= (TextView) findViewById(R.id.txtRESULT);

        on = (Button) findViewById(R.id.btnOnOff);
        /**
         * necesario para habilitar/inhabilita botones. :c
         */

        botones[0]=(ImageButton)findViewById(R.id.btnZero);
        botones[1]=(ImageButton)findViewById(R.id.btnOne);
        botones[2]=(ImageButton)findViewById(R.id.btnTwo);
        botones[3]=(ImageButton)findViewById(R.id.btnThree);
        botones[4]=(ImageButton)findViewById(R.id.btnFour);
        botones[5]=(ImageButton)findViewById(R.id.btnFive);
        botones[6]=(ImageButton)findViewById(R.id.btnSix);
        botones[7]=(ImageButton)findViewById(R.id.btnSeven);
        botones[8]=(ImageButton)findViewById(R.id.btnEight);
        botones[9]=(ImageButton)findViewById(R.id.btnNine);
        botones[10]=(ImageButton)findViewById(R.id.btnPlus);
        botones[11]=(ImageButton)findViewById(R.id.btnMinus);
        botones[12]=(ImageButton)findViewById(R.id.btnDiv);
        botones[13]=(ImageButton)findViewById(R.id.btnX);
        botones[14]=(ImageButton)findViewById(R.id.btnPoint);
        botones[15]=(ImageButton)findViewById(R.id.btnSame);
        botones[16]=(ImageButton)findViewById(R.id.btnDelete);
        botones[17]=(ImageButton)findViewById(R.id.btnPower);
        botones[18]=(ImageButton)findViewById(R.id.btnClear);
        botones[19]=(ImageButton)findViewById(R.id.btnRoot);
        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(false);
        }
    }

    /**
     * manejador de eventos clicks
     *
     * @param v
     */
    public void clickKey(View v){
        if(textDisplay=="sintax Error" || textDisplay=="i" ){
            textDisplay="";
            resul=0;
            content= new ArrayList<>();
        }
        switch (v.getId()){
            case R.id.btnZero:
                textDisplay=textDisplay+0;
                content.add("0");
                break;
            case R.id.btnOne:
                textDisplay=textDisplay+1;
                content.add("1");
                break;
            case R.id.btnTwo:
                textDisplay=textDisplay+2;
                content.add("2");
                break;
            case R.id.btnThree:
                textDisplay=textDisplay+3;
                content.add("3");
                break;
            case R.id.btnFour:
                textDisplay=textDisplay+4;
                content.add("4");
                break;
            case R.id.btnFive:
                textDisplay=textDisplay+5;
                content.add("5");
                break;
            case R.id.btnSix:
                textDisplay=textDisplay+6;
                content.add("6");
                break;
            case R.id.btnSeven:
                textDisplay=textDisplay+7;
                content.add("7");
                break;
            case R.id.btnEight:
                textDisplay=textDisplay+8;
                content.add("8");
                break;
            case R.id.btnNine:
                textDisplay=textDisplay+9;
                content.add("9");
                break;
            case R.id.btnPlus:
                if(resul!=0){
                    textDisplay=resul+"";
                }
                if(verificarFormato()==true){
                    igual();
                    textDisplay=result.getText().toString();
                }
                textDisplay=textDisplay+"+";
                content.add("+");

                break;
            case R.id.btnMinus:
                if(resul!=0){
                    textDisplay=resul+"";
                }
                if(verificarFormato()==true){
                    igual();
                    textDisplay=result.getText().toString();
                }
                textDisplay=textDisplay+"-";
                content.add("-");
                break;
            case R.id.btnDiv:
                if(resul!=0){
                    textDisplay=resul+"";
                }
                if(verificarFormato()==true){
                    igual();
                    textDisplay=result.getText().toString();
                }
                textDisplay=textDisplay+"/";
                content.add("/");
                break;
            case R.id.btnX:
                if(resul!=0){
                    textDisplay=resul+"";
                }
                if(verificarFormato()==true){
                    igual();
                    textDisplay=result.getText().toString();
                }
                textDisplay=textDisplay+"x";
                content.add("*");
                break;
            case R.id.btnPoint:
                textDisplay=textDisplay+".";
                content.add(".");
                break;
            case R.id.btnPower:
                if(resul!=0){
                    textDisplay=resul+"";
                }
                if(verificarFormato()==true){
                    igual();
                    textDisplay=result.getText().toString();
                }
                textDisplay=textDisplay+"^";
                content.add("^");
                break;
            case R.id.btnSame:
               igual();
                break;
            case R.id.btnClear:
                textDisplay="";
                result.setText("");
                content = new ArrayList<>();
                break;
            case R.id.btnRoot:
                textDisplay=textDisplay+root;
                content.add(root+"");
                igualRaiz();
                break;
            case R.id.btnDelete:
                if(textDisplay.length()>0){
                    textDisplay=textDisplay.substring(0, textDisplay.length()-1);
                }
                if(content.size()>0){
                    content.remove(content.size()-1);
                }
                break;
        }
        _display.setText(textDisplay);
    }

    /**
     * ordena el array y almacena un caracter en cada posicion
     * @return array con un elemento en cada posicion.
     */
    public List sortArray(){
        List<String> array= new ArrayList<>();
        String valores="";
        array.add("0");
        for (int i=0;i<content.size();i++){
            if(content.get(i).matches("[-+^*/√]")){
                if(!valores.isEmpty() || !valores.equals("")){
                    array.add(valores);
                }
                array.add(content.get(i));
                valores="";
            }else if(content.get(i).matches("[0-9]") ){
                valores=valores+content.get(i);
            }else if(content.get(i).matches("[.]")){
                valores=valores+content.get(i);
            }
        }
        if(!valores.isEmpty() || !valores.equals("")){
            array.add(valores);
        }
        return array;
    }

    /**
    hace el corte para cuando hay mas de una operacion
    en el display
     */
    public boolean verificarFormato(){
        operationList=sortArray();
        int val =0;
        for (int i = 0; i <operationList.size() ; i++) {
            if(operationList.get(i).matches("[-+*/^]")){
                val=val+1;
            }
        }

        if(val==1 && operationList.size()==4){
            return true;
        }else if (val==2 && operationList.size()==5){
            return true;
        }else if(val==3 && operationList.size()==6){
            return true;
        }
        return false;
    }

    public void igualRaiz(){
        List<String> operationList=sortArray();
        if(operationList.get(2).matches("[√]")){
            resul=Math.sqrt(Double.parseDouble(operationList.get(1)));
        }else if(operationList.get(1).equals("-")){
            textDisplay="i";
            result.setText("");

        }
        else if (operationList.size()>3){
            result.setText("");
            textDisplay="sintax Error";
        }

        result.setText(resul+"");
        content= new ArrayList<>();
        String aux= resul+"";
        for (int i = 0; i <aux.length() ; i++) {
            content.add(aux.charAt(i)+"");
        }
        resul=0;
    }

    /**
     * realiza la operacion requerida
     */
    public void igual(){
      operationList=sortArray();
        if (operationList.get(1).matches("[+/*^.]")) {
            textDisplay = "sintax Error";
            result.setText("");
        }else if(operationList.get(1).matches("[-√]")){
        }
        else{
            resul=Double.parseDouble(operationList.get(1));
        }
        for (int i = 1; i < operationList.size(); i++) {
            if(operationList.get(i-1).matches("[-√+/*^]")){
                switch (operationList.get(i-1)){
                    case "+":
                        if(operationList.get(i).equals("-")){
                            resul=resul+-Double.parseDouble(operationList.get(i+1));
                            i=i+1;
                        }
                        else if(operationList.get(i).matches("[√+/*^]")){
                            textDisplay="sintax Error";
                            result.setText("");
                        }else {
                            resul = resul + Double.parseDouble(operationList.get(i));
                        }
                        break;
                    case "-":
                        if(operationList.get(i).equals("-")){
                            resul=resul+Double.parseDouble(operationList.get(i+1));
                            i=i+1;
                        }
                        else if(operationList.get(i).matches("[+√/*^]")){
                            textDisplay="sintax Error";
                            result.setText("");
                        }else {
                            resul = resul - Double.parseDouble(operationList.get(i));
                        }
                        break;
                    case "*":
                        if(operationList.get(i).equals("-")){
                            resul=resul*-Double.parseDouble(operationList.get(i+1));
                            i=i+1;
                        }
                        else if(operationList.get(i).matches("[√+/*^]")){
                            textDisplay="sintax Error";
                            result.setText("");
                        }
                        else{
                            resul=resul*Double.parseDouble(operationList.get(i));
                        }
                        break;
                    case "/":

                        if(operationList.get(i).equals("-")){
                            if(operationList.get(i+1).equals("0") || operationList.get(i+1).equals("0.0") ){
                            textDisplay="sintax Error";
                                result.setText("");

                            }
                            resul=resul/-Double.parseDouble(operationList.get(i+1));
                            i=i+1;
                        }else if(operationList.get(i).matches("[√+/*^]")){
                            textDisplay="sintax Error";
                            result.setText("");

                        }
                        else {
                            if(operationList.get(i).equals("0") || operationList.get(i).equals("0.0")){
                                textDisplay="sintax Error";
                                result.setText("");
                            }
                            resul = resul/Double.parseDouble(operationList.get(i));
                        }
                        break;
                    case "^":
                        if(operationList.get(i).equals("-")){
                            resul=Math.pow(resul,-Double.parseDouble(operationList.get(i+1)));
                            i=i+1;
                        }else if(operationList.get(i).matches("[√+/*^]")){
                            textDisplay="sintax Error";
                            result.setText("");
                        }else{
                            resul=Math.pow(resul,Double.parseDouble(operationList.get(i)));
                        }
                        break;
                        //
             /*       case "√":
                        if (operationList.get(i).equals("-")){
                            result.setText("sintax Error");
                        }else{
                            resul=Math.sqrt(Double.parseDouble(operationList.get(i)));
                        }
                        break;
                        */
                }
            }
        }
        result.setText(resul+"");
        content= new ArrayList<>();
        String aux= resul+"";
        for (int i = 0; i <aux.length() ; i++) {
            content.add(aux.charAt(i)+"");
        }
        resul=0;
    }

    /**
     * Desabilitar o habilitar los botones
     * @param v
     */
    public void enableBtn(View v){
        if(onOff==true){
            for (int i = 0; i < botones.length; i++) {
                botones[i].setEnabled(true);
            }
            _display.setBackgroundColor(Color.parseColor("#ffffff"));
            on.setBackgroundColor(Color.parseColor("#29BB2B"));
            result.setBackgroundColor(Color.parseColor("#ffffff"));
            onOff=false;
        }else {
            for (int i = 0; i < botones.length; i++) {
                botones[i].setEnabled(false);
            }
            _display.setBackgroundColor(Color.parseColor("#ffffff"));
            on.setBackgroundColor(Color.parseColor("#CD3333"));
            result.setBackgroundColor(Color.parseColor("#ffffff"));

            onOff=true;
        }
        textDisplay="";
        content= new ArrayList<>();
        resul=0;
        result.setText("");
        _display.setText("");
    }

}
