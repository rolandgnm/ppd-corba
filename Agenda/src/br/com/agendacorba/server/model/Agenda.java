package br.com.agendacorba.server.model;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.ContactAlreadyExistsException;
import br.com.agendacorba.agenda.NoContactFoundException;

import java.util.List;

/**
 * Created by Roland on 9/17/16.
 */
public interface Agenda {
    Contact createContact(Contact created, Boolean propagate) throws ContactAlreadyExistsException;

    Contact update(Contact contact, Boolean propagate) throws NoContactFoundException, ContactAlreadyExistsException;

    void deleteByName(String name, Boolean propagate) throws NoContactFoundException;

    Contact getByName(String name) throws NoContactFoundException;

    List<Contact> getAll() throws NoContactFoundException;

}
