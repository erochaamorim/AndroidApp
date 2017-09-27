package app.devmedia.com.br.appdevmedia.entidades;

/**
 * Created by era on 05/08/2017.
 */

public enum Profissao {

    CIENTISTA("Cientista da Computação"),
    ANALISTA("Analista de TI"),
    PROFESSOR("Professor de Teoria da Computação"),
    MUSICO("Músico"),
    ILUSTRADOR("Ilustrador de Quadrinhos");

    private Profissao(String descricao) {

        this.descricao = descricao;

    }

    private String descricao;

    public String getDescricao() {

        return descricao;

    }

    @Override
    public String toString()  {

        return descricao;

    }

}
