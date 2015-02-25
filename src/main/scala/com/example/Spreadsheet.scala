package com.example

import java.net.URL
import scala.collection.JavaConverters._
import com.google.api.client.auth.oauth2.Credential
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.spreadsheet.SpreadsheetFeed
import com.google.gdata.data.spreadsheet.SpreadsheetEntry
import com.google.gdata.client.spreadsheet.ListQuery
import com.google.gdata.data.spreadsheet.ListFeed
import com.google.gdata.data.spreadsheet.ListEntry

object Spreadsheet {
  
  val SpreadsheetFeedUrl = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full")
  
  def apply(title:String,credential:Credential)={
    service.setOAuth2Credentials(credential)
    // Make a request to the API and get all spreadsheets.
    val feed = service.getFeed(SpreadsheetFeedUrl,classOf[SpreadsheetFeed])
    
    feed.getEntries().asScala.find( _.getTitle.getPlainText==title) match {
      case Some(s) => new Spreadsheet(s)
      case _ => throw new NoSuchElementException
    }
  }
  
  
  lazy val service:SpreadsheetService=new SpreadsheetService("SpreadsheetIntegration")
  
}



class Spreadsheet(entry:SpreadsheetEntry) {
  
  val worksheet = entry.getWorksheets().get(0)
  

} 