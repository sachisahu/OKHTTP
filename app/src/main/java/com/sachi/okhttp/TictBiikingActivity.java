package com.sachi.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.sachi.okhttp.databinding.ActivityTictBiikingBinding;

import java.util.ArrayList;
import java.util.List;

public class TictBiikingActivity extends AppCompatActivity {

    ActivityTictBiikingBinding binding;
    Database db;
    List<TicketTypeDataClass> listTkt = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTictBiikingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new Database(this);

        Intent intent = this.getIntent();
        String racename = null,racedate = null,racetime = null;

        listTkt.add(new TicketTypeDataClass(1,"Classic(50)",50));
        listTkt.add(new TicketTypeDataClass(2,"Gold(100)",100));
        listTkt.add(new TicketTypeDataClass(3,"Premium(150)",150));

        ArrayAdapter<TicketTypeDataClass> arrayAdapter = new ArrayAdapter<TicketTypeDataClass>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listTkt);

        binding.spinnerTiktCost.setAdapter(arrayAdapter);

        binding.spinnerTiktCost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int amt = listTkt.get(i).amt;
                tktAmt(amt);
                binding.txtTictketAmount.setText("");
                binding.txtTotalAmt.setText("");
            }

            private void tktAmt(int amt) {
                Log.d("Tkt Amt",amt+"");
                binding.txtTictketAmount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        if(charSequence!=null && charSequence.length()>0){
                            int totalTick = Integer.parseInt(charSequence.toString());
                            int totalAmt = totalTick*amt;
                            if(totalTick<6){
                                binding.txtTotalAmt.setText(totalAmt+"");
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        if(intent!= null){
            racename = intent.getStringExtra("RaceName");
            racedate = intent.getStringExtra("RaceDate");
            racetime = intent.getStringExtra("RaceTime");
            binding.raceName.setText(racename);
            binding.raceDate.setText(racedate);
            binding.raceTime.setText(racetime);
        }


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