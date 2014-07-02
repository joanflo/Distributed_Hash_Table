
package webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import peer.EntradaTEncam;
import peer.Peer;

/**
 * REST Web Service
 */
@Path("Peer")
public class Service {

    @Context
    private UriInfo context;
    
    // cada ordenador tiene 3 peers
    private static Peer[] peers = new Peer[3];
    
    

    /**
     * Creates a new instance of GenericResource
     */
    public Service() {
        if (peers[0] == null) {
            byte [] clave1 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x01};
            byte [] clave2 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x02};
            byte [] clave3 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x03};
            byte [] clave4 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x04};
            byte [] clave5 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x05};
            byte [] clave6 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x06};
            byte [] clave7 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x07};
            byte [] clave8 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x08};
            byte [] clave9 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x00,
                                         0x00, 0x00, 0x00, 0x00, 0x09};
            Service.peers[0] = new Peer(clave1);
            Service.peers[1] = new Peer(clave2);
            Service.peers[2] = new Peer(clave3);
        }
    }
    
    
    
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Path("put")
    public void put(@QueryParam("id") String idPeer, @QueryParam("key") String key, @QueryParam("data") String data) {
        int i = 0;
        while (peers[i].getIdPeer().equals(idPeer)) {
            i++;
        }
        EntradaTEncam nodoMasCercanoAKey = peers[i].getNode(key.getBytes());
        if (nodoMasCercanoAKey.getIdPeer().equals(idPeer)) {
            peers[i].put(key.getBytes(), data);
        } else {
            boolean encontrado = false;
            while (!encontrado) {
                URL url;
                try {
                    url = new URL(nodoMasCercanoAKey.getURLBase() + "Service/getNode/?id=" + idPeer + "&key=" + key);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() != 200) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        encontrado = br.readLine().equals("ok");
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                URL url;
                url = new URL(nodoMasCercanoAKey.getURLBase() + "Service/put?id=" + idPeer + "&key=" + key + "&data=" + data);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
            } catch (MalformedURLException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("get")
    public String get(@QueryParam("id") String idPeer, @QueryParam("key") String key) {
        int i = 0;
        while (peers[i].getIdPeer().equals(idPeer)) {
            i++;
        }
        EntradaTEncam nodoMasCercanoAKey = peers[i].getNode(key.getBytes());
        if (nodoMasCercanoAKey.getIdPeer().equals(idPeer)) {
            return peers[i].get(key.getBytes());
        } else {
            boolean encontrado = false;
            while (!encontrado) {
                URL url;
                try {
                    url = new URL(nodoMasCercanoAKey.getURLBase() + "Service/getNode/?id=" + idPeer + "&key=" + key);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() != 200) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        encontrado = br.readLine().equals("ok");
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
                }   catch (IOException ex) {
                    Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                URL url;
                url = new URL(nodoMasCercanoAKey.getURLBase() + "Service/get?id=" + idPeer + "&key=" + key);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                if (conn.getResponseCode() != 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    return br.readLine();
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }
    
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("get")
    public String getNode(@QueryParam("id") String idPeer, @QueryParam("key") String key) {
        int i = 0;
        while (peers[i].getIdPeer().equals(idPeer)) {
            i++;
        }
        EntradaTEncam nodoMasCercanoAKey = peers[i].getNode(key.getBytes());
        if (nodoMasCercanoAKey.getIdPeer().equals(idPeer)) {
            return "ok";
        }
        return "";
    }
    
    
}
