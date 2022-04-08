package com.sachi.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.sachi.okhttp.databinding.ActivityTictBiikingBinding;

public class TictBiikingActivity extends AppCompatActivity {

    ActivityTictBiikingBinding binding;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTictBiikingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new Database(this);

        Intent intent = this.getIntent();
        String racename = null,racedate = null,racetime = null;

        if(intent!= null){
            racename = intent.getStringExtra("RaceName");
            racedate = intent.getStringExtra("RaceDate");
            racetime = intent.getStringExtra("RaceTime");
            binding.raceName.setText(racename);
            binding.raceDate.setText(racedate);
            binding.raceTime.setText(racetime);
        }
        binding.txtTictketAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence!=null && charSequence.length()>0){
                    int totalTick = Integer.parseInt(charSequence.toString());
                    int totalAmt = totalTick*50;
                    if(totalTick<6){
                        binding.txtTotalAmt.setText(totalAmt+"");
                        Toast.makeText(TictBiikingActivity.this, ""+totalAmt, Toast.LENGTH_SHORT).show();
                    }else {
                        binding.txtTictketAmount.setText("");
                        Toast.makeText(TictBiikingActivity.this, "You can book On 6 tickets", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    binding.txtTotalAmt.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        String finalRacename = racename;
        String finalRacedate = racedate;
        String finalRacetime = racetime;
        binding.btnBook.setOnClickListener(i->{
            int txtno = (int) (Math.random() * 99999)+9999;
            boolean succ =  db.insetTicket(txtno+"", finalRacename, finalRacedate, finalRacetime,binding.txtTictketAmount.getText().toString(),binding.txtTotalAmt.getText().toString());
            Toast.makeText(this, ""+succ, Toast.LENGTH_SHORT).show();
        });

    }
}