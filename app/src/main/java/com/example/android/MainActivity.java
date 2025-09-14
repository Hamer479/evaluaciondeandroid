package com.example.android;

import com.example.android.RecyclerViewAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private Spinner spinner;
    private RadioButton radioTrabajo, radioPersonal;
    private CheckBox checkBox;
    private Button guardarBtn;
    private RecyclerView recyclerView;

    private ArrayList<String> tareas;
    private ArrayAdapter<String> recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        ratingBar = findViewById(R.id.ratingBar);
        spinner = findViewById(R.id.spinner);
        radioTrabajo = findViewById(R.id.radioButton);
        radioPersonal = findViewById(R.id.radioButton2);
        checkBox = findViewById(R.id.checkBox);
        guardarBtn = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recyclerView);

        // Inicializar datos
        tareas = new ArrayList<>();

        // Spinner adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Sin Hacer", "En Proceso", "Completada"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        // RecyclerView setup
        recyclerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tareas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(tareas)); // Usamos un adaptador personalizado

        // Acción del botón
        guardarBtn.setOnClickListener(v -> {
            String estado = spinner.getSelectedItem().toString();
            String categoria = radioTrabajo.isChecked() ? "Trabajo" : radioPersonal.isChecked() ? "Personal" : "Sin categoría";
            boolean completada = checkBox.isChecked();
            float prioridad = ratingBar.getRating();

            String tarea = "Estado: " + estado + ", Categoría: " + categoria + ", Prioridad: " + prioridad + ", Completada: " + completada;
            tareas.add(tarea);
            recyclerView.getAdapter().notifyDataSetChanged();
        });
    }
}