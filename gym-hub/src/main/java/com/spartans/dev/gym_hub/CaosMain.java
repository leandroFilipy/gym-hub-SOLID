package com.spartans.dev.gym_hub;

import com.spartans.dev.gym_hub.model.Aluno;
import com.spartans.dev.gym_hub.model.Professor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CaosMain {

    private static final List<Aluno> ALUNOS = new ArrayList<>();
    private static final List<Professor> PROFESSORES = new ArrayList<>();

    interface GatewayGigante {
        void salvarAluno(Aluno aluno);
        void salvarProfessor(Professor professor);
        void logAuditoria(String msg);
        void gerarBackup();
        String relatorioFinanceiro();
    }

    static class GatewaySoAluno implements GatewayGigante {
        @Override
        public void salvarAluno(Aluno aluno) {
            ALUNOS.add(aluno);
        }

        @Override
        public void salvarProfessor(Professor professor) {
            throw new UnsupportedOperationException("GatewaySoAluno não suporta professor (mas a interface manda).");
        }

        @Override
        public void logAuditoria(String msg) {
            System.out.println("[AUDITORIA] " + msg);
        }

        @Override
        public void gerarBackup() {
        }

        @Override
        public String relatorioFinanceiro() {
            return "Relatorio fake (so aluno): totalAlunos=" + ALUNOS.size();
        }
    }

    static class BancoDadosFake {
        private final GatewayGigante gateway;

        BancoDadosFake(GatewayGigante gateway) {
            this.gateway = gateway;
        }

        void salvarAluno(Aluno aluno) {
            gateway.salvarAluno(aluno);
            gateway.logAuditoria("salvou aluno id=" + aluno.getId());
        }

        void salvarProfessor(Professor professor) {
            gateway.salvarProfessor(professor);
            gateway.logAuditoria("salvou professor id=" + professor.getId());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("=== GYM HUB - CAOS (SEM SOLID) ===");
            System.out.println("1) Criar Aluno");
            System.out.println("2) Listar Alunos");
            System.out.println("3) Remover Aluno");
            System.out.println("4) Criar Professor");
            System.out.println("5) Listar Professores");
            System.out.println("6) Relatorio (qualquer coisa)");
            System.out.println("0) Sair");
            System.out.print("Opcao: ");

            String opcao = sc.nextLine().trim();
            if ("0".equals(opcao)) break;

            switch (opcao) {
                case "1" -> criarAluno(sc);
                case "2" -> listarAlunos();
                case "3" -> removerAluno(sc);
                case "4" -> criarProfessor(sc);
                case "5" -> listarProfessores();
                case "6" -> relatorio();
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    private static void criarAluno(Scanner sc) {
        try {
            System.out.print("ID: ");
            long id = Long.parseLong(sc.nextLine().trim());

            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Altura (ex 1.75): ");
            double altura = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Massa corporal (ex 80.5): ");
            double massa = Double.parseDouble(sc.nextLine().trim());

            if (nome == null || nome.isBlank()) {
                System.out.println("Nome inválido.");
                return;
            }
            if (altura <= 0 || massa <= 0) {
                System.out.println("Altura/massa inválidas.");
                return;
            }

            Aluno aluno = new Aluno();
            aluno.setId(id);
            aluno.setNome(nome);
            aluno.setAltura(altura);
            aluno.setMassaCorporal(massa);
            aluno.setNascimento(new Date());
            aluno.setCpf("000.000.000-00");
            aluno.setUser("user_" + id);
            aluno.setSenha("123");
            aluno.setDataCadastro(LocalDateTime.now());
            aluno.calcularEAtualizarImc();

            GatewayGigante gateway = new GatewaySoAluno();
            BancoDadosFake db = new BancoDadosFake(gateway);
            db.salvarAluno(aluno);

            System.out.println("Aluno criado. IMC=" + aluno.getImc());
        } catch (Exception e) {
            System.out.println("Erro criando aluno: " + e.getMessage());
        }
    }

    private static void listarAlunos() {
        if (ALUNOS.isEmpty()) {
            System.out.println("Sem alunos.");
            return;
        }
        System.out.println("Alunos:");
        for (Aluno a : ALUNOS) {
            System.out.println("- " + a.getId() + " | " + a.getNome() + " | IMC=" + a.getImc());
        }
    }

    private static void removerAluno(Scanner sc) {
        try {
            System.out.print("ID para remover: ");
            long id = Long.parseLong(sc.nextLine().trim());
            boolean removeu = ALUNOS.removeIf(a -> a.getId() == id);
            System.out.println(removeu ? "Removido (talvez)." : "Nao achei (mas tudo bem).");
        } catch (Exception e) {
            System.out.println("Erro removendo aluno: " + e.getMessage());
        }
    }

    private static void criarProfessor(Scanner sc) {
        try {
            System.out.print("ID: ");
            long id = Long.parseLong(sc.nextLine().trim());

            System.out.print("Nome: ");
            String nome = sc.nextLine();

            Professor professor = new Professor();
            professor.setId(id);
            professor.setNome(nome);
            professor.setCpf("111.111.111-11");
            professor.setCref("CREF-" + id);
            professor.setEspecialidade("Musculacao");
            professor.setSobre("Professor criado no caos.");
            professor.setAvaliacao(5.0);

            if (professor.getAvaliacao() >= 4.5) {
                PROFESSORES.add(professor);
                System.out.println("[DB] salvou professor direto na lista.");
            } else {
                BancoDadosFake db = new BancoDadosFake(new GatewaySoAluno());
                db.salvarProfessor(professor);
            }

            System.out.println("Professor criado.");
        } catch (Exception e) {
            System.out.println("Erro criando professor: " + e.getMessage());
        }
    }

    private static void listarProfessores() {
        if (PROFESSORES.isEmpty()) {
            System.out.println("Sem professores.");
            return;
        }
        System.out.println("Professores:");
        for (Professor p : PROFESSORES) {
            System.out.println("- " + p.getId() + " | " + p.getNome());
        }
    }

    private static void relatorio() {
        System.out.println("Relatorio fake: alunos=" + ALUNOS.size() + ", professores=" + PROFESSORES.size());
        if (!ALUNOS.isEmpty()) {
            System.out.println("Primeiro aluno: " + ALUNOS.get(0).getNome());
        }
    }
}

