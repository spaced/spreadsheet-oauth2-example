package com.example


import java.io.InputStreamReader
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp

object OAuth2 {
  lazy val datastoreFactory= 
    new FileDataStoreFactory(new java.io.File(System.getProperty("user.home"), ".store/sample_datastore"))

  
  def apply():Credential= {
    val JSON_FACTORY = JacksonFactory.getDefaultInstance()
    val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    val SCOPES = java.util.Arrays.asList("https://docs.google.com/feeds","https://spreadsheets.google.com/feeds")
    // load client secrets
    val clientSecrets = GoogleClientSecrets
      .load(JSON_FACTORY,new InputStreamReader(OAuth2.getClass.getResourceAsStream("/client_secrets.json")))
        
    val flow = new GoogleAuthorizationCodeFlow
      .Builder(httpTransport, JSON_FACTORY, clientSecrets,SCOPES)
      .setDataStoreFactory(datastoreFactory)
      .build()
      
    // authorize
    new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user")
  }
}