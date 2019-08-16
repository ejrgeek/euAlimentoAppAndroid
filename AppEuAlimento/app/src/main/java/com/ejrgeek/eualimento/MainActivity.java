package com.ejrgeek.eualimento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private Double mSaldoContaAtual = new Double(100.00);
    private Double valor = new Double(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.botaoMenos = findViewById(R.id.botao_menos);
        this.mViewHolder.botaoMais = findViewById(R.id.botao_mais);
        this.mViewHolder.botaoComprar = findViewById(R.id.botao_comprar);
        this.mViewHolder.valorTotal = findViewById(R.id.text_valor_total);
        this.mViewHolder.quantTotal = findViewById(R.id.text_quant_produto);
        this.mViewHolder.saldoConta = findViewById(R.id.text_saldo_conta);

        this.mViewHolder.saldoConta.setText("Saldo: " + mSaldoContaAtual);

        this.mViewHolder.botaoMais.setOnClickListener(this);
        this.mViewHolder.botaoMenos.setOnClickListener(this);
        this.mViewHolder.botaoComprar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String quantidade = this.mViewHolder.quantTotal.getText().toString();
        Integer quant = Integer.valueOf(quantidade);
        if (view.getId() == R.id.botao_mais){
            quant++;
            this.mViewHolder.quantTotal.setText(String.valueOf(quant));
            valor = 7.50 * quant;
            String msg = "Valor Total: " + valor;
            this.mViewHolder.valorTotal.setText(msg);
        }else if (view.getId() == R.id.botao_menos && quant > 0){
            quant--;
            this.mViewHolder.quantTotal.setText(String.valueOf(quant));
            valor = 7.50 * quant;
            String msg = "Valor Total: " + valor;
            this.mViewHolder.valorTotal.setText(msg);
        }else if ( view.getId() == R.id.botao_comprar && quant == 0){
            Toast.makeText(this, "Quantidade igual a 0", Toast.LENGTH_SHORT).show();
        }else if (view.getId() == R.id.botao_comprar && quant > 0 && valor < mSaldoContaAtual){
            mSaldoContaAtual -= valor;
            this.mViewHolder.saldoConta.setText("Saldo: " + mSaldoContaAtual);
            Toast.makeText(this, "Valor descontado da sua Conta", Toast.LENGTH_SHORT).show();
        }else if (valor > mSaldoContaAtual){
            Toast.makeText(this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
        }
    }

    private static class ViewHolder{
        Button botaoMenos, botaoMais, botaoComprar;
        TextView valorTotal, quantTotal, saldoConta;
    }
}
