package com.example.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class baseDatos extends SQLiteOpenHelper {
    public static abstract class Datostabla implements BaseColumns {
            public static final String NOMBRE_TABLE = "grupo";
        public static final String COLUMNA_MATRICULA = "matricula";
        public static final String COLUMNA_NAME = "nombre";
        public static final String COLUMNA_APELLIDOP= "apellidoPaterno";
        public static final String COLUMNA_APELLIDOM= "apellidoMaterno";
        public static final String COLUMNA_MATERIA= "materia";
        public static final String COLUMNA_CARRERA= "carrera";
        public static final String COLUMNA_PARCIAL1= "parcial1";
        public static final String COLUMNA_PARCIAL2= "parcial2";
        public static final String COLUMNA_PARCIAL3= "parcial3";
        public static final String COLUMNA_PROMEDIO= "promedio";



        private static final String CREAR_TABLA = "create table " + Datostabla.NOMBRE_TABLE + " ("
                + Datostabla.COLUMNA_MATRICULA + "  integer not null, "
                + Datostabla.COLUMNA_NAME + "  text not null, "
                + Datostabla.COLUMNA_APELLIDOP + " text not null, "
                + Datostabla.COLUMNA_APELLIDOM + "  text not null, "
                + Datostabla.COLUMNA_MATERIA + "  text not null, "
                + Datostabla.COLUMNA_CARRERA + "  text not null, "
                + Datostabla.COLUMNA_PARCIAL1 + "  integer , "
                + Datostabla.COLUMNA_PARCIAL2 + "  integer , "
                + Datostabla.COLUMNA_PARCIAL3 + "  integer , "
                + Datostabla.COLUMNA_PROMEDIO + "  integer  " +");";

        private static final String SQL_DELETE = "DROP TABLE IF EXISTS " + Datostabla.NOMBRE_TABLE;
    }
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Directorio.db";

    public baseDatos(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Datostabla.CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Datostabla.SQL_DELETE);
        onCreate(db);
    }
}