package app.devmedia.com.br.appdevmedia.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by erick.amorim on 02/10/2017.
 */

public class User {

    protected int id;
    protected String nome;
    protected String email;
    protected String miniBio;
    protected char sexo;
    protected int codProfissao;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMiniBio() {
        return miniBio;
    }

    public void setMiniBio(String miniBio) {
        this.miniBio = miniBio;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getCodProfissao() {
        return codProfissao;
    }

    public void setCodProfissao(int profissao) {
        this.codProfissao = profissao;
    }

}
