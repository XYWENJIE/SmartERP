package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;

/**
 * 房屋（房产）等信息
 */
@Entity
@Table(name = "TAB_PROPERTY")
public class Property extends BasicEntity{

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NUMBER_OF_ROOMS")
    private Integer numberOfRooms;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
