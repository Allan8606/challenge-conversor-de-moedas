package menu;

import java.util.LinkedHashMap;
import java.util.Map;

public class OpcaoMoeda {

    private static final Map<String, String[]> opcoes = new LinkedHashMap<>();

    static {
        opcoes.put("1", new String[]{"EUR", "USD"});
        opcoes.put("2", new String[]{"USD", "EUR"});
        opcoes.put("3", new String[]{"GBP", "USD"});
        opcoes.put("4", new String[]{"USD", "GBP"});
        opcoes.put("5", new String[]{"USD", "BRL"});
        opcoes.put("6", new String[]{"BRL", "USD"});
        opcoes.put("7", new String[]{"USD", "JPY"});
        opcoes.put("8", new String[]{"JPY", "USD"});
    }

    public static void exibirMenu() {
        System.out.println("\n----- Menu de Conversão de Moedas -----");
        for (Map.Entry<String, String[]> entrada : opcoes.entrySet()) {
            String chave = entrada.getKey();
            String[] moedas = entrada.getValue();
            System.out.printf("%s) %s para %s%n", chave, moedas[0], moedas[1]);
        }
        System.out.println("0) Sair");
    }

    public static boolean ehEscolhaValida(String escolha) {
        return opcoes.containsKey(escolha);
    }

    public static String[] obterMoedas(String escolha) {
        return opcoes.get(escolha);
    }

}
