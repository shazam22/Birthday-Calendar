package com.example.theodor.ipw4;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by theodor on 10.08.2015.
 */
class PersonajeAdapter extends BaseAdapter
{
    private Activity context;
    public int contor;
    public ArrayList<Personaj> personaje;

    public PersonajeAdapter (Activity _context)
    {
        //contor = 0;
        this.context = _context;
        personaje = new ArrayList<Personaj>();
    }

    @Override
    public View getView (int position, View convertView, ViewGroup list)
    {
        // functia trebuie sa intoarca view-ul de pe pozitia position din lista
        // convertView este un element din lista ce nu mai este vizibil si poate fi convertit
        View element;
        int layoutResource = 0;
        int viewType = getItemViewType(position);
        //switch (viewType){
          //  case 0:
           layoutResource = R.layout.personaj;
            //    break;
           // case 1:
             //   layoutResource = R.layout.personaj2;
             //   break;
        //}
            LayoutInflater inflater = context.getLayoutInflater();
            MyTag tag = new MyTag();
            //if (position % 2 == 1)
            //{
                element = inflater.inflate(R.layout.personaj, null);
                tag.nume = (TextView)element.findViewById(R.id.personaj_nume);
                tag.desen = (TextView)element.findViewById(R.id.personaj_desen);
                tag.imagine = (ImageView)element.findViewById(R.id.image1);
                tag.data = (TextView)element.findViewById(R.id.data);

            //}
            /*else
            {
                element = inflater.inflate(R.layout.personaj2, null);
                tag.nume = (TextView)element.findViewById(R.id.personaj_nume2);
                tag.desen = (TextView)element.findViewById(R.id.personaj_desen2);
                tag.imagine = (ImageView)element.findViewById(R.id.image2);
            }*/
            element.setTag(tag);

        System.out.println(position);
        //MyTag tag = (MyTag)element.getTag();
        tag.nume.setText(personaje.get(position).nume);
        tag.desen.setText(personaje.get(position).desen);
        tag.data.setText(personaje.get(position).day+" "+personaje.get(position).month+" "+personaje.get(position).year);
        System.out.println("IN GET VIEW " + personaje.get(position).nume);
        System.out.println("IN GET VIEW " + personaje.get(position).myURI);
        if (personaje.get(position).myURI == null) {
            if (position == 0) {
                tag.imagine.setImageResource(R.drawable.pitt);
                tag.data.setText("01 11 1964");
            }
                else if (position == 1) {
                tag.imagine.setImageResource(R.drawable.index);
                tag.data.setText("23 09 1970");
            }
                else if (position == 2) {
                tag.imagine.setImageResource(R.drawable.depp);
                tag.data.setText("15 03 1968");
            }
                else if (position == 3) {
                tag.imagine.setImageResource(R.drawable.portman);
                tag.data.setText("20 08 1983");
            }
                else if (position == 4) {
                tag.imagine.setImageResource(R.drawable.bale);
                tag.data.setText("12 04 1981");
            }
                else if (position == 5) {
                tag.imagine.setImageResource(R.drawable.batman);
                tag.data.setText("03 08 1992");
            }
                else if (position == 6) {
                tag.imagine.setImageResource(R.drawable.joker);
                tag.data.setText("02 06 1987");
            }
                else if (position == 7) {
                tag.data.setText("09 03 1991");
                tag.imagine.setImageResource(R.drawable.freeman);
            }
                else if (position == 8) {
                tag.data.setText("07 01 1882");
                tag.imagine.setImageResource(R.drawable.knightley);
            }
                System.out.println("AICI, POZITIA" + position);

            //else if (contor == 1)
        }
        else
            tag.imagine.setImageURI(Uri.parse(personaje.get(position).myURI));


        //TextView nume = (TextView)element.findViewById(R.id.personaj_nume);
        //TextView desen = (TextView)element.findViewById(R.id.personaj_desen);
        //ImageView icon = (ImageView) element.findViewById(R.id.image1);

        //icon.setImageResource(R.drawable.abc_switch_track_mtrl_alpha);
        //nume.setText(personaje.get(position).nume);
        //desen.setText(personaje.get(position).desen);

        return element;
    }



    @Override
    public int getCount() {
        // intoarce nr de elemente din lista
        return personaje.size();
    }

    @Override
    public Object getItem(int position)
    {
        // intoarce elementul de pe pozitia position din model
        return personaje.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        // fiecare element din lista poate avea un id, nu este insa obligatoriu
        return 0;
    }

    @Override
    public int getItemViewType (int position){
        return position % 2;
    }

    public void adaugaFunnyGuy (String nume, String desen)
    {
        Personaj p = new Personaj ();
        p.nume = nume;
        p.desen = desen;
        personaje.add(p);
        // acesta functie determina adaptorul sa ceara listei sa reafiseze continutul
        this.notifyDataSetChanged();
    }

}
