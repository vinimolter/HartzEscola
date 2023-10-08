package br.com.hartzescola.domain.funcionario.salario;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Salario {
    
    private long valorSalario;
    private int contaAgencia;
    private int contaTipo;
    private int contaNumero;
    
    public Salario(DadosSalario dados) {
        this.valorSalario = dados.valorSalario();
        this.contaAgencia = dados.contaAgencia();
        this.contaTipo = dados.contaTipo();
        this.contaNumero = dados.contaNumero();
    }

    public void atualizarSalario(Long valorAumento) {
        this.valorSalario += valorAumento;
    }

    public void atualizarInformacoes(DadosAlterarContaSalario dados) {
        if (dados.contaAgencia() != 0){
            this.contaAgencia = dados.contaAgencia();
        }
        if (dados.contaTipo() != 0){
            this.contaTipo = dados.contaTipo();
        }
        if (dados.contaNumero() != 0){
            this.contaNumero = dados.contaNumero();
        }
    }
}