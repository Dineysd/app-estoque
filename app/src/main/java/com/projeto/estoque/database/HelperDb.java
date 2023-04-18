package com.projeto.estoque.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import static com.projeto.estoque.database.TabelasSql.COLUMN_ATIVO;
import static com.projeto.estoque.database.TabelasSql.COLUMN_CATEGORIA_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_DATA;
import static com.projeto.estoque.database.TabelasSql.COLUMN_DESCRICAO;
import static com.projeto.estoque.database.TabelasSql.COLUMN_EMBALAGEM_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_MARCA_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_PRECO_UNIT;
import static com.projeto.estoque.database.TabelasSql.NAME_DATABASE;
import static com.projeto.estoque.database.TabelasSql.campos;
import static com.projeto.estoque.database.TabelasSql.sdf;
import static com.projeto.estoque.model.Categoria.TABLE_NAME_CATEGORIA;
import static com.projeto.estoque.model.Embalagem.TABLE_NAME_EMBALAGEM;
import static com.projeto.estoque.model.Marca.TABLE_NAME_MARCA;
import static com.projeto.estoque.model.Produto.TABLE_NAME_PRODUTO;

public class HelperDb extends SQLiteOpenHelper {

    private final String CNT_LOG = "HelperDb";
    private Context context;
    private ArrayList<String> createTable;
    private SQLiteDatabase db;
    private static HelperDb instance;

    public HelperDb(Context context, String nomeBanco) {
        super(context, nomeBanco, null, 2);
        this.context = context;
        createTable = new TabelasSql().getListTabelas();
    }

    public HelperDb(Context context) {
        super(context, NAME_DATABASE, null, 2);
        createTable = new TabelasSql().getListTabelas();
        this.context = context;
    }

    public static synchronized HelperDb getInstance(Context context) {
        if (instance == null) {
            instance = new HelperDb(context.getApplicationContext());
        }
        return instance;
    }

    public static void createDatabase(Context context){
        SQLiteDatabase db = context.openOrCreateDatabase(NAME_DATABASE, Context.MODE_PRIVATE, null);
        HelperDb instance = HelperDb.getInstance(context);
        instance.onCreate(db);
        instance.onUpgrade(db,1,2);
    }



    private HelperDb Open() {
        db = this.getWritableDatabase();
        return this;
    }

    private void Close() {
        if (db.isOpen()) {
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(CNT_LOG, "Iniciando a criação das tabelas");

        try {
            db.beginTransaction();

            for (int i = 0; i < createTable.size(); i++) {
                db.execSQL(createTable.get(i));
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        Log.i(CNT_LOG, "Tabelas atualização");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(CNT_LOG, "Iniciando a atualização das tabelas");
        if (oldVersion == 1 && newVersion == 2) {
            // insere 50 produtos aleatórios
            try {
                db.beginTransaction();
                Cursor cursor = db.query(TABLE_NAME_PRODUTO, campos(TABLE_NAME_PRODUTO), null, null, null, null, null);
                if(cursor != null && cursor.getCount() < 50) {
                    // Inserindo 50 produtos diferentes
                    for (int i = 1; i <= 50; i++) {

                        ContentValues valuesMarca = new ContentValues();
                        valuesMarca.put(COLUMN_DESCRICAO, "MARCA  " + i);
                        valuesMarca.put(COLUMN_ATIVO, true);
                        long idMarca = db.insert(TABLE_NAME_MARCA, null, valuesMarca);
                        Log.d(TABLE_NAME_MARCA, "id: "+idMarca);

                        ContentValues valuesEmb = new ContentValues();
                        valuesEmb.put(COLUMN_DESCRICAO, "EMBALAGEM  " + i);
                        valuesEmb.put(COLUMN_ATIVO, true);
                        long idEmb = db.insert(TABLE_NAME_EMBALAGEM, null, valuesEmb);
                        Log.d(TABLE_NAME_EMBALAGEM, "id: "+idEmb);

                        ContentValues valuesCat = new ContentValues();
                        valuesCat.put(COLUMN_DESCRICAO, "CATEGORIA  " + i);
                        valuesCat.put(COLUMN_ATIVO, true);
                        long idCategoria = db.insert(TABLE_NAME_CATEGORIA, null, valuesCat);
                        Log.d(TABLE_NAME_CATEGORIA, "id: "+idCategoria);

                        ContentValues values = new ContentValues();
                        values.put(COLUMN_DESCRICAO, "Produto " + i);
                        values.put(COLUMN_PRECO_UNIT, 10.0 * i);
                        values.put(COLUMN_MARCA_ID, idMarca);
                        values.put(COLUMN_CATEGORIA_ID, idCategoria);
                        values.put(COLUMN_EMBALAGEM_ID, idEmb);
                        values.put(COLUMN_DATA, sdf.format(new Date()));
                        values.put(COLUMN_ATIVO, true);
                        Log.d(CNT_LOG, "onUpgrade: " + i);
                        db.insert(TABLE_NAME_PRODUTO,null,  values);


                    }


                }
                db.setTransactionSuccessful();
            }catch (Exception e){
                Log.d(CNT_LOG, "erro ao atualizar Tabelas: "+e);
            }finally {
                db.endTransaction();
            }
            Log.i(CNT_LOG, "Tabelas atualizadas");
        }
    }

    public long Insert(String tabela, ContentValues values) {
        long linhasInseridas = 0;

        this.Open();
        try {
            Log.i(CNT_LOG, "Inserindo registro");

            linhasInseridas = db.insert(tabela, null, values);

            Log.i(CNT_LOG, "Linhas inseridas: " + String.valueOf(linhasInseridas));
        } finally {
            this.Close();
        }

        return linhasInseridas;
    }

    public long Update(String tabela, ContentValues values, String where, String[] whereArgs) {
        long linhasAlteradas = 0;

        this.Open();
        try {
            Log.i(CNT_LOG, "Alterando registro(s)");

            linhasAlteradas = db.update(tabela, values, where, whereArgs);

        } finally {
            this.Close();
            Log.i(CNT_LOG, "Linha(s) alterada(s): " + String.valueOf(linhasAlteradas));
        }

        return linhasAlteradas;
    }

    public int Delete(String tabela, String where, String[] whereArgs) {
        int linhasExcluidas = 0;

        this.Open();
        try {
            Log.i(CNT_LOG, "Iniciando exclusão");

            this.Open();

            linhasExcluidas = db.delete(tabela, where, whereArgs);

            Log.i(CNT_LOG, "Registro excluído(s): " + String.valueOf(linhasExcluidas));
        } finally {
            this.Close();
        }

        return linhasExcluidas;
    }

    public Cursor Select(String tabela, String campos[], String where, String[]
            whereArgs, String groupBy, String having, String orderBy) {
        Cursor c = null;

        this.Open();
        try {
            Log.i(CNT_LOG, "Iniciando Busca");
            this.Open();

            c = db.query(tabela, campos, where, whereArgs, groupBy, having, orderBy);

            Log.i(CNT_LOG, "Busca realizada. Total de registros: " + c.getCount());
        } finally {
            this.Close();
        }

        return c;
    }

    public Cursor QuerySelect(String sql, String[] args) {
        Cursor c = null;

        this.Open();
        try {
            Log.i(CNT_LOG, "Iniciando Busca");
            this.Open();

            c = db.rawQuery(sql,args);

            Log.i(CNT_LOG, "Busca realizada. Total de registros: " + c.getCount());
        } finally {
            this.Close();
        }

        return c;
    }
}
