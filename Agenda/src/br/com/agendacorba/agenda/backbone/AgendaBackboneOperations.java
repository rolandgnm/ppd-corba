package br.com.agendacorba.agenda.backbone;


/**
* br/com/agendacorba/agenda/backbone/AgendaBackboneOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Agenda.idl
* Monday, September 19, 2016 11:31:05 PM BRT
*/

public interface AgendaBackboneOperations 
{
  void propagateCreate (br.com.agendacorba.agenda.Contact newContact);
  br.com.agendacorba.agenda.Contact[] getAll ();
  void propagateUpdate (br.com.agendacorba.agenda.Contact updatedContact);
  void propagateDelete (br.com.agendacorba.agenda.Contact deletedContact);
  void bindDuplexConnection (String requesterName);
} // interface AgendaBackboneOperations
