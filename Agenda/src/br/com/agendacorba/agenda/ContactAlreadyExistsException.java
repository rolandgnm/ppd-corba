package br.com.agendacorba.agenda;


/**
* br/com/agendacorba/agenda/ContactAlreadyExistsException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Tuesday, September 20, 2016 10:59:36 PM BRT
*/

public final class ContactAlreadyExistsException extends org.omg.CORBA.UserException
{

  public ContactAlreadyExistsException ()
  {
    super(ContactAlreadyExistsExceptionHelper.id());
  } // ctor


  public ContactAlreadyExistsException (String $reason)
  {
    super(ContactAlreadyExistsExceptionHelper.id() + "  " + $reason);
  } // ctor

} // class ContactAlreadyExistsException
