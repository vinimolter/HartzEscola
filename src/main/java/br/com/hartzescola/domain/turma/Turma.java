package br.com.hartzescola.domain.turma;

import java.util.ArrayList;

import br.com.hartzescola.domain.aluno.Aluno;
import br.com.hartzescola.domain.curso.Curso;
import br.com.hartzescola.domain.funcionario.EnumCargo;
import br.com.hartzescola.domain.funcionario.Funcionario;
import br.com.hartzescola.domain.pagamento.Pagamentos;

public class Turma {

    private static int idTurma;
    private Funcionario professor;
    private ArrayList listaAlunos = new ArrayList<Aluno>();
    private Curso curso;
    private Enum EnumTurnoTurma;

    public Turma(Funcionario professor, Curso curso, Enum enumTurnoTurma) {
        if (professor.getCargo() != EnumCargo.PROFESSOR) {
            IllegalArgumentException("A pessoa selecionada para o curso não é um professor");
        } else {
            this.professor = professor;
            this.curso = curso;
            EnumTurnoTurma = enumTurnoTurma;
            idTurma++;
        }
    }

    private void IllegalArgumentException(String string) {
    }

    public Enum getEnumTurnoTurma() {
        return EnumTurnoTurma;
    }

    public static int getIdTurma() {
        return idTurma;
    }

    public Funcionario getProfessor() {
        return professor;
    }

    public ArrayList getListaAlunos() {
        return listaAlunos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void trocaProfessor(Turma idTurma, Funcionario professorNovo) {
        if (professorNovo.getCargo() != EnumCargo.PROFESSOR) {
            IllegalArgumentException("A pessoa selecionada para o curso não é um professor");
        } else {
            idTurma.professor = professorNovo;
        }
    }

    // public void matricular (Turma idTurma, Aluno aluno){
    //     Pagamentos pagamentos = new Pagamentos();
    //     if ((pagamentos.PagamentoViaCartao(idTurma, aluno)) == false){
    //         IllegalArgumentException("A pessoa selecionada para ingressar no curso pois o pagamento não foi aprovado");
    //     } 
    //     else {
    //         listaAlunos.add(aluno);
    //     }
    // }

}
