package br.com.agendacorba.server.control;

import br.com.agendacorba.agenda.Contact;
import br.com.agendacorba.agenda.ContactAlreadyExistsException;
import br.com.agendacorba.agenda.NoContactFoundException;
import br.com.agendacorba.server.access.AgendaAccessServiceHelper;
import br.com.agendacorba.server.backbone.AgendaBackboneServiceHelper;
import br.com.agendacorba.server.model.Agenda;
import br.com.agendacorba.server.model.AgendaImpl;

import java.util.List;

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
        backboneHelper.findServants();

        if (backboneHelper.hasServant()) {
            agendaModel = new AgendaImpl(backboneHelper.getCurrentList()); //pedir lista vai proprio controller
        }
        else
            agendaModel = new AgendaImpl();

        instanceName = backboneHelper.getInstanceName();
        accessHelper = new AgendaAccessServiceHelper(instanceName, ORBargs);

        /**
         * Passa referência da Controller pra ser chamado pelos POAImpl
         */
        backboneHelper.buildServantAndBind(this); //passa controller para POAImpl chamar action
        backboneHelper.notifyServants();

        accessHelper.buildServantAndBind(this);
    }

    //TODO RODAR PROGRAMA SEM BACKBONE E DEPOIS IMPLEMENTAR COM!

    @Override
    public void create(Contact created, Boolean propagate) throws ContactAlreadyExistsException {
        agendaModel.create(created);
//        if (propagate);
    }

    @Override
    public void update(Contact contact, Boolean propagate) throws NoContactFoundException {
        agendaModel.update(contact);
//        if (propagate);
    }

    @Override
    public void deleteByName(String name, Boolean propagate) throws NoContactFoundException {
        agendaModel.deleteByName(name);
//        if (propagate);
    }

    @Override
    public Contact getByName(String name) throws NoContactFoundException {
        return agendaModel.getByName(name);
    }

    @Override
    public Contact[] getAll() throws NoContactFoundException {
        return agendaModel.getAll();
    }
}
