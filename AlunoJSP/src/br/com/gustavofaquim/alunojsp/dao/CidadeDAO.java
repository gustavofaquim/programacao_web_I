package br.com.gustavofaquim.alunojsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.gustavofaquim.alunojsp.conexao.Conexao;
import br.com.gustavofaquim.alunojsp.model.Cidade;

public class CidadeDAO {
	private Connection conexao;
	private PreparedStatement stmt;
	
	public CidadeDAO() {
		this.conexao = new Conexao().getConexao();
	}
	
	public void inserir(Cidade cidade) {
		String sql = "insert into cidade (nome,estado) values (?, ?)";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cidade.getNome());
			stmt.setString(2, cidade.getEstado());
			stmt.execute();
			stmt.close();
		}catch(Exception e) {
		 throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Cidade> listar() {
		String sql = "select * from cidade";
		try {
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		
			ArrayList<Cidade> lista = new ArrayList<Cidade>();
				
			while(rs.next()) {
				Cidade cidadeResul = new Cidade();
				cidadeResul.setId(rs.getInt("id"));
				cidadeResul.setNome(rs.getString("nome"));
				cidadeResul.setEstado(rs.getString("estado"));
				lista.add(cidadeResul);
			}
			stmt.close();
			return lista;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	

	public Cidade pesquisaID(int id) {
		String sql ="select * from cidade where id= ? ";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			Cidade cidade = new Cidade();
			
			if(rs.next()) {
				cidade = new Cidade();
				cidade.setId(rs.getInt("id"));
				cidade.setNome(rs.getString("nome"));
				cidade.setEstado(rs.getString("estado"));
			}
			return cidade;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
