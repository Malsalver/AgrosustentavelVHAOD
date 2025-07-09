/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.agrosustentavel.DAO;


import br.agrosustentavel.Modelo.Produtor;
import br.agrosustentavel.conexao.ConexaoMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OdirJM
 */

public class ProdutorDAO {


    // Cadastrar produtor no banco
    public static void cadastrarProdutor(Produtor p) {
        String sql = "INSERT INTO produtor (nome, cpf, telefone, endereco, propriedade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getTelefone());
            stmt.setString(4, p.getEndereco());
            stmt.setString(5, p.getPropriedade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar propriedade de um produtor pelo nome
        public static String buscarPropriedade(String nomeProdutor) {
    String sql = "SELECT propriedade FROM produtor WHERE nome = ?";
    try (Connection conn = ConexaoMySQL.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nomeProdutor);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("propriedade");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return "Propriedade não encontrada";
}


    // Novo: Listar nomes dos produtores (para preencher ComboBox)
    public static List<String> listarNomes() {
        List<String> nomes = new ArrayList<>();
        String sql = "SELECT nome FROM produtor";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                nomes.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomes;
    }
    
public static String buscarProdutorPorPropriedade(String propriedade) {
    String sql = "SELECT nome FROM produtor WHERE propriedade = ?";
    try (Connection conn = ConexaoMySQL.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, propriedade);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("nome");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return "Não encontrado";
}

public static List<String> listarPropriedades() {
    List<String> propriedades = new ArrayList<>();
    String sql = "SELECT propriedade FROM produtor";

    try (Connection conn = ConexaoMySQL.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            propriedades.add(rs.getString("propriedade"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return propriedades;
}
}