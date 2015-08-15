package com.example.theodor.ipw4;

/**
 * Created by theodor on 10.08.2015.
 */
class Personaj
{
    public String nume;
    public String desen;
    public int imgid;
    public String myURI;
    public int year;
    public int month;
    public int day;
    public String email;

    @Override
    public String toString ()
    {
        // acesta functie este apelata de catre ArrayAdapter pentru a transforma obiectul intr-un String ce
        // sa fie afisat in lista
        return nume+" din desenul animat "+desen;
    }
}

