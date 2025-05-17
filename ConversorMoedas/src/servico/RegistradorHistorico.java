package servico;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegistradorHistorico {
    private static final Gson gson = new Gson();
    private static final Type tipoLista = new TypeToken<List<RegistroConversao>>(){}.getType();

    public static void adicionarRegistro(RegistroConversao registro, String nomeArquivo) throws IOException {
        List<RegistroConversao> registros = new ArrayList<>();

        try (FileReader leitor = new FileReader(nomeArquivo)) {
            registros = gson.fromJson(leitor, tipoLista);
            if (registros == null) {
                registros = new ArrayList<>();
            }
        } catch (IOException e) {
            // Arquivo pode não existir ainda
        }

        registros.add(registro);

        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            gson.toJson(registros, escritor);
        }
    }
}
