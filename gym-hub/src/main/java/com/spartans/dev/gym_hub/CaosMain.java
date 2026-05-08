package com.spartans.dev.gym_hub;

import com.spartans.dev.gym_hub.model.Aluno;
import com.spartans.dev.gym_hub.model.Professor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CaosMain {

    /*
     * VIOLA SRP - Single Responsibility Principle
     * A classe CaosMain está concentrando várias responsabilidades:
     * - controle do menu;
     * - leitura de dados pelo Scanner;
     * - validação de dados;
     * - criação de Aluno e Professor;
     * - regra de negócio;
     * - persistência em listas;
     * - geração de relatório.
     *
     * Pelo princípio da responsabilidade única, cada classe deveria ter apenas um motivo para mudar.
     * Aqui, se mudar a forma de salvar, o menu, a validação ou o relatório,
     * a mesma classe precisará ser alterada.
     */

    /*
     * VIOLA SRP / DIP
     * Essas listas funcionam como um "banco de dados fake" dentro da classe principal.
     * A classe CaosMain fica responsável também por armazenar os dados.
     *
     * O ideal seria existir uma camada de repositório, por exemplo:
     * AlunoRepository e ProfessorRepository.
     */
    private static final List<Aluno> ALUNOS = new ArrayList<>();
    private static final List<Professor> PROFESSORES = new ArrayList<>();

    /*
     * VIOLA ISP - Interface Segregation Principle
     * Essa interface obriga qualquer classe que a implemente a ter métodos de aluno,
     * professor, auditoria, backup e relatório financeiro.
     *
     * O problema é que nem toda implementação precisa de tudo isso.
     * Uma classe que só salva aluno não deveria ser obrigada a implementar métodos
     * de professor, backup ou relatório financeiro.
     *
     * O correto seria dividir em interfaces menores, por exemplo:
     * - AlunoRepository
     * - ProfessorRepository
     * - AuditoriaService
     * - BackupService
     * - RelatorioFinanceiroService
     */
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
            /*
             * VIOLA LSP - Liskov Substitution Principle
             * GatewaySoAluno implementa GatewayGigante, então teoricamente deveria poder
             * ser usado em qualquer lugar onde GatewayGigante é esperado.
             *
             * Porém, quando chamamos salvarProfessor(), ele quebra o comportamento esperado
             * lançando UnsupportedOperationException.
             *
             * Isso mostra que GatewaySoAluno não substitui corretamente GatewayGigante.
             */
            throw new UnsupportedOperationException("GatewaySoAluno não suporta professor (mas a interface manda).");
        }

        @Override
        public void logAuditoria(String msg) {
            /*
             * VIOLA SRP
             * A classe que deveria cuidar apenas de salvar aluno também está fazendo auditoria.
             * Isso mistura persistência com log.
             */
            System.out.println("[AUDITORIA] " + msg);
        }

        @Override
        public void gerarBackup() {
            /*
             * VIOLA ISP
             * Esse método existe apenas porque a interface obrigou.
             * A classe não precisa gerar backup, então fica um método vazio.
             */
        }

        @Override
        public String relatorioFinanceiro() {
            /*
             * VIOLA ISP / SRP
             * Uma classe voltada para salvar aluno não deveria ser obrigada
             * a gerar relatório financeiro.
             */
            return "Relatorio fake (so aluno): totalAlunos=" + ALUNOS.size();
        }
    }

    static class BancoDadosFake {
        /*
         * Parcialmente tenta seguir DIP porque depende de uma interface.
         * Porém, a interface GatewayGigante é grande demais e mal dividida.
         *
         * Além disso, o nome BancoDadosFake é genérico e a classe mistura operações
         * de aluno, professor e auditoria.
         */
        private final GatewayGigante gateway;

        BancoDadosFake(GatewayGigante gateway) {
            this.gateway = gateway;
        }

        void salvarAluno(Aluno aluno) {
            /*
             * VIOLA SRP
             * O método salva aluno e também registra auditoria.
             * Persistência e auditoria poderiam estar separadas.
             */
            gateway.salvarAluno(aluno);
            gateway.logAuditoria("salvou aluno id=" + aluno.getId());
        }

        void salvarProfessor(Professor professor) {
            /*
             * POSSÍVEL VIOLAÇÃO DE LSP
             * Aqui o código espera que qualquer GatewayGigante consiga salvar professor.
             * Mas GatewaySoAluno não consegue e lança exceção.
             */
            gateway.salvarProfessor(professor);
            gateway.logAuditoria("salvou professor id=" + professor.getId());
        }
    }

    public static void main(String[] args) {
        /*
         * VIOLA SRP
         * O método main está controlando diretamente o fluxo da aplicação,
         * exibindo menu, lendo entrada do usuário e chamando regras.
         *
         * Em um código mais organizado, poderia haver uma classe Menu,
         * uma classe Controller e services separados.
         */
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

            /*
             * VIOLA OCP - Open/Closed Principle
             * Sempre que uma nova opção for adicionada ao sistema,
             * será necessário alterar esse switch.
             *
             * O ideal seria ter comandos/classes separadas para cada ação,
             * permitindo adicionar funcionalidades sem modificar tanto o código existente.
             */
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
            /*
             * VIOLA SRP
             * Este método faz muitas coisas:
             * - lê dados do usuário;
             * - converte tipos;
             * - valida informações;
             * - cria o objeto Aluno;
             * - define dados padrão;
             * - calcula IMC;
             * - cria dependências;
             * - salva no banco fake;
             * - mostra mensagem na tela.
             *
             * O ideal seria separar essas responsabilidades.
             */
            System.out.print("ID: ");
            long id = Long.parseLong(sc.nextLine().trim());

            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Altura (ex 1.75): ");
            double altura = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Massa corporal (ex 80.5): ");
            double massa = Double.parseDouble(sc.nextLine().trim());

            /*
             * VIOLA SRP
             * A validação está dentro do método de criação.
             * O ideal seria ter uma classe ou método específico para validar Aluno.
             */
            if (nome == null || nome.isBlank()) {
                System.out.println("Nome inválido.");
                return;
            }

            if (altura <= 0 || massa <= 0) {
                System.out.println("Altura/massa inválidas.");
                return;
            }

            /*
             * VIOLA SRP
             * A montagem do objeto Aluno está sendo feita manualmente dentro do método.
             * Poderia existir uma Factory, Mapper ou Service responsável por criar o aluno.
             */
            Aluno aluno = new Aluno();
            aluno.setId(id);
            aluno.setNome(nome);
            aluno.setAltura(altura);
            aluno.setMassaCorporal(massa);

            /*
             * VIOLA SRP / Regra de negócio mal posicionada
             * Dados como nascimento, CPF, usuário e senha estão sendo definidos de forma fixa.
             * Isso mistura criação de objeto com regra de negócio e dados falsos.
             */
            aluno.setNascimento(new Date());
            aluno.setCpf("000.000.000-00");
            aluno.setUser("user_" + id);
            aluno.setSenha("123");
            aluno.setDataCadastro(LocalDateTime.now());

            /*
             * POSSÍVEL VIOLAÇÃO DE SRP
             * O cálculo do IMC pode até pertencer ao Aluno,
             * mas a decisão de quando calcular está espalhada no fluxo da tela.
             * Em uma arquitetura melhor, isso poderia estar em um service.
             */
            aluno.calcularEAtualizarImc();

            /*
             * VIOLA DIP - Dependency Inversion Principle
             * O método cria diretamente as implementações concretas GatewaySoAluno
             * e BancoDadosFake usando new.
             *
             * Isso deixa o código fortemente acoplado.
             * Se mudar a forma de salvar, será necessário alterar este método.
             *
             * O ideal seria receber as dependências por injeção, por exemplo:
             * AlunoService recebendo AlunoRepository no construtor.
             */
            GatewayGigante gateway = new GatewaySoAluno();
            BancoDadosFake db = new BancoDadosFake(gateway);

            db.salvarAluno(aluno);

            System.out.println("Aluno criado. IMC=" + aluno.getImc());
        } catch (Exception e) {
            /*
             * VIOLA SRP / Tratamento genérico de erro
             * O método captura qualquer Exception.
             * Isso dificulta saber qual erro realmente aconteceu:
             * erro de conversão, erro de validação, erro de banco etc.
             */
            System.out.println("Erro criando aluno: " + e.getMessage());
        }
    }

    private static void listarAlunos() {
        /*
         * VIOLA SRP
         * Este método acessa diretamente a lista de alunos e também imprime no console.
         * Ele mistura busca de dados com apresentação.
         *
         * O ideal seria uma camada buscar os alunos e outra cuidar da exibição.
         */
        if (ALUNOS.isEmpty()) {
            System.out.println("Sem alunos.");
            return;
        }

        System.out.println("Alunos:");

        for (Aluno a : ALUNOS) {
            /*
             * VIOLA SRP
             * A formatação da saída está fixa dentro da lógica.
             * Se mudar o formato de exibição, precisa alterar o método.
             */
            System.out.println("- " + a.getId() + " | " + a.getNome() + " | IMC=" + a.getImc());
        }
    }

    private static void removerAluno(Scanner sc) {
        try {
            /*
             * VIOLA SRP
             * O método lê entrada, converte ID, remove da lista e exibe mensagem.
             * São várias responsabilidades no mesmo método.
             */
            System.out.print("ID para remover: ");
            long id = Long.parseLong(sc.nextLine().trim());

            /*
             * VIOLA DIP / SRP
             * O método remove diretamente da lista estática.
             * Isso acopla a regra de remoção à estrutura de armazenamento.
             * O correto seria chamar algo como alunoRepository.removerPorId(id).
             */
            boolean removeu = ALUNOS.removeIf(a -> a.getId() == id);

            /*
             * Baixa clareza na regra de negócio.
             * A mensagem "Removido (talvez)" mostra que o comportamento não está bem definido.
             */
            System.out.println(removeu ? "Removido (talvez)." : "Nao achei (mas tudo bem).");
        } catch (Exception e) {
            /*
             * Tratamento de erro genérico.
             * O ideal seria tratar erro de número inválido separadamente.
             */
            System.out.println("Erro removendo aluno: " + e.getMessage());
        }
    }

    private static void criarProfessor(Scanner sc) {
        try {
            /*
             * VIOLA SRP
             * O método lê dados, cria professor, define valores padrão,
             * aplica regra de negócio, salva diretamente e mostra resultado.
             */
            System.out.print("ID: ");
            long id = Long.parseLong(sc.nextLine().trim());

            System.out.print("Nome: ");
            String nome = sc.nextLine();

            /*
             * VIOLA SRP
             * A criação e preenchimento do Professor está dentro do método de interface/menu.
             */
            Professor professor = new Professor();
            professor.setId(id);
            professor.setNome(nome);

            /*
             * VIOLA SRP / Regra de negócio mal posicionada
             * Dados fixos estão sendo colocados diretamente no método.
             * Isso dificulta manutenção e reaproveitamento.
             */
            professor.setCpf("111.111.111-11");
            professor.setCref("CREF-" + id);
            professor.setEspecialidade("Musculacao");
            professor.setSobre("Professor criado no caos.");
            professor.setAvaliacao(5.0);

            /*
             * VIOLA OCP
             * A regra de salvamento depende de um if fixo.
             * Se surgirem novas regras de avaliação ou novos tipos de professor,
             * será necessário modificar esse método.
             *
             * O ideal seria usar uma estratégia, service ou regra separada.
             */
            if (professor.getAvaliacao() >= 4.5) {
                /*
                 * VIOLA DIP
                 * O professor é salvo diretamente na lista estática,
                 * sem passar por uma abstração de repositório.
                 */
                PROFESSORES.add(professor);

                /*
                 * VIOLA SRP
                 * O método também faz log/mensagem de banco diretamente no console.
                 */
                System.out.println("[DB] salvou professor direto na lista.");
            } else {
                /*
                 * VIOLA LSP
                 * Aqui é criado um BancoDadosFake com GatewaySoAluno.
                 * Mas GatewaySoAluno não suporta salvar professor.
                 * Se esse else for executado, o código quebra.
                 */
                BancoDadosFake db = new BancoDadosFake(new GatewaySoAluno());
                db.salvarProfessor(professor);
            }

            System.out.println("Professor criado.");
        } catch (Exception e) {
            /*
             * Tratamento genérico de erro.
             * O código captura qualquer problema sem diferenciar a causa.
             */
            System.out.println("Erro criando professor: " + e.getMessage());
        }
    }

    private static void listarProfessores() {
        /*
         * VIOLA SRP
         * Este método busca os professores diretamente da lista
         * e também controla a exibição no console.
         */
        if (PROFESSORES.isEmpty()) {
            System.out.println("Sem professores.");
            return;
        }

        System.out.println("Professores:");

        for (Professor p : PROFESSORES) {
            /*
             * VIOLA SRP
             * A formatação da listagem está presa dentro do método.
             */
            System.out.println("- " + p.getId() + " | " + p.getNome());
        }
    }

    private static void relatorio() {
        /*
         * VIOLA SRP
         * A geração de relatório está dentro da classe principal.
         * O ideal seria existir uma classe RelatorioService.
         */
        System.out.println("Relatorio fake: alunos=" + ALUNOS.size() + ", professores=" + PROFESSORES.size());

        /*
         * VIOLA OCP
         * O relatório está fixo no código.
         * Se quiser adicionar novas informações, será necessário modificar este método.
         */
        if (!ALUNOS.isEmpty()) {
            System.out.println("Primeiro aluno: " + ALUNOS.get(0).getNome());
        }
    }
}
