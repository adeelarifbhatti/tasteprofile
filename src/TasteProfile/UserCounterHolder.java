package TasteProfile;

/**
* TasteProfile/UserCounterHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from tasteprofile.idl
* Friday, September 20, 2019 3:58:46 PM CEST
*/

public final class UserCounterHolder implements org.omg.CORBA.portable.Streamable
{
  public TasteProfile.UserCounter value = null;

  public UserCounterHolder ()
  {
  }

  public UserCounterHolder (TasteProfile.UserCounter initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TasteProfile.UserCounterHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TasteProfile.UserCounterHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TasteProfile.UserCounterHelper.type ();
  }

}
