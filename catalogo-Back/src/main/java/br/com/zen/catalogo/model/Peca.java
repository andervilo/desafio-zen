package br.com.zen.catalogo.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class Peca {

    @Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", length = 200)
    private String nome;

    @Column(name = "veiculo_aplicacao", length = 200)
    private String veiculoAplicacao;

    @Column(name = "peso_liquido",  precision = 10, scale = 2)
	@Type(type = "big_decimal")
	private BigDecimal pesoLiquido;

    @Column(name = "peso_bruto",  precision = 10, scale = 2)
	@Type(type = "big_decimal")
    private BigDecimal pesoBruto;
    
    public Peca() {
    }

    public Peca(Long id, String nome, String veiculoAplicacao, BigDecimal pesoLiquido, BigDecimal pesoBruto) {
        this.id = id;
        this.nome = nome;
        this.veiculoAplicacao = veiculoAplicacao;
        this.pesoLiquido = pesoLiquido;
        this.pesoBruto = pesoBruto;
    }

    public Peca(String nome, String veiculoAplicacao, BigDecimal pesoLiquido, BigDecimal pesoBruto) {
        this.nome = nome;
        this.veiculoAplicacao = veiculoAplicacao;
        this.pesoLiquido = pesoLiquido;
        this.pesoBruto = pesoBruto;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVeiculoAplicacao() {
        return this.veiculoAplicacao;
    }

    public void setVeiculoAplicacao(String veiculoAplicacao) {
        this.veiculoAplicacao = veiculoAplicacao;
    }

    public BigDecimal getPesoLiquido() {
        return this.pesoLiquido;
    }

    public void setPesoLiquido(BigDecimal pesoLiquido) {
        this.pesoLiquido = pesoLiquido;
    }

    public BigDecimal getPesoBruto() {
        return this.pesoBruto;
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", veiculoAplicacao='" + getVeiculoAplicacao() + "'" +
            ", pesoLiquido='" + getPesoLiquido() + "'" +
            ", pesoBruto='" + getPesoBruto() + "'" +
            "}";
    }

}