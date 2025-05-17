package servico;

public class RegistroConversao {
    private String dataHora;
    private double valorOriginal;
    private String moedaOrigem;
    private double valorConvertido;
    private String moedaDestino;

    public RegistroConversao(String dataHora, double valorOriginal, String moedaOrigem,
                             double valorConvertido, String moedaDestino) {
        this.dataHora = dataHora;
        this.valorOriginal = valorOriginal;
        this.moedaOrigem = moedaOrigem;
        this.valorConvertido = valorConvertido;
        this.moedaDestino = moedaDestino;
    }

    public String getDataHora() { return dataHora; }
    public double getValorOriginal() { return valorOriginal; }
    public String getMoedaOrigem() { return moedaOrigem; }
    public double getValorConvertido() { return valorConvertido; }
    public String getMoedaDestino() { return moedaDestino; }
}
