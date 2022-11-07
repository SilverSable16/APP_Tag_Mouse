package com.example.tagmouse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tagmouse.interfaces.ProductoAPI;
import com.example.tagmouse.models.Producto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText txtid;
TextView lblidproducto, lblnombre, lblmarca,lblfecha_ingreso,lbldescripcion,lbliprecio_costo, lbl_precio_venta,lblexistencias;
Button btnbuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtid= findViewById(R.id.txtid);
        lblidproducto= findViewById(R.id.lblidproducto);
        lblnombre= findViewById(R.id.lblnombre);
        lblmarca= findViewById(R.id.lblmarca);
        lblfecha_ingreso= findViewById(R.id.lblfecha_ingreso);
        lbldescripcion= findViewById(R.id.lbldescripcion);
        lbliprecio_costo= findViewById(R.id.lbliprecio_costo);
        lbl_precio_venta= findViewById(R.id.lbl_precio_venta);
        lblexistencias= findViewById(R.id.lblexistencias);
        btnbuscar= findViewById(R.id.btnbuscar);
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find(txtid.getText().toString());}

        });
    }
    private void find(String id_productos){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://localhost:5001/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ProductoAPI productoAPI=retrofit.create(ProductoAPI.class);
        Call<Producto> call=productoAPI.find(id_productos);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {

                    Producto id_productos=response.body();
                    lblidproducto.setText(id_productos.getId_productos());
                    lblnombre.setText(id_productos.getProducto());
                    lblmarca.setText(id_productos.getIdMarca());
                    lbldescripcion.setText(id_productos.getDescripcion());
                    lbliprecio_costo.setText(id_productos.getPrecio_costo());
                    lbl_precio_venta.setText(id_productos.getPrecio_venta());
                    lblexistencias.setText(id_productos.getExistencia());

            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

            }
        });
    }
}