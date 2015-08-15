package com.example.theodor.ipw4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by theodor on 13.08.2015.
 */
public class BirthdayDetails extends ActionBarActivity {
    Uri vi;
    int myYear, myMonth, myDay;
    //DatePicker myDatePicker = (DatePicker) findViewById(R.id.date1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatePicker myDatePicker = (DatePicker) findViewById(R.id.date1);
        Calendar c = Calendar.getInstance();
        DatePicker.OnDateChangedListener dateSetListener = new DatePicker.OnDateChangedListener() {

            public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                //c.set(year, monthOfYear, dayOfMonth);
                //System.out.println ("TEST");
                myYear = year;
                myDay = dayOfMonth;
                myMonth = monthOfYear;

            }
        };

        setContentView(R.layout.birthday);
        Intent intent = getIntent();
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
    public void clickImage(View v)
    {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        final int ACTIVITY_SELECT_IMAGE = 1234;
        startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
    }

    public void Save (View v)
    {
        Intent raspuns = new Intent();
        EditText edit1 = (EditText) findViewById(R.id.edit1);
        EditText edit2 = (EditText) findViewById(R.id.edit2);
        EditText edit3 = (EditText) findViewById(R.id.edit3);
        raspuns.putExtra("nume", edit1.getText().toString());
        System.out.println("THIS " + edit1.getText().toString());
        raspuns.putExtra("desen", edit2.getText().toString());
        raspuns.putExtra("uri", vi.toString());
        raspuns.putExtra("email", edit3.getText().toString());
        DatePicker datePicker = (DatePicker)findViewById(R.id.date1);
        myDay = datePicker.getDayOfMonth();
        myMonth = datePicker.getMonth();
        myYear = datePicker.getYear();
        raspuns.putExtra("year", myYear);
        raspuns.putExtra("month", myMonth);
        raspuns.putExtra("day", myDay);
        Toast.makeText(this, myYear+ " "+myMonth+" "+myDay,Toast.LENGTH_SHORT).show();
        setResult(2, raspuns);
        finish();
    }

    public void onActivityResult (int requestCode, int responseCode, Intent data)
    {
        System.out.println("onActivity");
        System.out.println(requestCode);
        System.out.println(responseCode);
        if (responseCode == -1)
        {
            System.out.println("respCode");
            if (requestCode == 1234)
            {
                Uri u = data.getData();
                vi = u;
                //System.out.println(u);
                //ImageView v = (ImageView)findViewById(R.id.view1);
                //v.setImageURI(u);
                System.out.println("URI" + u);
            }
        }
    }
}
