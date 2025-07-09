/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.agrosustentavel.DAO;


import br.agrosustentavel.Modelo.Consulta;
import br.agrosustentavel.conexao.ConexaoMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author OdirJM
 */

public class ConsultaDAO {

    public static void adicionarConsulta(Consulta c) {
        String sql = "INSERT INTO consulta (produtor, propriedade, tipo, data, consumo_agua, consumo_energia) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getProdutor());
            stmt.setString(2, c.getPropriedade());
            stmt.setString(3, c.getTipo());
            stmt.setString(4, c.getData());
            stmt.setString(5, c.getConsumoAgua());
            stmt.setString(6, c.getConsumoEnergia());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método de excluir
        public static boolean excluirConsulta(int id) {
    String sql = "DELETE FROM consulta WHERE id=?";
       try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        int afetados = stmt.executeUpdate();  // retorna quantas linhas foram afetadas
        return afetados > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public static List<Consulta> listarConsultas() {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Consulta c = new Consulta(
                        rs.getInt("id"),
                        rs.getString("produtor"),
                        rs.getString("propriedade"),
                        rs.getString("tipo"),
                        rs.getString("data"),
                        rs.getString("consumo_agua"),
                        rs.getString("consumo_energia")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
}