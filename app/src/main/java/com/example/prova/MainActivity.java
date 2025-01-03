package com.example.prova;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtConselho;
    LinearLayout btnBuscarConselho, btnSalvarConselho, btnListarConselhos;
    String conselhoAtual;
    int conselhoIdAtual;
    Conexao conexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtConselho = findViewById(R.id.txtConselho);
        btnBuscarConselho = findViewById(R.id.btnBuscar);
        btnSalvarConselho = findViewById(R.id.btnSalvar);
        btnListarConselhos = findViewById(R.id.btnLista);

        btnBuscarConselho.setOnClickListener(this);
        btnSalvarConselho.setOnClickListener(this);
        btnListarConselhos.setOnClickListener(this);

        conexao = new Conexao(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnBuscar) {
            buscarConselho();
        } else if (id == R.id.btnSalvar) {
            salvarConselho(conselhoIdAtual, conselhoAtual);
        } else if (id == R.id.btnLista) {
            listarConselhos();
        }
    }

    private void buscarConselho() {
        ConselhoService conselhoService = new ConselhoService() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject slipObject = jsonObject.getJSONObject("slip");
                    int id = slipObject.getInt("id");
                    String advice = slipObject.getString("advice");
                    conselhoAtual = advice;
                    conselhoIdAtual = id;
                    txtConselho.setText(advice);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        conselhoService.execute();
    }

    private void salvarConselho(int id, String conselho) {
        if (conselho != null) {
            if (conexao.conselhoExiste(conselho)) {
                Toast.makeText(this, "Conselho já existe.", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = conexao.getWritableDatabase();
            try {
                ContentValues values = new ContentValues();
                values.put("id", id);
                values.put("conselho", conselho);

                long newRowId = db.insert("conselho", null, values);
                if (newRowId != -1) {
                    Toast.makeText(this, "Conselho salvo com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Erro ao salvar o conselho.", Toast.LENGTH_SHORT).show();
                }
            } finally {
                if (db != null && db.isOpen()) {
                    db.close();
                }
            }
        } else {
            Toast.makeText(this, "Não há conselho para salvar. Busque um conselho primeiro.", Toast.LENGTH_SHORT).show();
        }
    }

    private void listarConselhos() {
        Intent intent = new Intent(this, TelaLista.class);
        startActivity(intent);
    }
}
