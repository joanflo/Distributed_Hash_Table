
package webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
import peer.Main;
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
    
    public static final String[] HOSTS = {"95.17.222.57", "83.41.172.20", "83.37.241.231"};
    public static final String PUERTO = "8080";   
    public static final int MI_IP = 2;
    
    // Claves de los diferentes peers
    private static final byte [] clave1 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x01};
    private static final byte [] clave2 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x02};
    private static final byte [] clave3 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x03};
    private static final byte [] clave4 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x04};
    private static final byte [] clave5 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x05};
    private static final byte [] clave6 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x06};
    private static final byte [] clave7 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                                     0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x07};
    private static final byte [] clave8 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x08};
    private static final byte [] clave9 = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x00,
                                                      0x00, 0x00, 0x00, 0x00, 0x09};
    
    public static final byte [][] CLAVES = {clave1, clave2, clave3, 
                                            clave4, clave5, clave6, 
                                            clave7, clave8, clave9};
    
    /**
     * Creates a new instance of GenericResource
     */
    public Service() {
        if (peers[0] == null) {
            Service.peers[0] = new Peer(clave7);
            Service.peers[1] = new Peer(clave8);
            Service.peers[2] = new Peer(clave9);
            Service.peers[0].addPeer(new EntradaTEncam(clave1, HOSTS[0], PUERTO));
            Service.peers[0].addPeer(new EntradaTEncam(clave2, HOSTS[0], PUERTO));
            Service.peers[0].addPeer(new EntradaTEncam(clave3, HOSTS[0], PUERTO));
            Service.peers[0].addPeer(new EntradaTEncam(clave4, HOSTS[1], PUERTO));
            Service.peers[0].addPeer(new EntradaTEncam(clave5, HOSTS[1], PUERTO));
            Service.peers[0].addPeer(new EntradaTEncam(clave6, HOSTS[1], PUERTO));
            //Service.peers[0].addPeer(new EntradaTEncam(clave7, HOSTS[2], PUERTO));
            Service.peers[0].addPeer(new EntradaTEncam(clave8, HOSTS[2], PUERTO));
            Service.peers[0].addPeer(new EntradaTEncam(clave9, HOSTS[2], PUERTO));
            Service.peers[1].addPeer(new EntradaTEncam(clave1, HOSTS[0], PUERTO));
            Service.peers[1].addPeer(new EntradaTEncam(clave2, HOSTS[0], PUERTO));
            Service.peers[1].addPeer(new EntradaTEncam(clave3, HOSTS[0], PUERTO));
            Service.peers[1].addPeer(new EntradaTEncam(clave4, HOSTS[1], PUERTO));
            Service.peers[1].addPeer(new EntradaTEncam(clave5, HOSTS[1], PUERTO));
            Service.peers[1].addPeer(new EntradaTEncam(clave6, HOSTS[1], PUERTO));
            Service.peers[1].addPeer(new EntradaTEncam(clave7, HOSTS[2], PUERTO));
            //Service.peers[1].addPeer(new EntradaTEncam(clave8, HOSTS[2], PUERTO));
            Service.peers[1].addPeer(new EntradaTEncam(clave9, HOSTS[2], PUERTO));
            Service.peers[2].addPeer(new EntradaTEncam(clave1, HOSTS[0], PUERTO));
            Service.peers[2].addPeer(new EntradaTEncam(clave2, HOSTS[0], PUERTO));
            Service.peers[2].addPeer(new EntradaTEncam(clave3, HOSTS[0], PUERTO));
            Service.peers[2].addPeer(new EntradaTEncam(clave4, HOSTS[1], PUERTO));
            Service.peers[2].addPeer(new EntradaTEncam(clave5, HOSTS[1], PUERTO));
            Service.peers[2].addPeer(new EntradaTEncam(clave6, HOSTS[1], PUERTO));
            Service.peers[2].addPeer(new EntradaTEncam(clave7, HOSTS[2], PUERTO));
            Service.peers[2].addPeer(new EntradaTEncam(clave8, HOSTS[2], PUERTO));
            //Service.peers[2].addPeer(new EntradaTEncam(clave9, HOSTS[2], PUERTO));
        }
    }
    
    
    
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Path("put")
    public void put(@QueryParam("id") String idPeer, @QueryParam("name") String name, @QueryParam("data") String data) {
        int i = 0;
        while (!peers[i].getIdPeer().equals(idPeer)) {
            Main.printBytes(peers[i].getIdPeer().getBytes());
            Main.printBytes(idPeer.getBytes());
            i++;
        }
        System.out.println(idPeer);
        Main.printBytes(idPeer.getBytes());
        System.out.println("ooooooooooooooooooooooooooo");
        byte [] key = peers[i].sha1(name);
        Main.printBytes(key);
        System.out.println("ooooooooooooooooooooooooooo");
        EntradaTEncam nodoMasCercanoAKey = peers[i].getNode(key);
        if (new String(nodoMasCercanoAKey.getIdPeer()).equals(idPeer)) {
            peers[i].put(key, data);
        } else {
            boolean encontrado = false;
            while (!encontrado) {
                URL url;
                try {
                    url = new URL(nodoMasCercanoAKey.getURLBase() + "Peer/getNode/?id=" + 
                            URLEncoder.encode(new String(nodoMasCercanoAKey.getIdPeer()), "UTF-8") + 
                            "&key=" + URLEncoder.encode(new String(key), "UTF-8"));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() == 200) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String respuesta = br.readLine();
                        String idNodoSolicitado = new String(nodoMasCercanoAKey.getIdPeer());
                        nodoMasCercanoAKey = new EntradaTEncam(respuesta.substring(0, 20).getBytes(), respuesta.substring(20), "8080");
                        System.out.println("*********************************");
                        System.out.println("Id nodo solicitado");
                        Main.printBytes(idNodoSolicitado.getBytes());
                        System.out.println("nodo obtenido");
                        Main.printBytes(nodoMasCercanoAKey.getIdPeer());
                        System.out.println("*********************************");
                        if (idNodoSolicitado.equals(new String(nodoMasCercanoAKey.getIdPeer()))) {
                            System.out.println("Encontrado!!!!!");
                            encontrado = true;
                        }
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                URL url;
                url = new URL(nodoMasCercanoAKey.getURLBase() + "Peer/put?id="
                        + URLEncoder.encode(new String(nodoMasCercanoAKey.getIdPeer()), "UTF-8")
                        + "&name=" + URLEncoder.encode(name, "UTF-8") 
                        + "&data=" + URLEncoder.encode(data, "UTF-8"));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
                if (conn.getResponseCode() == 200) {
                }
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
    public String get(@QueryParam("id") String idPeer, @QueryParam("name") String name) {
        int i = 0;
        while (!peers[i].getIdPeer().equals(idPeer)) {
            i++;
        }
        byte [] key = peers[i].sha1(name);
        EntradaTEncam nodoMasCercanoAKey = peers[i].getNode(key);
        if (new String(nodoMasCercanoAKey.getIdPeer()).equals(idPeer)) {
            return peers[i].get(key);
        } else {
            boolean encontrado = false;
            while (!encontrado) {
                URL url;
                try {
                    url = new URL(nodoMasCercanoAKey.getURLBase() + "Peer/getNode/?id=" + 
                            URLEncoder.encode(new String(nodoMasCercanoAKey.getIdPeer()), "UTF-8") + 
                            "&key=" + URLEncoder.encode(new String(key), "UTF-8"));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() == 200) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String respuesta = br.readLine();
                        String idNodoSolicitado = new String(nodoMasCercanoAKey.getIdPeer());
                        nodoMasCercanoAKey = new EntradaTEncam(respuesta.substring(0, 20).getBytes(), respuesta.substring(20), "8080");
                        System.out.println("*********************************");
                        System.out.println("Id nodo solicitado");
                        Main.printBytes(idNodoSolicitado.getBytes());
                        System.out.println("nodo obtenido");
                        Main.printBytes(nodoMasCercanoAKey.getIdPeer());
                        System.out.println("*********************************");
                        if (idNodoSolicitado.equals(new String(nodoMasCercanoAKey.getIdPeer()))) {
                            System.out.println("Encontrado!!!!!");
                            encontrado = true;
                        }
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                URL url;
                url = new URL(nodoMasCercanoAKey.getURLBase() + "Peer/get?id=" + 
                        URLEncoder.encode(new String(nodoMasCercanoAKey.getIdPeer()), "UTF-8") + 
                        "&name=" + URLEncoder.encode(name, "UTF-8"));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                if (conn.getResponseCode() == 200) {
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
    @Path("getNode")
    public String getNode(@QueryParam("id") String idPeer, @QueryParam("key") String key) {
        int i = 0;
        while (!peers[i].getIdPeer().equals(idPeer)) {
            i++;
        }
        EntradaTEncam nodoMasCercanoAKey = peers[i].getNode(key.getBytes());
        Main.printBytes(key.getBytes());
        System.out.println(".........................");
        Main.printBytes(nodoMasCercanoAKey.getIdPeer());
        Main.printBytes(idPeer.getBytes());
        System.out.println("------------------------------------");
        return new String(nodoMasCercanoAKey.getIdPeer()) + nodoMasCercanoAKey.getHost();
    }
    
    
}
