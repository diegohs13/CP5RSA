package org.example.CP5RSA;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class CriptografiaRSA {

    private BigInteger p, q, n, e, d;

    // Inicializar com as chaves fixas
    public CriptografiaRSA() {
        p = new BigInteger("53");
        q = new BigInteger("61");
        n = new BigInteger("3233");
        e = new BigInteger("17");
        d = new BigInteger("2753");
    }

    // Método para cifrar uma mensagem dividida em blocos
    public String cifrar(String mensagem, BigInteger publicKey, BigInteger modulus) {
        byte[] messageBytes = mensagem.getBytes(StandardCharsets.UTF_8);
        int blockSize = modulus.bitLength() / 8;  // Tamanho do bloco em bytes

        StringBuilder mensagemCifrada = new StringBuilder();
        for (int i = 0; i < messageBytes.length; i += blockSize) {
            int length = Math.min(blockSize, messageBytes.length - i);
            byte[] block = new byte[length];
            System.arraycopy(messageBytes, i, block, 0, length);

            BigInteger blockBigInt = new BigInteger(1, block);  // Converter bloco para BigInteger
            BigInteger encryptedBlock = blockBigInt.modPow(publicKey, modulus);  // Cifrar o bloco
            mensagemCifrada.append(encryptedBlock.toString()).append(" ");  // Adicionar ao resultado
        }

        return mensagemCifrada.toString().trim();  // Retornar a cifra completa como string
    }

    // Método para decifrar uma mensagem dividida em blocos
    public String decifrar(String mensagemCifrada) {
        String[] blocks = mensagemCifrada.trim().split(" ");
        StringBuilder mensagemDecifrada = new StringBuilder();

        for (String block : blocks) {
            BigInteger encryptedBlock = new BigInteger(block.trim());
            BigInteger decryptedBlock = encryptedBlock.modPow(d, n);  // Decifrar o bloco
            mensagemDecifrada.append(new String(decryptedBlock.toByteArray(), StandardCharsets.UTF_8));
        }

        return mensagemDecifrada.toString();  // Retornar a mensagem decifrada
    }

}
