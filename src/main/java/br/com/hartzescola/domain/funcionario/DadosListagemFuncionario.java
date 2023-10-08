package br.com.hartzescola.domain.funcionario;

public record DadosListagemFuncionario(Long id, String nome, String email, String telefone, EnumCargo cargo) {

    public DadosListagemFuncionario(Funcionario funcionario){
        this(funcionario.getId(),funcionario.getNome(), funcionario.getEmail(), funcionario.getTelefone(), funcionario.getCargo());
    }

} 