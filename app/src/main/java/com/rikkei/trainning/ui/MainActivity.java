package com.rikkei.trainning.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText txtYourToppings, txtTimePicker, txtDatePicker;
    private Button btnShowDialog1, btnShowDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        txtTimePicker.setOnClickListener(this);
        txtDatePicker.setOnClickListener(this);
        btnShowDialog1.setOnClickListener(this);
        btnShowDialog2.setOnClickListener(this);
    }

    private void initView() {
        txtYourToppings = findViewById(R.id.txtYourToppings);
        txtTimePicker = findViewById(R.id.txtTimePicker);
        txtDatePicker = findViewById(R.id.txtDatePicker);
        btnShowDialog1 = findViewById(R.id.btnShowDialog1);
        btnShowDialog2 = findViewById(R.id.btnShowDialog2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtTimePicker:
                Calendar calendar = Calendar.getInstance();
                int hh = calendar.get(Calendar.HOUR_OF_DAY);
                int mm = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        txtTimePicker.setText(i+":"+i1);
                    }
                },hh,mm,true);
                timePickerDialog.show();
                break;
            case R.id.txtDatePicker:
                Calendar calendar1 = Calendar.getInstance();
                int dd = calendar1.get(Calendar.DAY_OF_MONTH);
                int MM = calendar1.get(Calendar.MONTH);
                int yy = calendar1.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtDatePicker.setText(i2+"-"+(i1+1)+"-"+i);
                    }
                },yy,MM,dd);
                datePickerDialog.show();
                break;
            case R.id.btnShowDialog1:
                String[] toppings = {"Onion","Lettuce","Tomato"};
                ArrayList<String> list = new ArrayList<>();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pick your toppings")
                        .setMultiChoiceItems(toppings, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                if(b){
                                    list.add(toppings[i]);
                                } else if(list.contains(toppings[i])){
                                    list.remove(toppings[i]);
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String yourToppings = "";
                                for(String j:list){
                                    yourToppings += j + "  ";
                                }
                                txtYourToppings.setText(yourToppings);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog =  builder.create();
                dialog.show();
                break;
            case R.id.btnShowDialog2:
                DialogFragment newFragment = new BrightnessDialogFragment();
                newFragment.show(getSupportFragmentManager(),"DialogFragment");
                break;
        }
    }
}