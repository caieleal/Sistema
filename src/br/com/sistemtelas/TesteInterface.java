package br.com.sistemtelas;

public interface TesteInterface {
	
	public String sql = "INSERT INTO usuarios(id, usuario, telefone, login, senha, perfil, email) values (?,?,?,?,?,?,?)";
	
	public void createUsu();
}
