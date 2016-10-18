package com.example.student.teastallv2;

/**
 * Project : TeaStallv.2
 * Created by Md. Abu Raihan Srabon on 10/2/15 @ 10:50 PM.
 * Email : m.arsrabon@gmail.com
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    static int price = 5;
    int total_customer;
    int total;
    EditText T_customer;
    TextView txt_Sugar;
    TextView txt_Milk;
    TextView txt_tea_typ;
    TextView txt_Total;
    TextView txt_T_customer;
    CheckBox Sugar;
    CheckBox Milk;
    Button btn_add;
    Button btn_sub;
    Button btn_gen;
    Button btn_Reset;
    Spinner spn_tea_Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        T_customer = (EditText) findViewById(R.id.edt_cust);

        txt_Milk = (TextView) findViewById(R.id.txt_milk);
        txt_Sugar = (TextView) findViewById(R.id.txt_sugar);
        txt_Total = (TextView) findViewById(R.id.txt_total);
        txt_tea_typ = (TextView) findViewById(R.id.txt_tea_type);
        txt_T_customer = (TextView) findViewById(R.id.txt_customer);

        Sugar = (CheckBox) findViewById(R.id.chkbox_sugar);
        Milk = (CheckBox) findViewById(R.id.chkbox_milk);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_Reset = (Button) findViewById(R.id.btn_rset);
        btn_gen = (Button) findViewById(R.id.btn_gen);

        spn_tea_Type = (Spinner) findViewById(R.id.OS_list);

        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_Reset.setOnClickListener(this);
        btn_gen.setOnClickListener(this);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tea_types, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn_tea_Type.setAdapter(adapter);


        txt_tea_typ.setText("Tea Type : " + spn_tea_Type.getSelectedItem().toString());

        spn_tea_Type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this , "Position : " + position , Toast.LENGTH_SHORT).show();
                txt_tea_typ.setText("Tea Type : " + spn_tea_Type.getSelectedItem().toString());
                if(spn_tea_Type.getSelectedItem().toString().equals("Milk Tea")){
                    Milk.setEnabled(true);
                }else {
                    Milk.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Sugar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Sugar is Selected...", Toast.LENGTH_SHORT).show();
                    txt_Sugar.setText("Sugar : Yes");
                } else {
                    txt_Sugar.setText("Sugar : No");
                }
            }
        });
        Milk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Milk is Selected...", Toast.LENGTH_SHORT).show();
                    txt_Milk.setText("Milk : Yes");
                } else {
                    txt_Milk.setText("Milk : No");
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        if (v == btn_add) {
            if(Integer.valueOf(T_customer.getText().toString()) >= 0){
                total_customer = Integer.valueOf(T_customer.getText().toString()) + 1;
            }else {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
            T_customer.setText(String.valueOf(total_customer));
        } else if (v == btn_sub) {
            if(Integer.valueOf(T_customer.getText().toString()) >= 1){
                total_customer = Integer.valueOf(T_customer.getText().toString()) - 1;
            }else {
                Toast.makeText(MainActivity.this, "your total customer is 0", Toast.LENGTH_SHORT).show();
            }
            T_customer.setText(String.valueOf(total_customer));
        } else if (v == btn_gen) {
            total = price * Integer.valueOf(T_customer.getText().toString());
            txt_Total.setText("Total : " + String.valueOf(total));
            txt_tea_typ.setText("Tea Type : " + spn_tea_Type.getSelectedItem().toString());
            txt_T_customer.setText("Customer Number : " + T_customer.getText().toString());

        } else if (v == btn_Reset) {
            txt_Milk.setText("Milk : No");
            txt_Sugar.setText("Sugar : No");
            txt_Total.setText("Total : 0");
            total_customer = 1;
            if (Milk.isChecked()) {
                Milk.setChecked(false);
            }
            if (Sugar.isChecked()) {
                Sugar.setChecked(false);
            }
            T_customer.setText(String.valueOf(total_customer));
            txt_T_customer.setText("Customer Number : 0");
        }
    }
}
