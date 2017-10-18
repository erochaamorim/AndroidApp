package app.devmedia.com.br.appdevmedia.entidades;

import java.math.BigDecimal;

public class Produto {

	protected int id;
	protected String urlImg, titulo, descricao;
	protected BigDecimal valor;

	public Produto() {

	}
	
	public Produto(int id, String urlImg, String titulo, String descricao, BigDecimal valor) {

		this.id = id;
		this.urlImg = urlImg;
		this.titulo = titulo;
		this.descricao = descricao;
		this.valor = valor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrlImg() {
		return urlImg;
	}
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
