package com.example.admin.appcadastroprodutos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.appcadastroprodutos.BDHelper.ProdutosBd;
import com.example.admin.appcadastroprodutos.model.Produtos;

public class FormularioProdutos extends AppCompatActivity implements View.OnClickListener{
    // importando as editText do activity_formulario.xml
    EditText editText_NomeProduto, editText_Descricao, editText_Quantidade;
    // importando botões
    Button btn_Polimorf;
    // importando produtos pra saber se vai ser editavel ou não
    Produtos editarProduto, produto;
    ProdutosBd bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produtos);
        // instanciando produto
        produto = new Produtos();
        // instanciando bdhelper com a lista de formularios
        bdHelper = new ProdutosBd(FormularioProdutos.this);

        // pegando intenção de editar produto
        Intent intent = getIntent();
        editarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");

        editText_NomeProduto = (EditText) findViewById(R.id.editText_NomeProduto);
        editText_Descricao = (EditText) findViewById(R.id.editText_Descricao);
        editText_Quantidade = (EditText) findViewById(R.id.editText_Quantidade);

        btn_Polimorf = (Button) findViewById(R.id.btn_Polimorf);

        if (editarProduto != null) {
            btn_Polimorf.setText("Modificar");

            // definindo a parte de setters para não trazer campos vazios de produtos ao clicar em editar
            editText_NomeProduto.setText(editarProduto.getNome());
            editText_Descricao.setText(editarProduto.getDescricao());
            editText_Quantidade.setText(editarProduto.getQuantidade() + "");

            produto.setId(editarProduto.getId());
        } else {
            btn_Polimorf.setText("Cadastrar");
        }

        btn_Polimorf.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // pegando os dados do produto definido pelo usuário
        produto.setNome(editText_NomeProduto.getText().toString());
        produto.setDescricao(editText_Descricao.getText().toString());
        produto.setQuantidade(Integer.parseInt(editText_Quantidade.getText().toString()));

        if (btn_Polimorf.getText().toString().equals("Cadastrar")) {
            bdHelper.salvarProduto(produto);
            bdHelper.close();
        } else {
            bdHelper.alterarProduto(produto);
            bdHelper.close();
        }

    }
}
