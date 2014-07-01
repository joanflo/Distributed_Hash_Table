/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package peer;

import java.math.BigInteger;

/**
 *
 * @author Carlos
 */
public class Main {
    
    public static void printBytes(byte [] b) {
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String [] args){
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
        byte [] clave4 = new byte[] {0x64, 0x00, 0x00, 0x00, 0x00,
                                     0x00, 0x00, 0x00, 0x00, 0x00,
                                     0x00, 0x00, 0x00, 0x00, 0x00,
                                     0x00, 0x00, 0x00, 0x00, 0x00};
        Peer p = new Peer(clave1);
        
        System.out.print("Clave 1: "); 
        printBytes(clave1);
        System.out.print("Clave 2: ");
        printBytes(clave2);
        System.out.print("Clave 3: ");
        printBytes(clave3);
        System.out.print("Clave 4: ");
        printBytes(clave4);
        
        System.out.print("Distancia clave 2 y clave 3: ");
        System.out.println(p.distance(clave2, clave3).length);
        printBytes(p.distance(clave2, clave3));
        System.out.print("Bit a 1 más sign. de clave 2: ");
        System.out.println(p.getPos1MasSignificativo(clave1));
        
        printBytes(p.sha1("Carlos"));
        
    }
    
}
