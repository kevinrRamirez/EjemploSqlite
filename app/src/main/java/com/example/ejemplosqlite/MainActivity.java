package com.example.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv_nombre, tv_telefono, tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_nombre = findViewById(R.id.ed_Nombre);
        tv_telefono = findViewById(R.id.ed_Telefono);
        tv_id = findViewById(R.id.ed_Id);


    }

    public void registro(View view)
    {
        Almacenamiento almacenamiento = new Almacenamiento(this, "almacenar", null, 1);
        SQLiteDatabase sqLiteDatabase = almacenamiento.getWritableDatabase();

        String id = tv_id.getText().toString().trim();
        String nombre = tv_nombre.getText().toString().trim();
        String telefono = tv_telefono.getText().toString().trim();

        if (!id.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty())
        {

            ContentValues contentValues = new ContentValues();

            contentValues.put("id", id);
            contentValues.put("nombre", nombre);
            contentValues.put("telefono", telefono);

            sqLiteDatabase.insert("ejemplo", null, contentValues);
            sqLiteDatabase.close();

            tv_telefono.setText("");
            tv_id.setText("");
            tv_nombre.setText("");

            Toast.makeText(this, "Regsitro exitoso", Toast.LENGTH_LONG ).show();

        }else{
            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG ).show();
        }

    }

    public void buscar(View view)
    {
        Almacenamiento almacenamiento = new Almacenamiento(this, "almacenar", null, 1);
        SQLiteDatabase sqLiteDatabase = almacenamiento.getWritableDatabase();

        String id = tv_id.getText().toString().trim();
        if (!id.isEmpty())
        {

            Cursor cursor = sqLiteDatabase.rawQuery
                    ("select nombre, telefono from ejemplo where id =" + id, null);

            if (cursor.moveToFirst())
            {
                tv_nombre.setText(cursor.getString(0));
                tv_telefono.setText(cursor.getString(1));
                sqLiteDatabase.close();
                Toast.makeText(this, "Busqueda exitosa", Toast.LENGTH_LONG ).show();
            }else
            {
                Toast.makeText(this, "No existe Persona", Toast.LENGTH_LONG ).show();
                sqLiteDatabase.close();
            }



        }else{
            Toast.makeText(this, "Introdusca el ID", Toast.LENGTH_LONG ).show();
        }

    }


    public void eliminar(View view)
    {
        Almacenamiento almacenamiento = new Almacenamiento(this,"almacenar", null,1);
        SQLiteDatabase sqLiteDatabase = almacenamiento.getWritableDatabase();

        String id = tv_id.getText().toString().trim();

        if (!id.isEmpty())
        {

            int cantidad = sqLiteDatabase.delete("ejemplo", "id="+id,null);
            sqLiteDatabase.close();

            if (cantidad == 1)
            {
                Toast.makeText(this, "Persona eliminada", Toast.LENGTH_LONG ).show();
            }else
            {
                Toast.makeText(this, "Error al eliminar", Toast.LENGTH_LONG ).show();
            }


        }else{
            Toast.makeText(this, "Introdusca el ID", Toast.LENGTH_LONG ).show();
        }
    }

    public void modificar(View view)
    {
        Almacenamiento almacenamiento = new Almacenamiento(this,"almacenar", null, 1);
        SQLiteDatabase sqLiteDatabase = almacenamiento.getWritableDatabase();

        String id = tv_id.getText().toString().trim();
        String nombre = tv_nombre.getText().toString().trim();
        String telefono = tv_telefono.getText().toString().trim();

        if (!id.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty())
        {

            ContentValues values = new ContentValues();
            values.put("id",id);
            values.put("nombre",nombre);
            values.put("telefono",telefono);

            int cantidad = sqLiteDatabase.update("ejemplo",values,"id="+id,null);

            sqLiteDatabase.close();

            if (cantidad == 1)
            {
                Toast.makeText(this, "Persona actualizada", Toast.LENGTH_LONG ).show();
            }else{
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_LONG ).show();
            }

        }else{
            Toast.makeText(this, "Campos faltantes", Toast.LENGTH_LONG ).show();
        }
    }
}
