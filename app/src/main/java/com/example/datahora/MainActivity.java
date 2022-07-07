package com.example.datahora;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowInsets;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txtHora, txtSegundos, txtData;
    private Handler handler = new Handler();
    private Runnable runnable;
    DateFormat data;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtData = findViewById(R.id.Data);
        txtHora = findViewById(R.id.Hora);
        txtSegundos = findViewById(R.id.Segundos);

        data = new SimpleDateFormat("dd/MM/yyyy");

        Atualizar();
    }

    public void Atualizar(){

        runnable = new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());

                String horasMinutosformatado = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE));
                String segundosFormatados = String.format(":%02d", calendar.get(Calendar.SECOND));

                txtHora.setText(horasMinutosformatado);
                txtSegundos.setText(segundosFormatados);

                date = new Date();
                txtData.setText(data.format(date));

                long agora = SystemClock.uptimeMillis();
                long proximo = agora + (1000-(agora%1000));

                handler.postAtTime(runnable, proximo);
                //handler.postAtTime(runnable, 1);
            }
        };
        runnable.run();
        //new Thread(runnable).start();
    }

    /*public void obterDataAtual(View view){
        intDateTimeData();
        Calendar cDefault = Calendar.getInstance();
        cDefault.set(ano, mes, dia);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                this,
                cDefault.get(Calendar.YEAR),
                cDefault.get(Calendar.MONTH),
                cDefault.get(Calendar.DAY_OF_MONTH)
                );

        Calendar cMin = Calendar.getInstance();
        Calendar cMax = Calendar.getInstance();

        cMax.set(Calendar.YEAR, 11, 31 );
        datePickerDialog.setMinDate( cMin );
        datePickerDialog.setMaxDate( cMax );

    }

    private void intDateTimeData() {
        if(ano == 0){
            Calendar c = Calendar.getInstance();
            ano = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            dia = c.get(Calendar.DAY_OF_MONTH);
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);
            segundos = c.get(Calendar.SECOND);

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }*/

    /*private void obterDataAtual(){
        long dataLong = txtData.getDate();

        Locale locale = new Locale("pt", "BR");

        SimpleDateFormat diaa = new SimpleDateFormat("dd", locale);
        SimpleDateFormat mess = new SimpleDateFormat("MM", locale);
        SimpleDateFormat anoo = new SimpleDateFormat("yyyy", locale);

        dia = Integer.parseInt(diaa.format(dataLong));
        mes = Integer.parseInt(mess.format(dataLong));
        ano = Integer.parseInt(anoo.format(dataLong));

        Toast.makeText(getContext(),
                "Dia: " + dia + "\nMes: " + mes + "\nAno: " + ano, Toast.LENGTH_LONG).show();
    }*/





    /*private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }*/

    //txtMostrarNome.setText(lista.get(i).getNome());

    /*SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");

    Data data = new Data();
    String dataFormatada = formataData.format(data);*/

    /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("yyyy/MM/dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));*/

    //System.out.println("Data formatada " + dataFormatada );


}