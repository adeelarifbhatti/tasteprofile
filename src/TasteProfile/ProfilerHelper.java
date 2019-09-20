package TasteProfile;


/**
* TasteProfile/ProfilerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from tasteprofile.idl
* Friday, September 20, 2019 3:58:46 PM CEST
*/


/* The service interface with the methods that can be invoked remotely by clients */
abstract public class ProfilerHelper
{
  private static String  _id = "IDL:TasteProfile/Profiler:1.0";

  public static void insert (org.omg.CORBA.Any a, TasteProfile.Profiler that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static TasteProfile.Profiler extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (TasteProfile.ProfilerHelper.id (), "Profiler");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static TasteProfile.Profiler read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ProfilerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, TasteProfile.Profiler value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static TasteProfile.Profiler narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof TasteProfile.Profiler)
      return (TasteProfile.Profiler)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      TasteProfile._ProfilerStub stub = new TasteProfile._ProfilerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static TasteProfile.Profiler unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof TasteProfile.Profiler)
      return (TasteProfile.Profiler)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      TasteProfile._ProfilerStub stub = new TasteProfile._ProfilerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
