package dk.schioler.event.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSchemaPath {

   static Logger LOG = LoggerFactory.getLogger(TestSchemaPath.class);
   
   static String errorMsg; 
   
   static String XML_FILE_NAME = "event-base-data.xml";
   static String XML_FILE_PATH = "src/test/resources/xml/";
   static String XML_FULL_PATH = XML_FILE_PATH + XML_FILE_NAME;
         
   public static void main(String[] args) {
      File f = new File(XML_FULL_PATH);
      
      InputStream is = null;
      InputStreamReader isr = null;
      
      try {
         is = new FileInputStream(f);
//         isr = new InputStreamReader(in, Charset.defaultCharset());
         
         
         
      } catch (Exception e) {
         errorMsg = e.getMessage();
         LOG.error(errorMsg);
         
         
      } finally {
         if (is != null ) {
            try {
               is.close();
            } catch (IOException e) {
               LOG.error(e.getMessage());
               e.printStackTrace();
            }
         } else {
            LOG.debug("Skipping is.close() - is == null");
         }
      }
      

      
      
   }
}
