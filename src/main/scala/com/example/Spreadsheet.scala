package com.example

import java.net.URL
import scala.collection.JavaConverters._
import com.google.api.client.auth.oauth2.Credential
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.spreadsheet.SpreadsheetFeed
import com.google.gdata.data.spreadsheet.SpreadsheetEntry


object Spreadsheets {
  
  val SpreadsheetFeedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full")
  
  def refreshCredential(credential:Credential)={
    service.setOAuth2Credentials(credential)
    this
  }
  
  def all={
    feed.getEntries.asScala.map( new Spreadsheet(_))
  }
  
  lazy val feed = service.getFeed(SpreadsheetFeedUrl,classOf[SpreadsheetFeed])
  
  lazy val service:SpreadsheetService=new SpreadsheetService("SpreadsheetIntegration")
  
}



class Spreadsheet(entry:SpreadsheetEntry) {
  
  lazy val worksheets = entry.getWorksheets()

} 