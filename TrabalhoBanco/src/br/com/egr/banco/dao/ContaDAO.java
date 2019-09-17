package br.com.egr.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.egr.banco.conexao.Conexao;
import br.com.egr.banco.model.Conta;


public class ContaDAO {
	private Connection conexao;
	private PreparedStatement stmt;
	
	public ContaDAO() {
		this.conexao = new Conexao().getConexao();
	}
	
	public void inserir(int numero, String tipo) {
		String sql = "insert into conta (numero, saldo,situacao,tipo) values (?,?,?,?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1,numero);
			stmt.setDouble(2, 0.0);
			stmt.setBoolean(3, true);
			stmt.setString(4,tipo);
			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
