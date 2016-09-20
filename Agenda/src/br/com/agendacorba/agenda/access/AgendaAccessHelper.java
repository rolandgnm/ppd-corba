package br.com.agendacorba.agenda.access;


/**
* br/com/agendacorba/agenda/access/AgendaAccessHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Monday, September 19, 2016 11:31:05 PM BRT
*/

abstract public class AgendaAccessHelper
{
  private static String  _id = "IDL:br/com/agendacorba/agenda/access/AgendaAccess:1.0";

  public static void insert (org.omg.CORBA.Any a, br.com.agendacorba.agenda.access.AgendaAccess that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static br.com.agendacorba.agenda.access.AgendaAccess extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (br.com.agendacorba.agenda.access.AgendaAccessHelper.id (), "AgendaAccess");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static br.com.agendacorba.agenda.access.AgendaAccess read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_AgendaAccessStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, br.com.agendacorba.agenda.access.AgendaAccess value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static br.com.agendacorba.agenda.access.AgendaAccess narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof br.com.agendacorba.agenda.access.AgendaAccess)
      return (br.com.agendacorba.agenda.access.AgendaAccess)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      br.com.agendacorba.agenda.access._AgendaAccessStub stub = new br.com.agendacorba.agenda.access._AgendaAccessStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static br.com.agendacorba.agenda.access.AgendaAccess unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof br.com.agendacorba.agenda.access.AgendaAccess)
      return (br.com.agendacorba.agenda.access.AgendaAccess)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      br.com.agendacorba.agenda.access._AgendaAccessStub stub = new br.com.agendacorba.agenda.access._AgendaAccessStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}