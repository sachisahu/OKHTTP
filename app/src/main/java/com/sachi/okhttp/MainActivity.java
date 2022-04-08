package com.sachi.okhttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.sachi.okhttp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Activity activity;
    List<RaceDataClass> raceDataClassesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        getdata();


    }

    private void getdata() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://ergast.com/api/f1/current.json")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("Status","Failed:"+e.getMessage());
            }


            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {


                String data = response.body().string();
                try {
                    JSONObject jsonObjectMRData = new JSONObject(data);
                    String MRData = jsonObjectMRData.getString("MRData");
                    JSONObject jsonObjectRaceData = new JSONObject(MRData);
                    String RaceData = jsonObjectRaceData.getString("RaceTable");
                    JSONObject jsonObjectRaces = new JSONObject(RaceData);
                    String Races = jsonObjectRaces.getString("Races");
                    JSONArray jsonArrayRace = new JSONArray(Races);
                    for (int x=0;x< jsonArrayRace.length();x++){
                        JSONObject jsonObject = jsonArrayRace.getJSONObject(x);
                        String season = jsonObject.getString("season");
                        //Log.d("Season",season);
                        String round = jsonObject.getString("round");
                        //Log.d("round",round);
                        String url = jsonObject.getString("url");
                        //Log.d("url",url);
                        String raceName = jsonObject.getString("raceName");
                        Log.d("Racename",raceName);
                        String circuit = jsonObject.getString("Circuit");
                        //Log.d("circuit",circuit);
                            JSONObject jsonObjectCircuit = new JSONObject(circuit);
                            String circuitID = jsonObjectCircuit.getString("circuitId");
                            //Log.d("CircuitID",circuitID);
                            String circuiturl = jsonObjectCircuit.getString("url");
                            //Log.d("CircuitUrl",circuiturl);
                            String circuitName = jsonObjectCircuit.getString("circuitName");
                            //Log.d("CircuitName",circuitName);
                            String circuitLocation = jsonObjectCircuit.getString("Location");
                            //Log.d("CircuitLocation",circuitLocation);
                                JSONObject jsonObjectCircuitLocation = new JSONObject(circuitLocation);
                                String circuitLocationLat = jsonObjectCircuitLocation.getString("lat");
                                //Log.d("CircuitLocationLatitude",circuitLocationLat);
                                String circuitLocationLong = jsonObjectCircuitLocation.getString("long");
                                //Log.d("CircuitLocationLong",circuitLocationLong);
                                String circuitLocationLocality = jsonObjectCircuitLocation.getString("locality");
                                //Log.d("CircuitLocationLocality",circuitLocationLocality);
                                String circuitLocationCountry = jsonObjectCircuitLocation.getString("country");
                                //Log.d("CircuitLocationCountry",circuitLocationCountry);

                        String date = jsonObject.getString("date");
                        Log.d("Circuit Date",date);
                        String time = jsonObject.getString("time");
                        Log.d("Circuit Time",time);

                        String firstPractice = jsonObject.getString("FirstPractice");
                        //Log.d("First Practice",firstPractice);
                        JSONObject jsonObjectFirst = new JSONObject(firstPractice);
                        String firstPracticeDate = jsonObjectFirst.getString("date");
                        //Log.d("First Practic Date", firstPracticeDate);
                        String firstPracticeTime = jsonObjectFirst.getString("time");
                        //Log.d("First Practic Time", firstPracticeTime);

                        String SecondPractice = jsonObject.getString("SecondPractice");
                        //Log.d("Second Practice",SecondPractice);
                        JSONObject jsonObjectSecond = new JSONObject(SecondPractice);
                        String secondPracticeDate = jsonObjectSecond.getString("date");
                        //Log.d("Second Practic Date", secondPracticeDate);
                        String secondPracticeTime = jsonObjectFirst.getString("time");
                        //Log.d("Second Practic Time", secondPracticeTime);

                        /*String ThirdPractice = jsonObject.getString("ThirdPractice");
                        Log.d("Third Practice",ThirdPractice);
                        JSONObject jsonObjectThird = new JSONObject(ThirdPractice);
                        String ThirdPracticeDate = jsonObjectThird.getString("date");
                        Log.d("Third Practic Date", ThirdPracticeDate);
                        String ThirdPracticeTime = jsonObjectThird.getString("time");
                        Log.d("Third Practic Time", ThirdPracticeTime);*/


                        String Qualifying = jsonObject.getString("Qualifying");
                        //Log.d("Qualifying",Qualifying);
                        JSONObject jsonObjectQualifying = new JSONObject(Qualifying);
                        String QualifyingDate = jsonObjectQualifying.getString("date");
                        //Log.d("Qualifying Date", QualifyingDate);
                        String QualifyingTime = jsonObjectQualifying.getString("time");
                        //Log.d("Qualifying Time", QualifyingTime);

                        raceDataClassesList.add(new RaceDataClass(raceName,date,time));

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            RaceListAdapter raceListAdapter = new RaceListAdapter(activity,raceDataClassesList);
                            binding.listView.setAdapter(raceListAdapter);
                            binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent  = new Intent(MainActivity.this,TictBiikingActivity.class);
                                    intent.putExtra("RaceName",raceDataClassesList.get(i).RaceName);
                                    intent.putExtra("RaceDate",raceDataClassesList.get(i).Date);
                                    intent.putExtra("RaceTime",raceDataClassesList.get(i).time);
                                    startActivity(intent);
                                    Toast.makeText(activity, ""+raceDataClassesList.get(i).RaceName, Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}