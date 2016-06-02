package com.example.linda.leetris;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.*;
import java.util.*;
import android.os.Handler;
import android.widget.*;
import android.content.*;
import android.app.AlertDialog;


public class myMainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_main_menu);
        /*
        RelativeLayout linear = (RelativeLayout)findViewById(R.id.hostingLayout);
        View view = new View(this);

        for(int i =0; i < view.Levels().size(); i ++)
        {
           linear.addView(view.Levels().get(i));
        }
        */

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(started){
            //THIS BLOCK WILL NOT DO ANYTHING AND WOULD DISABLE BACK BUTTON
            endGame(false);
        }
        else{
            super.onBackPressed();
            //THIS BLOCK WILL BE CALLED IF ABOVE COND IS FALSE, AND WOULD ENABLE BACK BUTTON
        }
    }
    android.os.Handler customHandler;
    android.os.Handler timerKeeper;
    private Runnable updateTimerThread = new Runnable()
    {
        public void run()
        {
            //Random rand = new Random();
            //ChangeImage(rand.nextInt(14),rand.nextInt(7),0, "j");
            //write here what ever you want to repeat
            if(started)
            {
                mover();
            }
            customHandler.postDelayed(this, speed);
        }
    };
    private Runnable updateTimerThreadKeeper = new Runnable()
    {
        public void run()
        {

            level++;
            AdjustSpeed();
            timerKeeper.postDelayed(this, 180000);
        }
    };
    int speed = 900;
    @Override
    protected void onStart() {
        super.onStart();

        ImageButton buttonname = (ImageButton) findViewById(R.id.bntStart);
        buttonname.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        ImageButton btnLeft = (ImageButton) findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MoveLeft();
            }
        });

        ImageButton btnRight = (ImageButton) findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MoveRight();
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MoveAllBackwards();
            }
        });

        ImageButton btnFoward = (ImageButton) findViewById(R.id.btnFront);
        btnFoward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MoveAllFoward();
            }
        });

        ImageButton btnFlip = (ImageButton) findViewById(R.id.btnFlip);
        btnFlip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                flip();
            }
        });

        ImageButton btnFlipSide = (ImageButton) findViewById(R.id.btnFlipSide);
        btnFlipSide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FlipSideWays();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.my_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void DoStuff()
    {
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.myScreenMenu);
        layout1.setVisibility(View.GONE);

        RelativeLayout layout2 = (RelativeLayout) findViewById(R.id.Playground);
        layout2.setVisibility(View.VISIBLE);


        customHandler = new android.os.Handler();
        customHandler.postDelayed(updateTimerThread, 0);
        timerKeeper = new android.os.Handler();
        timerKeeper.postDelayed(updateTimerThreadKeeper, 180000);
        //ChangeImage(0, 2, 0, "j");
        //ChangeImage(0, 3, 0, "j");
    }

    /*
        TimerTask scanTask;
        final Handler handler = new  Handler();
        Timer t = new Timer();

        public void doWifiScan(){

            scanTask = new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            //wifiManager.scan(context);
                            //Log.d("TIMER", "Timer set off");
                            //MinuteElapsed();
                        }
                    });
                }};


            t.schedule(scanTask, minutesT, secondsT);

        }
        int minutesT = 0;
        int secondsT = 600;

        public void stopScan(){

            if(scanTask!=null){
                //Log.d("TIMER", "timer canceled");
                t.cancel();
            }

        }
     */
    public boolean ChangeImage(int row, int col, int level, String symbol)
    {

        switch (level)
        {
            case 0: return ChangeImagelevel0(row,col, symbol);
            case 1: return ChangeImagelevel1(row,col, symbol);
            case 2: return ChangeImagelevel2(row,col, symbol);
            case 3: return ChangeImagelevel3(row,col, symbol);
            case 4: return ChangeImagelevel4(row,col, symbol);
            case 5: return ChangeImagelevel5(row,col, symbol);
            case 6: return ChangeImagelevel6(row,col, symbol);
        }
        return false;
    }
    public  boolean ChangeImagelevel0(int row, int col, String symbol)
    {
        switch (col)
        {
            case 0: return ChangeImageColumn0Level0(row,symbol);
            case 1: return ChangeImageColumn1Level0(row, symbol);
            case 2: return ChangeImageColumn2Level0(row, symbol);
            case 3: return ChangeImageColumn3Level0(row, symbol);
            case 4: return ChangeImageColumn4Level0(row, symbol);
            case 5: return ChangeImageColumn5Level0(row, symbol);
            case 6: return ChangeImageColumn6Level0(row, symbol);
        }
        return true;
    }
    public boolean ChangeImageColumn0Level0(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0) piece =(ImageView)findViewById(R.id.row0col0level0);

        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col0level0);

        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col0level0);

        else if( row == 3)  piece =(ImageView)findViewById(R.id.row3col0level0);

        else if( row == 4)  piece =(ImageView)findViewById(R.id.row4col0level0);

        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col0level0);

        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col0level0);

        else if(row  == 7)  piece =(ImageView)findViewById(R.id.row7col0level0);

        else if( row == 8)  piece =(ImageView)findViewById(R.id.row8col0level0);

        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col0level0);

        else if( row == 10)  piece =(ImageView)findViewById(R.id.row10col0level0);

        else if( row == 11)  piece =(ImageView)findViewById(R.id.row11col0level0);

        else if( row == 12)  piece =(ImageView)findViewById(R.id.row12col0level0);

        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col0level0);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn1Level0(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0) piece =(ImageView)findViewById(R.id.row0col1level0);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col1level0);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col1level0);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col1level0);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col1level0);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col1level0);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col1level0);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col1level0);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col1level0);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col1level0);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col1level0);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col1level0);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col1level0);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col1level0);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn2Level0(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col2level0);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col2level0);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col2level0);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col2level0);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col2level0);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col2level0);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col2level0);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col2level0);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col2level0);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col2level0);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col2level0);
        else if(row == 11) piece =(ImageView)findViewById(R.id.row11col2level0);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col2level0);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col2level0);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }

            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn3Level0(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col3level0);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col3level0);
        else if(row ==  2)  piece =(ImageView)findViewById(R.id.row2col3level0);
        else if(row ==  3)  piece =(ImageView)findViewById(R.id.row3col3level0);
        else if(row ==  4)  piece =(ImageView)findViewById(R.id.row4col3level0);
        else if(row ==  5)  piece =(ImageView)findViewById(R.id.row5col3level0);
        else if(row ==  6)  piece =(ImageView)findViewById(R.id.row6col3level0);
        else if(row ==  7)  piece =(ImageView)findViewById(R.id.row7col3level0);
        else if(row ==  8)  piece =(ImageView)findViewById(R.id.row8col3level0);
        else if(row ==  9)  piece =(ImageView)findViewById(R.id.row9col3level0);
        else if(row ==  10)  piece =(ImageView)findViewById(R.id.row10col3level0);
        else if(row ==  11)  piece =(ImageView)findViewById(R.id.row11col3level0);
        else if(row ==  12)  piece =(ImageView)findViewById(R.id.row12col3level0);
        else if(row ==  13)  piece =(ImageView)findViewById(R.id.row13col3level0);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn4Level0(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col4level0);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col4level0);
        else if(row ==  2)  piece =(ImageView)findViewById(R.id.row2col4level0);
        else if(row ==  3)  piece =(ImageView)findViewById(R.id.row3col4level0);
        else if(row ==  4)  piece =(ImageView)findViewById(R.id.row4col4level0);
        else if(row ==  5)  piece =(ImageView)findViewById(R.id.row5col4level0);
        else if(row ==  6)  piece =(ImageView)findViewById(R.id.row6col4level0);
        else if(row ==  7)  piece =(ImageView)findViewById(R.id.row7col4level0);
        else if(row ==  8)  piece =(ImageView)findViewById(R.id.row8col4level0);
        else if(row ==  9)  piece =(ImageView)findViewById(R.id.row9col4level0);
        else if(row ==  10)  piece =(ImageView)findViewById(R.id.row10col4level0);
        else if(row ==  11)  piece =(ImageView)findViewById(R.id.row11col4level0);
        else if(row ==  12)  piece =(ImageView)findViewById(R.id.row12col4level0);
        else if(row ==  13)  piece =(ImageView)findViewById(R.id.row13col4level0);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn5Level0(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col5level0);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col5level0);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col5level0);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col5level0);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col5level0);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col5level0);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col5level0);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col5level0);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col5level0);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col5level0);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col5level0);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col5level0);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col5level0);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col5level0);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn6Level0(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col6level0);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col6level0);
        else if(row ==  2)  piece =(ImageView)findViewById(R.id.row2col6level0);
        else if(row ==  3)  piece =(ImageView)findViewById(R.id.row3col6level0);
        else if(row ==  4)  piece =(ImageView)findViewById(R.id.row4col6level0);
        else if(row ==  5)  piece =(ImageView)findViewById(R.id.row5col6level0);
        else if(row ==  6)  piece =(ImageView)findViewById(R.id.row6col6level0);
        else if(row ==  7)  piece =(ImageView)findViewById(R.id.row7col6level0);
        else if(row ==  8)  piece =(ImageView)findViewById(R.id.row8col6level0);
        else if(row ==  9)  piece =(ImageView)findViewById(R.id.row9col6level0);
        else if(row ==  10)  piece =(ImageView)findViewById(R.id.row10col6level0);
        else if(row ==  11)  piece =(ImageView)findViewById(R.id.row11col6level0);
        else if(row ==  12)  piece =(ImageView)findViewById(R.id.row12col6level0);
        else if(row ==  13)  piece =(ImageView)findViewById(R.id.row13col6level0);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public  boolean ChangeImagelevel1(int row, int col, String symbol)
    {
        switch (col)
        {
            case 0: return ChangeImageColumn0level1(row,symbol);
            case 1: return ChangeImageColumn1level1(row, symbol);
            case 2: return ChangeImageColumn2level1(row, symbol);
            case 3: return ChangeImageColumn3level1(row, symbol);
            case 4: return ChangeImageColumn4level1(row, symbol);
            case 5: return ChangeImageColumn5level1(row, symbol);
            case 6: return ChangeImageColumn6level1(row, symbol);
        }
        return true;
    }
    public boolean ChangeImageColumn0level1(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col0level1);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col0level1);
        else if(row ==  2)  piece =(ImageView)findViewById(R.id.row2col0level1);
        else if(row ==  3)  piece =(ImageView)findViewById(R.id.row3col0level1);
        else if(row ==  4)  piece =(ImageView)findViewById(R.id.row4col0level1);
        else if(row ==  5)  piece =(ImageView)findViewById(R.id.row5col0level1);
        else if(row ==  6)  piece =(ImageView)findViewById(R.id.row6col0level1);
        else if(row ==  7)  piece =(ImageView)findViewById(R.id.row7col0level1);
        else if(row ==  8)  piece =(ImageView)findViewById(R.id.row8col0level1);
        else if(row ==  9)  piece =(ImageView)findViewById(R.id.row9col0level1);
        else if(row ==  10)  piece =(ImageView)findViewById(R.id.row10col0level1);
        else if(row ==  11)  piece =(ImageView)findViewById(R.id.row11col0level1);
        else if(row ==  12)  piece =(ImageView)findViewById(R.id.row12col0level1);
        else if(row ==  13)  piece =(ImageView)findViewById(R.id.row13col0level1);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn1level1(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col1level1);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col1level1);
        else if(row ==  2)  piece =(ImageView)findViewById(R.id.row2col1level1);
        else if(row ==  3)  piece =(ImageView)findViewById(R.id.row3col1level1);
        else if(row ==  4)  piece =(ImageView)findViewById(R.id.row4col1level1);
        else if(row ==  5)  piece =(ImageView)findViewById(R.id.row5col1level1);
        else if(row ==  6)  piece =(ImageView)findViewById(R.id.row6col1level1);
        else if(row ==  7)  piece =(ImageView)findViewById(R.id.row7col1level1);
        else if(row ==  8)  piece =(ImageView)findViewById(R.id.row8col1level1);
        else if(row ==  9)  piece =(ImageView)findViewById(R.id.row9col1level1);
        else if(row ==  10)  piece =(ImageView)findViewById(R.id.row10col1level1);
        else if(row ==  11)  piece =(ImageView)findViewById(R.id.row11col1level1);
        else if(row ==  12)  piece =(ImageView)findViewById(R.id.row12col1level1);
        else if(row ==  13)  piece =(ImageView)findViewById(R.id.row13col1level1);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn2level1(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col2level1);
        else if(row ==  1)  piece =(ImageView)findViewById(R.id.row1col2level1);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col2level1);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col2level1);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col2level1);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col2level1);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col2level1);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col2level1);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col2level1);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col2level1);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col2level1);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col2level1);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col2level1);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col2level1);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn3level1(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col3level1);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col3level1);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col3level1);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col3level1);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col3level1);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col3level1);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col3level1);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col3level1);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col3level1);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col3level1);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col3level1);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col3level1);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col3level1);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col3level1);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn4level1(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col4level1);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col4level1);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col4level1);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col4level1);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col4level1);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col4level1);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col4level1);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col4level1);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col4level1);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col4level1);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col4level1);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col4level1);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col4level1);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col4level1);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn5level1(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col5level1);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col5level1);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col5level1);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col5level1);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col5level1);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col5level1);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col5level1);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col5level1);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col5level1);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col5level1);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col5level1);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col5level1);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col5level1);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col5level1);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn6level1(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col6level1);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col6level1);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col6level1);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col6level1);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col6level1);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col6level1);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col6level1);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col6level1);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col6level1);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col6level1);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col6level1);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col6level1);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col6level1);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col6level1);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }

    public boolean ChangeImageColumn0level2(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col0level2);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col0level2);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col0level2);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col0level2);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col0level2);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col0level2);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col0level2);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col0level2);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col0level2);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col0level2);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col0level2);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col0level2);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col0level2);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col0level2);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn1level2(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col1level2);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col1level2);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col1level2);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col1level2);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col1level2);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col1level2);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col1level2);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col1level2);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col1level2);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col1level2);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col1level2);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col1level2);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col1level2);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col1level2);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn2level2(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col2level2);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col2level2);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col2level2);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col2level2);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col2level2);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col2level2);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col2level2);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col2level2);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col2level2);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col2level2);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col2level2);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col2level2);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col2level2);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col2level2);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn3level2(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col3level2);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col3level2);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col3level2);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col3level2);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col3level2);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col3level2);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col3level2);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col3level2);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col3level2);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col3level2);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col3level2);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col3level2);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col3level2);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col3level2);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn4level2(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col4level2);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col4level2);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col4level2);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col4level2);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col4level2);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col4level2);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col4level2);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col4level2);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col4level2);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col4level2);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col4level2);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col4level2);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col4level2);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col4level2);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn5level2(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col5level2);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col5level2);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col5level2);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col5level2);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col5level2);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col5level2);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col5level2);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col5level2);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col5level2);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col5level2);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col5level2);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col5level2);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col5level2);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col5level2);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn6level2(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col6level2);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col6level2);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col6level2);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col6level2);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col6level2);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col6level2);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col6level2);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col6level2);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col6level2);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col6level2);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col6level2);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col6level2);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col6level2);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col6level2);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public  boolean ChangeImagelevel2(int row, int col, String symbol)
    {
        switch (col)
        {
            case 0: return ChangeImageColumn0level2(row,symbol);
            case 1: return ChangeImageColumn1level2(row, symbol);
            case 2: return ChangeImageColumn2level2(row, symbol);
            case 3: return ChangeImageColumn3level2(row, symbol);
            case 4: return ChangeImageColumn4level2(row, symbol);
            case 5: return ChangeImageColumn5level2(row, symbol);
            case 6: return ChangeImageColumn6level2(row, symbol);
        }
        return true;
    }
    public boolean ChangeImageColumn0level3(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col0level3);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col0level3);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col0level3);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col0level3);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col0level3);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col0level3);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col0level3);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col0level3);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col0level3);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col0level3);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col0level3);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col0level3);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col0level3);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col0level3);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn1level3(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col1level3);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col1level3);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col1level3);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col1level3);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col1level3);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col1level3);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col1level3);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col1level3);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col1level3);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col1level3);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col1level3);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col1level3);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col1level3);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col1level3);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn2level3(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col2level3);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col2level3);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col2level3);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col2level3);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col2level3);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col2level3);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col2level3);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col2level3);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col2level3);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col2level3);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col2level3);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col2level3);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col2level3);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col2level3);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn3level3(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col3level3);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col3level3);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col3level3);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col3level3);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col3level3);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col3level3);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col3level3);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col3level3);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col3level3);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col3level3);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col3level3);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col3level3);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col3level3);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col3level3);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }

            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn4level3(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col4level3);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col4level3);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col4level3);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col4level3);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col4level3);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col4level3);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col4level3);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col4level3);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col4level3);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col4level3);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col4level3);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col4level3);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col4level3);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col4level3);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn5level3(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col5level3);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col5level3);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col5level3);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col5level3);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col5level3);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col5level3);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col5level3);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col5level3);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col5level3);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col5level3);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col5level3);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col5level3);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col5level3);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col5level3);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn6level3(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col6level3);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col6level3);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col6level3);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col6level3);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col6level3);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col6level3);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col6level3);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col6level3);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col6level3);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col6level3);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col6level3);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col6level3);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col6level3);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col6level3);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public  boolean ChangeImagelevel3(int row, int col, String symbol)
    {
        switch (col)
        {
            case 0: return ChangeImageColumn0level3(row,symbol);
            case 1: return ChangeImageColumn1level3(row, symbol);
            case 2: return ChangeImageColumn2level3(row, symbol);
            case 3: return ChangeImageColumn3level3(row, symbol);
            case 4: return ChangeImageColumn4level3(row, symbol);
            case 5: return ChangeImageColumn5level3(row, symbol);
            case 6: return ChangeImageColumn6level3(row, symbol);
        }
        return true;
    }
    public boolean ChangeImageColumn0level4(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col0level4);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col0level4);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col0level4);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col0level4);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col0level4);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col0level4);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col0level4);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col0level4);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col0level4);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col0level4);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col0level4);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col0level4);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col0level4);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col0level4);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn1level4(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col1level4);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col1level4);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col1level4);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col1level4);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col1level4);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col1level4);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col1level4);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col1level4);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col1level4);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col1level4);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col1level4);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col1level4);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col1level4);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col1level4);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn2level4(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col2level4);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col2level4);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col2level4);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col2level4);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col2level4);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col2level4);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col2level4);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col2level4);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col2level4);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col2level4);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col2level4);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col2level4);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col2level4);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col2level4);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn3level4(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col3level4);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col3level4);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col3level4);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col3level4);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col3level4);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col3level4);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col3level4);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col3level4);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col3level4);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col3level4);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col3level4);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col3level4);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col3level4);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col3level4);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn4level4(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col4level4);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col4level4);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col4level4);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col4level4);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col4level4);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col4level4);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col4level4);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col4level4);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col4level4);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col4level4);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col4level4);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col4level4);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col4level4);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col4level4);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn5level4(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col5level4);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col5level4);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col5level4);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col5level4);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col5level4);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col5level4);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col5level4);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col5level4);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col5level4);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col5level4);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col5level4);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col5level4);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col5level4);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col5level4);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn6level4(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col6level4);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col6level4);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col6level4);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col6level4);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col6level4);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col6level4);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col6level4);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col6level4);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col6level4);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col6level4);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col6level4);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col6level4);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col6level4);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col6level4);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public  boolean ChangeImagelevel4(int row, int col, String symbol)
    {
        switch (col)
        {
            case 0: return ChangeImageColumn0level4(row,symbol);
            case 1: return ChangeImageColumn1level4(row, symbol);
            case 2: return ChangeImageColumn2level4(row, symbol);
            case 3: return ChangeImageColumn3level4(row, symbol);
            case 4: return ChangeImageColumn4level4(row, symbol);
            case 5: return ChangeImageColumn5level4(row, symbol);
            case 6: return ChangeImageColumn6level4(row, symbol);
        }
        return true;
    }
    public boolean ChangeImageColumn0level5(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col0level5);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col0level5);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col0level5);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col0level5);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col0level5);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col0level5);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col0level5);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col0level5);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col0level5);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col0level5);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col0level5);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col0level5);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col0level5);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col0level5);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn1level5(int row, String symbol)
    {
        ImageView piece = null;

        if(row ==  0)  piece =(ImageView)findViewById(R.id.row0col1level5);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col1level5);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col1level5);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col1level5);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col1level5);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col1level5);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col1level5);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col1level5);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col1level5);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col1level5);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col1level5);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col1level5);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col1level5);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col1level5);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn2level5(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col2level5);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col2level5);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col2level5);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col2level5);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col2level5);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col2level5);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col2level5);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col2level5);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col2level5);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col2level5);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col2level5);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col2level5);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col2level5);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col2level5);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn3level5(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col3level5);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col3level5);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col3level5);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col3level5);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col3level5);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col3level5);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col3level5);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col3level5);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col3level5);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col3level5);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col3level5);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col3level5);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col3level5);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col3level5);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn4level5(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col4level5);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col4level5);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col4level5);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col4level5);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col4level5);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col4level5);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col4level5);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col4level5);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col4level5);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col4level5);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col4level5);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col4level5);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col4level5);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col4level5);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn5level5(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col5level5);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col5level5);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col5level5);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col5level5);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col5level5);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col5level5);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col5level5);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col5level5);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col5level5);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col5level5);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col5level5);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col5level5);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col5level5);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col5level5);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn6level5(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col6level5);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col6level5);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col6level5);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col6level5);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col6level5);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col6level5);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col6level5);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col6level5);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col6level5);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col6level5);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col6level5);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col6level5);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col6level5);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col6level5);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public  boolean ChangeImagelevel5(int row, int col, String symbol)
    {
        switch (col)
        {
            case 0: return ChangeImageColumn0level5(row,symbol);
            case 1: return ChangeImageColumn1level5(row, symbol);
            case 2: return ChangeImageColumn2level5(row, symbol);
            case 3: return ChangeImageColumn3level5(row, symbol);
            case 4: return ChangeImageColumn4level5(row, symbol);
            case 5: return ChangeImageColumn5level5(row, symbol);
            case 6: return ChangeImageColumn6level5(row, symbol);
        }
        return true;
    }
    public boolean ChangeImageColumn0level6(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col0level6);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col0level6);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col0level6);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col0level6);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col0level6);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col0level6);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col0level6);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col0level6);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col0level6);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col0level6);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col0level6);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col0level6);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col0level6);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col0level6);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn1level6(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col1level6);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col1level6);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col1level6);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col1level6);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col1level6);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col1level6);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col1level6);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col1level6);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col1level6);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col1level6);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col1level6);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col1level6);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col1level6);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col1level6);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn2level6(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col2level6);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col2level6);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col2level6);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col2level6);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col2level6);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col2level6);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col2level6);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col2level6);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col2level6);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col2level6);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col2level6);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col2level6);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col2level6);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col2level6);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn3level6(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col3level6);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col3level6);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col3level6);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col3level6);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col3level6);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col3level6);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col3level6);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col3level6);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col3level6);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col3level6);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col3level6);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col3level6);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col3level6);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col3level6);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn4level6(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col4level6);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col4level6);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col4level6);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col4level6);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col4level6);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col4level6);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col4level6);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col4level6);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col4level6);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col4level6);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col4level6);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col4level6);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col4level6);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col4level6);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn5level6(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col5level6);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col5level6);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col5level6);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col5level6);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col5level6);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col5level6);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col5level6);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col5level6);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col5level6);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col5level6);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col5level6);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col5level6);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col5level6);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col5level6);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public boolean ChangeImageColumn6level6(int row, String symbol)
    {
        ImageView piece = null;

        if(row == 0)  piece =(ImageView)findViewById(R.id.row0col6level6);
        else if(row == 1)  piece =(ImageView)findViewById(R.id.row1col6level6);
        else if(row == 2)  piece =(ImageView)findViewById(R.id.row2col6level6);
        else if(row == 3)  piece =(ImageView)findViewById(R.id.row3col6level6);
        else if(row == 4)  piece =(ImageView)findViewById(R.id.row4col6level6);
        else if(row == 5)  piece =(ImageView)findViewById(R.id.row5col6level6);
        else if(row == 6)  piece =(ImageView)findViewById(R.id.row6col6level6);
        else if(row == 7)  piece =(ImageView)findViewById(R.id.row7col6level6);
        else if(row == 8)  piece =(ImageView)findViewById(R.id.row8col6level6);
        else if(row == 9)  piece =(ImageView)findViewById(R.id.row9col6level6);
        else if(row == 10)  piece =(ImageView)findViewById(R.id.row10col6level6);
        else if(row == 11)  piece =(ImageView)findViewById(R.id.row11col6level6);
        else if(row == 12)  piece =(ImageView)findViewById(R.id.row12col6level6);
        else if(row == 13)  piece =(ImageView)findViewById(R.id.row13col6level6);


        if(piece != null)
        {
            if(symbol.toLowerCase().equals("i")) {
                piece.setImageResource(R.drawable.i);
            }
            else if (symbol.toLowerCase().equals("j")) {
                piece.setImageResource(R.drawable.j);
            }
            else if (symbol.toLowerCase().equals("l")) {
                piece.setImageResource(R.drawable.l);
            }
            else if (symbol.toLowerCase().equals("s")) {
                piece.setImageResource(R.drawable.s);
            }
            else if (symbol.toLowerCase().equals("z")) {
                piece.setImageResource(R.drawable.z);
            }
            else if (symbol.toLowerCase().equals("t")) {
                piece.setImageResource(R.drawable.t);
            }
            else if (symbol.toLowerCase().equals("o")) {
                piece.setImageResource(R.drawable.o);
            }
            else if(symbol.toLowerCase().equals("c")) {
                piece.setImageResource(R.drawable.c);
            }
            else if(symbol.toLowerCase().equals("empty")) {
                piece.setImageResource(R.drawable.empty);
            }
            else if(symbol.toLowerCase().equals("shadow")) {
                piece.setImageResource(R.drawable.shadow);
            }
            return true;
        }
        return false;
    }
    public  boolean ChangeImagelevel6(int row, int col, String symbol)
    {
        switch (col)
        {
            case 0: return ChangeImageColumn0level6(row,symbol);
            case 1: return ChangeImageColumn1level6(row, symbol);
            case 2: return ChangeImageColumn2level6(row, symbol);
            case 3: return ChangeImageColumn3level6(row, symbol);
            case 4: return ChangeImageColumn4level6(row, symbol);
            case 5: return ChangeImageColumn5level6(row, symbol);
            case 6: return ChangeImageColumn6level6(row, symbol);
        }
        return true;
    }
    ArrayList<String[][]> tetrisGrid;
    String[] activeGridBlocks = new String[4];
    //boolean started = false;
    //String [] shadowPieces = new String[4];
    int _score = 0;
    int level = 1;
    boolean busy = false;
    boolean launching = false;

    public List<String[]> GetPiecesHardCore()
    {
        ArrayList<String[]> pieces = new ArrayList<String[]>();

        pieces.add(new String[] { "o", "1,2,3.1,3,3.2,2,3.2,3,3" });

        pieces.add(new String[] { "i", "0,3,3.1,3,3.2,3,3.3,3,3", "1,1,3.1,2,3.1,3,3.1,4,3" });

        pieces.add(new String[] { "s", "1,4,3.1,3,3.2,3,3.2,2,3", "0,3,3.1,3,3.1,4,3.2,4,3" });

        pieces.add(new String[] { "z", "1,2,3.1,3,3.2,3,3.2,4,3", "0,4,3.1,4,3.1,3,3.2,3,3" });

        pieces.add(new String[] { "l", "1,4,3.1,3,3.1,2,3.2,2,3", "0,3,3.1,3,3.2,3,3.2,4,3"
                ,"1,2,3.1,3,3.1,4,3.0,4,3", "0,2,3.0,3,3.1,3,3.2,3,3" });

        pieces.add(new String[] { "j", "1,2,3.1,3,3.1,4,3.2,4,3", "2,3,3.1,3,3.0,3,3.0,4,3",
                "0,2,3.1,2,3.1,3,3.1,4,3", "0,3,3.1,3,3.2,3,3.2,2,3" });

        pieces.add(new String[] { "t", "1,2,3.1,3,3.1,4,3.2,3,3", "0,3,3.1,3,3.2,3,3.1,4,3",
                "1,2,3.1,3,3.1,4,3.0,3,3", "1,2,3.1,3,3.2,3,3.0,3,3" });

        return pieces;
    }
    public String[] GetPieceHardCore()
    {
        Random rand = new Random();
        List<String[]> pieces = GetPiecesHardCore();

        return pieces.get(rand.nextInt(pieces.size()));

    }
    public int Random( int max)
    {
        Random rand = new Random();
        int value = rand.nextInt( max);

        if(value != 0)
            return value;
        else return 1;
    }
    public void ActivateNewCont(String pieceInfor)
    {
        for (String active : activeGridBlocks)
        {
            String[] pieces = mySplit(active,",");
            int rowCur = Integer.parseInt(pieces[0].trim());
            int colCur = Integer.parseInt(pieces[1].trim()) ;
            int level = Integer.parseInt(pieces[2].trim());

            TetrisGrid(level,rowCur,colCur,pieceInfor);
        }
    }
    private void TetrisGrid(int thisLevel, int thisRow, int thisCol, String pieceInfor )
    {

        String[][] values = tetrisGrid.get(thisLevel);
        values[thisRow][thisCol] = pieceInfor;
        tetrisGrid.set(thisLevel, values);

    }
    public boolean inRange(int row, int col)
    {
        if (row > 25 || col < 0 || col > 9)
            return false;
        else
            return true;
    }
    public boolean allInRange(String[] options)
    {
        for (String value : options)
        {
            if (!inRange(Integer.parseInt(mySplit(value,",")[0]),
                    Integer.parseInt(mySplit(value,",")[1])))
                return false;
        }
        return true;

    }
    public String ChangeCurrent(String pieceInfo, int newCurrent)
    {
        int pos = pieceInfo.indexOf("`") - 1;
        String answer = "";

        for (int i = 0; i < pieceInfo.length(); i++)
        {
            if (i != pos)
                answer += pieceInfo.toCharArray()[i];
            else
                answer += String.valueOf( newCurrent);
        }
        return answer;
    }
    public void CleanUpBeforeFlip(int rowOffSet, int ColOffset)
    {
        for (int i = 0; i < 4; i++)
        {
            String[] pieces = mySplit(activeGridBlocks[i],",");
            int row = Integer.parseInt(pieces[0].trim()) + rowOffSet;
            int col = Integer.parseInt(pieces[1].trim()) + ColOffset;
            int level = Integer.parseInt(pieces[2].trim());
            TetrisGrid(level,row,col,null);
        }

    }
    public void CleanUpBeforeFlip()
    {
        for (int i = 0; i < 4; i++)
        {
            String[] pieces = mySplit(activeGridBlocks[i],",");
            int row = Integer.parseInt(pieces[0].trim());
            int col = Integer.parseInt(pieces[1].trim());
            int level = Integer.parseInt(pieces[2].trim());
            TetrisGrid(level,row,col, null);

        }

    }
    public boolean isActive(int row, int col)
    {
        for (String option : activeGridBlocks)
        {
            String[] pieces = mySplit(option,",");
            int rowCur = Integer.parseInt(pieces[0].trim());
            int colCur = Integer.parseInt(pieces[1].trim());

            if (row == rowCur && col == colCur)
                return true;

        }
        return false;
    }
    public boolean canFlip(String[] options)
    {
        for (String option : options)
        {
            String[] pieces = mySplit(option,",");
            int row = Integer.parseInt(pieces[0].trim());
            int col = Integer.parseInt(pieces[1].trim());

            if (((tetrisGrid.get(0))[row][ col]) != null
                    && !isActive(row, col))
                return false;
        }
        return true;
    }
    public boolean ActivateNewFlip(String pieceInfo, boolean Flip)
    {
        int current = Integer.parseInt(((mySplit(pieceInfo,"`")[0]).split("_"))[3]);
        int total = Integer.parseInt(((mySplit(pieceInfo,"`")[0]).split("_"))[2]);
        String[] options = new String[4];
        int newCurrent = 0;

        if (current < total)
        {
            if (Flip)
            {
                options = (mySplit(pieceInfo,"`"))[1].split("&")
                        [current /*- 1*/].split("\\.");
                newCurrent = current + 1;
            }
            else
            {
                options = (mySplit(pieceInfo,"`"))[1].split("&")
                        [current - 1].split("\\.");
            }
        }
        else
        {
            if (Flip)
            {
                options = (mySplit(pieceInfo,"`"))[1].split("&")
                        [0 /*- 1*/].split("\\.");
                newCurrent = 1;
            }
            else
            {
                options = (mySplit(pieceInfo,"`"))[1].split("&")
                        [current - 1].split("\\.");
            }
        }

        if (allInRange(options))
        {
            if (Flip)
                pieceInfo = ChangeCurrent(pieceInfo, newCurrent);

            if (!Flip)
                CleanUpBeforeFlip(-1, 0);

            if (!Flip || (Flip && canFlip(options)))
            {
                if (Flip)
                    CleanUpBeforeFlip();

                for (int i = 0; i < 4; i++)
                {
                    activeGridBlocks[i] = options[i];
                }
                ActivateNewCont(pieceInfo);
            }
            return true;
        }
        return false;

    }
    public void ActivateNew(String pieceInfo)
    {
        int current = Integer.parseInt(((mySplit(pieceInfo,"`")[0]).split("_"))[3]);

        String[] options = (mySplit(pieceInfo,"`"))[1].split("&")
                [current - 1].split("\\.");

        for (int i = 0; i < options.length; i++)
        {
            String[] chops = mySplit(options[i],",");

            options[i] = chops[0] + "," + (Integer.parseInt(chops[1])) + "," + chops[2];
        }

        for (int i = 0; i < 4; i++)
        {
            activeGridBlocks[i] = options[i];
        }
        //ActivateNewCont(pieceInfo);

    }
    public void ActivateNewNext(String pieceInfo)
    {
        int current = Integer.parseInt(((mySplit(pieceInfo,"`")[0]).split("_"))[3]);
        Exception exp;
        String[] options = null;
        //String[] options = (pieceInfo.split("`"))[1].split("&")
        //        [current - 1].split("\\.");
        try
        {
            String[] whole = (mySplit(pieceInfo,"`"));
            String[] separated = whole[1].split("&");
            String value = separated[current - 1];
            options = value.split("\\.");

        }
        catch (Exception ex)
        {
            exp = ex;
        }

        activePrevie = new String[4];

        for (int i = 0; i < 4; i++)
        {
            activePrevie[i] = options[i];
        }
        ActivateNewCont(pieceInfo);

    }
    String nextPieceToShow = null;
    String activePrevieSymbol = null;
    String[] activePrevie = null;
    public boolean canLaunch(String pieces)
    {
        String[] points = pieces.split("\\.");

        for (String point : points)
        {
            String[] pointPiece = mySplit(point,",");
            try {
                if (((tetrisGrid.get(Integer.parseInt(pointPiece[2])))[Integer.parseInt(pointPiece[0])][Integer.parseInt(pointPiece[1])]) != null)
                    return false;
            }
            catch (Exception ex)
            {
                //out of range
                return false;
            }
        }
        return true;
    }
    private void ShowMessage(String msg, String caption)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(caption);
        alertDialog.setMessage(msg);
        // alertDialog.setButton("OK",);
        //alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();
    }
    public void endGame(boolean showIt)
    {
        started = false;

        if (showIt)
        {
            ShowMessage("Your score is :" + _score, "Game Over"); //backin
            //ScreenGame.Visibility = System.Windows.Visibility.Collapsed;
            //ScreenMenu.Visibility = System.Windows.Visibility.Visible;
        }
            RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.myScreenMenu);
            layout1.setVisibility(View.VISIBLE);

            RelativeLayout layout2 = (RelativeLayout) findViewById(R.id.Playground);
            layout2.setVisibility(View.GONE);

        Clear();
    }
    private String Trim(String value, Character cut)
    {
        while (value.endsWith(String.valueOf(cut)))
        {
            value = value.substring(0, value.length() -1);
        }

        while (value.startsWith(String.valueOf(cut)))
        {
            value = value.substring(1);
        }

        return  value;
    }
    public void launchPiece()
    {
        launching = true;
        //DeactivateCurrent();
        String[] nextPiece = GetPieceHardCore();
        int numberOfPieces = nextPiece.length - 1;
        int currentOne = Random(numberOfPieces);

        String content = nextPiece[0] + "_moving_"
                + String.valueOf(numberOfPieces) + "_" + currentOne + "`";

        for (int i = 1; i < nextPiece.length; i++)
        {
            content += nextPiece[i] + "&";
        }
        content = Trim( content,'&');
        if (canLaunch(nextPiece[currentOne]))
        {

                String nextOne = content;
                ActivateNew(nextOne);
                ActivateNewNext(nextOne);
                //nextPieceToShow = nextOne;
                //activePrevieSymbol = nextOne.substring(0, 1);
                //showPreviewPiece();

        }
        else
        {
            endGame(true);
        }
        launching = false;
    }
    public void launch()
    {
        keyPressesEnabled = false;
        EatFullRows();
        launchPiece();
        showPieces();
        keyPressesEnabled = true;
        //timer.Start();

    }
    boolean keyPressesEnabled = true;
    public boolean isIn(int row, int col, int _level, String[] array)
    {
        for (String active : array)
        {
            String[] pieces = mySplit(active,",");
            int rowCur = Integer.parseInt(pieces[0].trim());
            int colCur = Integer.parseInt(pieces[1].trim());
            int levelCur = Integer.parseInt(pieces[2].trim());

            if (rowCur == row && colCur == col && _level == levelCur)
                return true;
        }
        return false;
    }
    public String[] GetPiecesAfterMoveSadow(int rowOffSet, int colOffSet, int levelOffSet)
    {
        String[] after = new String[4];

        for (int i = 0; i < 4; i++)
        {
            String[] pieces = mySplit(shadowPieces[i],",");
            after[i] = String.valueOf(Integer.parseInt(pieces[0]) + rowOffSet) + "," +
                    String.valueOf(Integer.parseInt(pieces[1]) + colOffSet) + "," +
                    String.valueOf(Integer.parseInt(pieces[2]) + levelOffSet);
        }
        return after;
    }
    private String[] GetPiecesAfterMove(int rowOffSet, int colOffSet, int levelOffSet)
    {
        String[] after = new String[4];

        for (int i = 0; i < 4; i++)
        {
            String[] pieces = mySplit(activeGridBlocks[i],",");
            after[i] = String.valueOf(Integer.parseInt(pieces[0]) + rowOffSet) + "," +
                    String.valueOf (Integer.parseInt(pieces[1]) + colOffSet) + "," +
                    String.valueOf(Integer.parseInt(pieces[2]) + levelOffSet);
        }
        return after;
    }
    public String[] GetPiecesAfterMoveFlitSideways()
    {
        String[] after = new String[4];
        String[] pieces = mySplit(activeGridBlocks[1],",");
        int mainRow = Integer.parseInt(pieces[0]);
        int mainCol = Integer.parseInt(pieces[1]);
        int mainLevel = Integer.parseInt(pieces[2]);

        for (int i = 0; i < 4; i++)
        {
            String[] morePieces = mySplit(activeGridBlocks[i],",");
            int currentRow = Integer.parseInt(morePieces[0]);
            int currentCol = Integer.parseInt(morePieces[1]);
            int currentLevel = Integer.parseInt(morePieces[2]);

            //#region Figure Out Region
            if (currentCol != mainCol )
            {
                if (currentCol == mainCol + 1)
                {
                    currentCol--;
                    currentLevel++;
                }
                else if (currentCol == mainCol + 2)
                {
                    currentCol--;
                    currentCol--;
                    currentLevel++;
                    currentLevel++;
                }
                else if (currentCol == mainCol - 1)
                {
                    currentCol++;
                    currentLevel--;
                }
                else if (currentCol == mainCol -2)
                {
                    currentCol++;
                    currentCol++;
                    currentLevel--;
                    currentLevel--;
                }
            }
            else if (mainLevel != currentLevel)
            {
                if (currentLevel == mainLevel + 1)
                {
                    currentLevel--;
                    currentCol--;
                }
                else if (currentLevel == mainLevel + 2)
                {
                    currentLevel--;
                    currentCol--;
                    currentLevel--;
                    currentCol--;
                }
                else if (currentLevel == mainLevel - 1)
                {
                    currentLevel++;
                    currentCol++;
                }
                else if (currentLevel == mainLevel - 2)
                {
                    currentLevel++;
                    currentCol++;
                }


            }
            //#endregion Figure Out Region

            after[i] = String.valueOf (currentRow) + "," + String.valueOf(currentCol) + "," +
                    String.valueOf(currentLevel);
        }
        return after;
    }
    public void FlipSideWays()
    {
        sideWayPieces = null;
        if(started)
            if(keyPressesEnabled)
                if(canFlipSideways()) {
                    MoveAllSideways(sideWayPieces);
                    showPieces();
                }
    }
    public void MoveAllSideways(String[] values)
    {
        String[] after = values;
        String[] prev = new String[4];
        //activeGridBlocks.CopyTo(prev, 0);
        prev = activeGridBlocks.clone();
        String value = MoveAllStringFlipSideways();
        activeGridBlocks = after;
        for (int i = 0; i < 4; i++)
        {
            String[] pieceedPrev = mySplit(prev[i],",");
            int row = Integer.parseInt(pieceedPrev[0].trim());
            int col = Integer.parseInt(pieceedPrev[1].trim());
            int level = Integer.parseInt(pieceedPrev[2].trim());
            //tetrisGrid[level][row, col] = null;
            TetrisGrid(level,row,col, null);

        }
        for (int i = 0; i < 4; i++)
        {
            String[] pieceedPrev = mySplit(activeGridBlocks[i],",");
            int row = Integer.parseInt(pieceedPrev[0].trim());
            int col = Integer.parseInt(pieceedPrev[1].trim());
            int level = Integer.parseInt(pieceedPrev[2].trim());
            //tetrisGrid[level][row, col] = value;
            TetrisGrid(level, row, col, value);
        }
    }
    public String MoveAllStringFlipSideways()
    {
        String[] pieces = mySplit(activeGridBlocks[0],",");
        int mainRow = Integer.parseInt(pieces[0]);
        int mainCol = Integer.parseInt(pieces[1]);
        int mainLevel = Integer.parseInt(pieces[2]);
        String pieceInfo = (tetrisGrid.get(Integer.parseInt(pieces[2])))[Integer.parseInt(pieces[0].trim())
                ][ Integer.parseInt(pieces[1].trim())];
        String after = (mySplit(pieceInfo,"`"))[0] + "`";
        String[] options = (mySplit(pieceInfo,"`"))[1].split("&");

        for (int i = 0; i < options.length; i++)
        {
            String afterMove = "";
            String[] piece = options[i].split("\\.");

            for (int j = 0; j < piece.length; j++)
            {

                String[] morePieces =mySplit( piece[j],",");
                int currentRow = Integer.parseInt(morePieces[0]);
                int currentCol = Integer.parseInt(morePieces[1]);
                int currentLevel = Integer.parseInt(morePieces[2]);

                //#region Figure Out Region
                if (currentCol != mainCol )
                {
                    if (currentCol == mainCol + 1)
                    {
                        currentCol--;
                        currentLevel++;
                    }
                    else if (currentCol == mainCol + 2)
                    {
                        currentCol--;
                        currentCol--;
                        currentLevel++;
                        currentLevel++;
                    }
                    else if (currentCol == mainCol - 1)
                    {
                        currentCol++;
                        currentLevel--;
                    }
                    else if (currentCol == mainCol -2)
                    {
                        currentCol++;
                        currentCol++;
                        currentLevel--;
                        currentLevel--;
                    }
                }
                else if (mainLevel != currentLevel)
                {
                    if (currentLevel == mainLevel + 1)
                    {
                        currentLevel--;
                        currentCol--;
                    }
                    else if (currentLevel == mainLevel + 2)
                    {
                        currentLevel--;
                        currentCol--;
                        currentLevel--;
                        currentCol--;
                    }
                    else if (currentLevel == mainLevel - 1)
                    {
                        currentLevel++;
                        currentCol++;
                    }
                    else if (currentLevel == mainLevel - 2)
                    {
                        currentLevel++;
                        currentCol++;
                    }


                }
                //#endregion Figure Out Region

                afterMove += (currentRow) + "," + (currentCol) + "," + (currentLevel) + ".";
            }
            after += Trim (afterMove,'.') + "&";
        }
        after = Trim( after,'&');
        return after;


    }
    String[] sideWayPieces;
    public boolean canFlipSideways()
    {
        sideWayPieces = GetPiecesAfterMoveFlitSideways();

        for (int i = 0; i < 4; i++)
        {
            String[] subPieces = mySplit(sideWayPieces[i],",");
            int row = Integer.parseInt(subPieces[0]);
            int col = Integer.parseInt(subPieces[1]);
            int level = Integer.parseInt(subPieces[2]);

            try
            {
                String value = (tetrisGrid.get(level))[row][ col];


            }
            catch(Exception ex)
            {
                return false;
            }
        }
        return true;
    }
    public boolean CanMoveBackwards()
    {
        for (int i = 0; i < activeGridBlocks.length; i++)
        {
            String[] values = mySplit(activeGridBlocks[i],",");
            int _row = Integer.parseInt(values[0]);
            int _col = Integer.parseInt(values[1]);
            int _level = Integer.parseInt(values[2]);

            if ((_level + 1) >= 7)
                return false;
            else if (((tetrisGrid.get(_level + 1))[_row][ _col]) != null
                    && !isIn(_row,_col,_level + 1, activeGridBlocks))
                return false;
        }

        return true;
    }
    public boolean CanMoveForward()
    {
        for (int i = 0; i < activeGridBlocks.length; i++)
        {
            String[] values = mySplit(activeGridBlocks[i],",");

            int _row = Integer.parseInt(values[0]);
            int _col = Integer.parseInt(values[1]);
            int _level = Integer.parseInt(values[2]);

            if ((_level -1) < 0)
                return false;
            else if (((tetrisGrid.get(_level -1))[_row][ _col]) != null
                    && !isIn(_row, _col, _level - 1, activeGridBlocks))
                return false;
        }

        return true;
    }
    public void MoveAllFoward()
    {
        while (busy)
        { }

        busy = true;
        keyPressesEnabled = false;

        if (CanMoveForward())
        {
            MoveAll(0, 0, -1);
            showPieces();
        }
        busy = false;
        keyPressesEnabled = true;

    }
    private void MoveAll(int rowOffSet, int colOffSet, int levelOffSet)
    {
        String[] after = GetPiecesAfterMove(rowOffSet, colOffSet, levelOffSet);
        String[] prev = new String[4];
        prev = activeGridBlocks.clone();
        String value = MoveAllString(rowOffSet,colOffSet, levelOffSet);
        activeGridBlocks = after;

        ApplyChanges(prev, null); //clean previous

        ApplyChanges(activeGridBlocks,value); // set new
    }
    private void ApplyChanges(String[] pieces, String value)
    {
        for (int i = 0; i < 4; i++)
        {
            String[] pieceedPrev = mySplit(pieces[i],",");
            int row = Integer.parseInt(pieceedPrev[0].trim());
            int col = Integer.parseInt(pieceedPrev[1].trim());
            int level = Integer.parseInt(pieceedPrev[2].trim());
            TetrisGrid(level, row, col, value);
        }
    }
    public void MoveAllBackwards()
    {
        while (busy)
        { }

        busy = true;
        keyPressesEnabled = false;
        if (CanMoveBackwards())
        {
            MoveAll(0, 0, 1);
            showPieces();
        }
        busy = false;
        keyPressesEnabled = true;
    }
    public void MoveAllDown()
    {
        MoveAll(1, 0,0);
    }
    public void MoveAllDownShadow()
    {
        MoveAllShadow(1, 0, 0);
        //tetrisGrid[0][rowCur, colCur] = pieceInfor;
    }
    public void MoveAllShadow(int rowOffSet, int colOffSet, int levelOffSet)
    {
        String[] after = GetPiecesAfterMoveSadow(rowOffSet, colOffSet, levelOffSet);
        String[] prev = new String[4];
        //shadowPieces.CopyTo(prev, 0);
        prev = shadowPieces.clone();
        //string value = MoveAllString(rowOffSet, colOffSet, levelOffSet);
        shadowPieces = after;

    }
    public void MoveAllRight()
    {
        MoveAll(0, 1,0);
    }
    public void MoveAllLeft()
    {
        MoveAll(0, -1, 0);
    }
    public String[] mySplit(String text,String delemeter){
        java.util.List<String> parts = new java.util.ArrayList<String>();

        text+=delemeter;

        for (int i = text.indexOf(delemeter), j=0; i != -1;) {
            parts.add(text.substring(j,i));
            j=i+delemeter.length();
            i = text.indexOf(delemeter,j);
        }

        return parts.toArray(new String[0]);
    }
    public String MoveAllString(int offSetRow, int OffSetCol, int levelOffSet)
    {
        String[] pieces = mySplit(activeGridBlocks[0],",");
        String pieceInfo = (tetrisGrid.get(Integer.parseInt(pieces[2])))[Integer.parseInt(pieces[0].trim())
                ][ Integer.parseInt(pieces[1].trim())];
        String[] prepare = mySplit(pieceInfo,"`");
        String after = prepare[0] + "`";
        String[] optionsUnseperated =(mySplit(pieceInfo,"`"));
        String[] options = mySplit((optionsUnseperated[1]),"&");

        for (int i = 0; i < options.length; i++)
        {
            String afterMove = "";
            String[] piece = options[i].split("\\.");

            for (int j = 0; j < piece.length; j++)
            {
                String[] morePieces = mySplit(piece[j],",");
                afterMove += (Integer.parseInt(morePieces[0]) + offSetRow) + "," +
                        (Integer.parseInt(morePieces[1]) + OffSetCol) + "," +  (levelOffSet + Integer.parseInt(morePieces[2])) + ".";
            }
            after += Trim( afterMove,'.') + "&";
        }
        after = Trim(after, '&');
        return after;


    }
    public boolean flip()
    {
        while (busy)
        { }

        busy = true;
        keyPressesEnabled = false;
        int row = Integer.parseInt(mySplit(activeGridBlocks[0],",")[0]);
        int col = Integer.parseInt(mySplit(activeGridBlocks[0],",")[1]);
        int level = Integer.parseInt(mySplit(activeGridBlocks[0],",")[2].trim());

        if (ActivateNewFlip(((tetrisGrid.get(level))[row][ col]), true))
        {
            showPieces();
            busy = false;
            keyPressesEnabled = true;
            return true;
        }
        busy = false;
        keyPressesEnabled = true;
        return false;
    }
    public boolean MoveLeft()
    {
        while (busy)
        { }

        if (canMoveToLeft())
        {
            //#region Move

            busy = true;
            keyPressesEnabled = false;



            MoveAllLeft();
            showPieces();
            busy = false;
            keyPressesEnabled = true;
            return true;
            //#endregion Move
        }
        return false;
    }
    public boolean canMoveToLeft()
    {
        for (int i = 0; i < 4; i++)
        {
            String[] pieces = mySplit(activeGridBlocks[i],",");
            int row = Integer.parseInt(pieces[0].trim());
            int col = Integer.parseInt(pieces[1].trim());
            int level = Integer.parseInt(pieces[2].trim());

            if ((col - 1 >= 0) && (((tetrisGrid.get(level))[row][ col - 1]) == null
                    || isIn(row, col - 1, level, activeGridBlocks)))
            // (tetrisGrid[0][row , col + 1].Split('_')[1].Trim().ToLower() == "moving"))
            {

            }
            else
            {
                return false;
            }
        }
        return true;
    }
    int linesForFullRows = 0;
    public boolean thereAreFullRows( int _level)
    {
        linesForFullRows = 0;
        for (int row = 17; row >= 0; row--)
        {
            boolean rowFull = true;
            for (int col = 0; col < 7; col++)
            {
                if (((tetrisGrid.get(_level))[row][ col]) == null)
                {
                    rowFull = false;
                    break;
                }
            }

            if (rowFull)
                linesForFullRows++;
        }

        if (linesForFullRows > 0)
            return true;
        else
            return false;
    }
    public void EatFullRowsStraight()
    {
        keyPressesEnabled = false;
        int lines = 0;

        for (int _level = 0; _level < 7; _level++)
        {

            if (thereAreFullRows( _level))
            {
                for (int j = 0; j < 4; j++)
                {
                    for (int i = 17; i > 0; i--)
                    {
                        if (RowIsFull(i, _level))// || RowIsEmpty(i))
                            MoveRowsAboveThisRowDown(i, _level);
                    }
                }
                score(linesForFullRows);
            }
        }

        keyPressesEnabled = true;
    }
    int linesThereAreFullRowsTwo =0;
    public boolean thereAreFullRows( int myCol, boolean level)
    {
        linesThereAreFullRowsTwo = 0;
        for (int row = 17; row >= 0; row--)
        {
            boolean rowFull = true;
            for (int col = 0; col < 7; col++)
            {
                if (((tetrisGrid.get(col))[row][ myCol]) == null)
                {
                    rowFull = false;
                    break;
                }
            }

            if (rowFull)
                linesThereAreFullRowsTwo++;
        }

        if (linesThereAreFullRowsTwo > 0)
            return true;
        else
            return false;
    }
    public void EatFullRowsBack()
    {
        keyPressesEnabled = false;
        int lines = 0;

        for (int col = 0; col < 7; col++)
        {
            if (thereAreFullRows( col, true))
            {
                for (int j = 0; j < 4; j++)
                {
                    for (int i = 17; i > 0; i--)
                    {
                        if (RowIsFull(i, col,true))// || RowIsEmpty(i))
                            MoveRowsAboveThisRowDown(i, col, true);
                    }
                }
                score(linesThereAreFullRowsTwo);
            }
        }

        keyPressesEnabled = true;
    }
    public void MoveRowsAboveThisRowDown(int row, int myCol, boolean level)
    {
        for (int j = row; j > 0; j--)
        {
            for (int i = 0; i < 7; i++)
            {
                if (((tetrisGrid.get(i))[j - 1][ myCol]) != null)
                {
                    // tetrisGrid[i][j, myCol] = tetrisGrid[i][j - 1, myCol];
                    TetrisGrid(i,j,myCol,(tetrisGrid.get(i))[j-1][myCol]);
                    TetrisGrid(i,j-1,myCol, null);
                    //tetrisGrid[i][j - 1, myCol] = null;
                }
                else
                {
                    //tetrisGrid[i][j, myCol]=null;
                    TetrisGrid(i,j,myCol,null);
                }
            }
        }
    }
    public void MoveRowsAboveThisRowDown(int row, int _level)
    {
        for (int j = row; j > 0; j--)
        {
            for (int i = 0; i < 7; i++)
            {
                if (((tetrisGrid.get(_level))[j - 1][ i]) != null)
                {
                    //tetrisGrid.get(_level)[j][ i] = ;
                    TetrisGrid(_level, j,i, (tetrisGrid.get(_level))[j - 1][ i]);
                    TetrisGrid(_level,j-1, i, null);
                    //tetrisGrid[_level][j - 1, i] = null;
                }
                else
                {
                    //tetrisGrid[_level][j, i] = null;
                    TetrisGrid(_level,j,i, null);
                }
            }
        }
    }
    public boolean RowIsFull(int row, int col, boolean level)//backwardsFunctionality
    {
        for (int i = 0; i < 7; i++)
        {
            if (((tetrisGrid.get(i))[row][ col]) == null)
                return false;
        }
        return true;
    }
    public boolean RowIsFull(int row, int _level)
    {
        for (int i = 0; i < 7; i++)
        {
            if ((tetrisGrid.get(_level))[row][ i] == null)
                return false;
        }
        return true;
    }
    public void score(int lines)
    {
        int[] points = new int[] { 0, 40, 100, 300, 1200 };

        int gainedPoints = points[lines] * level;

        _score += gainedPoints;
        //showScore.Text = _score.ToString();
    }
    public void EatFullRows()
    {
        EatFullRowsStraight();
        EatFullRowsBack();
    }
    public boolean MoveRight()
    {
        while (busy)
        { }

        if (canMoveRight())
        {
            //#region Move

            busy = true;
            keyPressesEnabled = false;

            MoveAllRight();
            showPieces();
            busy = false;
            keyPressesEnabled = true;
            return true;

            //#endregion Move
        }
        return false;
    }
    public void MoveDownShadow()
    {
        MoveAllDownShadow();
        //busy = false;
        //keyPressesEnabled = true;
    }
    public boolean MoveDown()
    {

        MoveAllDown();
        //int rowCur = int.Parse(activeGridBlocks[0].Split(',')[0]);
        //int colCur = int.Parse(activeGridBlocks[0].Split(',')[1]);
        //int level2 = int.Parse(activeGridBlocks[0].Split(',')[2].Trim());
        //ActivateNewFlip(tetrisGrid[level2][rowCur, colCur], false);
        showPieces();
        //busy = false;
        //keyPressesEnabled = true;
        return true;
    }
    public boolean canMoveLeft()
    {
        for (int i = 0; i < 4; i++)
        {
            String[] pieces = mySplit(activeGridBlocks[i],",");
            int row = Integer.parseInt(pieces[0].trim());
            int col = Integer.parseInt(pieces[1].trim());
            int level = Integer.parseInt(pieces[2].trim());
            if (col - 1 < 0)
                return false;
            else if ((tetrisGrid.get(level))[row][ col - 1] != null
                    && !isIn(1, col - 1, level, activeGridBlocks))
                return false;
        }
        return true;
    }
    public boolean canMoveRight()
    {
        for (int i = 0; i < 4; i++)
        {
            String[] pieces = mySplit(activeGridBlocks[i],",");
            int row = Integer.parseInt(pieces[0].trim());
            int col = Integer.parseInt(pieces[1].trim());
            int level = Integer.parseInt(pieces[2].trim());
            if (col + 1 > 6)
                return false;
            else if ((tetrisGrid.get(level))[row][ col + 1] != null
                    && !isIn(row, col + 1, level, activeGridBlocks))
                return false;
        }
        return true;
    }
    public boolean canMoveDownShadow()
    {
        for (int i = 0; i < 4; i++)
        {
            String[] pieces = mySplit(shadowPieces[i],",");
            int row = Integer.parseInt(pieces[0].trim());
            int col = Integer.parseInt(pieces[1].trim());
            int level = Integer.parseInt(pieces[2].trim());

            if (row + 1 > 17)
                return false;
            else if ((tetrisGrid.get(level))[row + 1][ col] != null
                    && !isIn(row + 1, col, level, shadowPieces))
                return false;
        }
        return true;
    }
    public boolean canMoveDown()
    {
        for (int i = 0; i < 4; i++)
        {
            String[] pieces = mySplit(activeGridBlocks[i],",");
            int row = Integer.parseInt(pieces[0].trim());
            int col = Integer.parseInt(pieces[1].trim());
            int level = Integer.parseInt(pieces[2].trim());

            if (row + 1 > 17)
                return false;
            else if ((tetrisGrid.get(level))[row + 1][ col] != null
                    && !isIn(row + 1, col,level, activeGridBlocks))
                return false;
        }
        return true;
    }
    public void Shadow()
    {
        shadowPieces = activeGridBlocks.clone();//.copy(shadowPieces, 0);
        boolean keepMoving = true;
        keyPressesEnabled = false;

        if (shadowPieces[0] != null)
        {
            boolean moved = false;

            while (keepMoving)
            {
                keepMoving = canMoveDownShadow();
                if (keepMoving)
                {
                    moved = true;
                    MoveDownShadow();
                }
            }

            if (moved)
            {
                for (int i = 0; i < 4; i++)
                {
                    String[] pieces = mySplit(shadowPieces[i],",");
                    int row = Integer.parseInt(pieces[0]) - 4;
                    int col = Integer.parseInt(pieces[1]);
                    int level = Integer.parseInt(pieces[2]);

                    if(!isIn(row +4, col,level, activeGridBlocks))
                    {
                        //view.ChangeCell(row, col, level, getMyImage("pieces/Shadow"));
                        ChangeImage(row,col,level,"shadow");
                    }
                }
            }
        }
        keyPressesEnabled = true;
    }
    String [] shadowPieces = new String[4];

    public void drop()
    {
        boolean keepMoving = true;
        keyPressesEnabled = false;

        while (keepMoving)
        {
            keepMoving = canMoveDown();
            if (keepMoving)
            {
                MoveDown();
            }
        }
        _score += 3;
        keyPressesEnabled = true;
        launch();
    }
    boolean started = false;

    public void mover()
    {
        while (busy)
        { }

        if (!movingDown)
        {
            movingDown = true;

            if (!launching)
            {
                if (activeGridBlocks[0] != null)
                {
                    if (canMoveDown())
                        MoveDown();
                    else
                        launch();
                }

            }
            movingDown = false;
        }

    }
    public void Clear()
    {
        //showLevel.Text = "";
        //showScore.Text = "";
        InitializeScreenGrid();
        activeGridBlocks = new String[4];
        speed = 700;
        //CleanPreview();
        //showPieces();
    }
    public void InitializeScreenGrid()
    {
        tetrisGrid = new ArrayList<String[][]>();

        for (int i = 0; i < 7; i++)
        {
            tetrisGrid.add(new String[18][7]);
        }
    }
    private static String getSizeName(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case android.content.res.Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "small";
            case android.content.res.Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "normal";
            case android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "large";
            case 4: // Configuration.SCREENLAYOUT_SIZE_XLARGE is API >= 9
                return "xlarge";
            default:
                return "undefined";
        }
    }
    boolean movingDown = false;
    int dropCount = 0;
    int minutes = 0;
    public void startGame()
    {

        Clear();
        DoStuff();


        showPieces();
        started = true;

        launch();

    }
    public void showPieces()
    {
        for (int myLevel = 0; myLevel < 7; myLevel++)
        {
            for (int row = 4; row < 18; row++)
            {
                for (int col = 0; col < 7; col++)
                {

                    if (((tetrisGrid.get(myLevel))[row ][ col]) != null)
                    {

                            String value = (((tetrisGrid.get(myLevel))[row ][col].split("_"))[0]).toLowerCase();
                            ChangeImage(row - 4, col, myLevel, value.trim().toLowerCase());
                    }
                    else
                    {
                        if (row != 17)
                        {
                            ChangeImage(row -4, col, myLevel, "c");
                        }
                        else
                        {
                            ChangeImage(row -4, col, myLevel, "empty");
                        }
                    }

                }
            }
        }
        Shadow();
    }
    public void AdjustSpeed()
    {
        if (level == 2)
            speed = 700;
        else if (level == 3)
            speed =  550;
        else if (level == 4)
            speed = 380;
        else if (level == 5)
            speed = 200;
        else if (level == 6)
            speed = 150;
        else if (level == 7)
            speed = 100;
        else if (level == 8)
            speed = 80;
        else if (level == 9)
            speed = 50;
        else if (level == 10)
            speed = 25;
        else if (level == 11)
            speed = 15;
        else if (level == 12)
            speed = 8;
        else if (level == 14)
            speed = 5;
        else
            speed = 3;
    }
}


