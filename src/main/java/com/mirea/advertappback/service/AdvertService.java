package com.mirea.advertappback.service;

import com.mirea.advertappback.repo.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

}
