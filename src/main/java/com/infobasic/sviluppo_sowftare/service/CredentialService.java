package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.CredentialDao;
import com.infobasic.sviluppo_sowftare.model.Credential;
import com.infobasic.sviluppo_sowftare.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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

    public User findCredentialUserByEmail(String email) throws SQLException {

        return credentialDao.findCredentialUserByEmail(email);
    }

}
