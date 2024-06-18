package com.empresa.projedata;


// Utilizando algumas importações para facilitar o manuseio do programa
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // 3.1 – Inserindo todos os funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 – Removendo o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 – Imprimindo todos os funcionários com todas suas informações
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
            System.out.println("Salário: " + String.format(Locale.GERMAN, "%,.2f", funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        }


        // 3.4 - Recebendo o aumento de salário em 10%
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10")); // Calcula o novo salário com um aumento de 10%.
            funcionario.setSalario(novoSalario); // Atualiza o salário dos funcionários
        }


        // 3.5 – Agrupando funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>(); //Cria um mapa para agrupar os funcionários por função.

        for (Funcionario funcionario : funcionarios) {
            funcionariosPorFuncao
                    .computeIfAbsent(funcionario.getFuncao(), k -> new ArrayList<>())
                    .add(funcionario);
        }

        //3.6 - Imprimindo funcionários agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("    Nome: " + funcionario.getNome());
                System.out.println("    Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
                System.out.println("    Salário: " + String.format(Locale.GERMAN, "%,.2f", funcionario.getSalario()));
                System.out.println();
            }
        }

        // 3.7 - (Correção, pois na plataforma pula de 3.6 para 3.8) Imprimindo funcionários que fazem aniversário nos meses 10 e 12
        for (Funcionario funcionario : funcionarios) {
            int mesNascimento = funcionario.getDataNascimento().getMonthValue(); //obtenção da data de aniversário
            if (mesNascimento == 10 || mesNascimento == 12) { //verificação do mês de aniversário
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
                System.out.println("Salário: " + String.format(Locale.GERMAN, "%,.2f", funcionario.getSalario()));
                System.out.println("Função: " + funcionario.getFuncao());
                System.out.println();
            }
        }

        //3.8- Verificação do funcionário mais velho
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min((f1, f2) -> f1.getDataNascimento().compareTo(f2.getDataNascimento()))
                .orElse(null);

        if (funcionarioMaisVelho != null) {
            int idade = LocalDate.now().getYear() - funcionarioMaisVelho.getDataNascimento().getYear();
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + idade);
        }

        // 3.9 - Funcionários em ordem alfabética
        funcionarios.stream()
                .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome())) // ordena por nome
                .forEach(funcionario -> {
                    System.out.println("Nome: " + funcionario.getNome());
                    System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
                    System.out.println("Salário: " + String.format(Locale.GERMAN, "%,.2f", funcionario.getSalario()));
                    System.out.println("Função: " + funcionario.getFuncao());
                    System.out.println(); // mostra de forma ordenada
                });

        // 3.10 - Calculando o total do salário de todos os funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add); //Soma todos os salários.

        System.out.println("Total dos salários: " + String.format(Locale.GERMAN, "%,.2f", totalSalarios));

        // 3.11 - Mostra quantos salários minimos ganha cada funcionário.

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Salários Mínimos: " + salariosMinimos);
        }







    }
}
