package br.com.agendacorba.agenda;


/**
* br/com/agendacorba/agenda/contactListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Monday, September 19, 2016 11:31:05 PM BRT
*/


//especifica tipo pra passagem de lista
public final class contactListHolder implements org.omg.CORBA.portable.Streamable
{
  public br.com.agendacorba.agenda.Contact value[] = null;

  public contactListHolder ()
  {
  }

  public contactListHolder (br.com.agendacorba.agenda.Contact[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = br.com.agendacorba.agenda.contactListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    br.com.agendacorba.agenda.contactListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return br.com.agendacorba.agenda.contactListHelper.type ();
  }

}
