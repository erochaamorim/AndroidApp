package app.devmedia.com.br.appdevmedia.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by erick.amorim on 07/08/2017.
 */

public class Pessoa implements Serializable {

    protected int id;
    protected String nome;
    protected String endereco;
    protected String cpfCnpj;
    protected TipoPessoa tipoPessoa;
    protected Sexo sexo;
    protected Profissao profissao;
    protected Date dtNasc;

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public String getNome() {

        return nome;

    }

    public void setNome(String nome) {

        this.nome = nome;

    }

    public String getEndereco() {

        return endereco;

    }

    public void setEndereco(String endereco) {

        this.endereco = endereco;

    }

    public String getCpfCnpj() {

        return cpfCnpj;

    }

    public void setCpfCnpj(String cpfCnpj) {

        this.cpfCnpj = cpfCnpj;

    }

    public TipoPessoa getTipoPessoa() {

        return tipoPessoa;

    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {

        this.tipoPessoa = tipoPessoa;

    }

    public Sexo getSexo() {

        return sexo;

    }

    public void setSexo(Sexo sexo) {

        this.sexo = sexo;

    }

    public Profissao getProfissao() {

        return profissao;

    }

    public void setProfissao(Profissao profissao) {

        this.profissao = profissao;

    }

    public Date getDtNasc() {

        return dtNasc;

    }

    public void setDtNasc(Date dtNasc) {

        this.dtNasc = dtNasc;

    }

    @Override
    public String toString() {

        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", tipoPessoa=" + tipoPessoa +
                ", sexo=" + sexo +
                ", profissao=" + profissao +
                ", dtNasc=" + dtNasc +
                '}';

    }
}
