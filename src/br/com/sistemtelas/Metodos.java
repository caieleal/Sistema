package br.com.sistemtelas;

public class Metodos implements TesteInterface {

	private String sql;

	@Override
	public void createUsu() {
		this.sql = "INSERT INTO usuarios(id, usuario, telefone, login, senha, perfil, email) values (?,?,?,?,?,?,?)";
	}
}
