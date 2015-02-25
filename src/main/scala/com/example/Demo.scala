package com.example

object Demo extends App {
  
  val credential = OAuth2()
  		
  val spreadSheets = Spreadsheets
    .refreshCredential(credential)
    .all
 
  spreadSheets.foreach { s =>
    val worksheet = s.worksheets.get(0)
    val title = worksheet.getTitle().getPlainText()
    val rowCount = worksheet.getRowCount()
    val colCount = worksheet.getColCount()
    println(title + "- rows:" + rowCount + " cols: " + colCount);
  }

}