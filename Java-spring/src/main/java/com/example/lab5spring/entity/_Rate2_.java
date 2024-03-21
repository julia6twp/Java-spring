package com.example.lab5spring.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "rate", schema = "baza_okienka_lab4", catalog = "")
public class _Rate2_ {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "ocena")
    private Integer ocena;
    @Basic
    @Column(name = "grupy")
    private Integer grupy;
    @Basic
    @Column(name = "data_wystawienia_oceny")
    private Date dataWystawieniaOceny;
    @Basic
    @Column(name = "komentarz")
    private String komentarz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public Integer getGrupy() {
        return grupy;
    }

    public void setGrupy(Integer grupy) {
        this.grupy = grupy;
    }

    public Date getDataWystawieniaOceny() {
        return dataWystawieniaOceny;
    }

    public void setDataWystawieniaOceny(Date dataWystawieniaOceny) {
        this.dataWystawieniaOceny = dataWystawieniaOceny;
    }

    public String getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(String komentarz) {
        this.komentarz = komentarz;
    }
}
