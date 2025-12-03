package com.example.eva;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.clases.Alerta;
import com.example.eva.database.AppDB;
import com.example.eva.database.GestorDatabase;
import com.example.eva.fragmento.ControllerFrgCni;
import com.example.eva.fragmento.FrgCniSensorIA;

public class DeteccionActivity extends AppCompatActivity {

    private TextView txtToken, txtContexto, txtControl;
    private CheckBox chGuardarToken;
    private Button btnValida, btnNoValida;

    private GestorDatabase gestorDatabase;
    private Alerta alerta;

    private Context pantallaAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deteccion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        txtToken = findViewById(R.id.txtToken);
        txtContexto = findViewById(R.id.txtContexto);
        txtControl = findViewById(R.id.txtControl);
        chGuardarToken = findViewById(R.id.chGuardarToken);
        btnValida = findViewById(R.id.btnValida);
        btnNoValida = findViewById(R.id.btnNoValida);

        btnValida.setOnClickListener(v -> anadirAlertaToDatabase());

        btnNoValida.setOnClickListener(v -> {
            ControllerFrgCni.reiniciarEditText();
            finish();
        });


        // Como la información pasada de otro activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            alerta = (Alerta) bundle.get("alerta");
        }

        // Ahora que ya tengo la alerta inicializo los TextView
        txtControl.setText(this.getString(R.string.title_control, alerta.getControl()));
        txtToken.setText(this.getString(R.string.title_token, alerta.getToken()));
        txtContexto.setText(this.getString(R.string.title_contexto, alerta.getContexto()));

        // Inicio db
        SQLiteOpenHelper openHelper = new AppDB(this);
        gestorDatabase = new GestorDatabase(openHelper.getWritableDatabase());


    }

    private void anadirAlertaToDatabase() {
        gestorDatabase.anadirAlerta(alerta);
        GestorAlertas.getAlertasFromDatabase(gestorDatabase.getAlertas());
        ControllerFrgCni.reiniciarEditText();
        finish();
    }
}