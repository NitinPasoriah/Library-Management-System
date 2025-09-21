package org.example.factory;

import org.example.Patron;

public class patronFactory {
    public static Patron createPatron(String patronMemberId, String patronName, String contactNo){
         return new Patron(patronMemberId, patronName, contactNo);
    }
}
