package TasteProfile;

/**
* TasteProfile/TopThreeUsersHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from tasteprofile.idl
* Friday, September 20, 2019 3:58:46 PM CEST
*/

public final class TopThreeUsersHolder implements org.omg.CORBA.portable.Streamable
{
  public TasteProfile.TopThreeUsers value = null;

  public TopThreeUsersHolder ()
  {
  }

  public TopThreeUsersHolder (TasteProfile.TopThreeUsers initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TasteProfile.TopThreeUsersHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TasteProfile.TopThreeUsersHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TasteProfile.TopThreeUsersHelper.type ();
  }

}
