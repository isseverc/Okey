/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okey;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author canberk.issever
 */
public class Okey {
    
    public static List<Taslar> taslariUret(){
        
        Taslar tas = null;
        List<Taslar> taslar = new ArrayList<>();
        
        for(int i=0; i<106; i++){
            
            if(i < 53){
                if(i >= 0 && i <= 12){
                    tas = new Taslar("Sarı", i+1, i);
                }else if(i >= 13 && i <= 25){
                    tas = new Taslar("Mavi", i-12, i);
                }else if(i >= 26 && i <= 38){
                    tas = new Taslar("Siyah", i-25, i);
                }else if(i >= 39 && i <= 51){
                    tas = new Taslar("Kırmızı", i-38, i);
                }else if(i == 52){
                    tas = new Taslar("Renksiz", 0, i);
                    tas.setSahteOkey(true);
                }
            }else{
                if(i >= 52 && i <= 65){
                    tas = new Taslar("Sarı", i-52, i);
                }else if(i >= 66 && i <= 78){
                    tas = new Taslar("Mavi", i-13-52, i);
                }else if(i >= 79 && i <= 91){
                    tas = new Taslar("Siyah", i-26-52, i);
                }else if(i >= 92 && i <= 104){
                    tas = new Taslar("Kırmızı", i-39-52, i);
                }else if(i == 105){
                    tas = new Taslar("Renksiz", 0, i);
                    tas.setSahteOkey(true);
                }
            }
            
            taslar.add(tas);
        }
        
        return taslar;
    }
    
    public static void taslariKaristir(List<Taslar> taslar){
        
        Collections.shuffle(taslar);
 
    }
    
    public static void okeyBelirle(List<Taslar> taslar){
                   
        Random random = new Random();
        
        while(true){
            Taslar gosterge = taslar.get(random.nextInt(106));
            if(!gosterge.isSahteOkey()){
                taslar.stream().filter(tas -> tas.getRenk().equals(gosterge.getRenk()) 
                                        && tas.getSayi().equals((gosterge.getSayi() % 13) + 1))
                                        .forEach(tas -> {{
                                                            tas.setOkey(true);
                                                            tas.setBosta(false);
                                                        }});
                break;
            }
        }
        
        for(Taslar tas1: taslar){
            if(tas1.isOkey()){
                taslar.stream().filter(tas -> tas.isSahteOkey())
                                            .forEach(tas -> {{
                                                                tas.setSayi(tas1.getSayi());
                                                                tas.setRenk(tas1.getRenk());
                                                            }});
            }
        }
    }
    
    public static List<Oyuncu> oyunculariYarat(){
        
        List<Oyuncu> oyuncular = new ArrayList<Oyuncu>();
            for (int i = 1; i <= 4; i++) {
                    oyuncular.add(new Oyuncu(i));
            }
            
        return oyuncular;
    }
    
    public static void taslariDagit(List<Oyuncu> oyuncular, List<Taslar> taslar) {
        
            Random random = new Random();
            
            Oyuncu ilkOyuncu = oyuncular.get(random.nextInt(4));
            
            ilkOyuncu.getTaslar().addAll(taslar.subList(0, 15));
            
            int i = 1;
            for (Oyuncu oyuncu: oyuncular) {
                if(!oyuncu.getNo().equals(ilkOyuncu.getNo())){
                        oyuncu.getTaslar().addAll(taslar.subList((i * 14) + 1, ((i + 1) * 14) + 1));
                    i++;
                }
            }
    }
    
    public static void taslariDiz(List<Oyuncu> oyuncular) {
            oyuncular.forEach(oyuncu -> {{
                            oyuncu.setTaslar(oyuncu.getTaslar().stream().sorted(Comparator.comparing(Taslar::getSayi)
                                                                                .thenComparing(Taslar::getRenk))
                                                                        .collect(Collectors.toList()));
                                        }});
    }
    
    public static void cifteGiden(List<Oyuncu> oyuncular) {

        for(Oyuncu oyuncu: oyuncular){
            
            int ciftSayisi = 0;
            List<Taslar> taslar = oyuncu.getTaslar();
            
            for(int i=0; i<taslar.size()-1; i++){
                if(taslar.get(i).getSayi().equals(taslar.get(i + 1).getSayi()) 
                        && taslar.get(i).getRenk().equals(taslar.get(i + 1).getRenk())
                        && !taslar.get(i).isOkey()){
                    ciftSayisi++;
                }
            }
            
            oyuncu.setCiftSayisi(ciftSayisi);
        }
    }
    
