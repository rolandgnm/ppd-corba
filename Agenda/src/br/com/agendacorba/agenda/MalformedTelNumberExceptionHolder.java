package br.com.agendacorba.agenda;

/**
* br/com/agendacorba/agenda/MalformedTelNumberExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Monday, September 19, 2016 11:31:05 PM BRT
*/

public final class MalformedTelNumberExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public br.com.agendacorba.agenda.MalformedTelNumberException value = null;

  public MalformedTelNumberExceptionHolder ()
  {
  }

  public MalformedTelNumberExceptionHolder (br.com.agendacorba.agenda.MalformedTelNumberException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = br.com.agendacorba.agenda.MalformedTelNumberExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    br.com.agendacorba.agenda.MalformedTelNumberExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return br.com.agendacorba.agenda.MalformedTelNumberExceptionHelper.type ();
  }

}
