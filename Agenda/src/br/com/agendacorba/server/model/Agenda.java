package br.com.agendacorba.server.model;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.ContactAlreadyExistsException;
import br.com.agendacorba.agenda.NoContactFoundException;
import br.com.agendacorba.agenda.access.AgendaAccessOperations;
import br.com.agendacorba.agenda.access.AgendaAccessPOA;

/**
 * Created by Roland on 9/17/16.
 */
public interface Agenda extends AgendaAccessOperations {

}