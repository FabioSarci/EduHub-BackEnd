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

    public Credential findCredentialByEmail(Credential credential){
        return credentialDao.findByEmail(credential);
    }

    public List<Credential> findAllCredentials(){
        return credentialDao.findAll();
    }

    public long countCredentials(){
        return credentialDao.count();
    }

}
