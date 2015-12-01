package com.example.romain.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import utils.TimeView;

public class Compass extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometre ;
    private Sensor magnetometre;

    public void onAccuracyChanged(Sensor sensor,int i)
    {}

    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magnetometre, SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometre = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        TimeView timeView = (TimeView) findViewById(R.id.timeView);
        timeView.setTime("2021 21:05:21");
    }

    private float[] mGravity;
    private float[] mGeomagnetic;

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimut = orientation[0];
                float pitch = orientation[1];
                float roll = orientation[2];

                Log.d("SENSOR", "Azimut: "  + azimut);
                Log.d("SENSOR", "Pitch: " + pitch);
                Log.d("SENSOR", "Roll: "  + roll);
            }
        }
    }

    }
