package br.com.agendacorba.server.model;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.ContactAlreadyExistsException;
import br.com.agendacorba.agenda.NoContactFoundException;
import br.com.agendacorba.agenda.access.AgendaAccessOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by Roland on 9/15/16.
 */
public class AgendaImpl implements Agenda {
    private List<Contact> contactList;

    public AgendaImpl(Contact[] currentList) {
        contactList = Arrays.asList(currentList);

    }

    public AgendaImpl() {
        contactList = new ArrayList<>();
    }


    @Override
    public void create(Contact created) throws ContactAlreadyExistsException {
        try {
            Contact exists = getByName(created.name);
            if (exists != null)
                throw new ContactAlreadyExistsException();

        } catch (NoContactFoundException e) {
            contactList.add(created);
        }
    }

    @Override
    public void update(Contact contact) throws NoContactFoundException {
        Contact existingContact = getByName(contact.name);
        existingContact.telNumber = contact.telNumber;

    }

    @Override
    public void deleteByName(String name) throws NoContactFoundException {
        Contact existingContact = getByName(name);
        contactList.remove(existingContact);
    }

    @Override
    public Contact getByName(String name) throws NoContactFoundException {
        Contact existing = null;

        if(contactList.isEmpty())
            throw new NoContactFoundException();

        for(Contact i : contactList) {
            if(i.name.equals(name))
                existing = i;
        }
        return existing;
    }

    @Override
    public Contact[] getAll() throws NoContactFoundException {
        if (contactList.size() > 0)
            return (Contact[]) contactList.toArray();
        else
            throw new NoContactFoundException();

    }
}
