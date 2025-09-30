package com.example.eva.presentation;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eva.R;
import com.example.eva.data.AsistenteDB;
import com.example.eva.data.Cliente;

public class AddClient extends AppCompatActivity {
    EditText etName, etSurname;
    Button btnAddClient;
    TextView txtError;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_client);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        btnAddClient = findViewById(R.id.btnAddClient);
        txtError = findViewById(R.id.txtError);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            Cliente c = (Cliente) extras.getSerializable("cliente");
            etName.setText(c.getNombre());
            etSurname.setText(c.getApellidos());
            id = c.getId();
        }

        btnAddClient.setOnClickListener(v -> addClient());
    }

    private boolean addClient() {
        if (etName.getText().toString().equals("")) {
            txtError.setText("Debes añadir un nombre");
            return false;
        }
        if (etSurname.getText().toString().equals("")) {
            txtError.setText("Debes añadir apellidos");
            return false;
        }

        AsistenteDB asistente = new AsistenteDB(this);
        SQLiteDatabase db =  asistente.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", etName.getText().toString());
        values.put("apellidos", etSurname.getText().toString());

        db.insert("clientes", null, values);

        db.close();

        Toast.makeText(this, "Añadido", Toast.LENGTH_LONG).show();
        restablecerEditText();

        return true;
    }

    private void restablecerEditText(){
        etName.setText("");
        etSurname.setText("");
    }
}