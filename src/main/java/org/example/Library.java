package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private List<Patron> patronList;
    private Inventory inventory;
    private Map<String, Patron> patronMap;

    public Library(){
        this.patronList = new ArrayList<>();
        this.inventory = new Inventory();
        this.patronMap = new HashMap<>();
    }

//    Getters
    public List<Patron> getPatronList() {
        return patronList;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public Patron getPatron(String patronMemberId){
        return patronMap.get(patronMemberId);
    }

    public void addPatron(Patron patron){
        if (patron!=null & !patronList.contains(patron)){
            patronList.add(patron);
            patronMap.put(patron.getPatronMemberId(), patron);
        }
    }
    public void updatePatron(Patron patron){
        if (patron!=null & patronMap.containsKey(patron.getPatronMemberId())){
            patronMap.put(patron.getPatronMemberId(), patron);
            for(int i=0; i<patronList.size(); i++){
                if(patronList.get(i).getPatronMemberId().equals(patron.getPatronMemberId())){
                    patronList.set(i, patron);
                    break;
                }
            }
        }

    }
}
