/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.agrosustentavel.DAO;


import br.agrosustentavel.Modelo.Usuario;
import br.agrosustentavel.conexao.ConexaoMySQL;
import java.sql.*;

/**
 *
 * @author OdirJM
 */

public class UsuarioDAO {

    public static boolean autenticar(String usuario, String senha) {
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String recuperarSenha(String usuario) {
        String sql = "SELECT senha FROM usuario WHERE usuario = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("senha");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void cadastrarUsuario(Usuario u) {
        String sql = "INSERT INTO usuario (usuario, senha) VALUES (?, ?)";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getUsuario());
            stmt.setString(2, u.getSenha());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
