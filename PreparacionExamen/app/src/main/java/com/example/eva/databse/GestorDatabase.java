package com.example.eva.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eva.domain.model.Equipo;
import com.example.eva.domain.model.Piloto;

import java.util.ArrayList;

public class GestorDatabase {
    private SQLiteDatabase db;

    public GestorDatabase(Context context) {
        this.db = new AppDB(context).getWritableDatabase();
    }


    public ArrayList<Piloto> getPilotos() {
        final String SQL_PILOTOS = "SELECT driverNumber, fullName, broadcastName, teamName, imageURL, posicionActual, posicionAnterior FROM pilotos ORDER BY posicionActual";

        /* Este contador es el que se encarga de darle una posición real a los pilotos que no tienen posición ,
            otra opcion que tenia era asignarle el driver number pero no me convencio
         */
        int contadorPosiciones = 0;

        ArrayList<Piloto> pilotos = new ArrayList<>();

        Cursor cursor = db.rawQuery(SQL_PILOTOS, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                int driver_number = cursor.getInt(0);
                String full_name = cursor.getString(1);
                String broadcast_name = cursor.getString(2);
                String team_name = cursor.getString(3);
//                String team_colour = cursor.getString(4);
                String headshot_url = cursor.getString(4);

                Piloto p = new Piloto(full_name, driver_number, broadcast_name, new Equipo(team_name), headshot_url);

                if (!cursor.isNull(5)) {
                    p.setPosicionActual(cursor.getInt(5));
                } else {
                    p.setPosicionActual(++contadorPosiciones);
                }

                if (!cursor.isNull(6)) {
                    p.setPosicionAnterior(cursor.getInt(6));
                }

                pilotos.add(p);


            } while (cursor.moveToNext());
        }

        return pilotos;
    }

    public void setPilotos(ArrayList<Piloto> pilotos) {
        if (getPilotos() == pilotos) return;

        final String DELETE_PILOTOS_SQL = "DELETE FROM pilotos";

        db.delete("pilotos", "", new String[]{});


        ContentValues cv;
        for (int i = 0; i < pilotos.size(); i++) {
            Piloto p = pilotos.get(i);

            cv = new ContentValues();
            cv.put("driverNumber", p.getDriverNumber());
            cv.put("fullName", p.getFullName());
            cv.put("broadcastName", p.getBroadcastName());
            cv.put("teamName", p.getEquipo().getTeamName());
            cv.put("imageURL", p.getImageURL());

            cv.put("posicionActual", p.getPosicionActual());
            cv.put("posicionAnterior", p.getPosicionAnterior());

            long log = db.insert("pilotos", null, cv);

            if (log < 0) {
                System.out.println("Hubo un problema al añadir a: " + p.getFullName());
            }
            System.out.println("yago, 1º Se inserto la infrmacion en la db " + cv);
        }
    }
}
