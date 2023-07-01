package com.example.computer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //显示结果
    private EditText resultText;
    //按钮0-9
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    //运算符
    private  Button plus;   // 加号+
    private  Button sub;    // 减号-
    private  Button multi;  // 乘号×
    private  Button divide; // 除号÷
    private  Button point;  // 小数点.
    private  Button equal;  // 等于=
    private  Button clean;  // 清空
    private  Button delete; // 删除
    private  Button negative;//切换正负

    private  Button sqrt;//开方

    boolean flag = false;
    boolean dot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
    }

    @Override
    public void onClick(View view) {
        String input = resultText.getText().toString();
        int id = view.getId();
        if (id == R.id.plus || id == R.id.sub || id == R.id.multi || id == R.id.divide ) {
            if(!input.isEmpty()&&!input.contains(" ") && input.charAt(input.length()-1) != '.'){
                dot = false;
                flag = false;
                resultText.setText(input + " " + ((Button) view).getText() + " ");
            }else{
                return;
            }
        } else if (id == R.id.point) {
            if (input.isEmpty() || input.substring(input.length() - 1).equals(" ")||dot)
                return;//如果最后是空格，无响应
            else{
                resultText.setText(input + '.');
                dot = true;
            }

        } else if (id == R.id.clean) {
            dot = false;
            flag = false;
            resultText.setText("");
        } else if (id == R.id.delete) {
            if (!input.isEmpty()) {
                resultText.setText(input.substring(0, input.length() - 1));
            }
        } else if (id == R.id.equal) {
            getResult();

        } else if (id == R.id.negative) {
            setNegative();
        } else if (id == R.id.sqrt) {
            getSqrt();
        } else {
            if(flag){
                flag = false;
                resultText.setText(((Button) view).getText());
            }else{
                resultText.setText(input + ((Button) view).getText());
            }

        }
    }

    public void setNegative(){
        String message = resultText.getText().toString();
        if(message.isEmpty()){
            return;
        }
        if(message.indexOf("-")==0){
            resultText.setText(message.substring(1));
        } else if (message.charAt(0)!='0' || message.length()!=1) {
            resultText.setText("-"+message);
        }
    }

    public void getSqrt(){
        String message = resultText.getText().toString();
        if(message.isEmpty()){
            return;
        }
        if(!message.contains(" ") && !message.contains("-")){
            double d = Double.parseDouble(message);
            resultText.setText(Math.sqrt(d)+"");
        } else{
            Toast.makeText(this,"负号不能开根号",Toast.LENGTH_SHORT).show();
            return;
        }
    }
    public void initial(){
        resultText = (EditText) findViewById(R.id.result);
        plus = (Button) findViewById(R.id.plus);
        sub = (Button) findViewById(R.id.sub);
        multi = (Button) findViewById(R.id.multi);
        divide = (Button) findViewById(R.id.divide);
        point = (Button) findViewById(R.id.point);
        equal = (Button) findViewById(R.id.equal);
        clean = (Button) findViewById(R.id.clean);
        delete = (Button) findViewById(R.id.delete);
        negative = (Button) findViewById(R.id.negative);
        sqrt = (Button) findViewById(R.id.sqrt);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);


        plus.setOnClickListener(this);
        sub.setOnClickListener(this);
        multi.setOnClickListener(this);
        divide.setOnClickListener(this);
        point.setOnClickListener(this);
        equal.setOnClickListener(this);
        sub.setOnClickListener(this);
        clean.setOnClickListener(this);
        delete.setOnClickListener(this);
        negative.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
    }
        //运算结果的方法
    private void getResult(){
        try {
            flag = true;
            //获取文本框的内容
            String message = resultText.getText().toString();
            if(message==null||message.equals("")){//如果内容为空
                return;
            }
            if(!message.contains(" ")){//如果不包含运算符
                return;
            }
            double result = 0.0;//返回结果

            //运算符前的数字，从0下标到第一个空格下标前
            String s1 = message.substring(0,message.indexOf(" "));
            //运算符，第一个空格下标和第二个空格下标之间
            String op = message.substring(message.indexOf(" ")+1,message.indexOf(" ")+2);
            //运算符后的数字，从第二个空格后全是
            String s2 = message.substring(message.indexOf(" ")+3);

            if(!s1.equals("")&&!s2.equals("")) {//如果数字合法
                double num1 = Double.parseDouble(s1);//则数字都是double类型
                double num2 = Double.parseDouble(s2);
                if (op.equals("+")) //如果是 +
                    result = num1 + num2;
                else if (op.equals("-"))
                    result = num1 - num2;
                else if (op.equals("×"))
                    result = num1 * num2;
                else if (op.equals("÷")) {
                    if (num2 == 0) //如果被除数是0
                        Toast.makeText(this,"除数不能为0，请重新输入！",Toast.LENGTH_SHORT).show();
                    else //否则执行正常是除法运算
                        result = num1 / num2;
                }

                if (!s1.contains(".") && !s2.contains(".") && !op.equals("÷")) {//如果都是整型
                    int r = (int) result;
                    resultText.setText(r + "");
                } else{//如果有浮点型
                    resultText.setText(result + "");
                }
            } else if(!s1.equals("") && s2.equals("")){//如果是只输入运算符前的数
                return;//直接返回当前文本框的内容
            } else if(s1.equals("") && !s2.equals("")){//如果是只输入运算符后面的数
                double num = Double.parseDouble(s2);
                //运算符前没有输入数字
                if (op.equals("+"))
                    result = 0 + num;
                else if (op.equals("-"))
                    result = 0 - num;
                else if (op.equals("×"))
                    result = 0;
                else if (op.equals("÷"))
                    result = 0;

                if (!s2.contains(".")) {
                    int r = (int) result;
                    resultText.setText(r + "");
                } else{
                    resultText.setText(result + "");
                }
            }else {
                resultText.setText("");
            }
        }catch (Exception e){
            Toast.makeText(this,"表达式错误",Toast.LENGTH_SHORT).show();
            return;
        }

    }

}
