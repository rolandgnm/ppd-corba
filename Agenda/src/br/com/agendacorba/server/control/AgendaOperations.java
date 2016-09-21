package br.com.agendacorba.server.control;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.ContactAlreadyExistsException;
import br.com.agendacorba.agenda.MalformedTelNumberException;
import br.com.agendacorba.agenda.NoContactFoundException;
import br.com.agendacorba.agenda.access.AgendaAccess;
import br.com.agendacorba.agenda.access.AgendaAccessOperations;

import java.util.List;

/**
 * If Agenda.IDL chenage its methods,
 * this interface must follow.
 */
public interface AgendaOperations {

    void create(Contact created, Boolean propagate) throws ContactAlreadyExistsException, MalformedTelNumberException;

    void update(Contact contact, Boolean propagate) throws NoContactFoundException, MalformedTelNumberException;

    void deleteByName(String name, Boolean propagate) throws NoContactFoundException;

    Contact getByName(String name) throws NoContactFoundException;

    Contact[] getAll() throws NoContactFoundException;

}
