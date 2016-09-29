package br.com.yasmin.avaliacao.core.constante;

import java.io.Serializable;

/**
 * Enum de estados da federacao.
 * 
 * Classe <code>EstadosEnum</code>.
 * 
 * @author yasminfarias
 * @version1.0 (28/09/2016)
 */
public enum EstadosEnum implements Serializable {
        
       AC("Acre"),
       AL("Alagoas"),
       AM("Amazonas"),
       AP("Amapa"),
       BA("Bahia"),
       CE("Ceara"),
       DF("Distrito Federal"),
       ES("Espirito Santo"),
       GO("Goias"),
       MA("Maranhao"),
       MG("Minas Gerais"),
       MT("Mato Grosso"),
       MS("Mato Grosso do Sul"),
       PA("Para"),
       PB("Paraiba"),
       PE("Pernambuco"),
       PI("Piaui"),
       PR("Parana"),
       RJ("Rio de Janeiro"),
       RN("Rio Grande do Norte"),
       RO("Rondonia"),
       RR("Roraima"),
       RS("Rio Grande do Sul"),
       SC("Santa Catarina"),
       SE("Sergipe"),
       SP("Sao Paulo"),
       TO("Tocantins");
       
       private String nome;
       
       /**
        * Construtor do enum, com parametro nomeExtenso.
        * @paramnome String -O nome por extenso do enum
        */
       private EstadosEnum(String nome) {
               this.nome= nome;
       }
       
       /**
        * Retorna o nome em extenso do enum.
        * @returnString -O nome por extenso
        */
       public String getNome() {
               return nome;
       }
}
