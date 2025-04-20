package com.example.tpcontact.api;

import com.example.tpcontact.bean.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ContactApi {
    @POST("/api/contact/")
    Call<Contact> saveContact(@Body Contact contact);

    // Nouvelle méthode pour récupérer tous les contacts
    @GET("/api/contact")
    Call<List<Contact>> getAllContacts();
}

