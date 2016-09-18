package br.com.agendacorba.server.control;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.ContactAlreadyExistsException;
import br.com.agendacorba.agenda.NoContactFoundException;

import java.util.List;

/**
 * If Agenda.IDL chenage its methods,
 * this interface must follow.
 */
public interface AgendaOperations {

    void create(Contact created, Boolean propagate) throws ContactAlreadyExistsException;

    void update(Contact contact, Boolean propagate) throws NoContactFoundException;

    void deleteByName(String name, Boolean propagate) throws NoContactFoundException;

    Contact getByName(String name) throws NoContactFoundException;

    Contact[] getAll() throws NoContactFoundException;

}
