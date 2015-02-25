package com.example

object Demo extends App {
  
   val credential=OAuth2()
   val spreadsheet=Spreadsheet("mytest",credential)
   val worksheet=spreadsheet.worksheet
   val title = worksheet.getTitle().getPlainText()
   val rowCount = worksheet.getRowCount()
   val colCount = worksheet.getColCount()
   println(title + "- rows:" + rowCount + " cols: " + colCount);

}