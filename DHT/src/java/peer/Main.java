
package peer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservice.Service;

/**
 *
 */
public class Main {
    
    private final String NOMBRE_FICHERO = "mkjdgdz";
    private final String URL_FICHERO = "Adios_mundo";
    
    public Main() {
        byte [] clave = new byte[] {0x00, 0x00, 0x00, 0x00, 0x00,
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
        /*Peer p = new Peer(clave);
        Service.peers[0].addPeer(new EntradaTEncam(clave1, HOSTS[0], PUERTO));
        p.addPeer(new EntradaTEncam(clave1, "localhost", "8080"));
        p.addPeer(new EntradaTEncam(clave3, "localhost", "8080"));
        Main.printBytes(p.sha1(NOMBRE_FICHERO));*/
        try {
            URL url;
            EntradaTEncam entrada = new EntradaTEncam(clave, "95.17.222.57", "8080");
            url = new URL(entrada.getURLBase() + "Peer/put?id=" + URLEncoder.encode(new String(clave), "UTF-8") + "&name=" + NOMBRE_FICHERO + "&data=" + URL_FICHERO);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.out.println(entrada.getURLBase() + "Peer/put?id=" + URLEncoder.encode(new String(clave)) + "&name=" + URLEncoder.encode(NOMBRE_FICHERO) + "&data=" + URL_FICHERO);
            conn.setRequestMethod("PUT");
            if (conn.getResponseCode() == 200) {
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            URL url;
            EntradaTEncam entrada = new EntradaTEncam(clave, "95.17.222.57", "8080");
            url = new URL(entrada.getURLBase() + "Peer/get?id=" + URLEncoder.encode(new String(clave), "UTF-8") + "&name=" + NOMBRE_FICHERO);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                System.out.println("Get hecho");
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                System.out.println(br.readLine());
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void printBytes(byte [] b) {
        String s = "";
        for (int i = 0; i < b.length; i++) {
            s += String.valueOf(b[i]) + " ";
        }
        System.out.println(s);
    }
    
    
    public static void main(String [] args){
        new Main();
    }
    
}
