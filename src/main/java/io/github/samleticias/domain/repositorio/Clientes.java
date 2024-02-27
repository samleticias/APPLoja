package io.github.samleticias.domain.repositorio;

import io.github.samleticias.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
// notação de Repository indica que classe faz operação na base de dados
// tbm traduz as exceções do banco
public class Clientes {
    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE ";
    @Autowired
    // spring injeta jdbc template
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT , new Object[]{cliente.getName()});
        return cliente;
    }

    public List<Cliente> obterTodos(){
        // RowMapper: mapeia o resultado do banco de dados para uma classe
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Cliente(nome, id);
            }
        });
    }
}
