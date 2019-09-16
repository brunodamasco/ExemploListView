package com.example.exemplolistview.dao;

import android.content.Context;

import com.example.exemplolistview.dao.helper.DaoHelper;
import com.example.exemplolistview.model.Estado;

import java.sql.SQLException;
import java.util.List;

public class EstadoDao extends DaoHelper<Estado> {
    public EstadoDao(Context c) {
        super(c, Estado.class);
    }

    public List<Estado> getEstadoAtivos() {
        try {
            return this.getDao().queryBuilder().where()
                    .eq("ativo", 1).query();
        } catch (SQLException e) {
            return null;
        }
    }
}
