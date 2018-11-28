package facci.pm.ta2.poo.pra1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import facci.pm.ta2.poo.datalevel.DataException;
import facci.pm.ta2.poo.datalevel.DataObject;
import facci.pm.ta2.poo.datalevel.SaveCallback;

public class InsertActivity extends AppCompatActivity {

    Button buttonCamara, buttonInsert;
    ImageView imageViewFoto;
    Bitmap imageBitmap;
    EditText editTextnombre;
    EditText editTextprecio;
    EditText editTextdescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        buttonCamara = (Button) findViewById(R.id.buttonCamara);
        buttonInsert = (Button) findViewById(R.id.ButtonInsert);
        imageViewFoto = (ImageView) findViewById(R.id.imageViewFoto);
        editTextnombre = (EditText) findViewById(R.id.EditTextNombre);
        editTextprecio= (EditText) findViewById(R.id.EditTextPrecio);
        editTextdescripcion = (EditText) findViewById(R.id.EditTextDescripcion);
        buttonCamara.setOnClickListener(new View.OnClickListener() {
            static final int REQUEST_IMAGE = 1;
            @Override

            public void onClick(View v) {
                Intent abrirCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(abrirCamara.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(abrirCamara,REQUEST_IMAGE);
                }
            }
        });
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DataObject objeto = new DataObject("item");
                String name = editTextnombre.getText().toString();
                String price = editTextprecio.getText().toString();
                String descripcion = editTextdescripcion.getText().toString();
                imageViewFoto.buildDrawingCache();
                Bitmap bmap = imageViewFoto.getDrawingCache();
                objeto.put("name", name);
                objeto.put("description", descripcion);
                objeto.put("price", price);
                objeto.put("image", bmap);
                objeto.saveInBackground(new SaveCallback<DataObject>() {

                    @Override
                    public void done(DataObject object, DataException e) {
                        Toast.makeText(getApplicationContext(),"Inserción Correcta", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(InsertActivity.this, ResultsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Esconde el teclado cuando se da click en cualquier parte de la Activity que no sea un EditText
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        return super.onTouchEvent(event);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final int REQUEST_IMAGE = 1;
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==  REQUEST_IMAGE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            //Asignar al objeto de tipo bitmap lo que me devuelve el objeto data.
            imageBitmap = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imageBitmap);

        }
    }
}


