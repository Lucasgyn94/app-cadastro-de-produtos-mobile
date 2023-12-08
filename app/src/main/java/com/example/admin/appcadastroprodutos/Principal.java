package com.example.admin.appcadastroprodutos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.admin.appcadastroprodutos.BDHelper.ProdutosBd;
import com.example.admin.appcadastroprodutos.model.Produtos;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {


    ListView lista;
    ProdutosBd bdHelper;
    ArrayList<Produtos> listview_Produtos;
    Produtos produto;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Button btn_cadastrar = findViewById(R.id.btn_Cadastrar);
        btn_cadastrar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, FormularioProdutos.class);
                startActivity(intent);
            }
        });

        // adaptando a lista na tela com arrayAdapter, colocaremos uma lista para passar por um adapter e jogar dentro de uma listView
        lista = (ListView) findViewById(R.id.listview_Produtos);

        // inserindo menu de contexto para trabalhar com nossa lista
        registerForContextMenu(lista);

        // ouvindo os clique de nossa aplicação e atualizando automaticamente ao se modificar
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Produtos produtoEscolhido = (Produtos) adapter.getItemAtPosition(position);

                Intent intent = new Intent(Principal.this, FormularioProdutos.class);
                intent.putExtra("produto-escolhido", produtoEscolhido);
                startActivity(intent);
            }
        });

        // definindo o clique longo
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                produto = (Produtos) adapter.getItemAtPosition(position);
                return false;
            }
        });


    }
    // desenvolvendo a ação do botão
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Este Produto");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                bdHelper = new ProdutosBd(Principal.this);
                bdHelper.deletarProduto(produto);
                bdHelper.close();
                carregarListaProduto();
                return true;
            }
        });
    }

    // metodo para carregar nossos produtos
    protected void onResume() {
        super.onResume();
        carregarListaProduto();
    }

    // criando metodo para carregar lista
    public void carregarListaProduto() {
        bdHelper = new ProdutosBd(Principal.this);
        listview_Produtos = bdHelper.getList();
        bdHelper.close();

        if (listview_Produtos != null) {
            adapter = new ArrayAdapter<Produtos>(Principal.this, android.R.layout.simple_list_item_1, listview_Produtos);
            lista.setAdapter(adapter);
        }
    }

}
