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
    void create(Contact created) throws ContactAlreadyExistsException;

    void update(Contact contact) throws NoContactFoundException;

    void deleteByName(String name) throws NoContactFoundException;

    Contact getByName(String name) throws NoContactFoundException;

    Contact[] getAll() throws NoContactFoundException;

}