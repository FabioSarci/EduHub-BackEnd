package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.CredentialDao;
import com.infobasic.sviluppo_sowftare.model.Credential;

import java.util.List;

public class CredentialService {

    private static CredentialDao credentialDao = new CredentialDao();

    public Credential addCredential(Credential credential){
        return  credentialDao.create(credential);
    }

    public Credential editCredential(Credential credential){
        return credentialDao.update(credential);
    }

    public Credential findCredentialByEmail(String email){
        return credentialDao.findByEmail(email);
    }

    public List<Credential> findAllCredentials(){
        return credentialDao.findAll();
    }

    public long countCredentials(){
        return credentialDao.count();
    }

}
