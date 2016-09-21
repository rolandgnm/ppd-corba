package br.com.agendacorba.server.backbone;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.ContactAlreadyExistsException;
import br.com.agendacorba.agenda.MalformedTelNumberException;
import br.com.agendacorba.agenda.NoContactFoundException;
import br.com.agendacorba.agenda.backbone.AgendaBackbonePOA;
import br.com.agendacorba.server.control.ServerController;

/**
 * Created by Roland on 9/15/16.
 */
public class AgendaBackboneImpl extends AgendaBackbonePOA {

    private ServerController serverCtrl;
    final boolean PROPAGATE = false;


    public AgendaBackboneImpl(ServerController serverController) {
        this.serverCtrl = serverController;
    }

    @Override
    public void propagateCreate(Contact newContact) {
        try {
            serverCtrl.create(newContact, PROPAGATE);
        } catch (ContactAlreadyExistsException | MalformedTelNumberException ignored) {

        }
    }

    @Override
    public Contact[] getAll() {
        try {
            return serverCtrl.getAll();
        } catch (NoContactFoundException e) {
            System.out.println("! Lista vazia!");
            return new Contact[0];
        }

    }

    @Override
    public void propagateUpdate(Contact updatedContact) {
        try {
            serverCtrl.update(updatedContact, PROPAGATE);
        } catch (NoContactFoundException | MalformedTelNumberException ignored) {
        }
    }

    @Override
    public void propagateDelete(Contact deletedContact) {
        try {
            serverCtrl.deleteByName(deletedContact.name, PROPAGATE);
        } catch (NoContactFoundException ignored) {
        }
    }

    @Override
    public void bindDuplexConnection(String requesterName) {
        serverCtrl.bindDuplexConnection(requesterName);

    }
}
