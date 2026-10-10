package Aslenix.Simple_Pharmacy.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordUtil
{

    private static  final Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);

    public static String hashPassword(String password){
        try {
            return argon2.hash(3, 65536, 1, password.toCharArray());
        }
        finally {
            argon2.wipeArray(password.toCharArray());
        }
    }

    public static boolean verifyPassword(String hash,  String password) {
        try {
            return argon2.verify(hash, password.toCharArray());
        } finally {
            argon2.wipeArray(password.toCharArray());
        }
    }

}
