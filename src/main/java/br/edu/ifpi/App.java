package br.edu.ifpi;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.enums.StatusAluno;
import br.edu.ifpi.enums.StatusCurso;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Sistema de cursos online!");

        Connection conexao = Conexao.getConexao();

        // ProfessorDao professorDao = new ProfessorDao(conexao);

        // String nome = JOptionPane.showInputDialog("Nome: ");
        // String email = JOptionPane.showInputDialog("Email: ");

        // Professor novoProfessor = new Professor(nome, email);

        // int retorno = professorDao.cadastrar(novoProfessor);

        // String mensagem = retorno > 0 ? "Sucesso!" : "Falhou!";

        // JOptionPane.showMessageDialog(null, mensagem, "Retorno", JOptionPane.INFORMATION_MESSAGE);
        // professorDao.consultarTodos();


        ////////////////////////////////////////////////////////////////////////////////////////////////


        // AlunoDao alunoDao = new AlunoDao(conexao);
        // String nome = JOptionPane.showInputDialog("Nome do aluno: ");
        // String email = JOptionPane.showInputDialog("Email do aluno: ");
        // StatusAluno status = JOptionPane.showConfirmDialog(null, "O aluno está ativo? ", "Status", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ? StatusAluno.ATIVO : StatusAluno.INATIVO;

        // Aluno novoAluno = new Aluno(nome, email, status);

        // int retorno = alunoDao.cadastrar(novoAluno);

        // String mensagem = retorno > 0 ? "Cadastro do aluno realizado com sucesso!" : "Falha ao cadastrar o aluno.";

        // JOptionPane.showMessageDialog(null, mensagem, "Retorno", JOptionPane.INFORMATION_MESSAGE);
        // alunoDao.consultarTodos();


        ////////////////////////////////////////////////////////////////////////////////////////////////


        ProfessorDao professorDao = new ProfessorDao(conexao);

        CursoDao cursoDao = new CursoDao(conexao);

        String nome = JOptionPane.showInputDialog("Nome do curso: ");
        int CargaHoraria = Integer.parseInt(JOptionPane.showInputDialog("Carga horária: "));
        StatusCurso status = JOptionPane.showConfirmDialog(null, "O curso está aberto? ", "Status", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ? StatusCurso.ABERTO : StatusCurso.FECHADO;
        int idProfessor = Integer.parseInt(JOptionPane.showInputDialog("Id do professor: "));
        Professor professor = professorDao.consultarPorId(idProfessor);

        Curso curso1 = new Curso(nome, CargaHoraria, status, professor);
        int retorno = cursoDao.cadastrar(curso1);

        String mensagem = retorno > 0 ? "Cadastro do curso realizado com sucesso!" : "Falha ao cadastrar o curso.";
        JOptionPane.showMessageDialog(null, mensagem, "Retorno", JOptionPane.INFORMATION_MESSAGE);
        







    }

}
