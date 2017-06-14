package entity;

import java.util.Date;

public class Produto {

	private Integer idProduto;
	private String nome;
	private Integer estoque;
	private Double valor;
	private Date dataCadastro;
	
	private Categoria categoria;
	
	public Produto(Integer idProduto, String nome, Integer estoque,
			Double valor, Date dataCadastro) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.estoque = estoque;
		this.valor = valor;
		this.dataCadastro = dataCadastro;
	}
	public Produto() {
		super();
	}
	
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getEstoque() {
		return estoque;
	}
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
