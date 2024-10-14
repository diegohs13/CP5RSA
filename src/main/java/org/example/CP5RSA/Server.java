package org.example.CP5RSA;

import java.net.ServerSocket;
import java.net.Socket;
import java.math.BigInteger;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        try {

            // Chave pública e módulo do cliente
            BigInteger chavePublicaCliente = new BigInteger("17");
            BigInteger moduloCliente = new BigInteger("3233");

            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor esperando por conexão...");
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado!");

            CriptografiaRSA rsa = new CriptografiaRSA();

            // Receber mensagem cifrada do cliente
            System.out.println("Aguardando mensagem cifrada do cliente...");
            String mensagemCifrada = Conexao.receberMensagem(socket);
            System.out.println("Mensagem cifrada recebida: " + mensagemCifrada);

            // Decifrar a mensagem usando a chave privada do servidor
            String mensagemDecifrada = rsa.decifrar(mensagemCifrada);
            System.out.println("Mensagem decifrada: " + mensagemDecifrada);

            // Enviar resposta cifrada ao cliente
            Scanner input = new Scanner(System.in);
            System.out.print("Digite sua resposta para o cliente: ");
            String resposta = input.nextLine();
            String respostaCifrada = rsa.cifrar(resposta, chavePublicaCliente, moduloCliente);
            Conexao.enviarMensagem(socket, respostaCifrada);
            System.out.println("Mensagem cifrada enviada: " + respostaCifrada);

            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
