/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package peer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 *
 * @author Carlos
 */
public class Peer {
    private final int BITS_CLAVE = 160; // Permite tener máximo 2^160 peers
    private final int K = 3; // Tamaño de los k-bucket
    
    // Identificador del Peer
    private final byte[] idPeer; // Tendra que ser de 160 / 8 = 20 posiciones
            
    /* El HashMap tendrá como clave una cadena de 20 bytes (160 bits) y como 
     * valor un String con una URL simulando la localización del fichero */
    HashMap<String, String> tabla;
    
    private EntradaTEncam[][] tablaEncaminamiento;
            
    public Peer(byte[] idPeer) {
        this.idPeer = idPeer;
        tabla = new HashMap<>();
        tablaEncaminamiento = new EntradaTEncam[BITS_CLAVE][K];
    }
    
    // Registra a un peer en su tabla de encaminamiento
    public void addPeer(EntradaTEncam entradaPeer) {
        byte[] distancia = distance(idPeer, entradaPeer.getIdPeer());
        int kBucket = getPos1MasSignificativo(distancia); // kBucket = fila de la tabla
        if (kBucket != -1) { // Es él mismo
            int posicion = 0;
            while (tablaEncaminamiento[kBucket][posicion] != null && posicion < K) {
                posicion++;
            }
            if (posicion != K) { // Si no se ha llenado su k-bucket
                tablaEncaminamiento[kBucket][posicion] = entradaPeer;
            } 
        }
    }
    
    public byte[] distance(byte[] key, byte[] guid) {
        byte[] resultado = new byte[BITS_CLAVE / 8];
        for (int i = 0; i < BITS_CLAVE / 8; i++) {
            resultado[i] = (byte) (key[i] ^ guid[i]);
        }
        return resultado;
    }
    
    public byte[] sha1(String value) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        System.out.println(md.digest(value.getBytes()).length);
        return md.digest(value.getBytes());
    }
    
    public byte[] getNode(byte[] key) {
        byte[] distancia = distance(idPeer, key);
        int kBucket = getPos1MasSignificativo(distancia); // filaTabla = k-bucket
        if (kBucket == -1) {
            return idPeer;
        } else {
            if (tablaEncaminamiento[kBucket][0] != null) {
                return buscarIdMasProxima(kBucket, key); // Buscamos en el k-bucket la id más próxima
            }
            for (int i = kBucket - 1; i >= 0; i--) { // Buscamos k-bucket no vacía más similar
                if (bitACero(distancia, i)) {
                    return idPeer;
                }
                if (tablaEncaminamiento[i][0] != null) {
                    return buscarIdMasProxima(i, key);
                }
            }
        }
    }
    
    public byte[] buscarIdMasProxima(int kBucket, byte []] key) {
        byte [] resultado = tablaEncaminamiento[kBucket][0];
        for (int i = 1; i < K; i++) {
            if (esPrimeroEsMajor(tablaEncaminamiento[kBucket][0], key))
        }
    }
    
    private boolean bitACero (byte [] key, int posicion) {
        byte b = key[posicion / 8];
        for (int i =  0; i < posicion % 8; i++) {
            b = (byte) (b / 2);
        }
        return b % 2 == 0;
    }
    
    // Obtiene la posición del bit más significativo de la clave b
    public int getPos1MasSignificativo(byte[] key) {
        byte [] b = key.clone();
        for (int i = 0; i < BITS_CLAVE / 8; i++) {
            int posBit = -1;
            // Miramos, dentro del byte, donde se encuentra el 1 más significativo
            for (int j = 7; j >= 0; j--) {
                if (b[i] % 2 == 1) {
                    posBit = j;
                }
                b[i] = (byte) (b[i] / 2);
            }
            if (posBit > -1) {
                return posBit + 8*i;
            }
        }
        return -1; // Clave con todos los bits a 0
    }
}
