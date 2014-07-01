/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package peer;

/**
 *
 * @author Carlos
 */

// Representa una entrada de la tabla de encaminamiento
public class EntradaTEncam {
    private String host, puerto;
    private byte[] idPeer;

    public EntradaTEncam(byte[] idPeer, String host, String puerto) {
        this.idPeer = idPeer;
        this.host = host;
        this.puerto = puerto;
    }
    
    /**
     * @return the idPeer
     */
    public byte[] getIdPeer() {
        return idPeer;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @return the puerto
     */
    public String getPuerto() {
        return puerto;
    }
    
}
