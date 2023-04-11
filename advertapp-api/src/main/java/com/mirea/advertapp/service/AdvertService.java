package com.mirea.advertapp.service;

import com.mirea.advertapp.repo.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

}
