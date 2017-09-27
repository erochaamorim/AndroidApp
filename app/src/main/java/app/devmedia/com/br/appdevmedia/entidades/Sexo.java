package app.devmedia.com.br.appdevmedia.entidades;

/**
 * Created by erick.amorim on 07/08/2017.
 */

public enum Sexo {

    FEMININO("Feminino"), MASCULINO("Masculino");

    private Sexo(String descricao) {

        this.descricao = descricao;

    }

    protected String descricao;

    public String getDescricao() {

        return this.descricao;

    }

}