    public static void perGiden(List<Oyuncu> oyuncular) {

        for(Oyuncu oyuncu: oyuncular){
            
            List<Taslar> taslar = oyuncu.getTaslar();

            List<Taslar> renkSiraliTaslar = taslar.stream().filter(tas -> !tas.isOkey()).sorted(Comparator.comparing(Taslar::getRenk)
                                                                                )
                                                                        .collect(Collectors.toList());

            for(int i=0; i<renkSiraliTaslar.size()-1; i++){
                if(i <= renkSiraliTaslar.size()-3){
                    if(renkSiraliTaslar.get(i).getSayi().equals(renkSiraliTaslar.get(i+1).getSayi() - 1) 
                            && renkSiraliTaslar.get(i+1).getSayi().equals(renkSiraliTaslar.get(i+2).getSayi() - 1) 
                            && renkSiraliTaslar.get(i).getRenk().equals(renkSiraliTaslar.get(i+1).getRenk())
                            && renkSiraliTaslar.get(i+1).getRenk().equals(renkSiraliTaslar.get(i+2).getRenk())){
                        
                        renkSiraliTaslar.get(i).setBosta(false);
                        renkSiraliTaslar.get(i+1).setBosta(false);
                        renkSiraliTaslar.get(i+2).setBosta(false);
                    }
                }
            }
            
            
            
            List<Taslar> bostaSiraliTaslar = taslar.stream().filter(tas -> !tas.isOkey()).filter(tas -> tas.isBosta()).sorted(Comparator.comparing(Taslar::getSayi)
                                                                                .thenComparing(Taslar::getRenk))
                                                                        .collect(Collectors.toList());
            
            for(int i=0; i<bostaSiraliTaslar.size()-1; i++){
                if(i <= bostaSiraliTaslar.size()-3){
                    if(bostaSiraliTaslar.get(i).getSayi().equals(bostaSiraliTaslar.get(i+1).getSayi()) 
                            && bostaSiraliTaslar.get(i+1).getSayi().equals(bostaSiraliTaslar.get(i+2).getSayi()) 
                            && !bostaSiraliTaslar.get(i).getRenk().equals(bostaSiraliTaslar.get(i+1).getRenk())
                            && !bostaSiraliTaslar.get(i+1).getRenk().equals(bostaSiraliTaslar.get(i+2).getRenk())){
                        
                        bostaSiraliTaslar.get(i).setBosta(false);
                        bostaSiraliTaslar.get(i+1).setBosta(false);
                        bostaSiraliTaslar.get(i+2).setBosta(false);
                    }
                }
            }
            
        }
    }
    
    public static void enIyiHesapla(List<Oyuncu> oyuncular){
        
        for(Oyuncu oyuncu: oyuncular){
            
            List<Taslar> taslar = oyuncu.getTaslar();
            
            int okeySayisi = 0;
            
            for(Taslar tas: taslar){
                if(tas.isOkey()){
                    okeySayisi++;
                }
            }
            
            oyuncu.setOkeySayisi(okeySayisi);
            
            List<Taslar> kalanTaslar = taslar.stream().filter(tas -> !tas.isOkey()).filter(tas -> tas.isBosta()).sorted(Comparator.comparing(Taslar::getSayi)
                                                                                .thenComparing(Taslar::getRenk))
                                                                        .collect(Collectors.toList());
            
            if((oyuncu.getCiftSayisi() * 2) > (taslar.size() - kalanTaslar.size())){
                oyuncu.setBitmeSekli("Çiftle Biter");
                oyuncu.setKalanTaslar(taslar.size() - (oyuncu.getCiftSayisi() * 2) - (oyuncu.getOkeySayisi() * 2));
            }else{
                oyuncu.setBitmeSekli("Normal Biter");
                oyuncu.setKalanTaslar(kalanTaslar.size() - (oyuncu.getOkeySayisi() * 3));
            }
        }
    }
    
    public static void enIyiEliYazdir(List<Oyuncu> oyuncular){
        
        List<Oyuncu> enIyiyeGoreSirali = oyuncular.stream().sorted(Comparator.
                                                                    comparingInt(Oyuncu::getKalanTaslar))
                                                            .collect(Collectors.toList());
        
        System.out.println("---------- En İyi Ele Sahip Oyuncuya Göre Sıralama: ----------\n");
        
        Iterator iter = enIyiyeGoreSirali.iterator();
        while(iter.hasNext()){
            Oyuncu oyuncu = (Oyuncu) iter.next();
            List<Taslar> taslarPrint = oyuncu.getTaslar();
                        
            System.out.println("<Oyuncu-" + oyuncu.getNo()+">" 
                    + ", Kalan Taş Sayısı: " + oyuncu.getKalanTaslar() 
                    + ", Bitme Şekli: " + oyuncu.getBitmeSekli()
                    + ", Çift Sayısı: " + oyuncu.getCiftSayisi()
                    + ", Okey Sayısı: " + oyuncu.getOkeySayisi());
            
            System.out.println("---------- Elindeki Taşlar: ----------");
            Iterator iter2 = taslarPrint.iterator();
            while(iter2.hasNext()){
                Taslar tas = (Taslar) iter2.next();
                if(tas.isOkey()){
                    System.out.println(tas.getRenk() 
                            + "-" + tas.getSayi()
                            + ", *OKEY*");
                }else{
                   System.out.println(tas.getRenk() 
                           + "-" + tas.getSayi());
                }
            }
            System.out.println("------------------------------\n");
        }   
    }
    
    
    
    
    
    public static void main(String[] args) {
        
        List<Taslar> taslar = Okey.taslariUret();
        
        Okey.taslariKaristir(taslar);
        
        Okey.okeyBelirle(taslar);
        
        List<Oyuncu> oyuncular = Okey.oyunculariYarat();
        
        Okey.taslariDagit(oyuncular, taslar);
        
        Okey.taslariDiz(oyuncular);
        
        Okey.cifteGiden(oyuncular);
        
        Okey.perGiden(oyuncular);
        
        Okey.enIyiHesapla(oyuncular);
        
        Okey.enIyiEliYazdir(oyuncular);
          
    }
    
}
