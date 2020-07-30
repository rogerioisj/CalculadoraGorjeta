package br.com.rogerioisj.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText valorDigitado;
    TextView porcentagem;
    SeekBar porcentagemSeekbar;
    TextView gorjeta;
    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaCampos();
        defineTextoApresentado();
    }

    private void inicializaCampos() {
        valorDigitado = findViewById(R.id.insere_valor);
        porcentagem = findViewById(R.id.porcetagem);
        porcentagemSeekbar = findViewById(R.id.seekbar_porcetagem);
        gorjeta = findViewById(R.id.valor_gorjeta);
        total = findViewById(R.id.valor_total);
    }

    public void defineTextoApresentado() {
        porcentagemSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int porcetagemSelecionada, boolean b) {
                porcentagem.setText(porcetagemSelecionada + "%");
                System.out.println("Teste: " + valorDigitado.getText().toString());
                if(valorDigitado.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Insira um valor primeiro!", Toast.LENGTH_SHORT).show();
                } else{
                    calcula(seekBar, porcetagemSelecionada);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void calcula(SeekBar seekBar, int porcetagemSelecionada) {
        double valorDigitadoFormatado = Double.parseDouble(valorDigitado.getText().toString());
        double valorGorjeta = (valorDigitadoFormatado / seekBar.getMax()) * porcetagemSelecionada;

        gorjeta.setText("R$ " + valorGorjeta);
        total.setText("R$ " + (valorGorjeta + valorDigitadoFormatado));
    }
}