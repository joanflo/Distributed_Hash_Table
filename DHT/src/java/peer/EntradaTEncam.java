

package peer;

/**
 * Representa una entrada de la tabla de encaminamiento
 */
public class EntradaTEncam {
    private String host, puerto;
    private byte[] idPeer;

    
    /**
     * URI: http://host:port/Peer?id=idPeer
     * @param idPeer
     * @param host
     * @param puerto 
     */
    public EntradaTEncam(byte[] idPeer, String host, String puerto) {
        this.idPeer = idPeer;
        this.host = host;
        this.puerto = puerto;
    }
    
    /**
     * @return idPeer
     */
    public byte[] getIdPeer() {
        return idPeer;
    }

    /**
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * @return puerto
     */
    public String getPuerto() {
        return puerto;
    }
    
}
