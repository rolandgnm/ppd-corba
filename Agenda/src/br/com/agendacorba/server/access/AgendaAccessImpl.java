package br.com.agendacorba.server.access;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.ContactAlreadyExistsException;
import br.com.agendacorba.agenda.MalformedTelNumberException;
import br.com.agendacorba.agenda.NoContactFoundException;
import br.com.agendacorba.agenda.access.AgendaAccessPOA;
import br.com.agendacorba.server.control.ServerController;

/**
 * Created by Roland on 9/15/16.
 */
public class AgendaAccessImpl extends AgendaAccessPOA {
    private ServerController serverCtrl;
    final boolean PROPAGATE = true;


    public AgendaAccessImpl(ServerController serverCtrl) {
        this.serverCtrl = serverCtrl;
    }

    @Override
    public void create(Contact created) throws ContactAlreadyExistsException, MalformedTelNumberException {
        serverCtrl.create(created, PROPAGATE);
    }

    @Override
    public Contact getByName(String name) throws NoContactFoundException {
        return serverCtrl.getByName(name);
    }

    @Override
    public Contact[] getAll() throws NoContactFoundException {
        return serverCtrl.getAll();
    }

    @Override
    public void update(Contact contact) throws NoContactFoundException, MalformedTelNumberException {
        serverCtrl.update(contact, PROPAGATE);

    }

    @Override
    public void deleteByName(String name) throws NoContactFoundException {
        serverCtrl.deleteByName(name, PROPAGATE);
    }
}
