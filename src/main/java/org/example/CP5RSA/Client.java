package org.example.CP5RSA;

import java.net.Socket;
import java.math.BigInteger;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {

            // Chave pública e módulo do servidor
            BigInteger chavePublicaServidor = new BigInteger("17");
            BigInteger moduloServidor = new BigInteger("3233");

            // Conectar ao servidor
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectado ao servidor!");

            CriptografiaRSA rsa = new CriptografiaRSA();

            // Enviar mensagem cifrada para o servidor
            Scanner input = new Scanner(System.in);
            System.out.print("Digite sua mensagem para o servidor: ");
            String mensagem = input.nextLine();
            String mensagemCifrada = rsa.cifrar(mensagem, chavePublicaServidor, moduloServidor);
            Conexao.enviarMensagem(socket, mensagemCifrada);
            System.out.println("Mensagem cifrada enviada: " + mensagemCifrada);

            // Receber a resposta cifrada do servidor
            System.out.println("Aguardando resposta do servidor...");
            String respostaCifrada = Conexao.receberMensagem(socket);
            System.out.println("Resposta cifrada recebida: " + respostaCifrada);

            // Decifrar a resposta usando a chave privada do cliente
            String respostaDecifrada = rsa.decifrar(respostaCifrada);
            System.out.println("Servidor respondeu: " + respostaDecifrada);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
