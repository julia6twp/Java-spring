package com.example.lab5spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher", schema = "baza_okienka_lab4", catalog = "")
public class _Teacher2_ {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "imie")
    private String imie;
    @Basic
    @Column(name = "nazwisko")
    private String nazwisko;

    public _Teacher2_(int id, String imie, String nazwisko, String stan, Integer rokUrodzenia, Double wynagrodzenie, Integer grupa) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stan = stan;
        this.rokUrodzenia = rokUrodzenia;
        this.wynagrodzenie = wynagrodzenie;
        this.grupa = grupa;
    }

    public _Teacher2_() {
    }

    @Basic
    @Column(name = "stan")
    private String stan;
    @Basic
    @Column(name = "rok_urodzenia")
    private Integer rokUrodzenia;
    @Basic
    @Column(name = "wynagrodzenie")
    private Double wynagrodzenie;
    @Basic
    @Column(name = "grupa")
    private Integer grupa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public Integer getRokUrodzenia() {
        return rokUrodzenia;
    }

    public void setRokUrodzenia(Integer rokUrodzenia) {
        this.rokUrodzenia = rokUrodzenia;
    }

    public Double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(Double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public Integer getGrupa() {
        return grupa;
    }

    public void setGrupa(Integer grupa) {
        this.grupa = grupa;
    }
}
