package com.example.relogio_wear;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.WindowInsets;
import android.widget.TextView;

import com.example.relogio_wear.databinding.ActivityMainWearBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivityWear extends Activity {

    private ActivityMainWearBinding binding;

    private TextView txtHora, txtSegundos, txtData;
    private Handler handler = new Handler();
    private Runnable runnable;
    DateFormat data;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainWearBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

    /*private class Engine extends CanvasWatchFaceService.Engine {
        boolean mIsRound;
        int chinSize;

        @Override
        public void onApplyWindowInsets(WindowInsets insets) {
            super.onApplyWindowInsets(insets);
            mIsRound = insets.isRound();
            chinSize = insets.getSystemWindowInsetBottom();
        }
    }*/


}