package com.example.basketmenejeri;

import java.util.Random;

public class GameClass  {
    Random rastgele = new Random();
     int birincitakimgucu;
     int ikincitakimgucu;
     int ikilikorani;
     int uclukorani, serbestatisorani , birincitakımataksayi , ikincitakımataksayi;
     Boolean adamadamaalan=true;
     int pres;
     Boolean ataksavunma = true;
     int birincitakımatakgucu;
     int ikincitakımatakgucu;
    int birincitakımdefansgucu;
    int ikincitakımdefansgucu;

 public GameClass(){
     this.birincitakimgucu = birincitakimgucu;
     this.ikincitakimgucu = ikincitakimgucu;
     this.ikilikorani = ikilikorani;
     this.uclukorani = uclukorani;
     this.serbestatisorani = serbestatisorani;
     this.birincitakımataksayi = birincitakımataksayi;
     this.ikincitakımataksayi = ikincitakımataksayi;
     this.adamadamaalan=adamadamaalan;
     this.ataksavunma=ataksavunma;
     this.pres=pres;
     this.birincitakımatakgucu = birincitakımatakgucu;
     this.birincitakımdefansgucu = birincitakımdefansgucu;
     this.ikincitakımatakgucu = ikincitakımatakgucu;
     this.ikincitakımdefansgucu = ikincitakımdefansgucu;

 }
    public int getBirincitakimgucu() {
        return birincitakimgucu;
    }

    public void setBirincitakimgucu(int birincitakimgucu) {
        this.birincitakimgucu = birincitakimgucu;
    }

    public int getIkilikorani() {
        return ikilikorani;
    }

    public void setIkilikorani(int ikilikorani) {
        this.ikilikorani = ikilikorani;
    }

    public int getUclukorani() {
        return uclukorani;
    }

    public void setUclukorani(int uclukorani) {
        this.uclukorani = uclukorani;
    }

    public int getSerbestatisorani() {
        return serbestatisorani;
    }

    public void setSerbestatisorani(int serbestatisorani) {
        this.serbestatisorani = serbestatisorani;
    }

    public int getBirincitakımataksayi() {
        return birincitakımataksayi;
    }

    public void setBirincitakımataksayi(int birincitakımataksayi) {
        this.birincitakımataksayi = birincitakımataksayi;
    }

    public int getIkincitakımataksayi() {
        return ikincitakımataksayi;
    }

    public void setIkincitakımataksayi(int ikincitakımataksayi) {
        this.ikincitakımataksayi = ikincitakımataksayi;
    }

    public Boolean getAdamadamaalan() {
        return adamadamaalan;
    }

    public void setAdamadamaalan(Boolean adamadamaalan) {
        this.adamadamaalan = adamadamaalan;
    }

    public int getPres() {
        return pres;
    }

    public void setPres(int pres) {
        this.pres = pres;
    }

    public Boolean getAtaksavunma() {
        return ataksavunma;
    }

    public void setAtaksavunma(Boolean ataksavunma) {
        this.ataksavunma = ataksavunma;
    }

    public int getBirincitakımatakgucu() {
        return birincitakımatakgucu;
    }

    public void setBirincitakımatakgucu(int birincitakımatakgucu) {
        this.birincitakımatakgucu = birincitakımatakgucu;
    }

    public int getIkincitakımatakgucu() {
        return ikincitakımatakgucu;
    }

    public void setIkincitakımatakgucu(int ikincitakımatakgucu) {
        this.ikincitakımatakgucu = ikincitakımatakgucu;
    }

    public int getBirincitakımdefansgucu() {
        return birincitakımdefansgucu;
    }

    public void setBirincitakımdefansgucu(int birincitakımdefansgucu) {
        this.birincitakımdefansgucu = birincitakımdefansgucu;
    }

    public int getIkincitakımdefansgucu() {
        return ikincitakımdefansgucu;
    }

    public void setIkincitakımdefansgucu(int ikincitakımdefansgucu) {
        this.ikincitakımdefansgucu = ikincitakımdefansgucu;
    }

    public int getIkincitakimgucu() {
        return ikincitakimgucu;
    }

    public void setIkincitakimgucu(int ikincitakimgucu) {
        this.ikincitakimgucu = ikincitakimgucu;
    }

    public int macyap(int birincitakimgucu , boolean adamadamaalan , int pres){
   birincitakımatakgucu=0;
   birincitakımdefansgucu=0;
           if(ataksavunma == true){
               birincitakımatakgucu = birincitakımatakgucu + 2;
           birincitakımdefansgucu = birincitakımdefansgucu -2;
           }
           else{
            birincitakımatakgucu = birincitakımatakgucu - 2;
               birincitakımdefansgucu = birincitakımdefansgucu +2;
        }
        if(pres > 70)
            birincitakımatakgucu = birincitakımatakgucu + 4;
         else if(pres >40 && pres<70 )
             birincitakımatakgucu = birincitakımatakgucu +2;
         else{
             birincitakımatakgucu=birincitakımatakgucu-1;
        }
        if(adamadamaalan ==true){
            birincitakımdefansgucu = birincitakımdefansgucu +2;
            birincitakımatakgucu = birincitakımatakgucu -1;
        }
        else{
            birincitakımdefansgucu = birincitakımdefansgucu -2;
            birincitakımatakgucu = birincitakımatakgucu +1;
        }
     birincitakimgucu = birincitakimgucu +birincitakımatakgucu + birincitakımdefansgucu;


   return birincitakimgucu;

  }
}
