package br.fecap.ads.projeton1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;


public class CalculoIMCActivity extends AppCompatActivity {

    // Declaração dos elementos da interface (View)
    private Button btnFechar;
    private EditText campoAltura;
    private EditText campoPeso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_imcactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vinculando os elementos com os Views
        campoAltura = findViewById(R.id.editTextAltura);
        campoPeso = findViewById(R.id.editTextPeso);
        btnFechar = findViewById(R.id.btnFechar);

        // Ao clicar no botão btnFechar vai fechar a tela e voltar para MainActivity
        btnFechar.setOnClickListener(view -> {
            finish();
        });

    }

    // Função para calcular o IMC e levar a tela de cada classificação
    public void calculaIMC(View view) {

        // Variaveis para recuperar (get) e converter em String:
        String altura = campoAltura.getText().toString().trim();
        String peso = campoPeso.getText().toString().trim();

        // Verifica se o campo de peso está vazio, caso esteja ele vai usar um return para o usuario não prosseguir sem digitar
        if (peso.isEmpty()) {
            campoPeso.setError("Informe seu peso");
            campoPeso.requestFocus(); // Coloca o foco no campo para o usuário digitar
            return;
        }

        // Verifica se o campo de altura está vazio, caso esteja ele vai usar um return para o usuario não prosseguir sem digitar
        if (altura.isEmpty()) {
            campoAltura.setError("Informe sua altura");
            campoAltura.requestFocus(); // Coloca o foco no campo para o usuário digitar
            return;
        }

        // Converter os dados para Numerico:
        Double numAltura = Double.parseDouble(altura);
        Double numPeso = Double.parseDouble(peso);
        Double numImc = numPeso / (numAltura * numAltura);

        // Verifica se o peso é valido
        if (numPeso <= 0 || numPeso >= 1000) {
            campoPeso.setError("Peso inválido");
            campoPeso.requestFocus(); // Coloca o foco no campo para o usuário digitar
            return;
        }

        // Verifica se a altura é valida
        if (numAltura <= 0 || numAltura > 3) {
            campoAltura.setError("Altura inválida");
            campoAltura.requestFocus(); // Coloca o foco no campo para o usuário digitar
            return;
        }


        if (numImc < 18.5) { // Abaixo do peso

            Intent intent = new Intent(this, AbaixoDoPesoActivity.class);

            // Adicionar parametros para outra Activity:
            intent.putExtra("altura", numAltura);
            intent.putExtra("peso", numPeso);
            intent.putExtra("imc", numImc);
            intent.putExtra("classificacao", "Abaixo do peso");

            startActivity(intent);

        } else if (numImc >= 18.5 && numImc < 25) { // Peso normal

            Intent intent = new Intent(this, PesoNormalActivity.class);

            // Adicionar parametros para outra Activity:
            intent.putExtra("altura", numAltura);
            intent.putExtra("peso", numPeso);
            intent.putExtra("imc", numImc);
            intent.putExtra("classificacao", "Peso normal");

            startActivity(intent);

        } else if (numImc >= 25 && numImc < 30) { // Sobrepeso

            Intent intent = new Intent(this, SobrepesoActivity.class);

            // Adicionar parametros para outra Activity:
            intent.putExtra("altura", numAltura);
            intent.putExtra("peso", numPeso);
            intent.putExtra("imc", numImc);
            intent.putExtra("classificacao", "Sobrepeso");

            startActivity(intent);

        } else if (numImc >= 30 && numImc < 35) { // Obesidade grau 1

            Intent intent = new Intent(this, Obesidade1Activity.class);

            // Adicionar parametros para outra Activity:
            intent.putExtra("altura", numAltura);
            intent.putExtra("peso", numPeso);
            intent.putExtra("imc", numImc);
            intent.putExtra("classificacao", "Obesidade grau 1");

            startActivity(intent);

        } else if (numImc >= 35 && numImc < 40) { // Obesidade grau 2

            Intent intent = new Intent(this, Obesidade2Activity.class);

            // Adicionar parametros para outra Activity:
            intent.putExtra("altura", numAltura);
            intent.putExtra("peso", numPeso);
            intent.putExtra("imc", numImc);
            intent.putExtra("classificacao", "Obesidade grau 2");

            startActivity(intent);

        } else if (numImc >= 40) { // Obesidade grau 3

            Intent intent = new Intent(this, Obesidade3Activity.class);

            // Adicionar parametros para outra Activity:
            intent.putExtra("altura", numAltura);
            intent.putExtra("peso", numPeso);
            intent.putExtra("imc", numImc);
            intent.putExtra("classificacao", "Obesidade grau 3");

            startActivity(intent);

        }

    }

    // Função para limpar os campos de Altura e Peso
    public void limparCampo(View view) {
        campoAltura.setText("");
        campoPeso.setText("");
    }


}
