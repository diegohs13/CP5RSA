package org.example.CP5RSA;

import java.io.*;
import java.net.Socket;


public class Conexao {

    // Enviar uma mensagem cifrada
    public static void enviarMensagem(Socket socket, String mensagem) throws IOException {
        OutputStream out = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"), true);
        writer.println(mensagem.trim());
    }

    // Receber uma mensagem
    public static String receberMensagem(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String mensagem = reader.readLine();
        if (mensagem != null) {
            return mensagem.trim();
        } else {
            throw new IOException("Mensagem recebida é nula.");
        }
    }

}