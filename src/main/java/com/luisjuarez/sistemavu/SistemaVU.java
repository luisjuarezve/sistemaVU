package com.luisjuarez.sistemavu;

import com.luisjuarez.sistemavu.view.Login;
import com.luisjuarez.sistemavu.view.components.Item;
import com.luisjuarez.sistemavu.view.components.ItemInvoice;
import com.luisjuarez.sistemavu.view.components.SearchBar;


public class SistemaVU {

    public static void main(String[] args){
        Login lg = new Login();
        lg.setVisible(true);
        lg.add(new ItemInvoice());
        lg.add(new Item());
                
    }
}
