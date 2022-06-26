package com.example.apiejemplo.ui.gallery;

import android.widget.EditText;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PersonajesAPI {
    @GET("{id}")
    public Call<Personajes> find(@Path("id") String id);

}
