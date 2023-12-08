package com.example.admin.appcadastroprodutos.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.appcadastroprodutos.model.Produtos;

import java.util.ArrayList;

public class ProdutosBd extends SQLiteOpenHelper {

    // definindo nome do banco de dados
    private static final String DATABASE = "bdprodutos";
    // definindo versão do banco de dados
    private static final int VERSION = 1;

    public ProdutosBd(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    // criando tabela
    @Override
    public void onCreate(SQLiteDatabase db) {
        String produto = "CREATE TABLE produtos" +
                         "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                         "nomeproduto TEXT NOT NULL, " +
                         "descricao TEXT NOT NULL, " +
                         "quantidade INTEGER);";

        // definindo a execução da querye
        db.execSQL(produto);
    }
    // verificação tabela
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String produto = "DROP TABLE IF EXISTS produtos";
        db.execSQL(produto);
    }

    // salvando produtos
    public void salvarProduto(Produtos produto) {
        ContentValues values = new ContentValues();

        values.put("nomeproduto", produto.getNome());
        values.put("descricao", produto.getDescricao());
        values.put("quantidade", produto.getQuantidade());

        getWritableDatabase().insert("produtos", null, values);

    }

    // criando o metodo alterar

    public void alterarProduto(Produtos produto) {
        ContentValues values = new ContentValues();

        values.put("nomeproduto", produto.getNome());
        values.put("descricao", produto.getDescricao());
        values.put("quantidade", produto.getQuantidade());

        String[] args = {produto.getId().toString()};

        getWritableDatabase().update("produtos",values, "id=?", args);

    }

    // metodo deletar
    public void deletarProduto(Produtos produto) {
        String[] args = {produto.getId().toString()};
        getWritableDatabase().delete("produtos","id=?",args);
    }

    // listar produtos

    public ArrayList<Produtos> getList() {
        String[] columns = {"id", "nomeproduto", "descricao", "quantidade"};
        // salvando lista no cursor
        Cursor cursor = getWritableDatabase().query("produtos", columns, null, null, null, null, null, null);

        ArrayList<Produtos> produtos = new ArrayList<Produtos>();

        while (cursor.moveToNext()) {
            Produtos produto = new Produtos();
            produto.setId(cursor.getLong(0));
            produto.setNome(cursor.getString(1));
            produto.setDescricao(cursor.getString(2));
            produto.setQuantidade(cursor.getInt(3));

            produtos.add(produto);
        }

        return produtos;
    }
}
