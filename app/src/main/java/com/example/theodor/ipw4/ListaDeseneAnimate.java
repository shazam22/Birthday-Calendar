package com.example.theodor.ipw4;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by theodor on 10.08.2015.
 */


public class ListaDeseneAnimate extends ActionBarActivity
{
    // ArrayList<Personaj> personaje; -> mutat in PersonajeAdapter
    PersonajeAdapter adapter;
    public int contor;
    public String email;

    @Override
    public void onCreate (Bundle savedInstanceBundle)
    {
        contor = 0;
        super.onCreate (savedInstanceBundle);
        // personaje = new ArrayList<Personaj>(); -> mutat in Personaje Adapter
        adapter = new PersonajeAdapter (this);
        setContentView(R.layout.activity_main);
        //adaugam cateva personaje in lista
        adapter.adaugaFunnyGuy("Brad", "Pitt");
        adapter.adaugaFunnyGuy("Fred", "Flinstone");
        adapter.adaugaFunnyGuy("Johnny", "Depp");
        adapter.adaugaFunnyGuy("Natalie", "Portman");
        adapter.adaugaFunnyGuy("Christian", "Bale");
        adapter.adaugaFunnyGuy("The", "Batman");
        adapter.adaugaFunnyGuy("The", "Joker");
        adapter.adaugaFunnyGuy("Morgan", "Freeman");
        adapter.adaugaFunnyGuy("Keira", "Knightley");
        Button btn = (Button) findViewById(R.id.adauga1);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListaDeseneAnimate.this, BirthdayDetails.class);
                startActivityForResult(intent, 2);
            }
        };
        // pentru a seta actiunea click lung
        GridView grid = (GridView)findViewById(R.id.grid);

        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> listAdapter, View view,
                                           int position, long id) {
                //Personaj p = (Personaj) adapter.getItem(position);
                //Toast.makeText(ListaDeseneAnimate.this, "click lung pe " + p.nume, Toast.LENGTH_LONG).show();
                adapter.personaje.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        btn.setOnClickListener(listener);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int nr, long l) {
                //++contor;
                //Button b = (Button) ;
                //String bText = b.getText().toString();
                //Toast.makeText(this,bText, Toast.LENGTH_SHORT).show();
                //TextView textView = (TextView) findViewById(R.id.text);
                //textView.setText(bText);
                //System.out.println(contor);

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT, "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ListaDeseneAnimate.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //setListAdapter (adapter);
        grid.setAdapter(adapter);
    }


    @Override
    public void onActivityResult (int requestCode, int responseCode, Intent data)
    {
        // raspunsul vine de la formularul de adaugare

        System.out.println("onActivity");
        if (requestCode == 2)
        {
            System.out.println("reqCode " + responseCode);
            // s-a apasat butonul Adauga
            if (responseCode==2)
            {
                String nume = data.getStringExtra("nume");
                String desen = data.getStringExtra("desen");

                Personaj p = new Personaj();
                //EditText nume = (EditText)findViewById(R.id.nume);
                //EditText desen = (EditText) findViewById(R.id.desen);
                p.nume = nume;
                p.desen = desen;
                p.myURI = data.getStringExtra("uri");
                p.year = data.getIntExtra("year", 0);
                p.month = data.getIntExtra("month", 0);
                p.day = data.getIntExtra("day", 0);
                p.email = data.getStringExtra("email");
                System.out.println("OVER HERE "+p.email);
                email = p.email;

               // System.out.println("OVER HERE "+data.getIntExtra("year", 0)+" "+data.getIntExtra("month",0));
                ++contor;
                adapter.personaje.add(p);
                System.out.println("P.MYUri: "+p.myURI);
                //System.out.println ("This is it");
                adapter.notifyDataSetChanged();

                // adaptor.adaugaFilm (nume, an, regizor);
            }
            // s-a apasat butonul Renunta
            else if (responseCode==0)
            {
            }
        }
    }


    public void apasa (View v){
        ++contor;
        Button b = (Button) v;
        String bText = b.getText().toString();
        Toast.makeText(this,bText, Toast.LENGTH_SHORT).show();
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(bText);
        System.out.println(contor);

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT, "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ListaDeseneAnimate.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }


   // @Override
    public void onListItemClick (ListView list, View v, int position, long id)
    {
        // afisam numele personajului pe care s-a dat click folosind un Toast
        Personaj p = (Personaj)adapter.getItem (position);
        Toast.makeText(this, p.nume, Toast.LENGTH_LONG).show();
    }

}