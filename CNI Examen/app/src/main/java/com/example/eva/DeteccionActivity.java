package com.example.eva;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.fragmento.FrgCniSensorIA;

public class DeteccionActivity extends AppCompatActivity {

    private TextView txtToken, txtContexto, txtControl;
    private CheckBox chGuardarToken;
    private Button btnValida, btnNoValida;

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


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            txtControl.setText(this.getString(R.string.title_control, bundle.getString("control")));
            txtToken.setText(this.getString(R.string.title_token, bundle.getString("token")));
            txtContexto.setText(this.getString(R.string.title_contexto, bundle.getString("contexto")));
        }


        //System.out.println("yago " + frgCniSensorIA.getTag());
        //txtControl.setText(this.getString(R.string.title_token, frgCniSensorIA.getTag()));
    }
}