package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Client {
    private int id;
    private String nom,prenom,sexe,addr,mail,phone,birthdate,card;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    /**
     * Return the age of the client.
     * @return
     */
    public int Age()
    {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            Date date = formatter.parse(this.birthdate);
            LocalDate now = LocalDate.now();
            LocalDate birthday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
// using period
            Period period = Period.between(birthday, now);
            int age =  period.getYears();
            return age;
            }catch(ParseException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    public String toString(){
        return ""+this.prenom+","+this.nom+",Age :"+this.Age()+";"+this.mail;
    }
}
