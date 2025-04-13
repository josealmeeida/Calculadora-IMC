package br.fecap.ads.projeton1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SobrepesoActivity extends AppCompatActivity {

    // Declaração dos elementos da interface (View)
    private Button btnFechar;
    private TextView textPeso;
    private TextView textAltura;
    private TextView textImc;
    private TextView textClassificacao;
    private TextView textMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sobrepeso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Instanciar os elementos e vincular ao id:
        textPeso = findViewById(R.id.textPeso);
        textAltura = findViewById(R.id.textAltura);
        textImc = findViewById(R.id.textImc);
        textClassificacao = findViewById(R.id.textClassificacao);
        textMensagem = findViewById(R.id.textMensagem);
        btnFechar = findViewById(R.id.btnFechar);

        // Ao clicar no botão btnFechar vai fechar a tela e voltar para CalculoIMCActivity
        btnFechar.setOnClickListener(view -> {
            finish();
        });

        // Recebendo os dados da Tela que foram enviados pelo Intent:
        Bundle bundle = getIntent().getExtras();

        // Decomposição dos dados do objeto enviado:
        Double altura             = bundle.getDouble("altura");
        Double peso               = bundle.getDouble("peso");
        Double imc                = bundle.getDouble("imc");
        String classificacao      = bundle.getString("classificacao");

        // Mostrar os dados:
        textPeso.setText(String.format("Peso: %.2f Kg", peso));
        textAltura.setText(String.format("Altura: %.2f cm", altura));
        textImc.setText(String.format("IMC: %.2f", imc));
        textClassificacao.setText("Classificação: " + classificacao);
        textMensagem.setText("Está com o sobrepeso, cuida-se!!");

    }
}