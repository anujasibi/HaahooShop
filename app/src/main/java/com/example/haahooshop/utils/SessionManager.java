package com.example.haahooshop.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionManager {

    private SharedPreferences sharedPreferences;


    public boolean card;

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public boolean isCard() {
        return card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public SessionManager(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void setTokens(String token){
        sharedPreferences.edit().putString("token",token).commit();
    }
    public String getTokens(){

        return  sharedPreferences.getString("token","");
    }

    public void setPdtName(String PdtName){
        sharedPreferences.edit().putString("PdtName",PdtName).commit();
    }
    public String getPdtName(){

        return  sharedPreferences.getString("PdtName","");
    }

    public void setPdtid(String Pdtid){
        sharedPreferences.edit().putString("Pdtid",Pdtid).commit();
    }
    public String getPdtid(){

        return  sharedPreferences.getString("Pdtid","");
    }


    public void setprice(String price){
        sharedPreferences.edit().putString("price",price).commit();
    }
    public String getprice(){

        return  sharedPreferences.getString("price","");
    }
    public void setret(String ret){
        sharedPreferences.edit().putString("ret",ret).commit();
    }
    public String getret(){

        return  sharedPreferences.getString("ret","");
    }
    public void setdis(String dis){
        sharedPreferences.edit().putString("dis",dis).commit();
    }
    public String getdis(){

        return  sharedPreferences.getString("dis","");
    }
    public void setstock(String stock){
        sharedPreferences.edit().putString("stock",stock).commit();
    }
    public String getstock(){

        return  sharedPreferences.getString("stock","");
    }
    public void setdes(String des){
        sharedPreferences.edit().putString("des",des).commit();
    }
    public String getdes(){

        return  sharedPreferences.getString("des","");
    }
    public void setcatName(String cname){
        sharedPreferences.edit().putString("cname",cname).commit();
    }
    public String getcatName(){

        return  sharedPreferences.getString("cname","");
    }
    public void setcatid(String cid){
        sharedPreferences.edit().putString("cid",cid).commit();
    }
    public String getcatid(){

        return  sharedPreferences.getString("cid","");
    }

    public String getcatdistance(){

        return  sharedPreferences.getString("distance","");
    }
    public void setcatdistance(String distance){
        sharedPreferences.edit().putString("distance",distance).commit();
    }

    public String getcheck(){

        return  sharedPreferences.getString("check","");
    }
    public void setcheck(String check){
        sharedPreferences.edit().putString("check",check).commit();
    }
    public String getcheckn(){

        return  sharedPreferences.getString("checkn","");
    }
    public void setcheckn(String checkn){
        sharedPreferences.edit().putString("checkn",checkn).commit();
    }
    public String getradio(){

        return  sharedPreferences.getString("radio","");
    }
    public void setradio(String radio){
        sharedPreferences.edit().putString("radio",radio).commit();
    }

    public String getID() {
        String lat = sharedPreferences.getString("ID","");

        return lat;
    }

    public void setID(String ID) {
        sharedPreferences.edit().putString("ID",ID).commit();
    }

}
