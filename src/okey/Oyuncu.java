/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okey;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author canberk.issever
 */
public class Oyuncu {
    
    private List<Taslar> taslar;
    private Integer no;
    private Integer ciftSayisi = 0;
    private Integer kalanTaslar = 0;
    private Integer okeySayisi = 0;
    private String bitmeSekli = "";

    public Oyuncu(Integer no) {
            super();
            this.no = no;
            this.taslar = new ArrayList<>();
    }

    public List<Taslar> getTaslar() {
            return taslar;
    }

    public void setTaslar(List<Taslar> taslar) {
            this.taslar = taslar;
    }

    public Integer getNo() {
            return no;
    }

    public void setNo(Integer no) {
            this.no = no;
    }

    public Integer getCiftSayisi() {
        return ciftSayisi;
    }

    public void setCiftSayisi(Integer ciftSayisi) {
        this.ciftSayisi = ciftSayisi;
    }

    public Integer getKalanTaslar() {
        return kalanTaslar;
    }

    public void setKalanTaslar(Integer kalanTaslar) {
        this.kalanTaslar = kalanTaslar;
    }

    public Integer getOkeySayisi() {
        return okeySayisi;
    }

    public void setOkeySayisi(Integer okeySayisi) {
        this.okeySayisi = okeySayisi;
    }

    public String getBitmeSekli() {
        return bitmeSekli;
    }

    public void setBitmeSekli(String bitmeSekli) {
        this.bitmeSekli = bitmeSekli;
    }
    
}