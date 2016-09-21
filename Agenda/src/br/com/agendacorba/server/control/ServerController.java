package br.com.agendacorba.server.control;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.ContactAlreadyExistsException;
import br.com.agendacorba.agenda.MalformedTelNumberException;
import br.com.agendacorba.agenda.NoContactFoundException;
import br.com.agendacorba.server.access.AgendaAccessServiceHelper;
import br.com.agendacorba.server.backbone.AgendaBackboneServiceHelper;
import br.com.agendacorba.server.model.Agenda;
import br.com.agendacorba.server.model.AgendaModel;

/**
 * Created by Roland on 9/15/16.
 */
public class ServerController implements AgendaOperations {

    String instanceName;
    AgendaAccessServiceHelper accessHelper;
    AgendaBackboneServiceHelper backboneHelper;
    Agenda agendaModel;

    public ServerController(String[] ORBargs) {
        backboneHelper = new AgendaBackboneServiceHelper(ORBargs);
        backboneHelper.findBackboneServants();

        if (backboneHelper.hasSiblings())
            agendaModel = new AgendaModel(backboneHelper.getCurrentList()); //pedir lista vai proprio controller
        else
            agendaModel = new AgendaModel();

        try {
            instanceName = backboneHelper.getAgendaAccessInstanceName();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            System.exit(0);
        }

        accessHelper = new AgendaAccessServiceHelper(instanceName, ORBargs);

        /**
         * Passa referência da Controller pra ser chamado pelo servant
         */
        ServerController controller = this;
        new Thread() {
            public void run() {
                backboneHelper.buildBackboneInstanceAndBind(controller); //passa controller para POAImpl chamar action
            }
        }.start();

        backboneHelper.notifyServants();

        accessHelper.buildServantAndBind(this);
    }

    //TODO RODAR PROGRAMA SEM BACKBONE E DEPOIS IMPLEMENTAR COM!

    @Override
    public void create(Contact created, Boolean propagate) throws ContactAlreadyExistsException, MalformedTelNumberException {
        try {
            agendaModel.create(created);
        } catch (ContactAlreadyExistsException e) {
            throw new ContactAlreadyExistsException();
        } catch (MalformedTelNumberException e) {
            throw new MalformedTelNumberException();
        }

        if (propagate)
            backboneHelper.propagateCreate(created);
    }

    @Override
    public void update(Contact contact, Boolean propagate) throws NoContactFoundException, MalformedTelNumberException {
        agendaModel.update(contact);
        if (propagate)
            backboneHelper.propagateUpdate(contact);
    }

    @Override
    public void deleteByName(String name, Boolean propagate) throws NoContactFoundException {
        Contact contact = agendaModel.getByName(name);
        agendaModel.deleteByName(name);
        if (propagate)
            backboneHelper.propagateDelete(contact);
    }

    @Override
    public Contact getByName(String name) throws NoContactFoundException {
        return agendaModel.getByName(name);
    }

    @Override
    public Contact[] getAll() throws NoContactFoundException {
        try {
            return agendaModel.getAll();
        } catch (NoContactFoundException e) {
            throw new NoContactFoundException();
        }
    }

    public void bindDuplexConnection(String requesterName) {
        backboneHelper.bindDuplexConnection(requesterName);
    }
}
