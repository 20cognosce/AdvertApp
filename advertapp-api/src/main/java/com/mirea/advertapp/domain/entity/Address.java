package com.mirea.advertapp.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "county")
    private String county;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @Column(name = "street")
    private String street;
    @Column(name = "house_number")
    private String houseNumber;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    private List<Advert> adverts;
}
