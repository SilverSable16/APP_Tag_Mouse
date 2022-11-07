package com.example.tagmouse.interfaces;

import com.example.tagmouse.models.Producto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductoAPI {
    @GET("api/Productos/{id_productos}")
    public Call<Producto> find(@Path("id_productos") String id_productos);

}
