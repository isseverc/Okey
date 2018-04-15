/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okey;

/**
 *
 * @author canberk.issever
 */
public class Taslar {
    
    private String renk;
    private Integer sayi;
    private Integer index;
    private boolean okey = false;
    private boolean sahteOkey = false;
    private boolean bosta = true;
    
    Taslar(String renk, Integer sayi, Integer index) {
        this.renk = renk;
        this.sayi = sayi;
        this.index = index;
        this.okey = false;
        this.sahteOkey = false;
    }

    public Integer getIndex() {
            return index;
    }

    public void setIndex(Integer index) {
            this.index = index;
    }

    public Integer getSayi() {
            return sayi;
    }

    public void setSayi(Integer sayi) {
            this.sayi = sayi;
    }

    public String getRenk() {
            return renk;
    }

    public void setRenk(String renk) {
            this.renk = renk;
    }

    public boolean isOkey() {
            return okey;
    }

    public void setOkey(boolean okey) {
            this.okey = okey;
    }

    public boolean isSahteOkey() {
            return sahteOkey;
    }

    public void setSahteOkey(boolean sahteOkey) {
            this.sahteOkey = sahteOkey;
    }

    public boolean isBosta() {
        return bosta;
    }

    public void setBosta(boolean bosta) {
        this.bosta = bosta;
    }
    
}
