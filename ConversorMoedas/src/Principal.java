import menu.OpcaoMoeda;
import servico.RegistroConversao;
import servico.ServicoCambio;
import servico.RegistradorHistorico;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bem-vindo ao Novo Conversor de Moedas ===");

        while (true) {
            OpcaoMoeda.exibirMenu();
            System.out.print("Selecione a opção desejada (0 para sair): ");
            String escolha = scanner.nextLine();

            if ("0".equals(escolha)) {
                System.out.println("Encerrando o programa. Até logo!");
                break;
            }

            if (!OpcaoMoeda.ehEscolhaValida(escolha)) {
                System.out.println("Opção inválida, por favor tente novamente.");
                continue;
            }

            System.out.print("Digite o valor para converter: ");
            String entradaValor = scanner.nextLine();

            try {
                double valor = Double.parseDouble(entradaValor);
                if (valor <= 0) {
                    System.out.println("O valor deve ser maior que zero.");
                    continue;
                }

                String[] moedas = OpcaoMoeda.obterMoedas(escolha);
                double convertido = ServicoCambio.converter(valor, moedas[0], moedas[1]);

                String resultado = String.format("%.2f %s = %.2f %s", valor, moedas[0], convertido, moedas[1]);
                System.out.println("Resultado: " + resultado);

                String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                RegistroConversao registro = new RegistroConversao(dataHora, valor, moedas[0], convertido, moedas[1]);

                RegistradorHistorico.adicionarRegistro(registro, "historico.json");

            } catch (NumberFormatException e) {
                System.out.println("Erro: entrada inválida para valor.");
            } catch (IOException e) {
                System.out.println("Erro ao salvar histórico: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("Processo interrompido.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
        scanner.close();




    }
}