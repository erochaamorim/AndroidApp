package app.devmedia.com.br.appdevmedia.entidades;

public class Profissao {
	
	protected int cod;
	protected String descricao;
	protected String subDescricao;
    protected String urlImg;

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    public String getSubDescricao() {
        return subDescricao;
    }
    public void setSubDescricao(String subDescricao) {
        this.subDescricao= subDescricao;
    }

}
