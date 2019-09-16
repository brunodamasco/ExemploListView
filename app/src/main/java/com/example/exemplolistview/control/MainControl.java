package com.example.exemplolistview.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.exemplolistview.R;
import com.example.exemplolistview.model.Estado;
import com.example.exemplolistview.model.EstadoBO;

import java.util.ArrayList;
import java.util.List;

public class MainControl {
    private Activity activity;
    private EditText editNome;
    private EditText editSigla;
    private ListView lvEstado;
    private List<Estado> listEstado;
    private ArrayAdapter<Estado> adapterEstado;
    private Estado estado;
    private TextView tvContador;

    public MainControl(Activity activity) {
        this.activity = activity;
        initComponents();
    }

    public void initComponents() {
        editNome = activity.findViewById(R.id.editNome);
        editSigla = activity.findViewById(R.id.editSigla);
        lvEstado = activity.findViewById(R.id.lvEstado);
        tvContador = activity.findViewById(R.id.tvContador);
        configListView();
    }

    public void configListView() {
        listEstado = new ArrayList<>();
        listEstado.add(new Estado("Santa Catarina", "SC"));
        adapterEstado = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_list_item_1,
                listEstado
        );
        lvEstado.setAdapter(adapterEstado);
        cliqueCurto();
        cliqueLongo();
    }

    public void cliqueCurto() {
        lvEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                estado = adapterEstado.getItem(position);
                confirmarEdicao(estado);
            }
        });
    }

    public void cliqueLongo() {
        lvEstado.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                estado = adapterEstado.getItem(i);
                confirmarExclusao(estado);
                //return false; // Executa o clique curto junto
                return true; // Não executa o clique curto junto
            }
        });
    }

    private Estado getDadosForm() {
        Estado e = new Estado();
        e.setNome(editNome.getText().toString());
        e.setSigla(editSigla.getText().toString());
        return e;
    }

    private boolean valida(EstadoBO estadoBO) {
        if (!estadoBO.validaNome(estado)) {
            editNome.setError("Preencha o campo 'NOME' corretamente!");
            editNome.requestFocus();
            return false;
        } else if (!estadoBO.validaSigla(estado)) {
            editSigla.setError("Preencha o campo 'SIGLA' corretamente");
            editSigla.requestFocus();
            return false;
        }
        return true;
    }

    private void addEstadoLv(Estado e) {
        adapterEstado.add(e);
    }

    private void alterarEstado(Estado e) {
        estado.setNome(e.getNome());
        estado.setSigla(e.getSigla());
        adapterEstado.notifyDataSetChanged();
    }

    private void excluirEstadoLv(Estado e) {
        adapterEstado.remove(e);
        atualizarContador();
    }

    private void confirmarExclusao(final Estado e) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Excluir Estado");
        alerta.setMessage(e.toString());
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                estado = null;
            }
        });
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                excluirEstadoLv(e);
                estado = null;
            }
        });
        alerta.show();
    }

    private void carregarForm(Estado e) {
        editNome.setText(e.getNome());
        editSigla.setText(e.getSigla());
    }

    private void atualizarContador() {
        tvContador.setText("Contador: " + adapterEstado.getCount());
    }

    public void salvarAction() {
        estado = new Estado();
        estado = getDadosForm();
        EstadoBO estadoBO = new EstadoBO(estado);
        if (valida(estadoBO)) {
            Estado e = getDadosForm();
            addEstadoLv(e);
            atualizarContador();
        } else {
            Estado e = getDadosForm();
            alterarEstado(e);
        }
        estado = null;
    }

    private void confirmarEdicao(final Estado e) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Mostrar dados");
        alerta.setMessage(e.toString());
        alerta.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                estado = null;
            }
        });
        alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                carregarForm(e);
            }
        });
        alerta.show();
    }
}

