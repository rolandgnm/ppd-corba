package br.com.agendacorba.agenda;


/**
* br/com/agendacorba/agenda/Contact.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Monday, September 19, 2016 11:31:05 PM BRT
*/

public final class Contact implements org.omg.CORBA.portable.IDLEntity
{
  public String name = null;
  public String telNumber = null;

  public Contact ()
  {
  } // ctor

  public Contact (String _name, String _telNumber)
  {
    name = _name;
    telNumber = _telNumber;
  } // ctor

} // class Contact