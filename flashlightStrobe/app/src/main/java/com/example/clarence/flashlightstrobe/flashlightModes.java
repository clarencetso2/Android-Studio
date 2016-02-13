package com.example.clarence.flashlightstrobe;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.text.InputFilter;
import android.view.View;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.security.Policy;
import java.util.Timer;
import java.util.TimerTask;


public class flashlightModes extends Activity {


    private Button buttonStrobe;
    private Camera camera;


    private final Context context = this;
    int hasCamera = Camera.getNumberOfCameras();

    @Override
    protected void onStop() {
        super.onStop();

        if (camera != null) {
            camera.release();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight_modes);
        buttonStrobe = (Button) findViewById(R.id.toggleStrobe);

        final PackageManager pack = context.getPackageManager();


        if (hasCamera == 0) {
            AlertDialog alert = new AlertDialog.Builder(context).create();
            alert.setTitle("Flash not detected");
            alert.setMessage("This device has no flash");
            alert.show();


        }

        camera = Camera.open();


    }


    public void toggleStrobe(View view) {
        final Timer time = new Timer();
        final Parameters p = camera.getParameters();


        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                camera.setParameters(p);
                p.setFlashMode(Parameters.FLASH_MODE_OFF);
                camera.setParameters(p);
                if (buttonStrobe.getText().equals("Strobe On")) {
                    time.cancel();
                    time.purge();
                }
            }
        }, 0, 200);


    }

    public void translateMorse(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String text = editText.getText().toString();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == 0) {
                break;
            }
            else {
                executeTranslation(c);
                letterSpace();


            }


        }
    }

    public void executeTranslation(char yaa) {

        switch (yaa) {
            case '\0': end();break;
            case ' ': wordSpace();end();break;
            case 'A': dot();strikeSpace();line();end(); break;
            case 'B':line();strikeSpace();dot();strikeSpace();dot();strikeSpace();dot();end(); break;
            case 'C': line();strikeSpace();dot();strikeSpace();line();strikeSpace();dot();end();break;
            case 'D': line();strikeSpace();dot();strikeSpace();dot();end();break;
            case 'E': dot();end();break;
            case 'F': dot();strikeSpace();dot();strikeSpace();line();strikeSpace();dot();end();break;
            case 'G': line();strikeSpace();line();strikeSpace();dot();end();break;
            case 'H': dot();strikeSpace();dot();strikeSpace();dot();strikeSpace();dot();end();break;
            case 'I': dot();strikeSpace();dot();end();break;
            case 'J': dot();strikeSpace();line();strikeSpace();line();strikeSpace();line();end();break;
            case 'K': line();strikeSpace();dot();strikeSpace();line();end();break;
            case 'L': dot();strikeSpace();line();strikeSpace();dot();strikeSpace();dot();end();break;
            case 'M': line();strikeSpace();line();end();break;
            case 'N': line();strikeSpace();dot();end();break;
            case 'O': line();strikeSpace();line();strikeSpace();line();end();break;
            case 'P': dot();strikeSpace();line();strikeSpace();line();strikeSpace();dot();end();break;
            case 'Q': line();strikeSpace();line();strikeSpace();dot();strikeSpace();line();end();break;
            case 'R': dot();strikeSpace();line();strikeSpace();dot();end();break;
            case 'S': dot();strikeSpace();dot();strikeSpace();dot();end();break;
            case 'T': line();end();break;
            case 'U':dot();strikeSpace();dot();strikeSpace();line();end();break;
            case 'V':dot();strikeSpace();dot();strikeSpace();dot();strikeSpace();line();end();break;
            case 'W':dot();strikeSpace();line();strikeSpace();line();end();break;
            case 'X':line();strikeSpace();dot();strikeSpace();dot();strikeSpace();line();end();break;
            case 'Y':line();strikeSpace();dot();strikeSpace();line();strikeSpace();line();end();break;
            case 'Z':line();strikeSpace();line();strikeSpace();dot();strikeSpace();dot();end();break;
            case '0':line();strikeSpace();line();strikeSpace();line();strikeSpace();line();strikeSpace();line();end();break;
            case '1':dot();strikeSpace();line();strikeSpace();line();strikeSpace();line();strikeSpace();line();end();break;
            case '2':dot();strikeSpace();dot();strikeSpace();line();strikeSpace();line();strikeSpace();line();end();break;
            case '3':dot();strikeSpace();dot();strikeSpace();dot();strikeSpace();line();strikeSpace();line();end();break;
            case '4':dot();strikeSpace();dot();strikeSpace();dot();strikeSpace();dot();strikeSpace();line();end();break;
            case '5':dot();strikeSpace();dot();strikeSpace();dot();strikeSpace();dot();strikeSpace();dot();end();break;
            case '6':line();strikeSpace();dot();strikeSpace();dot();strikeSpace();dot();strikeSpace();dot();end();break;
            case '7':line();strikeSpace();line();strikeSpace();dot();strikeSpace();dot();strikeSpace();dot();end();break;
            case '8':line();strikeSpace();line();strikeSpace();line();strikeSpace();dot();strikeSpace();dot();end();break;
            case '9':line();strikeSpace();line();strikeSpace();line();strikeSpace();line();strikeSpace();dot();end();break;
        }

    }


   public void dot(){

       final Parameters dot = camera.getParameters();
       long begin = System.currentTimeMillis();
       long end = begin + 200;

       while(System.currentTimeMillis() < end){

           dot.setFlashMode(Parameters.FLASH_MODE_TORCH);
           camera.setParameters(dot);

       }

    }

    public void line(){

        final Parameters dot = camera.getParameters();
        long begin = System.currentTimeMillis();
        long end = begin + 600;

        while(System.currentTimeMillis() < end){

            dot.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(dot);

        }

    }

    public void strikeSpace() {

        long begin = System.currentTimeMillis();
        long end = begin + 200;

        while (System.currentTimeMillis() < end) {

            final Parameters dotPSpace = camera.getParameters();
            dotPSpace.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(dotPSpace);

        }
    }

    public void letterSpace() {

        long begin = System.currentTimeMillis();
        long end = begin + 600;

        while (System.currentTimeMillis() < end) {

            final Parameters letterSpace = camera.getParameters();
            letterSpace.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(letterSpace);

        }
    }

    public void wordSpace(){

        long begin = System.currentTimeMillis();
        long end = begin + 1400;

        while(System.currentTimeMillis() < end) {

            final Parameters wordSpace = camera.getParameters();
            wordSpace.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(wordSpace);

        }


    }

    public void end(){

        final Parameters ending = camera.getParameters();
        ending.setFlashMode(Parameters.FLASH_MODE_OFF);
        camera.setParameters(ending);

        }






}