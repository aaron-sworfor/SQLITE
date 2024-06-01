package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     EditText etmatricula,etnombre,etapellidom,etapellidop,etmateria,etcarrera,parcial1,parcial2,parcial3,promedio;
     Button btninsertar,btnbuscar,btnactualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etmatricula = (EditText) findViewById(R.id.etmatricula);
        etnombre = (EditText) findViewById(R.id.etnombre);
        etapellidop = (EditText) findViewById(R.id.etapellidop);
        etapellidom = (EditText) findViewById(R.id.etapellidom);
        etmateria = (EditText) findViewById(R.id.etmateria);
        etcarrera = (EditText) findViewById(R.id.etcarrera);
        parcial1 = (EditText) findViewById(R.id.parcial1);
        parcial2 = (EditText) findViewById(R.id.parcial2);
        parcial3 = (EditText) findViewById(R.id.parcial3);
        promedio = (EditText) findViewById(R.id.promedio);
        parcial1.setEnabled(false);
        parcial2.setEnabled(false);
        parcial3.setEnabled(false);
        promedio.setEnabled(false);
        btnbuscar = (Button) findViewById(R.id.btnbuscar);
        btninsertar = (Button) findViewById(R.id.btninsertar);
        btnactualizar = (Button) findViewById(R.id.btnactualizar);
        btnactualizar.setEnabled(false);

        baseDatos ayudadb =new baseDatos(getApplicationContext());

        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db =null;
                try {
                    db = ayudadb.getWritableDatabase();
                    ContentValues valores = new ContentValues();
                    valores.put(baseDatos.Datostabla.COLUMNA_MATRICULA, etmatricula.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_NAME, etnombre.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_APELLIDOP, etapellidop.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_APELLIDOM, etapellidom.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_MATERIA, etmateria.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_CARRERA, etcarrera.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_PARCIAL1, parcial1.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_PARCIAL2, parcial2.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_PARCIAL3, parcial3.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_PROMEDIO, promedio.getText().toString());

                    long idGuardar = db.insert(baseDatos.Datostabla.NOMBRE_TABLE, null, valores);
                    if (idGuardar == -1) {
                        Toast.makeText(getApplicationContext(), "Error al guardar el dato", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Dato guardado con ID: " + idGuardar, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }
            }
        });
        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db =null;
                try {
                    db = ayudadb.getWritableDatabase();
                    ContentValues valores = new ContentValues();
                    valores.put(baseDatos.Datostabla.COLUMNA_MATRICULA, etmatricula.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_NAME, etnombre.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_APELLIDOP, etapellidop.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_APELLIDOM, etapellidom.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_MATERIA, etmateria.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_CARRERA, etcarrera.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_PARCIAL1, parcial1.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_PARCIAL2, parcial2.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_PARCIAL3, parcial3.getText().toString());
                    valores.put(baseDatos.Datostabla.COLUMNA_PROMEDIO, promedio.getText().toString());
                    String[] dato={etmatricula.getText().toString()};
                    String seleccion =baseDatos.Datostabla.COLUMNA_MATRICULA+"=?";
                    int cout=db.update(baseDatos.Datostabla.NOMBRE_TABLE,valores,seleccion,dato);
                    if (cout == -1) {
                        Toast.makeText(getApplicationContext(), "Error al guardar el dato", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "dato actualizadi id: "+ cout, Toast.LENGTH_SHORT).show();
                        etmatricula.setText("");
                        etnombre.setText("");
                        etapellidop.setText("");
                        etapellidom.setText("");
                        etmateria.setText("");
                        etcarrera.setText("");
                        parcial1.setText("");
                        parcial2.setText("");
                        parcial3.setText("");
                        btnactualizar.setEnabled(false);
                        parcial1.setEnabled(false);
                        parcial2.setEnabled(false);
                        parcial3.setEnabled(false);
                        promedio.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }
            }
        });

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db =null;
                try {
                    db = ayudadb.getReadableDatabase();
                    String[] dato={etmatricula.getText().toString()};
                    String[] proyeccion={baseDatos.Datostabla.COLUMNA_NAME,baseDatos.Datostabla.COLUMNA_APELLIDOP,baseDatos.Datostabla.COLUMNA_APELLIDOM,baseDatos.Datostabla.COLUMNA_MATERIA,baseDatos.Datostabla.COLUMNA_CARRERA,baseDatos.Datostabla.COLUMNA_PARCIAL1,baseDatos.Datostabla.COLUMNA_PARCIAL2,baseDatos.Datostabla.COLUMNA_PARCIAL3,baseDatos.Datostabla.COLUMNA_PROMEDIO};
                    Cursor c =db.query(baseDatos.Datostabla.NOMBRE_TABLE,proyeccion,baseDatos.Datostabla.COLUMNA_MATRICULA+"=?",dato,null,null,null);
                    c.moveToFirst();
                    etnombre.setText(c.getString(0));
                    etapellidop.setText(c.getString(1));
                    etapellidom.setText(c.getString(2));
                    etmateria.setText(c.getString(3));
                    etcarrera.setText(c.getString(4));
                    parcial1.setText(c.getString(5));
                    parcial2.setText(c.getString(6));
                    parcial3.setText(c.getString(7));
                    promedio.setText(c.getString(8));
                    String p1 = c.getString(5);
                    String p2 = c.getString(6);
                    String p3 = c.getString(7);
                    String po = c.getString(8);
                    if (p1 == null || p1.isEmpty()) {
                        parcial1.setEnabled(true);
                        parcial2.setEnabled(false);
                        parcial3.setEnabled(false);
                        btnactualizar.setEnabled(true);
                        parcial1.setText("");
                    } else if (p2 == null || p2.isEmpty()) {
                        parcial1.setEnabled(false);
                        parcial2.setEnabled(true);
                        parcial3.setEnabled(false);
                        btnactualizar.setEnabled(true);
                        parcial2.setText("");
                    } else if (p3 == null || p3.isEmpty()) {
                        parcial1.setEnabled(false);
                        btnactualizar.setEnabled(true);
                        parcial2.setEnabled(false);
                        parcial3.setEnabled(true);
                        parcial3.setText("");
                    } else if (po == null || po.isEmpty())  {
                        parcial1.setEnabled(false);
                        parcial2.setEnabled(false);
                        parcial3.setEnabled(false);
                        int n1=Integer.parseInt(parcial1.getText().toString());
                        int n2=Integer.parseInt(parcial2.getText().toString());
                        int n3=Integer.parseInt(parcial3.getText().toString());
                        int pro=(n1+n2+n3)/3;
                        promedio.setText(String.valueOf(pro));
                    }
                    if (c.getString(0) == null) {
                        Toast.makeText(getApplicationContext(), "Error al buscar el dato", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "registro enciontrado  ", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }
            }
        });
    }

}