package com.example.apiejemplo.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apiejemplo.MainActivity;
import com.example.apiejemplo.databinding.FragmentGalleryBinding;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final Button btnActualizar = binding.btnActualizar;
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            final EditText edtcodigo = binding.edtCodigo;
            @Override
            public void onClick(View view) {
                find(edtcodigo.getText().toString());
                textView.setVisibility(View.GONE);

            }
        });
        return root;
    }

    private void find(String codigo){
        final TextView texto1 = binding.txtInfo1;
        final TextView texto2 = binding.txtInfo2;
        final TextView texto3 = binding.txtInfo3;
        final TextView texto4 = binding.txtInfo4;
        final TextView texto5 = binding.txtInfo5;
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Peticiones
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eyiogthd.lucusvirtual.es/api/personajes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PersonajesAPI personajesAPI = retrofit.create(PersonajesAPI.class);
        Call<Personajes> call = personajesAPI.find(codigo);
        call.enqueue(new Callback<Personajes>() {
            @Override
            public void onResponse(Call<Personajes> call, Response<Personajes> response) {
                try {
                    if (response.isSuccessful()){
                        Personajes elementos = response.body();
                        texto1.setText(elementos.getName());
                        texto2.setText(elementos.getStatus());
                        texto3.setText(elementos.getSpecie());
                        texto4.setText(elementos.getGender());
                        texto5.setText(elementos.getOrigin());
                    }else{
                        Toast.makeText(getActivity(), "No se encontro el ID: "+ codigo, Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){

                }
            }

            @Override
            public void onFailure(Call<Personajes> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}