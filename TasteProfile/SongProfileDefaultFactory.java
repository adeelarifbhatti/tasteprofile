package TasteProfile;


/**
* TasteProfile/SongProfileDefaultFactory.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from tasteprofile.idl
* Sunday, September 15, 2019 6:59:11 PM CEST
*/

public class SongProfileDefaultFactory implements org.omg.CORBA.portable.ValueFactory {

  public java.io.Serializable read_value (org.omg.CORBA_2_3.portable.InputStream is)
  {
    return is.read_value(new SongProfileImpl ());
  }
}
