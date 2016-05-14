package Test;

import Functions.Hasher;

public class HashTest {
    
    public static void main(String[] args) {
        Hasher hasher = new Hasher();
        
//        System.out.println(hasher.hash("MD2", "hello"));
//        System.out.println(hasher.hash("MD2", "hello").length() + "\n");
//        
//        System.out.println(hasher.hash("MD5", "hello"));
//        System.out.println(hasher.hash("MD5", "hello").length() + "\n");
//        
//        System.out.println(hasher.hash("SHA-1", "hello"));
//        System.out.println(hasher.hash("SHA-1", "hello").length() + "\n");
//        
//        System.out.println(hasher.hash("SHA-256", "hello"));
//        System.out.println(hasher.hash("SHA-256", "hello").length() + "\n");
//        
//        System.out.println(hasher.hash("SHA-384", "hello"));
//        System.out.println(hasher.hash("SHA-384", "hello").length() + "\n");
        
        System.out.println(hasher.hash("SHA-512", "hello"));
        System.out.println(hasher.hash("SHA-512", "hello").length() + "\n");
        
        System.out.println(hasher.hash("SHA-512", "pw"));
    }
    
}
