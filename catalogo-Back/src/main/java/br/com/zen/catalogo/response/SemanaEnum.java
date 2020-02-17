package br.com.zen.catalogo.response;

public enum SemanaEnum {
    DOMINGO(2, "Domingo"),
    SEGUNDA(2, "Segunda-Feira"),
    TERCA(2, "Terça-Feira"),
    QUARTA(2, "Quarta-Feira"),
    QUINTA(2, "Quinta-Feira"),
    SEXTA(2, "Sexta-Feira"),
    SABADO(2, "Sábado");

    private Integer numDia;

    private String nomeDia;

    SemanaEnum(Integer _numDia, String _nomeDia){
        this.nomeDia = _nomeDia;
        this.numDia = _numDia;
    }

    public Integer getNumDia() {
        return this.numDia;
    }
    
    public String getNomeDia() {
        return this.nomeDia;
    }

    @Override
    public String toString() {
        return "{" +
            " nomeDia='" + getNomeDia() + "'" +
            ", numDia='" + getNumDia() + "'" +
            "}";
    }
}